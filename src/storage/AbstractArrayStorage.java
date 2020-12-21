package storage;

import exсeption.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    abstract void saveByIndex(Resume resume, int index);

    abstract void deleteByIndex(int index);

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected void proceedUpdate(Object index, Resume resume) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected void proceedSave(Object index, Resume resume) {
        if (size < STORAGE_LIMIT) {
            saveByIndex(resume, (Integer) index);
            size++;
        } else {
            throw new StorageException("Storage overflow!", resume.getUuid());
        }
    }

    @Override
    protected void proceedDelete(Object index) {
        deleteByIndex((Integer) index);
        size--;
    }

    @Override
    protected Resume proceedGet(Object index) {
        return storage[(Integer) index];
    }

    public void clear() {
        System.out.println("Clearing all");
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = Arrays.asList(Arrays.copyOfRange(storage, 0, size));
        list.sort(Comparator.comparingInt(o -> o.getFullName().charAt(0)));
        return list;
    }

    public int size() {
        return size;
    }

    public List<Resume> getStorage() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }
}
