/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.Language;
import it.musicrizz.gameoflife.persistenza.ConfigurazioneDatabase;
import it.musicrizz.gameoflife.persistenza.DAOMondoSQL;
import it.musicrizz.gameoflife.persistenza.Descrizione;
import it.musicrizz.gameoflife.vista.FinestraSceltaMondiDataBase;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.util.EventObject;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
@NomeSwing(Language.FRAME_P_TEXT_MENU_OPEN_DB_IT)
@DescrizioneSwing(Language.FRAME_P_TOOLTIP_MENU_SAVE_DB_IT)
public class AzioneFinestraCaricaDB extends AzionePingAstratta   {
    
    private Log log = LogFactory.getLog(AzioneFinestraCaricaDB.class.getName());
    private FinestraSceltaMondiDataBase finestraSceltaMondiDataBase;
    private FramePrincipale framePrincipale;
    
    @Override
    public void esegui(EventObject eo) {
        List<Descrizione> lista;
        try{
            lista = DAOMondoSQL.doSelectAllMondiDescrizione();
            log.debug("Lista descrizione mondi carica ta - size -> "+lista.size());
            if(lista.size() == 0)   {
                /*if(framePrincipale.isRadioMenuIT())   {
                    JOptionPane.showMessageDialog(finestraSceltaMondiDataBase, Language.TEXT_DIALOG_CARICA_DB_IT, "OPS !!", JOptionPane.INFORMATION_MESSAGE);
                }else if(framePrincipale.isRadioMenuEN())   {
                    JOptionPane.showMessageDialog(finestraSceltaMondiDataBase, Language.TEXT_DIALOG_CARICA_DB_EN, "OPS !!", JOptionPane.INFORMATION_MESSAGE);
                }*/
            }else{
                finestraSceltaMondiDataBase.initJList(lista);
                finestraSceltaMondiDataBase.visualizza();
            }
        }catch(Exception e)    {
            log.error(e);
        }
    }

    @Override
    public boolean abilita(Integer statusId) {
        if((ConfigurazioneDatabase.getInstance().isEnableDB()) ||
           (statusId != Costanti.STATO_INIZIALE))return true;
        return false;
    }

    @Override
    public boolean disabilita(Integer statusId) {
        if((!ConfigurazioneDatabase.getInstance().isEnableDB()) ||
           (Costanti.STATO_START_TIMER == statusId))return true;
        return false;
    }

    /**
     * @param finestraSceltaMondiDataBase the finestraSceltaMondiDataBase to set
     */
    public void setFinestraSceltaMondiDataBase(FinestraSceltaMondiDataBase finestraSceltaMondiDataBase) {
        this.finestraSceltaMondiDataBase = finestraSceltaMondiDataBase;
    }

    /**
     * @param framePrincipale the framePrincipale to set
     */
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }
}
