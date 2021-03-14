package storage.serializer;

import java.io.IOException;

public interface Readable {
    void accept() throws IOException;
}
