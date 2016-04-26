/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.Language;
import it.musicrizz.gameoflife.modello.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Sistema;
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
@NomeSwing("OK")
public class AzioneOKSceltaMondoDB extends AzionePingAstratta   {
    
    private Log log = LogFactory.getLog(AzioneOKSceltaMondoDB.class.getName());
    private FinestraSceltaMondiDataBase finestraSceltaMondiDataBase;
    private FramePrincipale framePrincipale;
    
    
    @Override
    public void esegui(EventObject eo) {
        Descrizione descrizione = (Descrizione)finestraSceltaMondiDataBase.getSelectedValue();
        String nome = descrizione.getNome();
        if(nome == null) {
            if(framePrincipale.isRadioMenuIT()) JOptionPane.showMessageDialog(finestraSceltaMondiDataBase, 
                                                                                Language.TEXT_DIALOG_CARICA_DB_NO_SELCT_IT, "OPS !!", 
                                                                                JOptionPane.INFORMATION_MESSAGE);
            if(framePrincipale.isRadioMenuEN()) JOptionPane.showMessageDialog(finestraSceltaMondiDataBase, 
                                                                                Language.TEXT_DIALOG_CARICA_DB_NO_SELECT_EN, 
                                                                                "OPS !!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try{
                if(DAOMondoSQL.doSelectNomeMondo(nome))  {
                    Sistema sistema = new Sistema();
                    sistema.addCellule(DAOMondoSQL.doSelectCellule(nome));
                    log.debug("Cellule Caricate -> "+sistema.getListaCellule().size());
                    modello.putBean(Costanti.SISTEMA, sistema);
                    log.debug("Sistema inserito nel bean");
                    modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_NUOVA_CONFIGURAZIONE));
                    modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Configurazione caricata , fai partire il Timer"));
                    framePrincipale.cambiaPannello();
                    framePrincipale.getPannelloScacchiera().inizializzaTimer(ConfigurazioneParametri.getInstance().getTimer());
                    framePrincipale.getSliderTime().setValue(ConfigurazioneParametri.getInstance().getTimer());
                    log.debug("Cambio pannello al frame Principale");
                    framePrincipale.getPannelloScacchiera().setLarghezzaColonne(ConfigurazioneParametri.getInstance().getColonne());
                    log.debug("Imposto la larghezza delle colonne della tabella");
                    framePrincipale.pack();
                    framePrincipale.getPannelloScacchiera().abilitaMouseListenerTabella();
                    log.debug("Abilito il listener Mouse della tabella");
                    finestraSceltaMondiDataBase.nascondi();
                }else{
                     if(framePrincipale.isRadioMenuIT()) JOptionPane.showMessageDialog(finestraSceltaMondiDataBase, 
                                                                                Language.TEXT_DIALOG_CARICA_DB_NO_NAME_IT, "OPS !!", 
                                                                                JOptionPane.INFORMATION_MESSAGE);
                     if(framePrincipale.isRadioMenuEN()) JOptionPane.showMessageDialog(finestraSceltaMondiDataBase, 
                                                                                Language.TEXT_DIALOG_CARICA_DB_NO_NAME_EN, 
                                                                                "OPS !!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception e)   {
                log.error("AzioneOKSceltaMondi -> "+e);
            }
        }
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
