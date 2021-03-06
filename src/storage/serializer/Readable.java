package storage.serializer;

import model.Resume;

import java.io.IOException;

public interface Readable {
    void accept() throws IOException;
}
