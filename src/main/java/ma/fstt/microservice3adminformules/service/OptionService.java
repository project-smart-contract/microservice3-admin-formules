package ma.fstt.microservice3adminformules.service;

import ma.fstt.microservice3adminformules.entity.Option;

import java.util.List;
import java.util.Map;

public interface OptionService {

    public Option addOption(Map<String, Object> payload);

    public List<Option> getAllOptions();
    public Option updateOption(Long optionId, Map<String, Object> payload);
    public void deleteOptionById(Long optionId);
    public Option getOptionById(Long optionId);

}
