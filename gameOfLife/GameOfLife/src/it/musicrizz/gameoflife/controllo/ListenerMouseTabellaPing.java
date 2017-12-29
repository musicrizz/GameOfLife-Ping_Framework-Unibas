package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
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
            if(!s.isCellula(new Cellula(x, y)))   {
                s.addCellula(tabella.getSelectedRow(), tabella.getSelectedColumn());
                m.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing(Bundle.getString(Costanti.B_MSG_NUOVA_CELL, tabella.getSelectedRow(),tabella.getSelectedColumn())));
            }else{
                s.removeCellula(tabella.getSelectedRow(), tabella.getSelectedColumn());
                m.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing(Bundle.getString(Costanti.B_MSG_CELL_RIMOSSA, tabella.getSelectedRow(),tabella.getSelectedColumn())));
            }
     }
}
    
