package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    void saveByIndex(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (storage[Math.abs(index) - 1] != null) {
            storage[Math.abs(index)] = storage[Math.abs(index) - 1];
        }
        storage[Math.abs(index) - 1] = resume;
    }

    @Override
    protected void deleteByIndex(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }
}


