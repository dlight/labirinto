import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Tela extends JPanel implements ActionListener {
    Timer timer;
    int tela_w, tela_h;
    int grafo_w, grafo_h;

    int passo_x;

    int y;

    public Tela(int tela_largura, int tela_altura,
                int grafo_largura, int grafo_altura) {
        tela_w = tela_largura;
        tela_h = tela_altura;
        grafo_w = grafo_largura;
        grafo_h = grafo_altura;

        passo_x = tela_largura / grafo_largura;

        y = 0;

        setBackground(Color.black);
        setDoubleBuffered(true);

        timer = new Timer(25, this);
        timer.start();
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);

        for (int i = 0; i < tela_w; i += passo_x) {
            g.drawLine(i, 0, i, tela_h);
        }

        g.setColor(Color.red);

        g.drawLine(0, y, tela_w, y);
    }


    public void paint(Graphics tela) {
        super.paint(tela);

        draw((Graphics2D) tela);

        Toolkit.getDefaultToolkit().sync();
        tela.dispose();
    }


    public void actionPerformed(ActionEvent e) {
        y += 1;

        if (y > tela_h)
            y = 0;

        repaint();  
    }
}