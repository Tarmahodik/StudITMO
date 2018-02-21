package itmo.course3.algorithms.lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void Test(int na, int ma, int qa, int ra) {
        //try {
        int n = na;
        int m = ma;
        int q = qa;
        int r = ra;


        Random abc = new Random();
        int[][] DaEdges = new int[n][n];
        for (int j = 0; j < n; j++)
            for (int ji = 0; ji < n; ji++)
                DaEdges[j][ji] = 0;
        int numb = 0;


        for (int j = 1; j < m; j++) {
            int ii = abc.nextInt(n - 0) + 0;
            int jj = abc.nextInt(n - ii) + ii;
            int ww = abc.nextInt(r - q) + 1 + q;
            if (DaEdges[ii][jj] == 0)
                numb++;
            DaEdges[ii][jj] = ww;
        }


        Edge[] ed = new Edge[numb];
        int l = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (DaEdges[i][j] != 0) {
                    ed[l] = new Edge(i, j, DaEdges[i][j]);
                    l++;
                }
            }


        //AlgorithmMST.Piramidalnaya(ed,ed.length);
        List<Edge> intList = new ArrayList<Edge>();
        for (int index = 0; index < ed.length / 2; index += 2) {
            intList.add(ed[index]);
        }


        long time = System.nanoTime();
        Edge[] ed1 = AlgorithmMST.boruvka(n, intList);
        long ptime = System.nanoTime();
        System.out.println("Boruwka: " + (ptime - time) + " ");


        long time2 = System.nanoTime();
        Edge[] ed2 = AlgorithmMST.Kruskal_alg(n, ed);
        long ptime2 = System.nanoTime();
        System.out.println("Kruskal: " + (ptime2 - time2) + " ");

        //System.out.println();
        /** } catch (Exception E) {
         *  System.out.println("Baka Senpai!");
         * System.exit(1);
         }*/
    }

    public static void autoInput() {
        try {
            Scanner text = new Scanner(System.in);
            System.out.println("Enter quantity of vertexes: ");
            int n = text.nextInt();
            System.out.println("Enter quantity of edges: ");
            int m = text.nextInt();
            System.out.println("Enter min value: ");
            int q = text.nextInt();
            System.out.println("Enter max value: ");
            int r = text.nextInt();

            Random abc = new Random();

            int[][] DaEdges = new int[n][n];
            for (int j = 0; j < n; j++)
                for (int ji = 0; ji < n; ji++)
                    DaEdges[j][ji] = 0;
            int numb = 0;
            for (int j = 1; j < m; j++) {
                int ii = abc.nextInt(n - 0) + 0;
                int jj = abc.nextInt(n - ii) + ii;
                int ww = abc.nextInt(r - q) + 1 + q;
                if (DaEdges[ii][jj] != 0)
                    numb++;
                DaEdges[ii][jj] = ww;
            }
            Edge[] ed = new Edge[2 * numb];
            int l = 0;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (DaEdges[i][j] != 0) {
                        ed[l] = new Edge(i, j, DaEdges[i][j]);
                        ed[l + numb] = new Edge(j, i, DaEdges[i][j]);
                        l++;
                    }

            /*
            try {
                long time = System.nanoTime();
                Edge[] ed1 = AlgorithmMST.boruvka(n, ed);
                long ptime = System.nanoTime();
                System.out.println("A: " + ((ptime - time) / 1000000000) + " seconds");
            } catch (Exception B) {
                System.out.println("Boruvka cannot");
            }*/

            long time2 = System.nanoTime();
            Edge[] ed2 = AlgorithmMST.Kruskal_alg(n, ed);
            long ptime2 = System.nanoTime();
            System.out.println("B: " + ((ptime2 - time2) / 1000000000) + " seconds");
            System.out.println();
        } catch (Exception E) {
            System.out.println("Baka Senpai!");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        /*
        Scanner text = new Scanner(System.in);
        System.out.println("Enter quantity of vertexes: ");
        int n = text.nextInt();
        System.out.println("Enter quantity of edges: ");
        int m = text.nextInt();
        System.out.println("Enter min value: ");
        int q = text.nextInt();
        System.out.println("Enter max value: ");
        int r = text.nextInt();
        Test(n,m,q,r);
        */

        System.out.println("n*n/10");

        for (int i = 101; i < 10001; i += 100) {
            //Test(i, i * i, 1, 10000);
            Test(i, i * i / 10, 1, 10000);
            System.out.println();
        }
        System.out.println("n*n");

        for (int i = 101; i < 10001; i += 100) {
            //Test(i, i * i, 1, 10000);
            Test(i, i * i, 1, 10000);
            System.out.println();
        }
        /**
         *List<Edge> a = new ArrayList<Edge>();
         *a.add(new Edge(1, 1, 20));
         *a.add(new Edge(1, 1, 30));
         *a.add(new Edge(1, 1, 40));
         *a.add(new Edge(1, 1, 50));
         *for (Edge one : a) {
         *    one.weight += 15;
         *}
         *for (Edge one : a){
         *    System.out.println(one.weight);
         *}*/
    }


}
