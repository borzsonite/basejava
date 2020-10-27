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

    void save(Resume r) {
        storage[this.size()] = r;
        size++;
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
                System.arraycopy(storage, index + 1, storage, index, storage.length - 1 - index);
                size--;
                return;
            }
        }
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
