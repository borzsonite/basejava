import model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume("uuid10");
        Field field = resume.getClass().getDeclaredFields()[0]; // получение 1-го элемента в массиве полей
        System.out.println(field.getName());
        field.setAccessible(true); // открыть доступ на изменение final private поля
        System.out.println(field.get(resume)); // .get возвращает значение поля
        field.set(resume, "newUuid"); // устанавливает значение поля
        System.out.println(field.get(resume));
    }
}
