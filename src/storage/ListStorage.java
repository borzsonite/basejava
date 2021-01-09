package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().contains(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }


    @Override
    public void clear() {
        System.out.println("Clearing all");
        storage.clear();
    }

    @Override
    protected void doUpdate(Object index, Resume resume) {
        storage.set((Integer) index, resume);
    }

    @Override
    public void doSave(Object index, Resume resume) {
        storage.add(resume);

    }

    @Override
    public Resume doGet(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    public void doDelete(Object index) {
        storage.remove(storage.get((Integer) index));
    }

    @Override
    public List<Resume> getStorage() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
