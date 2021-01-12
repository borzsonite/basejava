package model;

import java.util.List;
import java.util.Objects;

public class Organisation {
    private final Link link;
    private final List<PeriodDescriptor> periodDescriptor;

    public Organisation(String name, String url, List<PeriodDescriptor> periodDescriptor) {
        Objects.requireNonNull(periodDescriptor, "period must not be null");
        this.link = new Link(name, url);
        this.periodDescriptor = periodDescriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organisation that = (Organisation) o;
        return Objects.equals(link, that.link) && periodDescriptor.equals(that.periodDescriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, periodDescriptor);
    }

    @Override
    public String toString() {
        return link + "" + periodDescriptor;
    }
}
