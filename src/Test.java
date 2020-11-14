import model.Resume;

import java.util.Arrays;

public class Test implements Cloneable {
    int size = 0;
    int STORAGE_LIMIT = 100;

    private Resume[] storage1 = new Resume[5];

    void setArray() {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r4 = new Resume();
        r4.setUuid("uuid4");

        System.out.println(r1.compareTo(r4));
        Arrays.binarySearch(storage1, 0, 0, r1);

        save(r1);
        save(r2);
        save(r3);
        save(r4);



        System.out.println(Arrays.toString(storage1));
       // Arrays.sort(storage1, 0, 4);
        System.out.println(Arrays.toString(storage1));

    }

    public void save(Resume r) {
      //  System.out.println(getIndex(r.getUuid()));
//        if (getIndex(r.getUuid()) != -1) {
//            System.out.println("Resume " + r.getUuid() + " already exist");
//        } else if (size >= STORAGE_LIMIT) {
//            System.out.println("Storage overflow");
//        } else {
//            storage1[size] = r;
//            size++;
//        }
    }


    public static void main(String[] args) {
        Test test = new Test();
        test.setArray();
        //System.out.println(test.getIndex("uuid4"));

        int[] array = new int[10];
        for(int i=0; i<7; i++) {
            array[i] = i;
        }
        System.out.println(Arrays.toString(array));
        int position = 2;
        System.arraycopy(array, 2, array, position +1, 7);
        System.out.println(Arrays.toString(array));
    }

    int getIndex(String uuid) {
        Resume keySearch = new Resume();
        keySearch.setUuid(uuid);
        return Arrays.binarySearch(storage1, 0, 4, keySearch);
    }

}
