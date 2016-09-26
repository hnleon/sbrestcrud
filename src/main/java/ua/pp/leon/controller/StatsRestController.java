package ua.pp.leon.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.leon.service.DailyReport;
import ua.pp.leon.service.StatsService;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@RestController
@RequestMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatsRestController {

    @Autowired
    private StatsService statsService;

    @RequestMapping(value = "/daily", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailyReport> dailyReport() {
        return statsService.generateDailyReport();
    }
}
