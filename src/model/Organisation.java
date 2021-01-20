package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static util.DateUtil.of;
import static util.DateUtil.NOW;


public class Organisation implements Serializable {
    private final Link link;
    private final List<Position> position;

    public Organisation(String name, String url, List<Position> position) {
        Objects.requireNonNull(position, "period must not be null");
        this.link = new Link(name, url);
        this.position = position;
    }

    public Organisation(String name, String url, Position... positions) {
        this.link = new Link(name, url);
        this.position = Arrays.asList(positions);
    }

    public Organisation (Link homePage, List<Position> positions) {
        this.link = homePage;
        this.position = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organisation that = (Organisation) o;
        return link.equals(that.link) && position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, position);
    }

    @Override
    public String toString() {
        return link + "" + position;
    }

    public static class Position implements Serializable {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private  String description;

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            this.startDate = Objects.requireNonNull(startDate);
            this.endDate = Objects.requireNonNull(endDate);
            this.title = Objects.requireNonNull(title);
            this.description = description;
        }

//        public Position(String title, String description, LocalDate startDate, LocalDate endDate) { //мой конструктор
//            this.title = title;
//            this.description = description;
//            this.startDate = startDate;
//            this.endDate = endDate;
//        }
//
//        public Position(String title, LocalDate startDate, LocalDate endDate) { //мой конструктор
//            this.title = title;
//            this.startDate = startDate;
//            this.endDate = endDate;
//        }


        @Override
        public String toString() {
            return "Позиция: " + title + '\'' +
                    ", дата начала: " + startDate +
                    ", дата окончания: " + endDate +
                    ", описание: " + description + '\'';
        }
    }
}
