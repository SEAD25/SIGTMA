package sn.uasz.SIGTMA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;
    private String nom;
    private String sexe;
    private String adresse;
    private String email;
    private String telephone;
    private String motDePasse;
    private String role;

    @OneToMany(mappedBy = "utilisateur")
    private List<RapportStatistique> rapports;

    @OneToMany(mappedBy = "utilisateur")
    private List<TheseMemoire> theses;
}
