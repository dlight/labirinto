import javax.swing.JFrame;

public class app extends JFrame {
    static int tela_w = 420;
    static int tela_h = 320;
    static int casas_w = 40;
    static int casas_h = 30;
    static int borda = 10;

    public app() {
        add(new tela(borda, tela_w, tela_h, casas_w, casas_h));
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Labirinto");
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        grafo g = new grafo(2, 3);
        System.out.printf("Antes:\n\n");
        g.print2();
        g.kruskal();
        System.out.printf("\nDepois:\n\n");
        g.print2();

        //new app();
    }
}