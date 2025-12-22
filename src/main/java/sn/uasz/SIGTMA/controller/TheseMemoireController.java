package sn.uasz.SIGTMA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.uasz.SIGTMA.model.TheseMemoire;
import sn.uasz.SIGTMA.dto.DossierDTO;
import sn.uasz.SIGTMA.service.TheseMemoireService;

import java.util.List;

@RestController
@RequestMapping("/api/theses")
@CrossOrigin("*")
public class TheseMemoireController {

    @Autowired
    private TheseMemoireService theseMemoireService;

    @PostMapping("/depot")
    public TheseMemoire deposerDossier(@RequestBody DossierDTO dossierDTO) {
        return theseMemoireService.deposerDossier(dossierDTO);
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public TheseMemoire ajouterThese(
            @RequestParam("titre") String titre,
            @RequestParam("type") String type,
            @RequestParam("annee") int annee,
            @RequestParam("resume") String resume,
            @RequestParam("utilisateurId") Long utilisateurId,
            @RequestParam(value = "fichier", required = false) org.springframework.web.multipart.MultipartFile fichier) {

        TheseMemoire these = new TheseMemoire();
        these.setTitre(titre);
        these.setType(type);
        these.setAnnee(annee);
        these.setResume(resume); // Correction de setResumer en setResume si nécessaire

        // Logique de sauvegarde de fichier simplifiée pour l'exemple
        if (fichier != null && !fichier.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + fichier.getOriginalFilename();
            // Dans un cas réel, sauvegarder sur le disque ou S3
            these.setFichier(fileName);
        }

        // On peut récupérer l'utilisateur via son ID (ajouter la logique service si
        // besoin)
        // these.setUtilisateur(...);

        return theseMemoireService.ajouterThese(these);
    }

    @GetMapping
    public List<TheseMemoire> listerTheses() {
        return theseMemoireService.listerTheses();
    }

    @PostMapping("/{id}/valider")
    public TheseMemoire validerThese(@PathVariable Long id) {
        return theseMemoireService.validerThese(id);
    }

    @PostMapping("/{id}/rejeter")
    public TheseMemoire rejeterThese(@PathVariable Long id) {
        return theseMemoireService.rejeterThese(id);
    }

    @GetMapping("/recherche")
    public List<TheseMemoire> rechercher(@RequestParam String motCle) {
        return theseMemoireService.rechercher(motCle);
    }

    @GetMapping("/{id}")
    public TheseMemoire getThese(@PathVariable Long id) {
        return theseMemoireService.trouverThese(id);
    }
}