import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\aberr\\IdeaProjects\\basejava\\.gitignore");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file1 = new File("./src/storage");
        System.out.println(file1.isDirectory());

        for(String elem: file1.list()) {
            System.out.println(elem);
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fis.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(FileInputStream fis1 = new FileInputStream(file)) {
            fis1.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
