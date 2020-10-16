import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < this.size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[this.size()] = r;
    }

    Resume get(String uuid) {
        for (int i=0; i<this.size(); i++ ) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        Resume dummyResume = new Resume();
        dummyResume.uuid = uuid;
        return dummyResume;
    }

    void delete(String uuid) {
        for (int index = 0; index < this.size(); index++) {
            if (storage[index].uuid == uuid) {
                System.arraycopy(storage, index + 1, storage, index, storage.length - 1 - index);
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[this.size()];
        for(int i=0; i<resumes.length; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        for (int i = 0; i < storage.length; i++) {
            if (i==0 && storage[i] == null) {
                return 0;
            } else if (i>0 && storage[i] == null) {
                return i;
            }
        }
        return storage.length;
    }
}
