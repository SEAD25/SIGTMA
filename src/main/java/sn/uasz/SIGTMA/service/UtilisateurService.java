package sn.uasz.SIGTMA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Import
import org.springframework.stereotype.Service;
import sn.uasz.SIGTMA.model.Utilisateur;
import sn.uasz.SIGTMA.repository.UtilisateurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injection de l'encodeur

    public Utilisateur creerUtilisateur(Utilisateur utilisateur) {
        // On crypte le mot de passe avant de sauvegarder
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }


    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Méthode simple de connexion (Temporaire avant Spring Security complet)
    public Utilisateur verifierConnexion(String email, String motDePasse) {
        Optional<Utilisateur> user = utilisateurRepository.findByEmail(email);
        if (user.isPresent()) {
            if (user.get().getMotDePasse().equals(motDePasse)) {
                return user.get();
            }
        }
        return null; // Connexion échouée
    }

    public Utilisateur trouverParEmail(String email) {
        return utilisateurRepository.findByEmail(email).orElse(null);
    }
}