package ma.fstt.microservice3adminformules.controller;


import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Produit;
import ma.fstt.microservice3adminformules.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET} , path = "/formule")
public class ProduitController {

    @Autowired
    private ProduitService produitService;


    @PostMapping("/produit/ajouter")
    public ResponseEntity<Produit> addProduit(@RequestBody Map<String, Object> payload) {
        Produit savedProduit = produitService.addProduit(payload);
        return ResponseEntity.created(URI.create("/produits/" + savedProduit.getId_produit())).body(savedProduit);
    }
}
