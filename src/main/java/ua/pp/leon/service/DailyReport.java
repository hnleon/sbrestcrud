package ua.pp.leon.service;

import java.util.Date;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
public class DailyReport {

    protected Date date;
    protected double sum;

    public DailyReport() {
    }

    public DailyReport(Date date, double sum) {
        this.date = date;
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
