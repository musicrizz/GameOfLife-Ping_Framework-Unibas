package it.musicrizz.gameoflife.vista;

import it.musicrizz.gameoflife.controllo.ListenerMouseMove;
import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.Sistema;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class PannelloGraphics2D extends JPanel{
    
    private Sistema sistema;
    private int righe;
    private int colonne;
    private boolean paint;
    
    public PannelloGraphics2D(int w,int h,Sistema s)   {
        sistema = s;
        righe = h;
        colonne = w;
        setSize(w*16,h*16);
        setMinimumSize(new Dimension(w*16, h*16));
        setBackground(Color.BLUE);
        addMouseMotionListener(new ListenerMouseMove(this));
        paint = false;
    }
    
    public void enablePainting()   {
        paint = true;
    }
    
    public void disablePainting()   {
        paint = false;
    }
       
    @Override
    public void paintComponent(Graphics g)   {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(Color.BLACK);
        g2.draw(new Rectangle2D.Double(0, 0, colonne*16, righe*16));
        g2.draw(new Rectangle2D.Double(0, 0, colonne*16+2, righe*16+2));
        if(!paint)return;
        Rectangle2D r = null;
        for(Cellula cell : sistema.getMondoMatrice())   {
            r = new Rectangle2D.Double(cell.getY()*16, cell.getX()*16, 15, 15);
            Ellipse2D e = new Ellipse2D.Double();
            e.setFrame(r);
            g2.setPaint(Color.BLUE);
            g2.draw(r);
            g2.setPaint(Color.GREEN);
            g2.fill(e);
        }
    }
    
    public void ridisegna()   {
        repaint();
    }
    
    public void abilitaMouseListener(MouseListener m)   {
        addMouseListener(m);
    }
    
    public void disabilitaMouseListener(MouseListener m)   {
        removeMouseListener(m);
    }
}
