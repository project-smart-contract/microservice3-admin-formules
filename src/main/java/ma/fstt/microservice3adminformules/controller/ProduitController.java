package ma.fstt.microservice3adminformules.controller;


import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Produit;
import ma.fstt.microservice3adminformules.repository.ProduitRepository;
import ma.fstt.microservice3adminformules.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET} , path = "/formule")
public class ProduitController {

    @Autowired
    private ProduitService produitService;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private FormuleService formuleService;


    @PostMapping("/produit/ajouter")
    public ResponseEntity<Produit> addProduit(@RequestBody Map<String, Object> payload) {
        Produit savedProduit = produitService.addProduit(payload);
        return ResponseEntity.created(URI.create("/produits/" + savedProduit.getId_produit())).body(savedProduit);
    }

    @GetMapping("/produit")
    public List<Produit> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        produits.forEach(produit -> produit.setFormules(formuleService.getFormulesByProduitId(produit.getId_produit())));
        return produits;
    }
}
