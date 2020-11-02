import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < this.size(); i++) {
            storage[i] = null;
        }
        size = 0;
    }

    boolean isExists(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                return true;
            }
        }
        return false;
    }

    void save(Resume r) {
        if (size > 0 && isExists(r)) {
            System.out.println("Resume already exists.");
            return;
        }
        storage[size] = r;
        size++;
        System.out.println("New resume saved.");
    }


    void update(Resume r) {
        if (isExists(r)) {
            storage[this.size()] = r;
            size++;
            System.out.println("Resume updated.");
            return;
        }
        System.out.println("Resume doesn't exist.");
    }

    Resume get(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        Resume dummyResume = new Resume();
        dummyResume.uuid = uuid;
        return dummyResume;
    }

    void delete(String uuid) {
        for (int index = 0; index < this.size(); index++) {
            if (storage[index].uuid.equals(uuid)) {
                storage[index] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                System.out.println("Resume deleted.");
                return;
            }
        }
        System.out.println("Resume doesn't exist.");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[this.size()];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
