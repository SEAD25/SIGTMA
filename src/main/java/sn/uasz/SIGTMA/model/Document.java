package sn.uasz.SIGTMA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.uasz.SIGTMA.enums.DocumentEnum;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Informations requises par le cahier des charges
    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String auteur;

    private String encadrant;

    private String filiere;

    @Column(name = "annee_academique")
    private String annee;

    // Pour distinguer "Thèse" ou "Mémoire"
    @Enumerated(EnumType.STRING)
    private DocumentEnum typeDocument;

    // Pour l'archivage numérique des fichiers
    private String cheminFichier;

    private LocalDate dateDepot;
}