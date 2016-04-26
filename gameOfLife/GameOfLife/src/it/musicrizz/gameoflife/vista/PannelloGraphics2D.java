/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.musicrizz.gameoflife.vista;

import it.musicrizz.gameoflife.controllo.ListenerMouseMove;
import it.musicrizz.gameoflife.modello.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Sistema;
import java.awt.Color;
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
 * @author Musicrizz
 */
public class PannelloGraphics2D extends JPanel{
    
    private Sistema sistema;
    private int righe;
    private int colonne;
    private Map<String,Rectangle2D.Double> mappa;
    
    public PannelloGraphics2D(int w,int h,Sistema s)   {
        sistema = s;
        righe = h;
        colonne = w;
        setSize(w*16,h*16);
        setBackground(Color.BLUE);
        addMouseMotionListener(new ListenerMouseMove(this));
    }
    
    public void initMappa()   {
        mappa = new HashMap<String, Rectangle2D.Double>();
         for(int i=0;i<righe;i++)   {
            for(int j=0;j<colonne;j++)   {
                mappa.put(i+","+j, new Rectangle2D.Double(j*16, i*16, 15, 15));
            }   
         }
    }
    
    @Override
    public void paintComponent(Graphics g)   {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(Color.BLACK);
        g2.draw(new Rectangle2D.Double(0, 0, colonne*16, righe*16));
        g2.draw(new Rectangle2D.Double(0, 0, colonne*16+2, righe*16+2));
        if(mappa == null)return;
        for(String s : mappa.keySet())   {
            Rectangle2D r = mappa.get(s);
            g2.setPaint(Color.BLUE);
            g2.draw(r);
            
            if(sistema.getCellula((int)r.getY()/16,(int)r.getX()/16) == null || 
              (sistema.getCellula((int)r.getY()/16,(int)r.getX()/16) != null && 
                    !sistema.getCellula((int)r.getY()/16,(int)r.getX()/16).isStatoCorrente()))   {
                    continue;
                }
                Ellipse2D e = new Ellipse2D.Double();
                e.setFrame(r);
                g2.setPaint(Color.GREEN);
                g2.fill(e);
            }
    }
    
    public Map<String,Rectangle2D.Double> getMappaRett()   {
        return this.mappa;
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
