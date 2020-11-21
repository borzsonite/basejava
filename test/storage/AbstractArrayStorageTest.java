package storage;

import exсeption.NotExistStorageException;
import exсeption.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateIfNotExist() {
        storage.update(storage.get("dummy"));
    }

    @Test
    public void save() {
        Resume resume = new Resume();
        storage.save(resume);
        Assert.assertNotNull(storage.get(resume.getUuid()));
    }

    @Test(expected = StorageException.class)
    public void saveIfOverflow() throws NoSuchFieldException, IllegalAccessException {
        for (int i = 0; i < STORAGE_LIMIT - 2; i++) {
            Resume resume = new Resume();
            storage.save(resume);
        }
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(storage.get(UUID_1), resumes[0]);
        Assert.assertEquals(storage.get(UUID_2), resumes[1]);
        Assert.assertEquals(storage.get(UUID_3), resumes[2]);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteIfNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(storage.get(UUID_1), resumes[0]);
    }

    @Test(expected = NotExistStorageException.class)
    public void getIfNotExist() {
        storage.get("dummy");
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {

        for (int i = 0; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        throw new ArrayIndexOutOfBoundsException(); //имитация переполнения
    }
}