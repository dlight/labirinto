import javax.swing.JFrame;

public class Labirinto extends JFrame {
    static int tela_w = 400;
    static int tela_h = 300;
    static int grafo_w = 4;
    static int grafo_h = 3;

    public Labirinto() {
        add(new Tela(tela_w, tela_h, grafo_w, grafo_h));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(tela_w, tela_h);
        setLocationRelativeTo(null);
        setTitle("Labirinto");
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Labirinto();
    }
}