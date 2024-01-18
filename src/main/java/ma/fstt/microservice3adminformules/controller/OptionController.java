package ma.fstt.microservice3adminformules.controller;


import ma.fstt.microservice3adminformules.entity.Option;
import ma.fstt.microservice3adminformules.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET} , path = "/service3")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @PostMapping("/option/ajouter")
    public ResponseEntity<Option> addOption(@RequestBody Map<String, Object> payload) {
        Option savedOption = optionService.addOption(payload);
        return ResponseEntity.created(URI.create("/options/" + savedOption.getId_option())).body(savedOption);
    }

    @GetMapping("/option")
    public ResponseEntity<List<Option>> listOptions() {
        List<Option> optionList = optionService.getAllOptions();
        return ResponseEntity.ok(optionList);
    }
}
