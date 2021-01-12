package model;

import java.time.LocalDate;

public class PeriodDescriptor {
    private final String title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private  String description;

    public PeriodDescriptor(String title, String description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PeriodDescriptor(String title, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Позиция: " + title + '\'' +
                ", дата начала: " + startDate +
                ", дата окончания: " + endDate +
                ", описание: " + description + '\'';
    }
}
