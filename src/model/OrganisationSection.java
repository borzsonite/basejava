package model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrganisationSection extends AbstractSection {

    private final Link link;
    private final List<Organisation> organisations;
    public OrganisationSection(Link link, List<Organisation> organisations) {
        Objects.requireNonNull(organisations, "organizations must not be null");
        this.link = link;
        this.organisations = organisations;
    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganisationSection that = (OrganisationSection) o;
        return organisations.equals(that.organisations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisations);
    }

    @Override
    public String toString() {
        return "OrganisationSection{" +
                "organisations=" + organisations +
                ", link=" + link +
                '}';
    }
}
