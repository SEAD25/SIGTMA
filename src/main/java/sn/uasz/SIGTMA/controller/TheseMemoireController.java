package sn.uasz.SIGTMA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.uasz.SIGTMA.model.TheseMemoire;
import sn.uasz.SIGTMA.service.TheseMemoireService;

import java.util.List;

@RestController
@RequestMapping("/api/theses")
@CrossOrigin("*")
public class TheseMemoireController {

    @Autowired
    private TheseMemoireService theseMemoireService;

    @PostMapping
    public TheseMemoire ajouterThese(@RequestBody TheseMemoire theseMemoire) {
        return theseMemoireService.ajouterThese(theseMemoire);
    }

    @GetMapping
    public List<TheseMemoire> listerTheses() {
        return theseMemoireService.listerTheses();
    }

    @GetMapping("/recherche")
    public List<TheseMemoire> rechercher(@RequestParam String motCle) {
        // Suppose que vous avez créé cette méthode dans le Service
        return theseMemoireService.rechercher(motCle);
    }

    @GetMapping("/{id}")
    public TheseMemoire getThese(@PathVariable Long id) {
        return theseMemoireService.trouverThese(id);
    }
}