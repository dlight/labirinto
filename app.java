/* Trabalho final: AED2 e PAED2 2010.2
 *
 * Labirinto
 *
 * Alunos: Elias Gabriel Amaral da Silva
 *         Flávio Fernando Vasconcelos
 */

import javax.swing.JFrame;


import java.util.HashSet;

public class app extends JFrame {
    static int tela_w = 420;
    static int tela_h = 320;
    static int casas_w = 4;
    static int casas_h = 3;
    static int borda = 10;

    public app() {
        grafo<par<Integer, Integer>, par<HashSet<par<Integer, Integer>>, HashSet<Integer>>> g =
            new grafo<par<Integer, Integer>,
            par<HashSet<par<Integer, Integer>>, HashSet<Integer>>>(casas_w, casas_h);

        g.kruskal();

        tela t = new tela(borda, tela_w, tela_h, casas_w, casas_h,
                        g.linhas_horizontais(), g.linhas_verticais());

        add(t);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Labirinto");
        setResizable(false);
        setVisible(true);

        g.profundidade(t);
    }

    public static void main(String[] args) {
        new app();
    }
}