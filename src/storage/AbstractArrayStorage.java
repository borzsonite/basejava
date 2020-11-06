package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        if (getIndex(r.uuid) == -1) {
            System.out.println("Resume" + r.uuid + "doesn't exist.");
        } else {
            storage[size] = r;
            System.out.println("Resume" + r.uuid + " updated.");
        }
    }

    public void save(Resume r) {
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
