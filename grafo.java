public class grafo {
    private boolean[][] matriz;
    private int width;
    private int height;

    private int w(int n) { return n / width; }
    private int h(int n) { return n % width; }

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

    public void print() {
        for (int j = 0; j < height; j++)
            for (int i = 0; i < width; i++)
                for (int l = 0; l < height; l++)
                    for (int k = 0; k < width; k++)
                        if (matriz[k+l*width][i+j*width])
                            System.out.printf("(%d, %d) -> (%d, %d)\n", k, l, i, j);
    }
}