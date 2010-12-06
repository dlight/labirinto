/* Trabalho final: AED2 e PAED2 2010.2
 *
 * Labirinto
 *
 * Alunos: Elias Gabriel Amaral da Silva
 *         Flávio Fernando Vasconcelos
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.HashSet;


public class app extends JFrame {
    int tela_w = 824;
    int tela_h = 624;
    int casas_w = 8;
    int casas_h = 6;
    int borda = 12;

    static int sleep = 80;

    private class size {
        public int w, h;
        public String str;

        size(int w, int h, String str) {
            this.w = w; this.h = h; this.str = str;
        }

        public String toString() { return str; }
    }

    private class size_listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            size p = (size)cb.getSelectedItem();
            reiniciar_size(p.w, p.h);
        }
    }

    private class button_listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            reiniciar_size(casas_w, casas_h);
        }
    }

    tela desenho;
    JPanel scr;

    private class thr implements Runnable {
        tela t;
        grafo<par<Integer, Integer>, par<HashSet<par<Integer, Integer>>, HashSet<Integer>>> g;
        thr(tela t,
            grafo<par<Integer, Integer>, par<HashSet<par<Integer, Integer>>, HashSet<Integer>>> g)
        { this.t = t; this.g = g; } 

        public void run() { 
            g.profundidade(t);
        }
    }

    private void reiniciar_size(int w, int h) {
        casas_w = w;
        casas_h = h;

        if (desenho != null)
            scr.remove(desenho);

        grafo<par<Integer, Integer>, par<HashSet<par<Integer, Integer>>, HashSet<Integer>>> g =

            new grafo<par<Integer, Integer>,
            par<HashSet<par<Integer, Integer>>, HashSet<Integer>>>(casas_w, casas_h);

        g.kruskal();

        desenho = new tela(borda, tela_w, tela_h, casas_w, casas_h,
                          g.linhas_horizontais(), g.linhas_verticais(), sleep);

        scr.add(desenho);
        scr.validate();

        new Thread(new thr(desenho, g)).start();
    }

    public app() {

        scr = new JPanel();

        JPanel q = new JPanel();

        q.setLayout(new BoxLayout(q, BoxLayout.Y_AXIS));

        JComboBox j = new JComboBox();
        j.addItem(new size(8, 6, "mínimo"));
        j.addItem(new size(20, 15, "pequeno"));
        j.addItem(new size(40, 30, "grande"));
        j.addActionListener(new size_listener());

        JButton b = new JButton("Reiniciar");
        b.addActionListener(new button_listener());


        q.add(b);
        q.add(j);

        scr.add(q);

        reiniciar_size(casas_w, casas_h);
        scr.add(desenho);

        add(scr);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Labirinto");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new app();
    }
}