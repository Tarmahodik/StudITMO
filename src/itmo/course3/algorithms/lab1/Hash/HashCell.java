package itmo.course3.algorithms.lab1.Hash;

import itmo.course3.algorithms.lab1.MyList.MyArrayList;


public class HashCell {
    private HashTable table;
    private MyArrayList<String> _list = new MyArrayList<String>();
    private int position = 0;

    public HashCell(String _value, HashTable table) {
        this.table = table;
        _list.add(_value);
    }

    public MyArrayList<String> getList() {
        return _list;
    }

}

