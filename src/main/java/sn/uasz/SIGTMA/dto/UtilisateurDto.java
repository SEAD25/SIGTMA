package sn.uasz.SIGTMA.dto;

import lombok.Data;
import sn.uasz.SIGTMA.enums.UserRole;

@Data
public class UtilisateurDto {
    private Long id;
    private String prenom;
    private String nom;
    private String sexe;
    private String adresse;
    private String email;
    private String telephone;
    private String motDePasse; // Pour l'inscription/modification
    private UserRole role;
}
