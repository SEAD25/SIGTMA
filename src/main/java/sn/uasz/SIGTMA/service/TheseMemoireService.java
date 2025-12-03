package sn.uasz.SIGTMA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.SIGTMA.model.TheseMemoire;
import sn.uasz.SIGTMA.repository.TheseMemoireRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TheseMemoireService {

    @Autowired
    private TheseMemoireRepository theseMemoireRepository;

    public TheseMemoire ajouterThese(TheseMemoire theseMemoire) {
        // Ajout automatique de la date de dépôt si elle n'est pas fournie
        if (theseMemoire.getDateDeDepot() == null) {
            theseMemoire.setDateDeDepot(LocalDate.now());
        }
        return theseMemoireRepository.save(theseMemoire);
    }

    public List<TheseMemoire> listerTheses() {
        return theseMemoireRepository.findAll();
    }

    public TheseMemoire trouverThese(Long id) {
        return theseMemoireRepository.findById(id).orElse(null);
    }

    public List<TheseMemoire> rechercher(String motCle) {
        if (motCle != null && !motCle.isEmpty()) {
            // Nécessite la méthode dans le Repository !
            return theseMemoireRepository.findByTitreContainingIgnoreCase(motCle);
        }
        return theseMemoireRepository.findAll();
    }
}