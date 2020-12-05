package storage;

import exсeption.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public class ListStorageTest extends AbstractStorageTest{

    private final Storage storage = new ListStorage();

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test()
    public void saveIfOverflow() {
        try {
            for (int i = storage.size() + 1; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени...");
        }
        storage.save(new Resume());
    }

}