package sn.uasz.SIGTMA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements UserDetails { // 1. Implémenter UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;
    private String nom;
    private String sexe;
    private String adresse;

    @Column(unique = true, nullable = false)
    private String email; // Ce sera notre "username"

    private String telephone;

    private String motDePasse; // Spring Security utilisera ce champ

    private String role; // "ADMIN", "BIBLIOTHECAIRE", "ETUDIANT"

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<RapportStatistique> rapports;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<TheseMemoire> theses;

    // --- Méthodes imposées par UserDetails ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convertit votre rôle String en autorité Spring Security
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email; // On utilise l'email pour se connecter
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}