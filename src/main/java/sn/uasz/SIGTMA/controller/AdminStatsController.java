package sn.uasz.SIGTMA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.uasz.SIGTMA.repository.TheseMemoireRepository;
import sn.uasz.SIGTMA.repository.UtilisateurRepository;
import sn.uasz.SIGTMA.enums.StatutThese;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/stats")
public class AdminStatsController {

    @Autowired
    private TheseMemoireRepository theseRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/summary")
    public Map<String, Object> getSummary(@RequestParam(required = false) String period) {
        Map<String, Object> stats = new HashMap<>();
        LocalDate now = LocalDate.now();
        LocalDate start;
        LocalDate end = now;

        if ("journalier".equals(period)) {
            start = now;
        } else if ("mensuel".equals(period)) {
            start = now.with(TemporalAdjusters.firstDayOfMonth());
        } else if ("annuel".equals(period)) {
            start = now.with(TemporalAdjusters.firstDayOfYear());
        } else {
            // Global
            start = LocalDate.of(2000, 1, 1);
        }

        stats.put("totalTheses", theseRepository.countByDateDeDepotBetween(start, end));
        stats.put("totalUsers", utilisateurRepository.count());
        stats.put("thesesValidees", theseRepository.countByStatus(StatutThese.VALIDEE));
        stats.put("thesesRejetees", theseRepository.countByStatus(StatutThese.REJETEE));
        stats.put("thesesEnAttente", theseRepository.countByStatus(StatutThese.EN_ATTENTE));

        return stats;
    }
}
