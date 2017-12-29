package it.musicrizz.gameoflife.vista;

import it.musicrizz.gameoflife.controllo.ListenerMousePannello2D;
import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.Sistema;
import it.unibas.ping.framework.Controllo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class PannelloGraphics2D extends JPanel{
    
    private Sistema sistema;
    private int righe;
    private int colonne;
    private int worldTranslateX, worldTranslateY;
    private boolean paint;
    private ListenerMousePannello2D mouseListener;
    
    public PannelloGraphics2D(int w, int h, Sistema s, Controllo c)   {
        sistema = s;
        righe = h;
        colonne = w;
        worldTranslateX = 0;
        worldTranslateY = 0;
        setSize(w*16,h*16);
        setMinimumSize(new Dimension(w*16, h*16));
        setBackground(Color.BLUE);
        mouseListener = new ListenerMousePannello2D(this, c);
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
        g2.translate(worldTranslateX, worldTranslateY);
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
    
    public void abilitaMouseListener()   {
        addMouseMotionListener(mouseListener);
        addMouseListener(mouseListener);
        
       // addMouseListener();
    }
    
    public void disabilitaMouseListener()   {
        removeMouseMotionListener(mouseListener);
        removeMouseListener(mouseListener);
    }

    /**
     * @return the worldTranslateX
     */
    public int getWorldTranslateX() {
        return worldTranslateX;
    }

    /**
     * @param worldTranslateX the worldTranslateX to set
     */
    public void setWorldTranslateX(int worldTranslateX) {
        this.worldTranslateX = worldTranslateX;
    }
    public void addWorldTranslateX(int Xoffset) {
        this.worldTranslateX += Xoffset;
    }

    /**
     * @return the worldTranslateY
     */
    public int getWorldTranslateY() {
        return worldTranslateY;
    }

    /**
     * @param worldTranslateY the worldTranslateY to set
     */
    public void setWorldTranslateY(int worldTranslateY) {
        this.worldTranslateY = worldTranslateY;
    }
    public void addworldTranslateY(int Yoffset) {
        this.worldTranslateY += Yoffset;
    }
}
