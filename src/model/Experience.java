package model;

import java.time.LocalDate;

public class Experience {
    private String place;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String position;
    private String content;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(int year, int month, int day) {
        LocalDate dateFrom = LocalDate.of(year, month, day);
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(int year, int month, int day) {
        LocalDate dateTo = LocalDate.of(year, month, day);
        this.dateTo = dateTo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
