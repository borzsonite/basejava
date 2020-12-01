package storage;

import model.Resume;


public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected void saveByIndex(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteByIndex(int index) {
        storage[index] = storage[size - 1];
    }
}
