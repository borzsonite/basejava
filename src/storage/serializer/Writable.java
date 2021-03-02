package storage.serializer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public interface Writable<T> {
    void accept(T collectionElement) throws IOException;
}
