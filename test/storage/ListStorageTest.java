package storage;

import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListStorageTest {
    private final Storage storage = new AbstractStorage() {
        @Override
        void saveByIndex(Resume resume) {

        }

        @Override
        protected int getIndex(String uuid) {
            return 0;
        }

        @Override
        protected void deleteByIndex(int index) {

        }
    };

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume("uuid1"));
        storage.save(new Resume("uuid2"));
        storage.save(new Resume("uuid3"));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }
};



