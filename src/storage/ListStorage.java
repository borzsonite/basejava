package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    protected Object getSearchKey(String uuid) {
        for (int i = 0; i<storage.size(); i++) {
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
    protected void proceedUpdate(Object index, Resume resume) {
        storage.set((Integer) index, resume);
    }

    @Override
    public void proceedSave(Object index, Resume resume) {
        storage.add(resume);

    }

    @Override
    public Resume proceedGet(Object index) {
        return storage.get((Integer)index);
    }

    @Override
    public void proceedDelete(Object index) {
        storage.remove(storage.get((Integer)index));
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(storage);
        list.sort(Comparator.comparingInt(o -> o.getFullName().charAt(0)));
        return list;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
