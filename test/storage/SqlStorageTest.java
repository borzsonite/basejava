package storage;

import com.sun.deploy.config.Config; // не видит Config из проекта
import db_config.Dbconfig; // этот видит но не видит в нем методов,


public class SqlStorageTest {
    super(Config.get().getStorageDir());
    super(Dbconfig.get().getStorageDir()); // хотя в AbstractStorageTest такой вызов срабатывает
}