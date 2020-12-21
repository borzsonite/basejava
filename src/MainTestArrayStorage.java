import model.Resume;
import storage.ArrayStorage;
import storage.MapResumeStorage;

import java.util.List;

public class MainTestArrayStorage {
    static final MapResumeStorage ARRAY_STORAGE = new MapResumeStorage();

    public static void main(String[] args) {
        Resume r7 = new Resume("uuid7", "Alex");
        Resume r1 = new Resume("uuid1", "Bob");
        Resume r2 = new Resume("uuid3", "Alex");
        Resume r3 = new Resume("uuid2", "Kate");
        Resume r4 = new Resume("uuid4", "Jhon");
        Resume r5 = new Resume("uuid6", "Mike");
        Resume r6 = new Resume("uuid5", "Christy");

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

        printAll();
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        List<Resume> list = ARRAY_STORAGE.getAllSorted();
        for (Resume r : list) {
            System.out.println(r);
        }
    }
}
