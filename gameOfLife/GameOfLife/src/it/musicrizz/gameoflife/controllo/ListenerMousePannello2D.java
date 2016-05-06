/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.vista.PannelloScacchiera;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

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
        //TODO
        /*if(pannello.getPannello2D() == null)return;
        sistema = (Sistema)controllo.getModello().getBean(Costanti.SISTEMA);
        for(String s : pannello.getPannello2D().getMappaRett().keySet())   {
            if(pannello.getPannello2D().getMappaRett().get(s).contains(e.getPoint()))  {
                Rectangle2D r = pannello.getPannello2D().getMappaRett().get(s);
                if(sistema.getCellula((int)r.getY()/16, (int)r.getX()/16) == null ||
                     (sistema.getCellula((int)r.getY()/16, (int)r.getX()/16) != null &&
                          (!sistema.getCellula((int)r.getY()/16, (int)r.getX()/16).isStatoCorrente())))   {
                    sistema.addCellula((int)r.getY()/16, (int)r.getX()/16, true, false);
                    controllo.getModello().putBean(Controllo.MESSAGGIO_STATO, 
                                                    new MessaggioPing("Cellula creata in pos : "+(int)r.getY()/16+","+(int)r.getX()/16));
                    
                }else{
                    sistema.removeCellula((int)r.getY()/16, (int)r.getX()/16);
                    controllo.getModello().putBean(Controllo.MESSAGGIO_STATO, 
                                                    new MessaggioPing("Cellula uccisa in pos : "+(int)r.getY()/16+","+(int)r.getX()/16));
                }
            }
        }*/
        pannello.getPannello2D().ridisegna();
    }
    
    
}
