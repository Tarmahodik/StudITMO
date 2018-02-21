package itmo.course3.algorithms.lab1.Hash;


public class HashTable {

    public HashCell[] cells = new HashCell[300];
    public int searchCounts = 0;//Для сбора статистики
    public double comp = 0;     //
    private int collisions = 0; //

    public void add(String value) {
        int hashCode = getHashCode(value);
        HashCell cell = null;

        try {
            cell = cells[hashCode];
        } catch (IndexOutOfBoundsException e) {
            this.resize(hashCode);
        }

        if (cell == null) {
            cells[hashCode] = new HashCell(value, this);
        } else {
            if (!cell.getList().contains(value)) {
                collisions++;
                cell.getList().add(value);
            }

        }
    }

    public boolean search(String value) {
        this.searchCounts++;
        HashCell cell = cells[getHashCode(value)];
        if (cell == null) {
            return false;
        }
        cell.getList().setTable(this);
        return cell.getList().searchforlabapoalgoritmamnomer1(value);
    }


    //Получение среднего количества сравнений
    public double getMean() {
        return comp / searchCounts;
    }

    public void setSearchCounts(int searchCounts) {
        this.searchCounts = searchCounts;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }

    public int getCollisions() {
        return collisions;
    }

    public void setCollisions(int collisions) {
        this.collisions = collisions;
    }

    private void resize(int newSize) {
        HashCell[] newCells = new HashCell[newSize + 1];
        for (int i = 0; i < this.cells.length; i++) {
            newCells[i] = this.cells[i];
        }
        this.cells = newCells;
    }

    public int getHashCode(String identifier) {
        return (int) identifier.charAt(0) + (int) identifier.charAt(1);
    }

}
