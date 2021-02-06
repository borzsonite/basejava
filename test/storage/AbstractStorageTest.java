package storage;

import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {

    protected Storage storage;
    protected static final File STORAGE_DIR = new File("C:\\projects\\storage");
    protected static final String STORAGE_PATH = "C:\\projects\\storage";


    Resume RESUME_1 = ResumeTestData.createResume("uuid1", "Bob");
    Resume RESUME_2 = ResumeTestData.createResume("uuid2", "Alex");
    Resume RESUME_3 = ResumeTestData.createResume("uuid3", "Mike");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    public AbstractStorageTest(File storageDir, SerializationStrategy strategy) {

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
        Resume resume = ResumeTestData.createResume("uuid1", "Bob"); // error
        //Resume resume = new Resume("uuid1", "Bob"); // ok
        storage.update(resume);
        assertTrue(resume.equals(storage.get("uuid1")));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateIfNotExist() {
        storage.update(ResumeTestData.createResume("dummyUuid", "dummyFullName"));
    }

    @Test
    public void save() {
        Resume resume = ResumeTestData.createResume("uuid4", "Mike");
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
        List<Resume> expectedResumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
            expectedResumes.sort(Comparator.comparing(Resume::getFullName).
                thenComparing(Resume::getUuid));
        assertEquals(expectedResumes, actualResumes);
    }

    @Test
    public void get() {
        assertTrue(RESUME_1.equals(storage.get("uuid1")));
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