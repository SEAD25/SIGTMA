package sn.uasz.SIGTMA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.uasz.SIGTMA.model.RapportStatistique;
import sn.uasz.SIGTMA.service.RapportStatistiqueService;

import java.util.List;

@RestController
@RequestMapping("/api/rapports")
@CrossOrigin("*")
public class RapportStatistiqueController {

    @Autowired
    private RapportStatistiqueService rapportService;

    @PostMapping
    public RapportStatistique genererRapport(@RequestBody RapportStatistique rapport) {
        return rapportService.genererRapport(rapport);
    }

    @GetMapping
    public List<RapportStatistique> listerRapports() {
        return rapportService.listerRapports();
    }
}