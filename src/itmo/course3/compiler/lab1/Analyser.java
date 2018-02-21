package itmo.course3.compiler.lab1;

import java.util.ArrayList;

public class Analyser {

    private ArrayList<ArrayList<String>> lexemsList;
    private ArrayList<Lexem> resultLexems = new ArrayList<>();

    private StringBuilder temp = null;
    private int type = 0;
    private int line = 1;
    private boolean comment = false;


    public Analyser() {
        lexemsList = new ArrayList<>();
        //id
        lexemsList.add(new ArrayList<>());
        //const
        lexemsList.add(new ArrayList<>());
        //assignment
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add(":=");
        lexemsList.add(tmp);
        //additive
        tmp = new ArrayList<>();
        tmp.add("+");
        tmp.add("-");
        lexemsList.add(tmp);
        //multiplicative
        tmp = new ArrayList<>();
        tmp.add("*");
        tmp.add("/");
        lexemsList.add(tmp);
        //logical
        tmp = new ArrayList<>();
        tmp.add(">");
        tmp.add("<");
        tmp.add("==");
        tmp.add(".AND.");
        tmp.add(".OR.");
        tmp.add(".XOR.");
        tmp.add(">>");
        tmp.add("<<");
        lexemsList.add(tmp);
        //unary
        tmp = new ArrayList<>();
        lexemsList.add(tmp);
        //keywords
        tmp = new ArrayList<>();
        tmp.add("Begin");
        tmp.add("End");
        tmp.add("Var");
        tmp.add("Boolean");
        tmp.add("Decimal");
        tmp.add("FOR");
        tmp.add("TO");
        tmp.add("DO");
        tmp.add("IF");
        tmp.add("ELSE");
        lexemsList.add(tmp);
        //brackets
        tmp = new ArrayList<>();
        tmp.add("(");
        tmp.add(")");
        lexemsList.add(tmp);
    }

    public void readByChar(String program) {
        line = 1;
        for (int i = 0; i < program.length(); i++) {
            analyseChar(program.charAt(i));
        }

//        lexemsList.isEmpty();
        for (Lexem lexem : resultLexems) {
            System.out.println(lexem.toString());
        }

    }

    private void analyseChar(char ch) {
        int asciiCode = (int) ch;

        if (ch == '{') {
            comment = true;
            return;
        } else if (ch == '}') {
            comment = false;
            return;
        }


        if (comment == true) {
            if (ch == '\n') line++;
            return;
        }

        if (ch == '(' || ch == ')') {
            if (temp != null)
                addLexem();
            temp = new StringBuilder();
            temp.append(ch);
            type = 9;
            addLexem();
            return;
        }

        if (type == -1 && (ch != ' ' && ch != '\n')) {
            temp.append(ch);
            return;
        }

        if ((ch == ' ' || ch == '\n')) {
            if (temp != null && !temp.toString().equals("")) {
                if (temp.charAt(0) == ':') type = -1;
                addLexem();
            }
            if (ch == '\n') line++;
            return;
        }

        /***********************/


        if (((asciiCode >= 65) && (asciiCode <= 90)) || ((asciiCode >= 97) && (asciiCode <= 122))) {
            if (temp == null) {
                type = 1;
                temp = new StringBuilder();
            } else if (type != 1 && temp.charAt(0) != '.' && temp.charAt(0) != ':') {
                addLexem();
                type = 1;
                temp = new StringBuilder();
            }
            temp.append(ch);
            return;
        }

        if (((asciiCode >= 48) && (asciiCode <= 57))) {
            if (temp == null) {
                type = 2;
                temp = new StringBuilder();
            } else if (type != 2) {
                addLexem();
                type = 2;
                temp = new StringBuilder();
            }
            temp.append(ch);
            return;
        }

        if (ch == ':') {
            if (temp == null) {
                type = 3;
                temp = new StringBuilder();
            } else {
                addLexem();
                type = 3;
                temp = new StringBuilder();
            }
            temp.append(ch);
            return;
        }

        if (ch == '-' || ch == '+') {
            if (temp == null) {
                type = 4;
                temp = new StringBuilder();
            } else {
                addLexem();
                temp = new StringBuilder();
                type = 4;
            }
            temp.append(ch);
            addLexem();
            return;
        }


        if (ch == '=') {
            if (temp == null) {
                temp = new StringBuilder();
                type = 6;
                temp.append(ch);
            } else if (temp.toString().equals(":")) {
                temp.append(ch);
                addLexem();
            } else if (temp.toString().equals("=")) {
                temp.append(ch);
                addLexem();
            } else {
                addLexem();
                type = 6;
                temp = new StringBuilder();
                temp.append(ch);
            }
            return;
        }
        if (ch == '<') {
            if (temp == null) {
                type = 6;
                temp = new StringBuilder();
                temp.append(ch);
            } else if (temp.toString().equals("<")) {
                temp.append(ch);
                addLexem();

            }
            return;

        }

        if (ch == '>') {
            if (temp == null) {
                type = 6;
                temp = new StringBuilder();
                temp.append(ch);
            } else if (temp.toString().equals(">")) {
                temp.append(ch);
                addLexem();

            }
            return;

        }

        if (ch == '*' || ch == '/') {
            if (temp == null) {
                type = 5;
                temp = new StringBuilder();
            } else {
                addLexem();
                type = 5;
                temp = new StringBuilder();
            }
            temp.append(ch);
            addLexem();
            return;
        }

        if (ch == '.') {
            if (temp == null) {
                type = 6;
                temp = new StringBuilder();
                temp.append(ch);
            } else if (temp.charAt(0) == '.') {
                if (temp.toString().equals(".NOT")) {
                    type = 7;
                    temp.append(ch);
                    addLexem();
                } else if (temp.toString().equals(".AND") || temp.toString().equals(".OR") || temp.toString().equals(".XOR")) {
                    temp.append(ch);
                    addLexem();
                } else {
                    type = -1;
                    temp.append(ch);
                    addLexem();
                }
            } else {
                type = -1;
                temp.append(ch);
                addLexem();
                temp = new StringBuilder();
                type = 6;
            }
            return;
        }

        if (ch == ';') {
            if (temp == null || temp.charAt(0) != ':') {
                type = -1;
                temp.append(ch);
                addLexem();
            } else if (temp.toString().equals(":Decimal") || temp.toString().equals(":Boolean")) {
                temp = new StringBuilder(temp.substring(1));
                addLexem();
            } else {
                temp.append(ch);
                type = -1;
                addLexem();
            }
            return;
        }

        if (temp != null) {
            addLexem();
            temp = new StringBuilder();
            temp.append(ch);
            type = -1;
            addLexem();
        } else {
            temp = new StringBuilder();
            temp.append(ch);
            type = -1;
            addLexem();
        }
    }

    private void addLexem() {
        int id;
        if (type == -1) {
            id = 0;
        } else {
            id = lexemsList.get(8).indexOf(temp.toString());
            if (id == -1) {
                if (type == 2) {
                    temp = new StringBuilder(Integer.toHexString(Integer.valueOf(temp.toString())));
                }
                id = lexemsList.get(type - 1).indexOf(temp.toString());
                if ((id == -1) && (type == 1 || type == 2)) {
                    lexemsList.get(type - 1).add(temp.toString());
                    id = lexemsList.get(type - 1).size() - 1;
                }
            } else {
                type = 8;
            }
        }

        Lexem lexem = new Lexem(type, id, line);
        resultLexems.add(lexem);
        temp = null;
        type = 0;
    }
}
