package storage;

import storage.serializer.JsonStreamSerializer;
import storage.serializer.StreamSerializer;
import storage.serializer.XmlStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}