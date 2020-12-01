// push at 17:12_01/12/2020
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
    protected abstract void proceedSave(Resume resume, Object resumePosition);
    protected abstract Resume proceedGet(Object resumePosition);
    protected abstract void proceedDelete(Object resumePosition);
    public abstract Resume[] getAll();
    public abstract int size();

    public void update(Resume resume) {
        Object resumePosition = getResume(resume.getUuid());
        if (!isExist(resumePosition)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        proceedUpdate(resume, resumePosition);
        System.out.println("Resume " + resume.getUuid() + " updated.");
    }

    public void save(Resume resume) {
        Object resumePosition = getResume(resume.getUuid());
        if (isExist(resumePosition)) {
            throw new ExistStorageException(resume.getUuid());
        }
        proceedSave(resume, resumePosition);
    }

    public Resume get(String uuid) {
        Object resumePosition = getResume(uuid);
        if (!isExist(resumePosition)) {
            throw new NotExistStorageException(uuid);
        }
        return proceedGet(resumePosition);
    }

    public void delete(String uuid) {
        Object resumePosition = getResume(uuid);
        if (!isExist(resumePosition)) {
            throw new NotExistStorageException(uuid);
        }
        proceedDelete(uuid);
    }

}
