package storage;
import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;

//Задача: перенести в класс код который будет общим для ArrayStorage, SortedArrayStorage, ListStorage, MapStorage. Общим будет код который проверяет в методах наличие/отуствие резюме в хранилище. Чтобы это сделать нужно зная uuid резюме пройтись по хранилищу на предмет наличия там резюме с соответствующим uuid. Возращаться будет либо null либо позиция резюме в хранилицще в виде индекса (лист, массив) или ключа (мапа).

public abstract class AbstractStorage implements Storage {

    protected abstract Object getPosition(String uuid);
    protected abstract void proceedUpdate(Resume resume, Object resumePosition);
    protected abstract void proceedSave(Resume resume, Object resumePosition);
    protected abstract Resume proceedGet(Object resumePosition);
    protected abstract void proceedDelete(Object resumePosition);


    public void update(Resume resume) {
        Object resumePosition = getPosition(resume.getUuid());
        if (resumePosition == Integer.valueOf(-1)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        proceedUpdate(resume, resumePosition);
        System.out.println("Resume " + resume.getUuid() + " updated.");
    }

        public void save(Resume resume) {
        Object resumePosition = getPosition(resume.getUuid());
        if (resumePosition != Integer.valueOf(-1)) {
            throw new ExistStorageException(resume.getUuid());
        }
        proceedSave(resume, resumePosition);
            System.out.println("Resume " + resume.getUuid() + " saved.");
    }

        public Resume get(String uuid) {
       Object resumePosition = getPosition(uuid);
        if (resumePosition == Integer.valueOf(-1)) {
            throw new NotExistStorageException(uuid);
        }
        return proceedGet(resumePosition);
    }

        public void delete(String uuid) {
        Object resumePosition = getPosition(uuid);
        if (resumePosition == Integer.valueOf(-1)) {
            throw new NotExistStorageException(uuid);
        }
        proceedDelete(resumePosition);
        System.out.println("Resume " + uuid + " deleted");
    }

}
