package sn.uasz.SIGTMA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.SIGTMA.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    // On pourra ajouter ici : findByMatricule(String matricule);
}