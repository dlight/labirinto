import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class grafo {
    private class par<E, D> {
        private E esq;
        private D dir;
        par(E e, D d) {
            esq = e;
            dir = d;
        }
        public boolean equals(par<E, D> p) {
            return esq.equals(p.esq) && dir.equals(p.dir);
        }
        
        public int hashCode() {
            return (esq == null ? 0 : esq.hashCode()) * 31 + (dir == null ? 0 : dir.hashCode());
        }
    }

    private HashSet<par<Integer, Integer>> matriz;
    private int width;
    private int height;

    public grafo(int w, int h) {
        this.width = w;
        this.height = h;

        matriz = new HashSet<par<Integer, Integer>>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (i != w-1)
                    matriz.add(new par<Integer, Integer>(i+j*w, i+1+j*w));

                if (j != h-1)
                    matriz.add(new par<Integer, Integer>(i+j*w, i+(j+1)*w));
            }
        }
    }

    private HashSet<par<HashSet<par<Integer, Integer>>, HashSet<Integer>>>

        kr_init() {
        int n = width*height;

        HashSet<par<HashSet<par<Integer, Integer>>, HashSet<Integer>>> p =
            new HashSet<par<HashSet<par<Integer, Integer>>, HashSet<Integer>>>(n);


        for (int i = 0; i < n; i++) {
            HashSet<par<Integer, Integer>> m = new HashSet<par<Integer, Integer>>();

            HashSet<Integer> v = new HashSet<Integer>();
            v.add(i);
            p.add(new par<HashSet<par<Integer, Integer>>, HashSet<Integer>>(m, v));
        }

        return p;
    }

    private par<HashSet<par<Integer, Integer>>, HashSet<Integer>>
        kr_busca (HashSet<par<HashSet<par<Integer, Integer>>, HashSet<Integer>>> floresta,
                  Integer vertice) {


        for (par<HashSet<par<Integer, Integer>>, HashSet<Integer>> e : floresta)
            if (e.dir.contains(vertice))
                return e;

        throw new RuntimeException("kr_busca");
    }

    private par<HashSet<par<Integer, Integer>>, HashSet<Integer>>
        kr_merge (par<HashSet<par<Integer, Integer>>, HashSet<Integer>> v1,
                  par<HashSet<par<Integer, Integer>>, HashSet<Integer>> v2,
                  par<Integer, Integer> aresta) {

        v1.esq.addAll(v2.esq);
        v1.dir.addAll(v2.dir);

        v1.esq.add(aresta);

        return v1;
    }

    private void
        kr_passo(HashSet<par<HashSet<par<Integer, Integer>>, HashSet<Integer>>> floresta,
                 par<Integer, Integer> aresta) {

        par<HashSet<par<Integer, Integer>>, HashSet<Integer>> v1 =
            kr_busca(floresta, aresta.esq);
        par<HashSet<par<Integer, Integer>>, HashSet<Integer>> v2 =
            kr_busca(floresta, aresta.dir);

        if (v1 != v2) {
            floresta.remove(v1);
            floresta.remove(v2);
            floresta.add(kr_merge(v1, v2, aresta));
        }
    }

    public void kruskal() {
        ArrayList<par<Integer, Integer>> a =
            new ArrayList<par<Integer, Integer>>(matriz);
        Collections.shuffle(a);

        HashSet<par<HashSet<par<Integer, Integer>>, HashSet<Integer>>> floresta = kr_init();

        for (par<Integer, Integer> i : a)
            if (floresta.size() > 1)
                kr_passo(floresta, i);

        matriz = floresta.iterator().next().esq;
    }

    public boolean[][] linhas_horizontais() {
        boolean[][] paredes = new boolean[width][height + 1];

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height+1; j++)
                paredes[i][j] = true;

        for (par<Integer, Integer> p : matriz) {
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

        for (par<Integer, Integer> p : matriz) {
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