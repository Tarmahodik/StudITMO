package itmo.course3.algorithms.lab3;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmMST {

    private static void MAX(Edge[] Mass, int sizedaheap, int I) {
        int L = (I + 1) * 2 - 1;
        int R = (I + 1) * 2;
        int Lal = 0;

        if (L < sizedaheap && Mass[L].weight > Mass[I].weight)
            Lal = L;
        else
            Lal = I;

        if (R < sizedaheap && Mass[R].weight > Mass[Lal].weight)
            Lal = R;

        if (Lal != I) {
            Edge Chislo = Mass[I];
            Mass[I] = Mass[Lal];
            Mass[Lal] = Chislo;

            MAX(Mass, sizedaheap, Lal);
        }
    }

    public static void Piramidalnaya(Edge[] mass, int n) {
        int heapSize = n;
        for (int p = (heapSize - 1) / 2; p >= 0; p--)
            MAX(mass, heapSize, p);

        for (int i = n - 1; i > 0; i--) {
            Edge temp = mass[i];
            mass[i] = mass[0];
            mass[0] = temp;

            heapSize--;
            MAX(mass, heapSize, 0);
        }
    }

    public static Edge MinEdge(List<Edge> ed, int index) {
        Edge min = new Edge(1, 1, Integer.MAX_VALUE);
        int min_index = -1;
        for (Edge one : ed)
            if (one.weight < min.weight && (one.from == index && one.to != index
                    || one.from != index && one.to == index)) {
                min = one;
            }
        return min;
    }

    public static Edge MinEdge(List<Edge> ed, int[] bel) {
        Edge min = new Edge(1, 1, Integer.MAX_VALUE);
        for (Edge one : ed)
            if (one.used == false && one.weight < min.weight && bel[one.from] != bel[one.to]) {
                min = one;
            }
        return min;
    }

    /**
     * !!!!!!!!!!!!!!!BORUVKA!!!!!!!!!!!!!!!!!!!!!!!
     */
    public static Edge[] boruvka(int n, List<Edge> ed) {
        Edge[] result = new Edge[n - 1];
        List<Edge> trial = ed;
        int x = 0;

        for (Edge one : trial)
            one.used = false;

        int[] belong = new int[n];
        for (int i = 0; i < n; i++)
            belong[i] = i;

        for (int i = 0; i < n; i++) {
            Edge u = MinEdge(trial, i);
            if (u.used == false) {
                u.used = true;

                for (int ji = 0; ji < belong.length; ji++)
                    if (belong[ji] == belong[u.from] && ji != u.from)
                        belong[ji] = belong[u.to];

                belong[u.from] = belong[u.to];
                result[x] = u;
                x++;
            }
        }

        List<Edge> todelete = new ArrayList<Edge>();
        for (Edge one : trial) {
            if (belong[one.from] == belong[one.to])
                todelete.add(one);
        }
        for (Edge one : todelete) {
            trial.remove(one);
        }

        while (x < n - 1) {
            Edge u = MinEdge(trial, belong);
            u.used = true;
            for (int ji = 0; ji < belong.length; ji++)
                if (belong[ji] == belong[u.from] && ji != u.from)
                    belong[ji] = belong[u.to];

            belong[u.from] = belong[u.to];
            result[x] = u;
            x++;
            trial.remove(u);

            todelete = new ArrayList<Edge>();
            for (Edge one : trial) {
                if (belong[one.from] == belong[one.to])
                    todelete.add(one);
            }
            for (Edge one : todelete) {
                trial.remove(one);
            }
        }
        return result;
    }

    /**
     * !!!!!!!!!!!!!!!Kruskal!!!!!!!!!!!!!!!!!!!!!!!
     */
    public static Edge[] Kruskal_alg(int n, Edge[] ed) {
        Edge[] result = new Edge[n - 1];
        Edge[] trial = ed;
        Piramidalnaya(trial, trial.length);
        int[] belong = new int[n];
        for (int i = 0; i < n; i++)
            belong[i] = i;

        int x = 0;
        for (int i = 0; i < trial.length; i++) {
            if (belong[trial[i].from] != belong[trial[i].to]) {
                for (int ji = 0; ji < belong.length; ji++)
                    if (belong[ji] == belong[trial[i].from] && ji != trial[i].from)
                        belong[ji] = belong[trial[i].to];
                belong[trial[i].from] = belong[trial[i].to];
                result[x] = trial[i];
                x++;
            }

            int sum = 0;
            for (int j = 0; j < belong.length - 1; j++)
                if (belong[j] == belong[j + 1])
                    sum++;
            for (int j = 0; j < belong.length; j++)
                if (sum == n - 1)
                    return result;
        }
        return result;
    }
}
