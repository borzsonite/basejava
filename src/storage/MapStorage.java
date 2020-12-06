package storage;

import model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected Object getIndex(String uuid) {
        if(storage.containsKey(uuid)) {
            return uuid;
        }
        return -1;
    }

    @Override
    protected void proceedUpdate(Object index, Resume resume) {
        storage.replace((String)index, resume);
    }

    @Override
    protected void proceedSave(Object index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume proceedGet(Object index) {
        return storage.get(index);
    }

    @Override
    protected void proceedDelete(Object index) {
        storage.remove(index);
    }

    @Override
    public void clear() {
    storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] result = new Resume[storage.size()];
        return storage.values().toArray(result);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
