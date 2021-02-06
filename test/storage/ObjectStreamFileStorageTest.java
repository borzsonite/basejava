package storage;

import static org.junit.Assert.*;

public class ObjectStreamFileStorageTest extends AbstractStorageTest{

    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamFileStorage()));
    }
}