package itmo.course3.compiler.lab1;

public class Lexem {

    /****
     * Лексемы
     * 1 - Идентификатор
     * 2 - Константа
     * 3 - Присваивание
     * 4 - Аддитивные
     * 5 - Мультипликативные
     * 6 - Логические
     * 7 - Унарное
     * 8 - Ключевое слово
     * 9 - Скобки
     */
    static int count = 8;

    private int type;
    private int id;
    private int line;

    public Lexem(int type, int id, int line) {
        this.id = id;
        this.type = type;
        this.line = line;
    }


    public String toString() {
        if (type == -1) {
            return "Error at line " + this.line;
        } else {

            return "СТРОКА № " + this.line + " " +
                    "|| ЛЕКСЕМА :" +
                    " " + toStringHelper(this.type) + "  (" +
                    "ТИП-ИД " + this.id + ")";
        }
    }

    public String toStringHelper(int t) {
        switch (t) {
            case 1:
                return "Идентификатор";
            case 2:
                return "Константа";
            case 3:
                return "Присваивание";
            case 4:
                return "Аддитивные";
            case 5:
                return "Мультипликативные";
            case 6:
                return "Логические";
            case 7:
                return "Унарное";
            case 8:
                return "Ключевое слово";
            case 9:
                return "Скобка";
            default:
                return "Ошибка";
        }


    }
}
