package ua.pp.leon.controller.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ua.pp.leon.service.OrderService.DailyReport;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
public class DailyReportWrapperMockup {

    private static final int DEFAULT_PAGE_SIZE = 20;

    public List<Map<String, List<DailyReport>>> _embedded = new ArrayList<>();
    public List<Map<String, Link>> _links = new ArrayList<>();
    public Map<String, Integer> _page = new LinkedHashMap<>();

    public DailyReportWrapperMockup(List<DailyReport> dailyReports) {
        this(dailyReports, 0, DEFAULT_PAGE_SIZE);
    }

    public DailyReportWrapperMockup(List<DailyReport> dailyReports, int page, int pageSize) {
        int lastPage = dailyReports.size() / pageSize + 1;

        Map<String, Link> links = new LinkedHashMap<>();
        links.put("first", new Link("http://localhost:8080/api/daily?page="+page+"&size="+DEFAULT_PAGE_SIZE));
        links.put("self", new Link("http://localhost:8080/api/daily?page="+page+"&size="+DEFAULT_PAGE_SIZE));
        links.put("next", new Link("http://localhost:8080/api/daily?page="+(page+1)+"&size="+DEFAULT_PAGE_SIZE));
        links.put("last", new Link("http://localhost:8080/api/daily?page="+lastPage+"&size="+DEFAULT_PAGE_SIZE));
        _links.add(links);

        _page.put("size", pageSize);
        _page.put("totalElements", dailyReports.size());
        _page.put("totalPages", lastPage);
        _page.put("number", 0);

        Map<String, List<DailyReport>> dailies = new LinkedHashMap<>();
        dailies.put("dailyReports", dailyReports);
        _embedded.add(dailies);
    }

    public class Link {

        protected String href;

        public Link(String href) {
            this.href = href;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
