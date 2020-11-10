package storage;
import model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    public Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        if (getIndex(resume.uuid) == -1) {
            System.out.println("Resume" + resume.uuid + "doesn't exist.");
        } else {
            storage[size] = resume;
            System.out.println("Resume " + resume.uuid + " updated.");
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size < storage.length - 1) {
            if (storage[Math.abs(index) - 1] != null) {
                storage[Math.abs(index)] = storage[Math.abs(index) - 1];
            }
            storage[Math.abs(index) - 1] = resume;
            System.out.println("Resume " + resume.getUuid() + " saved");
            size++;
        } else {
            System.out.println("Storage overflow");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Resume " + uuid + " deleted.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
