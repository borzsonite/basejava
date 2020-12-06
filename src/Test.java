import model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //////////////////Reflection example//////////////////
//        Resume resume = new Resume("uuid10");
//        Field field = resume.getClass().getDeclaredFields()[0]; // получение 1-го элемента в массиве полей
//        System.out.println(field.getName());
//        field.setAccessible(true); // открыть доступ на изменение final private поля
//        System.out.println(field.get(resume)); // .get возвращает значение поля
//        field.set(resume, "newUuid"); // устанавливает значение поля
//        System.out.println(field.get(resume));

        //////////////////ArrayList test//////////////////
        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(new Resume("uuid1"));
        resumeList.add(new Resume("uuid3"));
        resumeList.add(new Resume("uuid2"));

        Iterator<Resume> iterator = resumeList.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if(resume.getUuid().contains("uuid2")) {
                System.out.println("got " + resume.getUuid() + "index is " + resumeList.indexOf(resume));
            }
            System.out.println(resumeList.get(1));
        }


    }
}
