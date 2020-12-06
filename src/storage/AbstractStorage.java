package storage;

import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getIndex(String uuid);
    protected abstract void proceedUpdate(Object resumePosition, Resume resume);
    protected abstract void proceedSave(Object resumePosition, Resume resume);
    protected abstract Resume proceedGet(Object resumePosition);
    protected abstract void proceedDelete(Object resumePosition);

    public void update(Resume resume) {
        Object resumePosition = getIndex(resume.getUuid());
        if(isExist(resumePosition, resume.getUuid())) {
            proceedUpdate(resumePosition, resume);
            System.out.println("Resume " + resume.getUuid() + " updated.");
        }
    }

    public void save(Resume resume) {
        Object resumePosition = getIndex(resume.getUuid());
        if (resumePosition instanceof String || (Integer) resumePosition >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        proceedSave(resumePosition, resume);
        System.out.println("Resume " + resume.getUuid() + " saved.");
    }

    public Resume get(String uuid) {
        Object resumePosition = getIndex(uuid);
        if(isExist(resumePosition, uuid)) {
            return proceedGet(resumePosition);
        }
        return null;
    }

    public void delete(String uuid) {
        Object resumePosition = getIndex(uuid);
        if(isExist(resumePosition, uuid)) {
            proceedDelete(resumePosition);
            System.out.println("Resume " + uuid + " deleted");
        }
    }

    private boolean isExist(Object resumePosition, String uuid) {
        if(resumePosition == Integer.valueOf(-1)) {
            throw new NotExistStorageException(uuid);
        }
        return true;
    }
}
