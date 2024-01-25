package ma.fstt.microservice3adminformules.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Option;
import ma.fstt.microservice3adminformules.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OptionServiceImp implements OptionService{

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    private ObjectMapper objectMapper; // pour convertir l'objet en JSON
    @Override
    public Option addOption(Map<String, Object> payload) {
        String titre = (String) payload.get("titre");
        String description = (String) payload.get("description");
        Boolean isObligatory = (Boolean) payload.get("isObligatory");
        Double montantGarantie = (Double) payload.get("montantGarantie");
        Double franchise = (Double) payload.get("franchise");
        Double prixOption = (Double) payload.get("prixOption");

        Option option = new Option();
        option.setTitre(titre);
        option.setDescription(description);
        option.setObligatory(isObligatory);
        option.setMontantGarantie(montantGarantie);
        option.setFranchise(franchise);
        option.setPrixOption(prixOption);

        String formuleJson;
        try {
            formuleJson = objectMapper.writeValueAsString(option);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors de la conversion de la formule en JSON", e);
        }

        // Envoyer le JSON via Kafka
        kafkaTemplate.send("option-info", formuleJson);

        return optionRepository.save(option);
    }

    @Override
    public List<Option> getAllOptions() {

        List<Option> options = optionRepository.findAll();

        for (Option option : options) {
            String optionJson;
            try {
                optionJson = objectMapper.writeValueAsString(option);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Erreur lors de la conversion de l'option en JSON", e);
            }

            // Envoyer le JSON via Kafka
            kafkaTemplate.send("option-info", optionJson);
        }


        return options;
    }

    @Override
    public Option updateOption(Long optionId, Map<String, Object> payload) {
        Option existingOption = optionRepository.findById(optionId)
                .orElseThrow(() -> new IllegalArgumentException("Option non trouvée avec l'ID : " + optionId));

        if (payload.containsKey("titre")) {
            existingOption.setTitre((String) payload.get("titre"));
        }

        if (payload.containsKey("description")) {
            existingOption.setDescription((String) payload.get("description"));
        }

        if (payload.containsKey("isObligatory")) {
            existingOption.setObligatory((Boolean) payload.get("isObligatory"));
        }

        if (payload.containsKey("montantGarantie")) {
            existingOption.setMontantGarantie((Double) payload.get("montantGarantie"));
        }

        if (payload.containsKey("franchise")) {
            existingOption.setFranchise((Double) payload.get("franchise"));
        }

        if (payload.containsKey("prixOption")) {
            existingOption.setPrixOption((Double) payload.get("prixOption"));
        }

        return optionRepository.save(existingOption);
    }

    @Override
    public Option getOptionById(Long optionId) {
//        Option option = optionRepository.findById(optionId).orElse(null);

//         Convertir l'objet Formule en JSON
        return optionRepository.findById(optionId).orElse(null);

    }


    @Override
    public void deleteOptionById(Long optionId) {
        optionRepository.deleteById(optionId);
    }
}
