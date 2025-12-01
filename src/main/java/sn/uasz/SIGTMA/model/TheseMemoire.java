package sn.uasz.SIGTMA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheseMemoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String type;
    private int annee;
    private String resume;
    private String fichier;
    private LocalDate dateDeDepot;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Encadrant encadrant;

    @OneToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;
}
