package storage;

import model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected Object getPosition(String uuid) {
        return storage.containsKey(uuid)?uuid:-1;
    }

    @Override
    protected void proceedUpdate(Object key, Resume resume) {
        storage.replace((String)key, resume);
    }

    @Override
    protected void proceedSave(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume proceedGet(Object key) {
        return storage.get(key);
    }

    @Override
    protected void proceedDelete(Object key) {
        storage.remove(key);
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
