package ma.fstt.microservice3adminformules.service;

import ma.fstt.microservice3adminformules.entity.Formule;

import java.util.List;
import java.util.Map;

public interface FormuleService {

    public Formule addFormule(Map<String, Object> payload);
    public List<Formule> getFormulesByProduitId(Long produitId);
    public List<Formule> getAllFormules();
    public Formule getFormuleById(Long formuleId);
    public Formule updateFormule(Long formuleId, Map<String, Object> payload);
    public void deleteFormuleById(Long formuleId);
}
