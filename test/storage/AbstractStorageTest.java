package storage;

import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    Resume RESUME_1 = new Resume("uuid1", "Bob");
    Resume RESUME_2 = new Resume("uuid2", "Alex");
    Resume RESUME_3 = new Resume("uuid3", "Mike");

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
        Resume resume = new Resume("uuid1", "Bob");
        storage.update(resume);
        assertEquals(resume, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateIfNotExist() {
        storage.update(new Resume("dummyUuid", "dummyFullName"));
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid4", "Mike");
        storage.save(resume);
        Assert.assertNotNull(storage.get(resume.getUuid()));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void getAllSorted() {
        List<Resume> actualResumes = storage.getAllSorted();
        List<Resume> expectedResumes = new ArrayList<>();
        expectedResumes.add(RESUME_1);
        expectedResumes.add(RESUME_2);
        expectedResumes.add(RESUME_3);
        expectedResumes.sort(Comparator.comparing(Resume::getFullName).
                thenComparing(Resume::getUuid));
        assertEquals(expectedResumes, actualResumes);
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getIfNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
        storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteIfNotExist() {
        storage.delete("dummy");
    }


    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}