package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storage = new ArrayList<>();

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().contains(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }


    @Override
    public void clear() {
        System.out.println("Clearing all");
        storage.clear();
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        storage.set(index, resume);
    }

    @Override
    public void doSave(Resume resume, Integer index) {
        storage.add(resume);

    }

    @Override
    public Resume doGet(Integer index) {
        return storage.get(index);
    }

    @Override
    public void doDelete(Integer index) {
        storage.remove(storage.get(index));
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
