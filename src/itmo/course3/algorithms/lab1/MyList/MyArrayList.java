package itmo.course3.algorithms.lab1.MyList;

import itmo.course3.algorithms.lab1.Hash.HashTable;

import java.util.ArrayList;

public class MyArrayList<String> extends ArrayList<String> {
    HashTable table;

    public MyArrayList() {
        super();
    }

    public boolean searchforlabapoalgoritmamnomer1(String value) {

        for (int i = 0; i < size(); i++) {
            table.comp++;
            if (value.equals(super.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void setTable(HashTable table) {
        this.table = table;
    }
}
