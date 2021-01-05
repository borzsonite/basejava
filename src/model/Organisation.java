package model;

import java.util.List;

public class Organisation extends AbstractSection<List<Experience>> {

    private List<Experience> experience;

    @Override
    public List<Experience> getContent() {
        return experience;
    }

    @Override
    public void setContent(List<Experience> experience) {
        this.experience = experience;
    }
}
