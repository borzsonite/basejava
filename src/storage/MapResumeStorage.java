package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.containsValue(storage.get(uuid)) ? storage.get(uuid) : null;
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected void proceedUpdate(Resume key, Resume resume) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void proceedSave(Resume key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume proceedGet(Resume resume) {
        return (Resume) resume;
    }

    @Override
    protected void proceedDelete(Resume resume) {
        storage.remove(((Resume) resume).getUuid());
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
