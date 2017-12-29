package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.persistenza.DAOException;
import it.musicrizz.gameoflife.persistenza.DAOMondoSQL;
import it.musicrizz.gameoflife.persistenza.IDBroker;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import it.unibas.ping.annotazioni.IconaSwing;
import java.util.EventObject;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

@IconaSwing(Costanti.ICONA_BOTTONE_SAVE_DB)
public class AzioneSalvaDB extends AzionePingAstratta   {
    
    private Log log = LogFactory.getLog(AzioneSalvaDB.class);
    private FramePrincipale frame ;
    private Sistema sistema;
    
    @Override
    public void esegui(EventObject eo) {
        String nome = "";
        boolean continua = false;
        
        do{
            nome = JOptionPane.showInputDialog(frame, "", Bundle.getString(Costanti.SELEZIONE_NOME), JOptionPane.QUESTION_MESSAGE);
            if(nome == null)return;
            if(nome.length() == 0)  {
                JOptionPane.showMessageDialog(frame, Bundle.getString(Costanti.SELEZIONE_NOME_FAIL), "OPS!!", JOptionPane.INFORMATION_MESSAGE);
                continua = true;
            }else{
                try{
                    if(DAOMondoSQL.doSelectLookupNome(nome))   {
                        JOptionPane.showMessageDialog(frame, Bundle.getString(Costanti.NOME_ESISTENTE), "OPS!!", JOptionPane.INFORMATION_MESSAGE);
                        continua = true;
                    }else{
                        continua = false;
                    }
                }catch(DAOException daoe)   {
                    log.error("ecczione lookup Nome", daoe);
                    JOptionPane.showMessageDialog(frame, daoe.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }while(continua);
        
        try{
            long id = IDBroker.getInstance().getId();
            DAOMondoSQL.doInsertMondo(id, nome, sistema.getMondo());
            JOptionPane.showMessageDialog(frame, Bundle.getString(Costanti.SALVATAGGIO_DB_OK), "OPS!!", JOptionPane.INFORMATION_MESSAGE);        
        }catch(DAOException daoe)   {
            log.error(daoe);
            JOptionPane.showMessageDialog(frame, Bundle.getString(Costanti.SALVATAGGIO_DB_FAIL,daoe), "OPS!!", JOptionPane.ERROR_MESSAGE); 
        }
        
        
    }     

    @Override
    public boolean abilita(Integer statusId) {
        if(statusId != Costanti.STATO_INIZIALE || statusId != Costanti.STATO_START_TIMER)return true;
        return false;
    }

    @Override
    public boolean disabilita(Integer statusId) {
        if(Costanti.STATO_START_TIMER == statusId || Costanti.STATO_INIZIALE == statusId)return true;
        return false;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(FramePrincipale frame) {
        this.frame = frame;
    }

    /**
     * @param sistema the sistema to set
     */
    @Inietta(Costanti.SISTEMA)
    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }
    

}
