package db_config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Dbconfig {
    private static final File PROPS = new File("config\\resumes.properties");
    private static final Dbconfig INSTANCE = new Dbconfig();
    private Properties props = new Properties();
    private File storageDir;

    public static Dbconfig get() {
        return INSTANCE;
    }

    private Dbconfig() {
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }
}
