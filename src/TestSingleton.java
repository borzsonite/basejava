import model.SectionType;

public class TestSingleton {
    private static TestSingleton instance = new TestSingleton();

    public static TestSingleton getInstance() {
        return instance;
    }

    private TestSingleton() {

    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
        for(SectionType type: SectionType.values()) {
            System.out.println(type.getTitle());
        }
    }

    public enum Singleton {
        INSTANCE;
    }
}
