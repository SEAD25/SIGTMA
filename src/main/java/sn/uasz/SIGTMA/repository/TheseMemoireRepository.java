package sn.uasz.SIGTMA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.uasz.SIGTMA.model.TheseMemoire;
import sn.uasz.SIGTMA.enums.StatutThese;
import java.util.List;
import java.time.LocalDate;

public interface TheseMemoireRepository extends JpaRepository<TheseMemoire, Long> {

        List<TheseMemoire> findByTitreContainingIgnoreCase(String motCle);

        List<TheseMemoire> findByType(String type);

        long countByDateDeDepot(LocalDate date);

        @Query("SELECT COUNT(t) FROM TheseMemoire t WHERE t.dateDeDepot BETWEEN :start AND :end")
        long countByDateDeDepotBetween(LocalDate start, LocalDate end);

        long countByStatus(StatutThese status);

        List<TheseMemoire> findTop5ByOrderByDateDeDepotDesc();

        List<TheseMemoire> findTop10ByOrderByDateDeDepotDesc();

        @Query("SELECT t FROM TheseMemoire t WHERE " +
                        "(LOWER(t.titre) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
                        "LOWER(t.etudiant.nom) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
                        "LOWER(t.etudiant.prenom) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
                        "LOWER(t.encadrant.nom) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
                        "LOWER(t.encadrant.prenom) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
                        "LOWER(t.etudiant.filiere.nom) LIKE LOWER(CONCAT('%', :motCle, '%'))) " +
                        "AND (:date IS NULL OR t.dateDeDepot = :date)")
        List<TheseMemoire> rechercherAvecDate(String motCle, LocalDate date);

        @Query("SELECT t.etudiant.filiere.nom, COUNT(t) FROM TheseMemoire t GROUP BY t.etudiant.filiere.nom")
        List<Object[]> countThesesByFiliere();

        @Query("SELECT t.annee, COUNT(t) FROM TheseMemoire t GROUP BY t.annee ORDER BY t.annee DESC")
        List<Object[]> countThesesByAnnee();
}