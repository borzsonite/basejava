package storage;

import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//public class ListStorage extends AbstractStorage {
    public class ListStorage {
//    protected List<Resume> storage = new ArrayList<>();
//
//    @Override
//    public void clear() {
//        System.out.println("Clearing all");
//        storage.clear();
//    }
//
//    @Override
//    public void update(Resume resume) {
//        int index = storage.indexOf(resume);
//        if (index < 0) {
//            throw new NotExistStorageException(resume.getUuid());
//        }
//        storage.set(index, resume);
//        System.out.println("Resume " + resume.getUuid() + " updated.");
//    }
//
//    @Override
//    public void save(Resume resume) {
//        int index = storage.indexOf(resume);
//        if (index >= 0) {
//            throw new ExistStorageException(resume.getUuid());
//        }
//        saveByIndex(resume);
//        System.out.println("Resume " + resume.getUuid() + " saved");
//    }
//
//    @Override
//    public Resume get(String uuid) {
//        int index = getIndex(uuid);
//        if (index < 0) {
//            throw new NotExistStorageException(uuid);
//
//        }
//        return storage.get(index);
//    }
//
//    @Override
//    public void delete(String uuid) {
//        int index = getIndex(uuid);
//        if (index < 0) {
//            throw new NotExistStorageException(uuid);
//        }
//        deleteByIndex(index);
//        System.out.println("Resume " + uuid + " deleted");
//    }
//
//    @Override
//    public Resume[] getAll() {
//        Resume[] result = new Resume[storage.size()];
//        return (storage.toArray(result));
//    }
//
//    @Override
//    public int size() {
//        return storage.size();
//    }
//
//    @Override
//    void saveByIndex(Resume resume) {
//        storage.add(resume);
//    }
//
//    protected int getIndex(String uuid) {
//        for (Resume currentResume : storage) {
//            if (currentResume.getUuid().contains(uuid)) {
//                return storage.indexOf(currentResume);
//            }
//        }
//        return -1;
//    }
//
//    @Override
//    protected void deleteByIndex(int index) {
//        storage.remove(index);
//    }
}
