package storage;

import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void proceedUpdate(Object resumePosition, Resume resume);

    protected abstract void proceedSave(Object resumePosition, Resume resume);

    protected abstract Resume proceedGet(Object resumePosition);

    protected abstract void proceedDelete(Object resumePosition);

    protected abstract boolean isExist(Object searchKey);

    public abstract List<Resume> getStorage();

    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        proceedUpdate(searchKey, resume);
        System.out.println("Resume " + resume.getUuid() + " updated.");
    }

    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        proceedSave(searchKey, resume);
        System.out.println("Resume " + resume.getUuid() + " saved.");
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return proceedGet(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        proceedDelete(searchKey);
        System.out.println("Resume " + uuid + " deleted.");
    }


    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getStorage();
        list.sort(Comparator.comparingInt(o -> o.getFullName().charAt(0)));
        return list;
    }
}
