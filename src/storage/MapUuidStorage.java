package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return searchKey != null;
    }

    @Override
    protected void proceedUpdate(String uuid, Resume resume) {
        storage.replace(uuid, resume);
    }

    @Override
    protected void proceedSave(String uuid, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume proceedGet(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void proceedDelete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getStorage() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
