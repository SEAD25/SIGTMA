package sn.uasz.SIGTMA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.uasz.SIGTMA.model.Document;
import sn.uasz.SIGTMA.service.DocumentService;

import java.util.List;

@RestController // Indique que c'est une API REST qui renvoie du JSON
@RequestMapping("/api/documents") // L'adresse de base : http://localhost:8080/api/documents
@CrossOrigin("*") // Autorise tout le monde (utile pour le frontend plus tard)
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // 1. Ajouter un document (POST)
    @PostMapping
    public Document ajouter(@RequestBody Document document) {
        return documentService.ajouterDocument(document);
    }

    // 2. Lister tous les documents (GET)
    @GetMapping
    public List<Document> lister() {
        return documentService.listerTousLesDocuments();
    }
}