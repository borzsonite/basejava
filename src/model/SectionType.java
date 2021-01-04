package model;

public enum SectionType {
    PERSONAL("Личные качества"), //String
    OBJECTIVE("Позиция"), //String
    ACHIEVEMENT("Достижения"), //String
    QUALIFICATION("Квалификация"), //String
    EXPERIENCE("Опыт работы"), //
    EDUCATION("Образование");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public static void main(String[] args) {
        System.out.println(SectionType.ACHIEVEMENT);
    }

    public String getTitle() {
        return title;
    }
}
