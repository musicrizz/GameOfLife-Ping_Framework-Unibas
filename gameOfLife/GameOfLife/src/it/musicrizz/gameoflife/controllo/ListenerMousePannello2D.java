package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.vista.PannelloScacchiera;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Musicrizz
 */
public class ListenerMousePannello2D extends MouseAdapter  {
    
    private PannelloScacchiera pannello;
    private Sistema sistema;
    private Controllo controllo;
    
    public ListenerMousePannello2D (PannelloScacchiera p,Controllo c)   {
        pannello = p;
        controllo = c;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(pannello.getPannello2D() == null)return;
        sistema = (Sistema)controllo.getModello().getBean(Costanti.SISTEMA);
        Point p = e.getPoint();
        int x = p.x/16;
        int y = p.y/16;
        if(sistema.isCellula(new Cellula(y, x)))   {
            sistema.removeCellula(y, x);
            controllo.getModello().putBean(Controllo.MESSAGGIO_STATO, 
                                                    new MessaggioPing(Bundle.getString(Costanti.B_MSG_CELL_RIMOSSA, y,x)));
        }else{
            sistema.addCellula(y, x);
            controllo.getModello().putBean(Controllo.MESSAGGIO_STATO, 
                                                    new MessaggioPing(Bundle.getString(Costanti.B_MSG_NUOVA_CELL, y,x)));
        }
        pannello.getPannello2D().ridisegna();
    }  
}
