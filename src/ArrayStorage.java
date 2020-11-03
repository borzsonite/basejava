import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        int index = getIndex(r.uuid);
        if (index >= 0) {
            System.out.println("Resume already exists.");
        } else if (size < storage.length - 1) {
            storage[size] = r;
            size++;
            System.out.println("New resume saved.");
        } else {
            System.out.println("Not enough space.");
        }

    }

    void update(Resume r) {
        if (getIndex(r.uuid) == -1) {
            System.out.println("Resume doesn't exist.");
        } else {
            storage[size] = r;
            System.out.println("Resume updated.");
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume doesn't exist.");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Resume deleted.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
