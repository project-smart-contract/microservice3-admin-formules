package ma.fstt.microservice3adminformules.service;


import lombok.RequiredArgsConstructor;
import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Option;
import ma.fstt.microservice3adminformules.entity.Produit;
import ma.fstt.microservice3adminformules.repository.FormuleRepository;
import ma.fstt.microservice3adminformules.repository.OptionRepository;
import ma.fstt.microservice3adminformules.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormuleServiceImp implements FormuleService {

    @Autowired
    private FormuleRepository formuleRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    private ObjectMapper objectMapper; // pour convertir l'objet en JSON

    @Override
    public Formule addFormule(Map<String, Object> payload) {
        String titre = (String) payload.get("titre");
        String description = (String) payload.get("description");
        Boolean isNew = (Boolean) payload.get("isNew");

        List<String> images = (List<String>) payload.get("images"); // Liste d'images

        Long produitId = ((Number) payload.get("produit_id")).longValue();


        List<Integer> optionIds = (List<Integer>) payload.get("options");
        List<Long> optionIdsLong = optionIds.stream().map(Long::valueOf).collect(Collectors.toList());
        List<Option> options = optionRepository.findAllById(optionIdsLong);

        List<Integer> avantageIds = (List<Integer>) payload.get("avantages");
        List<Long> avantageIdsLong = avantageIds.stream().map(Long::valueOf).collect(Collectors.toList());
        List<Option> avantages = optionRepository.findAllById(avantageIdsLong);

        Formule formule = new Formule();
        formule.setTitre(titre);
        formule.setDescription(description);
        formule.setNew(isNew);
        formule.setOptions(options);
        formule.setAvantages(avantages);

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé avec l'ID : " + produitId));

        formule.setProduit(produit);
        formule.setImagesList(images);

        String formuleJson;
        try {
            formuleJson = objectMapper.writeValueAsString(formule);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors de la conversion de la formule en JSON", e);
        }

        // Envoyer le JSON via Kafka
        kafkaTemplate.send("formule-info", formuleJson);
        return formuleRepository.save(formule);

    }

    @Override
    public List<Formule> getFormulesByProduitId(Long produitId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé avec l'ID : " + produitId));

        return formuleRepository.findByProduit(produit);
    }


    @Override
    public List<Formule> getAllFormules() {
        List<Formule> formules = formuleRepository.findAll();


        return formules;
    }
    @Override
    public Formule getFormuleById(Long formuleId) {
        Formule formule = formuleRepository.findById(formuleId).orElse(null);

        // Convertir l'objet Formule en JSON
        String formuleJson;
        try {
            formuleJson = objectMapper.writeValueAsString(formule);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors de la conversion de la formule en JSON", e);
        }

        // Envoyer le JSON via Kafka
        kafkaTemplate.send("formule-info", formuleJson);

        return formule;
    }

    @Override
    public Formule updateFormule(Long formuleId, Map<String, Object> payload) {
        Formule existingFormule = formuleRepository.findById(formuleId)
                .orElseThrow(() -> new IllegalArgumentException("Formule non trouvée avec l'ID : " + formuleId));

        if (payload.containsKey("titre")) {
            existingFormule.setTitre((String) payload.get("titre"));
        }

        if (payload.containsKey("description")) {
            existingFormule.setDescription((String) payload.get("description"));
        }

        if (payload.containsKey("images")) {
            List<String> newImages = (List<String>) payload.get("images");
            existingFormule.setImagesList(newImages);
        }

        if (payload.containsKey("isNew")) {
            existingFormule.setNew((Boolean) payload.get("isNew"));
        }

        if (payload.containsKey("options")) {
            List<Integer> optionIds = (List<Integer>) payload.get("options");
            List<Long> optionIdsLong = optionIds.stream().map(Long::valueOf).collect(Collectors.toList());
            List<Option> options = optionRepository.findAllById(optionIdsLong);
            existingFormule.setOptions(options);
        }

        if (payload.containsKey("avantages")) {
            List<Integer> avantageIds = (List<Integer>) payload.get("avantages");
            List<Long> avantageIdsLong = avantageIds.stream().map(Long::valueOf).collect(Collectors.toList());
            List<Option> avantages = optionRepository.findAllById(avantageIdsLong);
            existingFormule.setAvantages(avantages);
        }

        if (payload.containsKey("produit_id")) {
            Long produitId = ((Number) payload.get("produit_id")).longValue();
            Produit produit = produitRepository.findById(produitId)
                    .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé avec l'ID : " + produitId));
            existingFormule.setProduit(produit);
        }


        return formuleRepository.save(existingFormule);
    }

    @Override
    public void deleteFormuleById(Long formuleId) {
        formuleRepository.deleteById(formuleId);
    }
}
