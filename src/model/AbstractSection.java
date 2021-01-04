package model;

public abstract class AbstractSection<T> {
    private T content;

     abstract void setContent(T content);
     abstract T getContent();
}
