package storage;

import exсeption.NotExistStorageException;
import exсeption.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public class AbstractArrayStorageTest {

    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertTrue(storage.size() == 0);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_1));
    }

    @Test
    public void save() {
        Resume resume = new Resume();
        storage.save(resume);
        Assert.assertTrue(storage.get(resume.getUuid()) != null);
    }

    @Test
    public void get() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(storage.get(UUID_1), resumes[0]);
        Assert.assertEquals(storage.get(UUID_2), resumes[1]);
        Assert.assertEquals(storage.get(UUID_3), resumes[2]);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        storage.delete(UUID_2);
        storage.delete(UUID_3);
        Assert.assertTrue(storage.size() == 0);

    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertArrayEquals(resumes, Arrays.copyOfRange(resumes, 0, 3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {

        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
            throw new ArrayIndexOutOfBoundsException(); //имитация переполнения
        } catch (StorageException e) {
            throw e;
        }


    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }


}