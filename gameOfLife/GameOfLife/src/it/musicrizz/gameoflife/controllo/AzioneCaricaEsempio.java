package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.persistenza.DAOCaricamentoProperties;
import it.musicrizz.gameoflife.persistenza.DAOCaricamentoXML;
import it.musicrizz.gameoflife.persistenza.IDAOCaricamento;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import java.io.File;
import java.io.FileInputStream;
import java.util.EventObject;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */

public class AzioneCaricaEsempio extends AzionePingAstratta   {
    
private static Log log = LogFactory.getLog(AzioneCaricaMondo.class);
    
    private FramePrincipale framePrincipale;
    
    @Override
    public void esegui(EventObject eo) {
        Sistema s = null;
        JMenuItem item = (JMenuItem)eo.getSource();
        try{
            log.debug("Inizio Caricamento configurazione");
            IDAOCaricamento daoProperties = new DAOCaricamentoProperties();
            if(item.getName().equals(Costanti.ESEMPIO_CANNONE_ALIANTE_ID))   {
                s = daoProperties.carica(Costanti.ESEMPIO_CANNONE_ALIANTE);
            }else if(item.getName().equals(Costanti.ESEMPIO_ASTRONAVE_LEGGERA_ID)){
                s = daoProperties.carica(Costanti.ESEMPIO_ASTRONAVE);
            }else{
                throw new Exception();
            }
            log.debug("Sistema Caricato");   
            
            modello.putBean(Costanti.SISTEMA, s);
            log.debug("Sistema inserito nel bean");
            modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_NUOVA_CONFIGURAZIONE));
            modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing(Bundle.getString(Costanti.AZIONE_CARICA_FILE_MSG)));
            framePrincipale.cambiaPannello();
            framePrincipale.getPannelloScacchiera().inizializzaTimer(ConfigurazioneParametri.getInstance().getTimer());
            framePrincipale.getSliderTime().setValue(ConfigurazioneParametri.getInstance().getTimer());
            log.debug("Cambio pannello al frame Principale");
            framePrincipale.getPannelloScacchiera().setLarghezzaColonne(ConfigurazioneParametri.getInstance().getColonne());
            log.debug("Imposto la larghezza delle colonne della tabella");
            framePrincipale.pack();
            framePrincipale.getPannelloScacchiera().abilitaMouseListenerTabella();
            log.debug("Abilito il listener Mouse della tabella");
            
        }catch(Exception e)   {
            JOptionPane.showMessageDialog(framePrincipale, Bundle.getString(Costanti.AZIONE_CARICA_FILE_ERROR) ,"ERROR" , JOptionPane.ERROR_MESSAGE);
        }          
    }

    /**
     * @param framePrincipale the framePrincipale to set
     */
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }

    @Override
    public boolean disabilita(Integer statusId) {
        if(Costanti.STATO_START_TIMER == statusId) return true;
        return false;
    }
    
    @Override
    public boolean abilita(Integer statusId) {
        if(statusId != Costanti.STATO_INIZIALE)return true;
        return false;
    }
    
    
}
