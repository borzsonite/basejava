package storage.serializer;

import java.io.IOException;

public interface Writable<T> {
    void accept(T collectionElement) throws IOException;
}
