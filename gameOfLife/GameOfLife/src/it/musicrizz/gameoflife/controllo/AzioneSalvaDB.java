package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.persistenza.ConfigurazioneDatabase;
import it.musicrizz.gameoflife.persistenza.DAOMondoSQL;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.util.EventObject;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class AzioneSalvaDB extends AzionePingAstratta   {
    
    private Log log = LogFactory.getLog(AzioneSalvaDB.class);
    private FramePrincipale frame ;
    private Sistema sistema;
    
    @Override
    public void esegui(EventObject eo) {
        boolean continua = true;
        String nome = null;;
        while(continua)   {
            
            if(nome == null) return;
            if(nome.isEmpty())   {
       
            }else{
                continua = false;
            }
        }
         try{
             boolean trovato = DAOMondoSQL.doSelectLookUp(nome);
             log.debug("AzioneSalvaDB LookUp trovato -> "+trovato);
             if(!trovato)   {
                 DAOMondoSQL.doInsertConfigurazioneMondo(nome);
                 log.debug("Configurazione Mondo Inserita");
                 //TODO
                 /*for(Cellula c : sistema.getListaCellule())   {
                     DAOMondoSQL.doInsertCellula(c, nome);
                 }*/
                 log.debug("Cellule Inserite");
                 //if(frame.isRadioMenuIT())JOptionPane.showMessageDialog(frame, Language.TEXT_DIALOG_SAVE_DB_MSG_OK_IT);
                 //if(frame.isRadioMenuEN())JOptionPane.showMessageDialog(frame, Language.TEXT_DIALOG_SAVE_DB_MSG_OK_EN);
             }else{
                 //if(frame.isRadioMenuIT())JOptionPane.showMessageDialog(frame, Language.TEXT_DIALOG_SAVE_DB_MSG_LOOKUP_IT);
                 //if(frame.isRadioMenuEN())JOptionPane.showMessageDialog(frame, Language.TEXT_DIALOG_SAVE_DB_MSG_LOOKUP_EN);
             }
         }catch(Exception e)   {
                JOptionPane.showMessageDialog(frame,"Eccezzione : \n"+e,"ERROR",JOptionPane.ERROR_MESSAGE);
         }
     }     

    @Override
    public boolean abilita(Integer statusId) {
        if((ConfigurazioneDatabase.getInstance().isEnableDB()) ||
           (statusId != Costanti.STATO_INIZIALE || statusId != Costanti.STATO_START_TIMER))return true;
        return false;
    }

    @Override
    public boolean disabilita(Integer statusId) {
        if((!ConfigurazioneDatabase.getInstance().isEnableDB()) ||
           (Costanti.STATO_START_TIMER == statusId || Costanti.STATO_INIZIALE == statusId))return true;
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
