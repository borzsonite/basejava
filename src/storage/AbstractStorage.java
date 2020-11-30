package storage;
import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import exсeption.StorageException;
import model.Resume;

import java.util.Arrays;


public abstract class AbstractStorage implements Storage {

    protected abstract Object getResume(String index);
    protected abstract boolean isExist(Object resumePosition);
    protected abstract void proceedUpdate(Resume resume, Object resumePosition);

    public void update(Resume resume) {
        Object resumePosition = getResume(resume.getUuid());
        if(!isExist(resumePosition)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        proceedUpdate(resume, resumePosition);
        System.out.println("Resume " + resume.getUuid() + " updated.");

//        int index = getResume (resume.getUuid());
//        if (index < 0) {
//            throw new NotExistStorageException(resume.getUuid());
//        }
//        storage[index] = resume;
//        System.out.println("Resume " + resume.getUuid() + " updated.");
    }


    public void save(Resume resume) {
        int index = getResume (resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size < storage.length) {
            saveByIndex(resume, index);
            size++;
            System.out.println("Resume " + resume.getUuid() + " saved");
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    abstract void saveByIndex(Resume resume, int index);

    public Resume get(String uuid) {
        int index = getResume (uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);

        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getResume (uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteByIndex(index);
        storage[size - 1] = null;
        size--;
        System.out.println("Resume " + uuid + " deleted");
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }


    protected abstract void deleteByIndex(int index);
}
