package sn.uasz.SIGTMA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sn.uasz.SIGTMA.model.Utilisateur;
import sn.uasz.SIGTMA.service.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin("*")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // 1. Inscription (C'est la SEULE route ouverte à tous dans SecurityConfig)
    @PostMapping("/inscription")
    public Utilisateur inscription(@RequestBody Utilisateur utilisateur) {
        // Le service va se charger d'encoder le mot de passe
        return utilisateurService.creerUtilisateur(utilisateur);
    }

    // 2. Lister les utilisateurs (Nécessite d'être authentifié)
    @GetMapping
    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurService.listerUtilisateurs();
    }

    // 3. (Optionnel mais très utile) : Savoir qui est connecté
    // Permet au Frontend de récupérer les infos de l'utilisateur courant après connexion
    @GetMapping("/profil")
    public Utilisateur monProfil() {
        // On récupère l'email de l'utilisateur connecté via le contexte de sécurité
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // Pour l'instant, je laisse simple : on renvoie null ou il faudra ajouter findByEmail au service.
        return null;
    }
}