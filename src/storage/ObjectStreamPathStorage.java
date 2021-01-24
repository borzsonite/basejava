package storage;

import model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class ObjectStreamPathStorage extends AbstractPathStorage{
    protected ObjectStreamPathStorage(String directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume resume, OutputStream os) throws IOException {

    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        return null;
    }
}
