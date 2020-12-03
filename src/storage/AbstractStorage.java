package storage;
import exсeption.ExistStorageException;
import exсeption.NotExistStorageException;
import model.Resume;

//Задача: перенести в класс код который будет общим для ArrayStorage, SortedArrayStorage, ListStorage, MapStorage. Общим будет код который проверяет в методах наличие/отуствие резюме в хранилище. Чтобы это сделать нужно зная uuid резюме пройтись по хранилищу на предмет наличия там резюме с соответствующим uuid. Возращаться будет либо null либо позиция резюме в хранилицще в виде индекса (лист, массив) или ключа (мапа).

public abstract class AbstractStorage implements Storage {

    protected abstract Object getPosition(String uuid);
    protected abstract void proceedUpdate(Object resumePosition, Resume resume);
    protected abstract void proceedSave(Object resumePosition, Resume resume);
    protected abstract Resume proceedGet(Object resumePosition);
    protected abstract void proceedDelete(Object resumePosition);
    abstract void saveByIndex(Resume resume, int index);
    abstract void deleteByIndex(int index);
    protected abstract int getIndex(String uuid);



    public void update(Resume resume) {
        Object resumePosition = getPosition(resume.getUuid());
        if (resumePosition == Integer.valueOf(-1)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        proceedUpdate(resumePosition, resume);
        System.out.println("Resume " + resume.getUuid() + " updated.");
    }

        public void save(Resume resume) {
        Object resumePosition = getPosition(resume.getUuid());
        if ((Integer)resumePosition >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        proceedSave(resumePosition, resume);
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
