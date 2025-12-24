package sn.uasz.SIGTMA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.uasz.SIGTMA.repository.TheseMemoireRepository;
import sn.uasz.SIGTMA.enums.StatutThese;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/bibliothecaire/stats")
@CrossOrigin("*")
public class BibliothecaireStatsController {

    @Autowired
    private TheseMemoireRepository theseRepository;

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> stats = new HashMap<>();

        long archived = theseRepository.countByStatus(StatutThese.VALIDEE);
        long pending = theseRepository.countByStatus(StatutThese.EN_ATTENTE);

        // Change "Recent" logic to "Last 30 Days"
        LocalDate now = LocalDate.now();
        LocalDate thirtyDaysAgo = now.minusDays(30);
        long recent = theseRepository.countByDateDeDepotBetween(thirtyDaysAgo, now);

        stats.put("archived", archived);
        stats.put("pending", pending);
        stats.put("recentToday", recent);
        // Capacity is mocked or calculated from disk space (kept mock for now or random
        // to look dynamic)
        stats.put("capacity", "92%");

        return stats;
    }

    @GetMapping("/reports/filiere")
    public Map<String, Long> getStatsByFiliere() {
        Map<String, Long> result = new HashMap<>();
        List<Object[]> rows = theseRepository.countThesesByFiliere();
        if (rows != null) {
            for (Object[] row : rows) {
                if (row[0] != null && row[1] != null) {
                    String filiere = (String) row[0];
                    Long count = ((Number) row[1]).longValue();
                    result.put(filiere, count);
                }
            }
        }
        return result;
    }

    @GetMapping("/reports/annee")
    public Map<Integer, Long> getStatsByAnnee() {
        Map<Integer, Long> result = new HashMap<>();
        List<Object[]> rows = theseRepository.countThesesByAnnee();
        if (rows != null) {
            for (Object[] row : rows) {
                if (row[0] != null && row[1] != null) {
                    Integer annee = (Integer) row[0];
                    Long count = ((Number) row[1]).longValue();
                    result.put(annee, count);
                }
            }
        }
        return result;
    }
}
