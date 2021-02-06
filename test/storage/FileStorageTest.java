package storage;


public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(STORAGE_DIR, new ObjectStreamFileStorage());
    }
}