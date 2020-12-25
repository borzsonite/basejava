package model;

public enum ContactType {
    PHONE("Телефон:"),
    SKYPE("Skype:"),
    EMAIL("Почта:"),
    LINKEDIN("Профиль LinkedIn:"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    String title;

    ContactType(String title) {
        this.title = title;
    }
}
