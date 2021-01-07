import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MainDirRecursionListing {
    /**
     * Сделайте рекурсивный обход и вывод имени файлов в каталогах и подкаталогах (корневой каталог- ваш проект)
     */
    public static void main(String[] args) throws IOException {
        printDirsContent(".");
    }

    static void printDirsContent(String dir) throws IOException {
        File file = new File(dir);
        String[] dirs = file.list();
        for(int i=0; i<dirs.length; i++) {
            File file1 = new File(dir + File.separator + dirs[i]);
            if(file1.isFile()) {
                System.out.println(file1.getCanonicalPath());
            } else {
                String path = dir + File.separator + dirs[i];
                printDirsContent(dir + File.separator + dirs[i]);
            }
        }
    }
}
