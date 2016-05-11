/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.IconaSwing;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.Language;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.persistenza.DAOCaricamentoProperties;
import it.musicrizz.gameoflife.persistenza.DAOCaricamentoXML;
import it.musicrizz.gameoflife.persistenza.IDAOCaricamento;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.io.File;
import java.util.EventObject;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
@NomeSwing(Language.FRAME_P_TEXT_MENU_OPEN_IT)
@DescrizioneSwing(Language.FRAME_P_TOOLTIP_MENU_OPEN_IT)
@IconaSwing(Costanti.ICONA_BOTTONE_OPEN)
public class AzioneCaricaMondo extends AzionePingAstratta   {
    
    private static Log log = LogFactory.getLog(AzioneCaricaMondo.class);
    
    private FramePrincipale framePrincipale;
    
    @Override
    public void esegui(EventObject eo) {
        Sistema s = null;
        try{
            int opt = framePrincipale.getFileChooser().showOpenDialog(framePrincipale);
            if(opt == JFileChooser.APPROVE_OPTION)   {
                File file = framePrincipale.getFileChooser().getSelectedFile();
                log.debug("Inizio Caricamento configurazione");
                StringTokenizer tok = new StringTokenizer(file.getName(), ".");
                tok.nextToken();
                String estenzioneFile = tok.nextToken();
                
                if(estenzioneFile.equalsIgnoreCase("properties") || estenzioneFile.equalsIgnoreCase("xml"))   {
                    
                    if(estenzioneFile.equalsIgnoreCase("properties"))   {
                        IDAOCaricamento daoProperties = new DAOCaricamentoProperties();
                        s = daoProperties.carica(file);
                        log.debug("Sistema Caricato");
                    }
                    
                    if(estenzioneFile.equalsIgnoreCase("xml"))   {
                        IDAOCaricamento daoXml = new DAOCaricamentoXML();
                        s = daoXml.carica(file);
                        log.debug("Sistema Caricato");
                    }
                    
                    modello.putBean(Costanti.SISTEMA, s);
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
                }else{
                   //if(framePrincipale.isRadioMenuIT())vista.finestraErrore(Language.MSG_FORMATO_FILE_IT);
                    //if(framePrincipale.isRadioMenuEN())vista.finestraErrore(Language.MSG_FORMATO_FILE_EN);
                }
            }
        }catch(Exception e)   {
            //if(framePrincipale.isRadioMenuIT())vista.finestraErrore(Language.MSG_ERRORE_CARICAMENTO_FILE_IT+"\n"+e);
            //if(framePrincipale.isRadioMenuEN())vista.finestraErrore(Language.MSG_ERRORE_CARICAMENTO_FILE_EN+"\n"+e);
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
