package storage;

import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doUpdate(Resume resume, SK resumePosition);

    protected abstract void doSave(Resume resume, SK resumePosition);

    protected abstract Resume doGet(SK resumePosition);

    protected abstract void doDelete(SK resumePosition);

    protected abstract boolean isExist(SK searchKey);

    public abstract List<Resume> doCopyAll();

    public void update(Resume resume) {
        SK searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
        System.out.println("Resume " + resume.getUuid() + " updated.");
    }

    public void save(Resume resume) {
        SK searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, searchKey);
        System.out.println("Resume " + resume.getUuid() + " saved.");
    }

    public Resume get(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
        System.out.println("Resume " + uuid + " deleted.");
    }


    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Comparator<Resume> sortByNameAndUuidComparator = Comparator.comparing(Resume::getFullName).
                thenComparing(Resume::getUuid);
        list.sort(sortByNameAndUuidComparator);

        return list;
    }
}
