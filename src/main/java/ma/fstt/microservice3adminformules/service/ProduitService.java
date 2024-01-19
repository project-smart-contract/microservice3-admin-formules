package ma.fstt.microservice3adminformules.service;

import ma.fstt.microservice3adminformules.entity.Produit;

import java.util.Map;

public interface ProduitService {

    public Produit addProduit(Map<String, Object> payload);
}
