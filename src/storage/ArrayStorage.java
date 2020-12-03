package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    //    protected int getIndex(String uuid) {
//        for (int i = 0; i < size; i++) {
//            if (storage[i].getUuid().equals(uuid)) {
//                return i;
//            }
//        }
//        return -1;
//    }

    @Override
    protected Resume proceedGet(Object resumePosition) {
        return storage[(Integer)resumePosition];
    }

        protected void saveByIndex(Resume resume, Object index) {
        storage[(Integer)size] = resume;
    }

    @Override
    protected void deleteByIndex(Object resumePositiin) {
        storage[(Integer)resumePositiin] = storage[size - 1];
    }
}
