package sn.uasz.SIGTMA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.SIGTMA.model.TheseMemoire;
import java.util.List;

public interface TheseMemoireRepository extends JpaRepository<TheseMemoire, Long> {

    // Génère le SQL : SELECT * FROM these_memoire WHERE UPPER(titre) LIKE UPPER('%motCle%')
    List<TheseMemoire> findByTitreContainingIgnoreCase(String motCle);

    // Si vous voulez aussi chercher par type (THESE ou MEMOIRE)
    List<TheseMemoire> findByType(String type);
}