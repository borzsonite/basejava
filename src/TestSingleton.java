import model.SectionType;

public class TestSingleton {
    private static final TestSingleton instance = new TestSingleton();

    private TestSingleton() {

    }

    public static TestSingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }
    }

    public enum Singleton {
        INSTANCE
    }
}
