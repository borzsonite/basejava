package storage;

import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getPosition(String uuid);

    protected abstract void proceedUpdate(Object resumePosition, Resume resume);

    protected abstract void proceedSave(Object resumePosition, Resume resume);

    protected abstract Resume proceedGet(Object resumePosition);

    protected abstract void proceedDelete(Object resumePosition);

    public void update(Resume resume) {
            proceedUpdate(isExist(resume.getUuid()), resume);
            System.out.println("Resume " + resume.getUuid() + " updated.");
    }

    public void save(Resume resume) {
        Object resumePosition = getPosition(resume.getUuid());
        if (resumePosition instanceof String || (Integer) resumePosition >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        proceedSave(resumePosition, resume);
        System.out.println("Resume " + resume.getUuid() + " saved.");
    }

    public Resume get(String uuid) {
        return proceedGet(isExist(uuid));
    }

    public void delete(String uuid) {
        proceedDelete(isExist(uuid));
        System.out.println("Resume " + uuid + " deleted.");
    }

    private Object isExist(String uuid) {
        Object resumePosition = getPosition(uuid);
        if (resumePosition == Integer.valueOf(-1)) {
            throw new NotExistStorageException(uuid);
        }
        return resumePosition;
    }
}
