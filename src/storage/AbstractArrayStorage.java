package storage;

import exсeption.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    abstract void saveByIndex(Resume resume, int index);

    abstract void deleteByIndex(int index);

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void doUpdate(Integer index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void doSave(Integer index, Resume resume) {
        if (size < STORAGE_LIMIT) {
            saveByIndex(resume, index);
            size++;
        } else {
            throw new StorageException("Storage overflow!", resume.getUuid());
        }
    }

    @Override
    protected void doDelete(Integer index) {
        deleteByIndex(index);
        size--;
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    public void clear() {
        System.out.println("Clearing all");
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public List<Resume> getStorage() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }
}
