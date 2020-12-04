package storage;

import model.Resume;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        System.out.println("Clearing all");
        storage.clear();
    }

    @Override
    protected Object getPosition(String uuid) {
        return getIndex(uuid);
    }

    @Override
    protected void proceedUpdate(Object resumePosition, Resume resume) {
        storage.set((Integer) resumePosition, resume);
    }

    @Override
    public void proceedSave(Object resumePosition, Resume resume) {
        saveByIndex(resume, (Integer) resumePosition);
    }

    @Override
    public Resume proceedGet(Object resumePosition) {
        return storage.get((Integer)resumePosition);
    }

    @Override
    public void proceedDelete(Object resumePosition) {
        deleteByIndex((Integer)resumePosition);
    }

    @Override
    public Resume[] getAll() {
        Resume[] result = new Resume[storage.size()];
        return (storage.toArray(result));
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    void saveByIndex(Resume resume, int index) {
        int position = Math.abs(index) - 1;
        storage.add(position, resume);
        Collections.sort(storage);
    }

    @Override
    void deleteByIndex(int index) {
       storage.remove(index);
    }

    protected int getIndex(String uuid) {
        for (Resume currentResume : storage) {
            if (currentResume.getUuid().contains(uuid)) {
                return storage.indexOf(currentResume);
            }
        }
        return -1;
    }
}
