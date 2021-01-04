package model;

import java.util.List;

public class Organisations extends AbstractSection<List<Experience>>{

    List<Experience> content;

    @Override
    public void setContent(List<Experience> content) {
        this.content = content;
    }

    @Override
    public List<Experience> getContent() {
        return content;
    }
}
