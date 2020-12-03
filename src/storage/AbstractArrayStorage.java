package storage;

import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import exсeption.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    @Override
    protected Object getPosition(String uuid) {
        int index = getIndex(uuid);
        return index;
    }

    @Override
    protected void proceedUpdate(Resume resume, Object resumePosition) {
        storage[(Integer) resumePosition] = resume;
    }

    @Override
    protected void proceedSave(Resume resume, Object resumePosition) {
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

//    public void update(Resume resume) {
//        int index = getIndex(resume.getUuid());
//        if (index < 0) {
//            throw new NotExistStorageException(resume.getUuid());
//        }
//        storage[index] = resume;
//        System.out.println("Resume " + resume.getUuid() + " updated.");
//    }

    //    public void save(Resume resume) {
//        int index = getIndex(resume.getUuid());
//        if (index >= 0) {
//            throw new ExistStorageException(resume.getUuid());
//        } else if (size < storage.length) {
//            saveByIndex(resume, index);
//            size++;
//            System.out.println("Resume " + resume.getUuid() + " saved");
//        } else {
//            throw new StorageException("Storage overflow", resume.getUuid());
//        }
//    }
//
//    public Resume get(String uuid) {
//        int index = getIndex(uuid);
//        if (index < 0) {
//            throw new NotExistStorageException(uuid);
//
//        }
//        return storage[index];
//    }
//
//    public void delete(String uuid) {
//        int index = getIndex(uuid);
//        if (index < 0) {
//            throw new NotExistStorageException(uuid);
//        }
//        deleteByIndex(index);
//        storage[size - 1] = null;
//        size--;
//        System.out.println("Resume " + uuid + " deleted");
//    }
//
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    //
    public int size() {
        return size;
    }
}
