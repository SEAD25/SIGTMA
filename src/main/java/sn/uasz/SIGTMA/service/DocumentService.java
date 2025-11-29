package sn.uasz.SIGTMA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.SIGTMA.model.Document;
import sn.uasz.SIGTMA.repository.DocumentRepository;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    // 1. Enregistrer un document (Thèse ou Mémoire)
    public Document ajouterDocument(Document document) {
        // Ici, on pourrait ajouter des vérifications (ex: l'année est-elle valide ?)
        return documentRepository.save(document);
    }

    // 2. Lister tous les documents
    public List<Document> listerTousLesDocuments() {
        return documentRepository.findAll();
    }

    // 3. Trouver par ID
    public Document trouverDocument(Long id) {
        return documentRepository.findById(id).orElse(null);
    }
}