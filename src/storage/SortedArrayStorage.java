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

    void saveByIndex(Resume resume, int index) {
        int position = Math.abs(index);
        if (storage[position - 1] != null) {
           Resume[] subArray =  Arrays.copyOfRange(storage, position -1, size);
           System.arraycopy(subArray, 0, storage, position, subArray.length);
        }
        storage[position - 1] = resume;
    }

    @Override
    protected void deleteByIndex(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }
}


