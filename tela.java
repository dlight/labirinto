import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Dimension;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class tela extends JPanel implements ActionListener {
    Timer timer;
    int borda;
    int tela_largura, tela_altura;

    int passo_x, passo_y;

    boolean[][] linhas_horizontais;
    boolean[][] linhas_verticais;

    public tela(int borda, int tela_w, int tela_h, int casas_w, int casas_h,
                boolean[][] linhas_horizontais, boolean[][] linhas_verticais) {
        tela_largura = tela_w;
        tela_altura = tela_h;

        this.linhas_horizontais = linhas_horizontais;
        this.linhas_verticais = linhas_verticais;

        this.borda = borda;

        passo_x = (tela_largura - 2*borda) / casas_w;
        passo_y = (tela_altura - 2*borda) / casas_h;

        setPreferredSize(new Dimension(tela_w, tela_h));
        setBackground(Color.white);
        setDoubleBuffered(true);

        //timer = new Timer(1000, this);
        //timer.start();
    }

    public void desenhar_linha(Graphics2D g, int x0, int y0, int x1, int y1) {
        g.drawLine(borda + x0 * passo_x,
                   borda + y0 * passo_y,
                   borda + x1 * passo_x,
                   borda + y1 * passo_y);
    }

    public void desenhar_linhas(Graphics2D g, boolean horizontal, boolean[][] m) { 
        g.setColor(Color.black);

        for (int x = 0; x < m.length; x++)
            for (int y = 0; y < m[x].length; y++)
                if (horizontal && m[x][y])
                    desenhar_linha(g, x, y, x+1, y);
                else if (!horizontal && m[x][y])
                    desenhar_linha(g, x, y, x, y+1);
    }

    public void desenhar(Graphics2D g) {
        desenhar_linhas(g, true, linhas_horizontais);
        desenhar_linhas(g, false, linhas_verticais);
    }

    public void paint(Graphics tela) {
        super.paint(tela);

        desenhar((Graphics2D) tela);

        Toolkit.getDefaultToolkit().sync();
        tela.dispose();
     }

    public void actionPerformed(ActionEvent e) {
        repaint();  
    }
}