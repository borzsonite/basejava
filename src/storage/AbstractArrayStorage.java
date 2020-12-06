package storage;

import exсeption.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    abstract void saveByIndex(Resume resume, int index);
    abstract void deleteByIndex(int index);

    @Override
    protected void proceedUpdate(Object resumePosition, Resume resume) {
        storage[(Integer) resumePosition] = resume;
    }

    @Override
    protected void proceedSave(Object resumePosition, Resume resume) {
        if (size < STORAGE_LIMIT) {
            saveByIndex(resume, (Integer) resumePosition);
            size++;
        } else {
            throw new StorageException("Storage overflow!", resume.getUuid());
        }
    }

    @Override
    protected void proceedDelete(Object resumePosition) {
        deleteByIndex((Integer) resumePosition);
        size--;
    }

    @Override
    protected Resume proceedGet(Object resumePosition) {
        return storage[(Integer)resumePosition];
    }

    public void clear() {
        System.out.println("Clearing all");
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    //
    public int size() {
        return size;
    }
}
