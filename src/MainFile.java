import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    /**
     * Сделайте рекурсивный обход и вывод имени файлов в каталогах и подкаталогах (корневой каталог- ваш проект)
     */
    public static void main(String[] args) throws IOException {
        //printDirsContent(".");
        printDirDeeply(new File("."));
    }

    static void printDirsContent(String dir) throws IOException {
        File file = new File(dir);
        String[] dirs = file.list();
        for (int i = 0; i < Objects.requireNonNull(dirs).length; i++) {
            File file1 = new File(dir + File.separator + dirs[i]);
            if (file1.isFile()) {
                System.out.println(file1.getName());
            } else {
                printDirsContent(dir + File.separator + dirs[i]); // это разве не рекурсия? вызов метода из самого метода
            }
        }
    }

    static void printDirDeeply(File dir) {
        File[] dirs = dir.listFiles();
        for(File dirList: dirs) {
            if(dirList.isFile()) {
                System.out.println("    file: " + dirList.getName());
            } else if(dirList.isDirectory()) {
                System.out.println("dir: " + dirList.getName());
                printDirDeeply(dirList);
            }
        }
    }
}
