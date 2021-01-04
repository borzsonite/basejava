package model;

import java.util.List;

public class BulletedListSection extends AbstractSection<List<String>> {
    private List<String> content;

    @Override
    List<String> getContent() {
        return content;
    }

    @Override
    public void setContent(List<String> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
