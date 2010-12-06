/* Trabalho final: AED2 e PAED2 2010.2
 *
 * Labirinto
 *
 * Alunos: Elias Gabriel Amaral da Silva
 *         Flávio Fernando Vasconcelos
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Dimension;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class tela extends JPanel implements gancho {
    int borda;
    int tela_largura, tela_altura;

    int passo_x, passo_y;

    boolean[][] linhas_horizontais;
    boolean[][] linhas_verticais;

    String[][] estado;

    int tm_sleep;

    public tela(int borda, int tela_w, int tela_h, int casas_w, int casas_h,
                boolean[][] linhas_horizontais, boolean[][] linhas_verticais, int tm_sleep) {
        tela_largura = tela_w;
        tela_altura = tela_h;

        this.tm_sleep = tm_sleep;

        this.linhas_horizontais = linhas_horizontais;
        this.linhas_verticais = linhas_verticais;

        this.borda = borda;

        passo_x = (tela_largura - 2*borda) / casas_w;
        passo_y = (tela_altura - 2*borda) / casas_h;

        setPreferredSize(new Dimension(tela_w, tela_h));
        setBackground(Color.white);
        setDoubleBuffered(true);
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

    private void des_round(Graphics2D g, Color c, int i, int j, int s, int r) {
        g.setColor(c);

        int p_x = passo_x < s ? 1 : passo_x / s;
        int p_y = passo_y < s ? 1 : passo_y / s;

        g.fillRoundRect(borda + p_x + i*passo_x,
                        borda + p_y + j*passo_y,
                        passo_x - 2*p_x,
                        passo_y - 2*p_y,
                        r, r);
    }

    private void des_rect(Graphics2D g, Color c, int i, int j, int s) {
        g.setColor(c);

        int p_x = passo_x < s ? 1 : passo_x / s;
        int p_y = passo_y < s ? 1 : passo_y / s;

        g.fillRect(borda + p_x + i*passo_x,
                   borda + p_y + j*passo_y,
                   passo_x - 2*p_x,
                   passo_y - 2*p_y);
    }

    public void desenhar_estado(Graphics2D g) {
        if (estado == null)
            return;

        for (int i = 0; i < estado.length; i++) {
            for (int j = 0; j < estado[i].length; j++) {
                if (estado[i][j] == null)
                    g.setColor(Color.white);
                else if (estado[i][j].equals("topo"))
                    des_round(g, Color.red, i, j, 12, 5);
                else if (estado[i][j].equals("caminho"))
                    des_round(g, Color.magenta, i, j, 6, 10);
                else if (estado[i][j].equals("lixo"))
                    des_rect(g, new Color(230, 230, 230), i, j, 4);
                else if (estado[i][j].equals("fim"))
                    des_round(g, Color.cyan, i, j, 12, 5);
            }
        }
    }

    public void desenhar(Graphics2D g) {
        desenhar_linhas(g, true, linhas_horizontais);
        desenhar_linhas(g, false, linhas_verticais);
        desenhar_estado(g);
    }

    public void paint(Graphics tela) {
        super.paint(tela);

        desenhar((Graphics2D) tela);

        Toolkit.getDefaultToolkit().sync();
        tela.dispose();
     }

    static private String[][] array_copy(String[][] a) {
        String[][] r = new String[a.length][a[0].length];

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                r[i][j] = a[i][j];

        return r;
    }

    public void callback(String[][] m) {
        estado = array_copy(m);
        repaint();
        try {
            Thread.sleep(tm_sleep);
        }
        catch(InterruptedException ie) {
            System.out.println("Ahn?\n");
        }
    }
}