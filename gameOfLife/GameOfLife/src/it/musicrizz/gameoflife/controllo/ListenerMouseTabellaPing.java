/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.framework.Applicazione;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.Modello;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.Sistema;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class ListenerMouseTabellaPing extends MouseAdapter{
    
    private Modello m = Applicazione.getInstance().getModello();
    
    @Override
    public void mouseClicked(MouseEvent e) {
            Object o = e.getSource();
            JTable tabella = (JTable)o;
            int px = tabella.rowAtPoint(e.getPoint());
            int py = tabella.columnAtPoint(e.getPoint());
            tabella.getSelectionModel().setLeadSelectionIndex(px);
            tabella.getColumnModel().getSelectionModel().setLeadSelectionIndex(py);
            int x = tabella.getSelectedRow();
            int y = tabella.getSelectedColumn();
            Sistema s = (Sistema)m.getBean(Costanti.SISTEMA);
            if((s.getCellula(x, y) == null) || ((s.getCellula(x, y) != null)&& (!s.getCellula(x, y).isStatoCorrente())))   {
                s.addCellula(tabella.getSelectedRow(), tabella.getSelectedColumn(), true, false);
                m.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Cellula creata in pos : "+tabella.getSelectedRow()+","+tabella.getSelectedColumn()));
            }else{
                s.removeCellula(tabella.getSelectedRow(), tabella.getSelectedColumn());
                m.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Cellula uccisa in pos : "+tabella.getSelectedRow()+","+tabella.getSelectedColumn()));
            }
     }
}
    
