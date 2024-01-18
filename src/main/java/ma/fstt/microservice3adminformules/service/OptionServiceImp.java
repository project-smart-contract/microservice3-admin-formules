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

        Option option = new Option();
        option.setTitre(titre);
        option.setDescription(description);
        option.setObligatory(isObligatory);

        return optionRepository.save(option);
    }

    @Override
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }
}
