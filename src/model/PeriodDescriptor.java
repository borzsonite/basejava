package model;

import java.time.LocalDate;

public class PeriodDescriptor {
    private final String title;
    LocalDate startDate;
    LocalDate endDate;
    private String description;

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
}
