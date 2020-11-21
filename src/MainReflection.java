import model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ////////////////Task from HW4///////////////
        Resume resume = new Resume("uuid10");
        Method method = Resume.class.getDeclaredMethod("toString");
        System.out.println(method.invoke(resume));
    }

}
