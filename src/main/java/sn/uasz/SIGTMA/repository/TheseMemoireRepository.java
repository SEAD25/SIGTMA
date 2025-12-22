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
}