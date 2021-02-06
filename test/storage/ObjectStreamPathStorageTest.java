package storage;

import static org.junit.Assert.*;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_PATH, new ObjectStreamPathStorage()) {
        });
    }
}