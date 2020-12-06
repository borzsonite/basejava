package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

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
    public Resume[] getAll() {
        Resume[] result = new Resume[storage.size()];
        return storage.toArray(result);
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected Object getIndex(String uuid) {
        for (int i = 0; i<storage.size(); i++) { // по идее, тут бы подошел indexOf, но не придумал как его можно здесь реализовать без цикла, если такая возможность скажи как.
            if (storage.get(i).getUuid().contains(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
