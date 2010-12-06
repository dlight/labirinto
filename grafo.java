/* Trabalho final: AED2 e PAED2 2010.2
 *
 * Labirinto
 *
 * Alunos: Elias Gabriel Amaral da Silva
 *         Flávio Fernando Vasconcelos
 */

import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.HashSet;

public class grafo<P extends par<Integer, Integer>> implements gancho {
    private HashSet<P> matriz;
    private int width;
    private int height;

    @SuppressWarnings(value = "unchecked") public grafo(int w, int h) {
        this.width = w;
        this.height = h;

        matriz = new HashSet<P>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (i != w-1)
                    matriz.add((P) new par<Integer, Integer>(i+j*w, i+1+j*w));

                if (j != h-1)
                    matriz.add((P) new par<Integer, Integer>(i+j*w, i+(j+1)*w));
            }
        }
    }

    static private String[][] array_copy(String[][] a) {
        String[][] r = new String[a.length][a[0].length];

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                r[i][j] = a[i][j];

        return r;
    }

    static private void print_state(String[][] s) {
        for (String[] a : s) {
            for (String e : a)
                System.out.printf("%s\t", e);
            System.out.printf("\n");
        }
    }

    public void callback(String[][] s) { print_state(s); System.out.printf("\n"); }

    public <U extends gancho> void profundidade(U obj) {
        Stack<Integer> s = new Stack<Integer>();

        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>(width*height);

        System.out.printf("%d , %d\n", width*height, a.size());

        for (int i = 0; i < width*height; i++)
            a.add(i, new ArrayList<Integer>());

        for(P p : matriz) {
            a.get(p.esq).add(p.dir);
            a.get(p.dir).add(p.esq);
        }

        for (ArrayList<Integer> i : a)
            Collections.shuffle(i);

        s.push(0);

        String[][] m = new String[width][height];

        int topo, vis;
        for (topo = s.peek(), vis = -1; topo != width*height-1; vis = topo, topo = s.peek()) {
            ArrayList<Integer> t = a.get(topo);

            if (t.size() == 0) {
                m[topo % width][topo / width] = "lixo";
                s.pop();
                m[s.peek() % width][s.peek() / width] = "topo";
            }
            else {
                int i = t.get(t.size() - 1);

                s.push(i);
                t.remove(t.size() - 1);
                a.get(i).remove(a.get(i).indexOf(topo));

                int xv = vis % width;
                int yv = vis / width;

                if (vis != -1 && (m[xv][yv] == null ||
                                  m[xv][yv].equals("topo")))
                    m[xv][yv] = "caminho";

                int x = topo % width;
                int y = topo / width;

                m[x][y] = "topo";
            }

                obj.callback(m);
        }

        m[vis % width][vis / width] = "caminho";
        m[topo % width][topo / width] = "fim";
        obj.callback(m);
    }

    private HashSet<par<HashSet<P>, HashSet<Integer>>>

        kr_init() {
        int n = width*height;

        HashSet<par<HashSet<P>, HashSet<Integer>>> p =
            new HashSet<par<HashSet<P>, HashSet<Integer>>>(n);


        for (int i = 0; i < n; i++) {
            HashSet<P> m = new HashSet<P>();

            HashSet<Integer> v = new HashSet<Integer>();
            v.add(i);
            p.add(new par<HashSet<P>, HashSet<Integer>>(m, v));
        }

        return p;
    }

    private par<HashSet<P>, HashSet<Integer>>
        kr_busca (HashSet<par<HashSet<P>, HashSet<Integer>>> floresta,
                  Integer vertice) {


        for (par<HashSet<P>, HashSet<Integer>> e : floresta)
            if (e.dir.contains(vertice))
                return e;

        throw new RuntimeException("kr_busca");
    }

    private par<HashSet<P>, HashSet<Integer>>
        kr_merge (par<HashSet<P>, HashSet<Integer>> v1,
                  par<HashSet<P>, HashSet<Integer>> v2,
                  P aresta) {

        v1.esq.addAll(v2.esq);
        v1.dir.addAll(v2.dir);

        v1.esq.add(aresta);

        return v1;
    }

    private void
        kr_passo(HashSet<par<HashSet<P>, HashSet<Integer>>> floresta,
                 P aresta) {

        par<HashSet<P>, HashSet<Integer>> v1 =
            kr_busca(floresta, aresta.esq);
        par<HashSet<P>, HashSet<Integer>> v2 =
            kr_busca(floresta, aresta.dir);

        if (v1 != v2) {
            floresta.remove(v1);
            floresta.remove(v2);
            floresta.add(kr_merge(v1, v2, aresta));
        }
    }

    public void kruskal() {
        ArrayList<P> a =
            new ArrayList<P>(matriz);
        Collections.shuffle(a);

        HashSet<par<HashSet<P>, HashSet<Integer>>> floresta = kr_init();

        for (P i : a)
            if (floresta.size() > 1)
                kr_passo(floresta, i);

        matriz = floresta.iterator().next().esq;
    }

    public boolean[][] linhas_horizontais() {
        boolean[][] paredes = new boolean[width][height + 1];

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height+1; j++)
                paredes[i][j] = true;

        for (P p : matriz) {
            int x1 = p.esq % width;
            int x2 = p.dir % width;

            if (x1 == x2)
                paredes[x1][p.esq / width + 1] = false;

        }

        return paredes;
    }

    public boolean[][] linhas_verticais() {
        boolean[][] paredes = new boolean[width + 1][height];
        for (int i = 0; i < width+1; i++)
            for (int j = 0; j < height; j++)
                paredes[i][j] = true;

        paredes[0][0] = false;
        paredes[width][height-1] = false;

        for (P p : matriz) {
            int y1 = p.esq / width;
            int y2 = p.dir / width;

            if (y1 == y2)
                paredes[p.esq % width + 1][y1] = false;
                }

        return paredes;
    }

    public void print() {
        for (par <Integer, Integer> p : matriz)
            System.out.printf("%d -> %d\n", p.esq, p.dir);
    }

    
}