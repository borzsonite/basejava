package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    public Resume[] storage = new Resume[STORAGE_LIMIT];
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
        int index = getIndex(r.getUuid());
        System.out.println("get index=" + index);
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size < storage.length - 1) {
            if (storage[Math.abs(index) - 1] != null) {
                storage[Math.abs(index)] = storage[Math.abs(index) - 1];
            }
            storage[Math.abs(index) - 1] = r;

            size++;
        } else {
            System.out.println("Storage overflow");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        System.out.println(index);
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
