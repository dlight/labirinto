import java.util.ArrayList;
import java.util.Collections;

public class grafo {
    private class par<E, D> {
        private E esq;
        private D dir;
        par(E e, D d) {
            esq = e;
            dir = d;
        }
    }

    private boolean[][] matriz;
    private int width;
    private int height;

    public grafo(int w, int h) {
        this.width = w;
        this.height = h;

        int n = w*h;

        matriz = new boolean[n][n];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (i != w-1)
                    matriz[i+j*w][i+1+j*w] = true;

                if (j != h-1)
                    matriz[i+j*w][i+(j+1)*w] = true;
            }
        }
    }

   private ArrayList<par<Integer, Integer>> arestas() {
        ArrayList<par<Integer, Integer>> a = new ArrayList<par<Integer, Integer>>();

        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz.length; j++)
                if (matriz[i][j])
                    a.add(new par<Integer, Integer>(i, j));

        return a;
    }

    private ArrayList<par<boolean[][], boolean[]>> kr_init() {
        ArrayList<par<boolean[][], boolean[]>> p =
            new ArrayList<par<boolean[][], boolean[]>>();

        int n = matriz.length;

        for (int i = 0; i < n; i++) {
            boolean[] v = new boolean[n];
            v[i] = true;

            p.add(new par<boolean[][], boolean[]>(new boolean[n][n], v));
        }

        return p;
    }

    private par<boolean[][], boolean[]>
        kr_busca (ArrayList<par<boolean[][], boolean[]>> floresta,
                  int vertice) {
        for (int i = 0; i < floresta.size(); i++)
            if (floresta.get(i).dir[vertice])
                return floresta.get(i);

        throw new RuntimeException("kr_busca");
    }

    private par<boolean[][], boolean[]>
        kr_merge(par<boolean[][], boolean[]> v1,
                 par<boolean[][], boolean[]> v2,
                 par<Integer, Integer> aresta) {
        
        for (int i = 0; i < v2.esq.length; i++)
            for (int j = 0; j < v2.esq[i].length; j++)
                if (v2.esq[i][j])
                    v1.esq[i][j] = true;
        
        for (int i = 0; i < v2.dir.length; i++)
            if (v2.dir[i])
                v1.dir[i] = true;

        v1.esq[aresta.esq][aresta.dir] = true;

        return v1;
    }

    private void kr_passo(ArrayList<par<boolean[][], boolean[]>> floresta,
              par<Integer, Integer> aresta) {

        par<boolean[][], boolean[]> v1 = kr_busca(floresta, aresta.esq);
        par<boolean[][], boolean[]> v2 = kr_busca(floresta, aresta.dir);
        
        if (v1 != v2) {
            floresta.remove(v1);
            floresta.remove(v2);
            floresta.add(kr_merge(v1, v2, aresta));
        }
    }

    public void kruskal() {
        ArrayList<par<Integer, Integer>> a = arestas();
        Collections.shuffle(a);

        ArrayList<par<boolean[][], boolean[]>> floresta = kr_init();

        for (par<Integer, Integer> i : a) {
            kr_passo(floresta, i);
        }

        matriz = floresta.get(0).esq;
    }

    public void print() {
        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++)
                for (int l = 0; l < height; l++)
                    for (int k = 0; k < width; k++)
                        if (matriz[k+l*width][i+j*width])
                            System.out.printf("(%d, %d) -> (%d, %d)\n", k, l, i, j);
    }

    public void print2() {
        for (par <Integer, Integer> p : arestas())
            System.out.printf("%d -> %d\n", p.esq, p.dir);
    }
}