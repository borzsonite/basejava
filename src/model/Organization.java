package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static util.DateUtil.NOW;
import static util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private Link link;
    private List<Position> position;

    public Organization() {
    }

    public Organization(String name, String url, List<Position> position) {
        Objects.requireNonNull(position, "period must not be null");
        this.link = new Link(name, url);
        this.position = position;
    }

    public Organization(String name, String url, Position... positions) {
        this.link = new Link(name, url);
        this.position = Arrays.asList(positions);
    }

    public Organization(Link homePage, List<Position> positions) {
        this.link = homePage;
        this.position = positions;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return Objects.equals(link, that.link) &&
                Objects.equals(position, that.position);
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
        private LocalDate startDate;
        private LocalDate endDate;
        private String title;
        private String description;

        public Position() {
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position position = (Position) o;
            return Objects.equals(startDate, position.startDate) &&
                    Objects.equals(endDate, position.endDate) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return "Позиция: " + title + '\'' +
                    ", дата начала: " + startDate +
                    ", дата окончания: " + endDate +
                    ", описание: " + description + '\'';
        }
    }
}
