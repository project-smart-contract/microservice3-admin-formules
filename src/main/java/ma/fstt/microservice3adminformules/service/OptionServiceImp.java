package ma.fstt.microservice3adminformules.service;


import lombok.RequiredArgsConstructor;
import ma.fstt.microservice3adminformules.entity.Option;
import ma.fstt.microservice3adminformules.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OptionServiceImp implements OptionService{

    @Autowired
    private OptionRepository optionRepository;

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

        return optionRepository.save(option);
    }

    @Override
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
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
    public void deleteOptionById(Long optionId) {
        optionRepository.deleteById(optionId);
    }
}
