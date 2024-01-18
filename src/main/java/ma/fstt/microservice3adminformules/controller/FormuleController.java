package ma.fstt.microservice3adminformules.controller;


import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.service.FormuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET} , path = "/formule")
public class FormuleController {

    @Autowired
    private FormuleService formuleService;

    @PostMapping("/ajouter")
    public ResponseEntity<Formule> addFormule(@RequestBody Map<String, Object> payload) {
        Formule savedFormule = formuleService.addFormule(payload);
        return ResponseEntity.created(URI.create("/formules/" + savedFormule.getId_formule())).body(savedFormule);
    }

    @GetMapping("")
    public ResponseEntity<List<Formule>> listFormules() {
        List<Formule> formuleList = formuleService.getAllFormules();
        return ResponseEntity.ok(formuleList);
    }

    @GetMapping("/{formuleId}")
    public ResponseEntity<Formule> getFormuleById(@PathVariable Long formuleId) {
        Formule formule = formuleService.getFormuleById(formuleId);
        if (formule != null) {
            return ResponseEntity.ok(formule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{formuleId}")
    public ResponseEntity<Void> deleteFormuleById(@PathVariable Long formuleId) {
        formuleService.deleteFormuleById(formuleId);
        return ResponseEntity.noContent().build();
    }

}
