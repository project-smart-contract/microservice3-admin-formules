package ma.fstt.microservice3adminformules.service;


import lombok.RequiredArgsConstructor;
import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Option;
import ma.fstt.microservice3adminformules.repository.FormuleRepository;
import ma.fstt.microservice3adminformules.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormuleServiceImp implements FormuleService{

    @Autowired
    private FormuleRepository formuleRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Formule addFormule(Map<String, Object> payload){
        String titre1 = (String) payload.get("titre1");
        String description1 = (String) payload.get("description1");
        String titre2 = (String) payload.get("titre2");
        String description2 = (String) payload.get("description2");

        List<Integer> optionIds = (List<Integer>) payload.get("options");
        List<Long> optionIdsLong = optionIds.stream().map(Long::valueOf).collect(Collectors.toList());
        List<Option> options = optionRepository.findAllById(optionIdsLong);

        List<Integer> avantageIds = (List<Integer>) payload.get("avantages");
        List<Long> avantageIdsLong = avantageIds.stream().map(Long::valueOf).collect(Collectors.toList());
        List<Option> avantages = optionRepository.findAllById(avantageIdsLong);



        Formule formule = new Formule();
        formule.setTitre1(titre1);
        formule.setDescription1(description1);
        formule.setTitre2(titre2);
        formule.setDescription2(description2);
        formule.setOptions(options);
        formule.setAvantages(avantages);

        return formuleRepository.save(formule);

    }

    @Override
    public List<Formule> getAllFormules() {
        List<Formule> formules = formuleRepository.findAll();

        return formules;
    }

    @Override
    public Formule getFormuleById(Long formuleId) {
        return formuleRepository.findById(formuleId).orElse(null);
    }

    @Override
    public void deleteFormuleById(Long formuleId) {
        // Supprimer la Formule par son ID
        formuleRepository.deleteById(formuleId);
    }
}
