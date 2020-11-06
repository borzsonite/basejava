package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
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

    public void update(Resume r) {
        if (getIndex(r.uuid) == -1) {
            System.out.println("Resume" + r.uuid + "doesn't exist.");
        } else {
            storage[size] = r;
            System.out.println("Resume" + r.uuid + " updated.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
