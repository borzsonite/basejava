package storage;
import exсeption.NotExistStorageException;
import model.Resume;

//Задача: перенести в класс код который будет общим для ArrayStorage, SortedArrayStorage, ListStorage, MapStorage. Общим будет код который проверяет в методах наличие/отуствие резюме в хранилище. Чтобы это сделать нужно зная uuid резюме пройтись по хранилищу на предмет наличия там резюме с соответствующим uuid. Возращаться будет либо null либо позиция резюме в хранилицще в виде индекса (лист, массив) или ключа (мапа).

public abstract class AbstractStorage implements Storage {

    protected abstract Object getPosition(String uuid);
    protected abstract Object proceedUpdate(Resume resume);

    public void update(Resume resume) {
        Object resumePosition = getPosition(resume.getUuid());
        if (resumePosition == null) {
            throw new NotExistStorageException(resume.getUuid());
        }
        proceedUpdate(resume);
        System.out.println("Resume " + resume.getUuid() + " updated.");
    }
}
