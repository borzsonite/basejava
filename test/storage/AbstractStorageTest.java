package storage;

import exсeption.NotExistStorageException;
import model.Resume;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {

    private final Storage storage;

    String UUID_1 = "uuid1";
    String UUID_2 = "uuid2";
    String UUID_3 = "uuid3";

    Resume RESUME_1 = new Resume(UUID_1);
    Resume RESUME_2 = new Resume(UUID_2);
    Resume RESUME_3 = new Resume(UUID_3);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateIfNotExist() {
        storage.update(new Resume());
    }

    @Test
    public void save() {
        Resume resume = new Resume();
        storage.save(resume);
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
    storage.delete(UUID_1);
    assertEquals(2,storage.size());
    storage.get(UUID_1);
    }

    @Test
    public void getAll() {
        Resume[] actualResumes = storage.getAll();
        Resume[] expectedResumes = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expectedResumes, actualResumes);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}