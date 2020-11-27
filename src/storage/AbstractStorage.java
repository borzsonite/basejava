package storage;

import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements Storage {

   protected List<Resume> storage = new ArrayList<>();

    public void clear() {
        System.out.println("Clearing all");
        storage.clear();
    }

    public void update(Resume resume) {
        int index = storage.indexOf(resume);
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        storage.set(index, resume);
        System.out.println("Resume " + resume.getUuid() + " updated.");
    }

    public void save(Resume resume) {
        int index = storage.indexOf(resume);
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
            saveByIndex(resume);
            System.out.println("Resume " + resume.getUuid() + " saved");
    }


    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);

        }
        return storage.get(index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteByIndex(index);
        System.out.println("Resume " + uuid + " deleted");
    }

    public Resume[] getAll() {
        Resume[] result = new Resume[storage.size()];
        return (storage.toArray(result));
    }

    public int size() {
        return storage.size();
    }

    protected abstract int getIndex(String uuid);
    protected abstract void deleteByIndex(int index);
    abstract void saveByIndex(Resume resume);

}
