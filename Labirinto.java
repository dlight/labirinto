import javax.swing.JFrame;

public class Labirinto extends JFrame {

    public Labirinto() {
        add(new Tela(280, 240));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(280, 240);
        setLocationRelativeTo(null);
        setTitle("Labirinto");
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Labirinto();
    }
}