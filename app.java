import javax.swing.JFrame;

public class app extends JFrame {
    static int tela_w = 420;
    static int tela_h = 320;
    static int casas_w = 40;
    static int casas_h = 30;
    static int borda = 10;

    public app(grafo g) {
        add(new tela(borda, tela_w, tela_h, casas_w, casas_h,
                     g.linhas_horizontais(), g.linhas_verticais()));
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Labirinto");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        grafo g = new grafo(casas_w, casas_h);
        g.kruskal();

        new app(g);
    }
}