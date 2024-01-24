package ma.fstt.microservice3adminformules.controller;


import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Produit;
import ma.fstt.microservice3adminformules.repository.ProduitRepository;
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
    @Autowired
    private ProduitRepository produitRepository ;

    @PostMapping("/ajouter")
    public ResponseEntity<Formule> addFormule(@RequestBody Map<String, Object> payload) {
        Formule savedFormule = formuleService.addFormule(payload);
        return ResponseEntity.created(URI.create("/formules/" + savedFormule.getId_formule())).body(savedFormule);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello amina";
    }

    @GetMapping("")
    public ResponseEntity<List<Formule>> listFormules() {
        List<Formule> formuleList = formuleService.getAllFormules();
        return ResponseEntity.ok(formuleList);
    }

    @GetMapping("/typeProduit/{produitId}")
    public ResponseEntity<List<Formule>> getFormulesByProduitId(@PathVariable Long produitId) {
        List<Formule> formuleListByProduit = formuleService.getFormulesByProduitId(produitId);
        return ResponseEntity.ok(formuleListByProduit);
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

    @GetMapping("/{idProduit}/{idFormule}")
    public Formule getFormule(@PathVariable Long idProduit, @PathVariable Long idFormule) {
        // Vérifiez d'abord si le produit avec l'ID donné existe
        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé avec l'ID : " + idProduit));

        // Ensuite, obtenez la formule avec l'ID donné
        Formule formule = formuleService.getFormuleById(idFormule);

        // Vérifiez si la formule appartient au produit
//        if (!formule.getProduit().equals(produit)) {
//            throw new IllegalArgumentException("La formule avec l'ID : " + idFormule + " n'appartient pas au produit avec l'ID : " + idProduit);
//        }

        return formule;
    }

    @PutMapping("/update/{formuleId}")
    public ResponseEntity<Formule> updateFormule(@PathVariable Long formuleId, @RequestBody Map<String, Object> payload) {
        Formule updatedFormule = formuleService.updateFormule(formuleId, payload);
        if (updatedFormule != null) {
            return ResponseEntity.ok(updatedFormule);
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
