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
    protected void proceedUpdate(Object resumePosition, Resume resume) {
        storage.set((Integer) resumePosition, resume);
    }

    @Override
    public void proceedSave(Object resumePosition, Resume resume) {
        storage.add(resume);

    }

    @Override
    public Resume proceedGet(Object resumePosition) {
        return storage.get((Integer)resumePosition);
    }

    @Override
    public void proceedDelete(Object resumePosition) {
        storage.remove(storage.get((Integer)resumePosition));
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
