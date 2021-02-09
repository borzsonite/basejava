package storage;

import exсeption.StorageException;
import model.Resume;
import storage.strategy.SerializationStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final SerializationStrategy strategy;

    protected FileStorage(File directory, SerializationStrategy strategy) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.strategy = strategy;
    }

     @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            strategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));

        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (Exception e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public List<Resume> doCopyAll() {
        List<Resume> resumes = new ArrayList<>();
        checkIfStorageIsNull();
        for (File file : directory.listFiles()) {
            resumes.add(doGet(file));
        }
        return resumes;
    }

    @Override
    public void clear() {
        checkIfStorageIsNull();
        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
    }

    @Override
    public int size() {
        checkIfStorageIsNull();
        return directory.listFiles().length;
    }

    protected void checkIfStorageIsNull() {
        if(directory.listFiles() == null) {
            throw new StorageException("Wrong storage name ", directory.getName());
        }
    }
}
