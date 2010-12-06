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
import javax.swing.JSlider;

import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.HashSet;


public class app extends JFrame {
    int tela_w;
    int tela_h;;
    int casas_w = 8;
    int casas_h = 6;
    int borda = 12;

    int min_tm = 3;
    int max_tm = 600;
    int def_tm = 150;

    tela desenho;
    JPanel scr;

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

    private class slider_listener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            var.sleep = max_tm - ((JSlider) e.getSource()).getValue() + min_tm;
        }
    }

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
                          g.linhas_horizontais(), g.linhas_verticais());

        scr.add(desenho);
        scr.validate();

        new Thread(new thr(desenho, g)).start();
    }

    public app(int w, int h) {
        tela_w = w;
        tela_h = h;

        scr = new JPanel();

        JPanel q = new JPanel();

        q.setLayout(new GridLayout(3, 1, 5, 10));

        JComboBox j = new JComboBox();
        j.addItem(new size(8, 6, "pequeno"));
        j.addItem(new size(20, 15, "médio"));
        j.addItem(new size(40, 30, "grande"));
        j.addActionListener(new size_listener());

        JButton b = new JButton("Reiniciar");

        JSlider slider = new JSlider(min_tm, max_tm, max_tm - def_tm);
        var.sleep = def_tm;

        slider.addChangeListener(new slider_listener());

        b.setMinimumSize(new Dimension(500, 500));

        b.addActionListener(new button_listener());

        q.add(slider);
        q.add(j);
        q.add(b);

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
        if (args.length >= 1 && args[0].equals("lap"))
            new app(664, 504);
        else if (args.length >= 1 && args[0].equals("mini"))
            new app(224, 174);
        else
            new app(824, 624);
    }
}