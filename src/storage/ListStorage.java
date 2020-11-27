package storage;

import model.Resume;

import java.util.Iterator;

public class ListStorage extends AbstractStorage {


    @Override
    void saveByIndex(Resume resume) {
        storage.add(resume);
    }

    protected int getIndex(String uuid) {
        for (Resume currentResume : storage) {
            if (currentResume.getUuid().contains(uuid)) {
                return storage.indexOf(currentResume);
            }
        }
        return -1;
    }

    @Override
    protected void deleteByIndex(int index) {
        storage.remove(index);
    }
}
