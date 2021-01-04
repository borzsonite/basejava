package model;

public abstract class AbstractSection<T> {
    private T content;

    abstract T getContent();

    abstract void setContent(T content);
}
