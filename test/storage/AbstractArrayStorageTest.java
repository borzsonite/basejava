package storage;

import exсeption.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveIfOverflow() {
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("testUuid" + i, "testName"));
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени...");
        }
        storage.save(new Resume("testUuid", "testName"));
    }
}
