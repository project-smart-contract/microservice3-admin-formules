package ma.fstt.microservice3adminformules.service;


import lombok.RequiredArgsConstructor;
import ma.fstt.microservice3adminformules.entity.Formule;
import ma.fstt.microservice3adminformules.entity.Option;
import ma.fstt.microservice3adminformules.entity.Produit;
import ma.fstt.microservice3adminformules.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitServiceImp implements ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public Produit addProduit(Map<String, Object> payload){
        String titre = (String) payload.get("titre");
        String description = (String) payload.get("description");
        String typeProduit = (String) payload.get("typeProduit");

        Produit produit = new Produit();
        produit.setTitre(titre);
        produit.setDescription(description);
        produit.setTypeProduit(typeProduit);

        return produitRepository.save(produit);
    }



    @Override
    public List<Produit> getAllProduits() {
        List<Produit> produits = produitRepository.findAll();

        return produits;
    }
}
