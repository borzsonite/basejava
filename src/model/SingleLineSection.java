package model;

public class SingleLineSection extends AbstractSection<String> {
    private String content;

    @Override
    protected String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
