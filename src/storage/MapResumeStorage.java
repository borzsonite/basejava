package storage;

import model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.containsValue(storage.get(uuid))?uuid:null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey!=null;
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
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(storage.values());
        list.sort(Comparator.comparingInt(o -> o.getFullName().charAt(0)));
        return list;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
