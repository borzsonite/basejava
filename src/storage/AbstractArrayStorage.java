package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 6;

    public Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        System.out.println("Clearing all");
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.uuid);
        if (index == -1) {
            System.out.println("Resume " + resume.getUuid() + "doesn't exist.");
        } else {
            storage[index] = resume;
            System.out.println("Resume " + resume.uuid + " updated.");
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size < storage.length) {
            saveByIndex(resume, index);
            size++;
            System.out.println("Resume " + resume.getUuid() + " saved");
        } else {
            System.out.println("Storage overflow");
        }
    }

    abstract void saveByIndex(Resume resume, int index);

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
            deleteByIndex(index);
            //storage[size - 1] = null;
            size--;
            System.out.println("Resume " + uuid + " deleted");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
    protected abstract void deleteByIndex(int index);
}
