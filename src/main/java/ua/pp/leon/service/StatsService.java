package ua.pp.leon.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pp.leon.domain.OrderRepository;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@Service
public class StatsService {

    @Autowired
    private OrderRepository orderRepository;

    public List<DailyReport> generateDailyReport() {
        List<DailyReport> result = new ArrayList<>();
        orderRepository.generateDailyReport().stream().forEach((day) -> {
            result.add(new DailyReport((Date) day[0], (Double) day[1]));
        });
        return result;
    }
}
