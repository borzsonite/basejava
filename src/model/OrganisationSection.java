package model;

import java.util.List;
import java.util.Objects;

public class OrganisationSection extends Section {

    private final List<Organisation> organisations;

    public OrganisationSection(List<Organisation> organisations) {
        Objects.requireNonNull(organisations, "organizations must not be null");
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
        return Objects.equals(organisations, that.organisations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisations);
    }

    @Override
    public String toString() {
        return "OrganisationSection{" +
                "organisations=" + organisations +
                '}';
    }
}
