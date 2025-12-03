package sn.uasz.SIGTMA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.SIGTMA.model.RapportStatistique;
import sn.uasz.SIGTMA.repository.RapportStatistiqueRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RapportStatistiqueService {

    @Autowired
    private RapportStatistiqueRepository rapportRepository;

    public RapportStatistique genererRapport(RapportStatistique rapport) {
        rapport.setDateGeneration(LocalDate.now());
        // Ici, on pourrait ajouter une logique pour calculer des stats réelles
        // et les mettre dans rapport.setResultat(...)
        return rapportRepository.save(rapport);
    }

    public List<RapportStatistique> listerRapports() {
        return rapportRepository.findAll();
    }
}