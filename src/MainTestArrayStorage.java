import model.Resume;
import storage.SortedArrayStorage;

/**
 * Test for your storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid3");
        Resume r3 = new Resume("uuid2");
        Resume r4 = new Resume("uuid4");
        Resume r5 = new Resume("uuid6");
        Resume r6 = new Resume("uuid5");
        Resume r7 = new Resume();

        ARRAY_STORAGE.save(r2); // uuid3
        ARRAY_STORAGE.save(r6); // uuid5
        ARRAY_STORAGE.save(r1); // uuid1
        ARRAY_STORAGE.save(r5); // uuid6
        ARRAY_STORAGE.save(r4); // uuid4
        ARRAY_STORAGE.save(r3); // uuid2
        ARRAY_STORAGE.save(r7); // uuid2
        ARRAY_STORAGE.update(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

       // System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r6.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
