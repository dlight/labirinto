import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Dimension;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;

public class Tela extends JPanel implements ActionListener {
    Timer timer;
    int tela_largura, tela_altura;
    int grafo_largura, grafo_altura;

    int passo_x, passo_y;

    boolean[][] labirinto_x;
    boolean[][] labirinto_y;

    Random rnd;

    public Tela(int tela_w, int tela_h, int grafo_w, int grafo_h) {
        tela_largura = tela_w;
        tela_altura = tela_h;

        grafo_largura = grafo_w;
        grafo_altura = grafo_h;

        passo_x = tela_largura / grafo_largura;
        passo_y = tela_altura / grafo_altura;

        labirinto_x = new boolean[grafo_w][grafo_h];
        labirinto_y = new boolean[grafo_w][grafo_h];
        rnd = new Random();

        novo_labirinto();

        setPreferredSize(new Dimension(tela_w, tela_h));
        setBackground(Color.white);
        setDoubleBuffered(true);

        timer = new Timer(1000, this);
        timer.start();
    }

    public void desenhar(Graphics2D g) {
        g.setColor(Color.black);

        for (int x = 0; x < grafo_largura; x++) {
            for (int y = 0; y < grafo_altura; y++) {
                if (labirinto_x[x][y])
                    g.drawLine(x * passo_x,
                               y * passo_y,
                               x * passo_x,
                               (y+1) * passo_y);

                if (labirinto_y[x][y])
                    g.drawLine(x * passo_x,
                               y * passo_y,
                               (x+1) * passo_x,
                               y * passo_y);
            }
        }
    }


    public void paint(Graphics tela) {
        super.paint(tela);

        desenhar((Graphics2D) tela);

        Toolkit.getDefaultToolkit().sync();
        tela.dispose();
    }

    public void novo_labirinto() {
        for (int x = 0; x < grafo_largura; x++) {
            for (int y = 0; y < grafo_altura; y++) {
                labirinto_x[x][y] = rnd.nextBoolean();
                labirinto_y[x][y] = rnd.nextBoolean();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        novo_labirinto();
        repaint();  
    }
}