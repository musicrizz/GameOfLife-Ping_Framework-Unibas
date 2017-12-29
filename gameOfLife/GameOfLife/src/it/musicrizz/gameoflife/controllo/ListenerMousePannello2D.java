package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.vista.PannelloGraphics2D;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class ListenerMousePannello2D extends MouseAdapter  {
    
    private static Log log = LogFactory.getLog(ListenerMousePannello2D.class.getName());
    
    private PannelloGraphics2D pannello;
    private Sistema sistema;
    private Controllo controllo;
    private static Point init;
    private int x,y;
    
    public ListenerMousePannello2D (PannelloGraphics2D p,Controllo c)   {
        pannello = p;
        controllo = c;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(pannello == null)return;
        sistema = (Sistema)controllo.getModello().getBean(Costanti.SISTEMA);
        Point p = e.getPoint();
        x = (p.x - pannello.getWorldTranslateX())/16;
        y = (p.y - pannello.getWorldTranslateY())/16;
        if(x < 0)x--;
        if(y < 0)y--;
        if(sistema.isCellula(new Cellula(y, x)))   {
            sistema.removeCellula(y, x);
            controllo.getModello().putBean(Controllo.MESSAGGIO_STATO, 
                                                    new MessaggioPing(Bundle.getString(Costanti.B_MSG_CELL_RIMOSSA, y,x)));
        }else{
            sistema.addCellula(y, x);
            controllo.getModello().putBean(Controllo.MESSAGGIO_STATO, 
                                                    new MessaggioPing(Bundle.getString(Costanti.B_MSG_NUOVA_CELL, y,x)));
        }
        pannello.ridisegna();
    }  

    @Override
    public void mousePressed(MouseEvent e) {
        init = e.getPoint(); 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point translate = new Point();
        translate.x = e.getPoint().x - init.x;
        translate.y = e.getPoint().y - init.y;
        init = e.getPoint();
        pannello.addWorldTranslateX(translate.x);
        pannello.addworldTranslateY(translate.y);
        pannello.ridisegna();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
            if(pannello.contains(e.getPoint()))   {
                pannello.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }else{
                pannello.setCursor(Cursor.getDefaultCursor());
            }
        }
    
}
