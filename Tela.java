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
    int width, height;

    int x;

    public Tela(int w, int h) {
        width = w;
        height = h;
        x = 0;

        setBackground(Color.black);
        setDoubleBuffered(true);

        timer = new Timer(25, this);
        timer.start();
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.blue);

        g.drawLine(x, 0, x, height);
    }


    public void paint(Graphics tela) {
        super.paint(tela);

        draw((Graphics2D) tela);

        Toolkit.getDefaultToolkit().sync();
        tela.dispose();
    }


    public void actionPerformed(ActionEvent e) {
        x += 1;

        if (x > width)
            x = 0;

        repaint();  
    }
}