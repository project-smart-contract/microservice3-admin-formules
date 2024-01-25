package ma.fstt.microservice3adminformules.controller;


import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Option;
import ma.fstt.microservice3adminformules.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET} , path = "/formule")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @PostMapping("/option/ajouter")
    public ResponseEntity<Option> addOption(@RequestBody Map<String, Object> payload) {
        Option savedOption = optionService.addOption(payload);
        return ResponseEntity.created(URI.create("/options/" + savedOption.getId())).body(savedOption);
//        return ResponseEntity.created(URI.create("/options/" + savedOption.getId_option())).body(savedOption);
    }

    @GetMapping("/option")
    public ResponseEntity<List<Option>> listOptions() {
        List<Option> optionList = optionService.getAllOptions();
        return ResponseEntity.ok(optionList);
    }

    @GetMapping("/option/{optionId}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long optionId) {
        Option option = optionService.getOptionById(optionId);
        if (option != null) {
            return ResponseEntity.ok(option);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/option/update/{optionId}")
    public ResponseEntity<Option> updateOption(@PathVariable Long optionId, @RequestBody Map<String, Object> payload) {
        Option updatedOption = optionService.updateOption(optionId, payload);
        if (updatedOption != null) {
            return ResponseEntity.ok(updatedOption);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/option/delete/{optionId}")
    public ResponseEntity<Void> deleteOptionById(@PathVariable Long optionId) {
        optionService.deleteOptionById(optionId);
        return ResponseEntity.noContent().build();
    }
}
