import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (Resume resume: storage) {
            if(resume != null) {
                resume = null;
            }
        }
    }

    void save(Resume r) {
        storage[this.size()+1] = r;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume.uuid == uuid) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
    }
}
