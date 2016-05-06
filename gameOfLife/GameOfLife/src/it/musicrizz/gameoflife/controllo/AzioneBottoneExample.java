/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.persistenza.DAOCaricamentoProperties;
import it.musicrizz.gameoflife.persistenza.IDAOCaricamento;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.util.EventObject;
import javax.swing.JButton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class AzioneBottoneExample extends AzionePingAstratta   {
    
    private static Log log = LogFactory.getLog(AzioneBottoneExample.class);
    private FramePrincipale framePrincipale;
    
    @Override
    public void esegui(EventObject eo) {
        try{
            String nomefile = null;
            JButton b = (JButton)eo.getSource();
            if(b.getName().equals("aliante")) nomefile=Costanti.CONFIGURAZIONE_CANNONE_ALIANTE;
            if(b.getName().equals("astronave")) nomefile=Costanti.CONFIGURAZIONE_ASTRONAVE;
            if(b.getName().equals("lampeggiatore")) nomefile=Costanti.CONFIGURAZIONE_LAMPEGGIATORE;
            if(b.getName().equals("rospo")) nomefile=Costanti.CONFIGURAZIONE_ROSPO;
            if(b.getName().equals("freccia")) nomefile=Costanti.CONFIGURAZIONE_FRECCIA;
            IDAOCaricamento daoCaricaProperties = new DAOCaricamentoProperties();
            Sistema s = daoCaricaProperties.carica(nomefile);
            modello.putBean(Costanti.SISTEMA, s);
            modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_NUOVA_CONFIGURAZIONE));
            modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Configurazione caricata , fai partire il Timer"));
            framePrincipale.cambiaPannello();
            framePrincipale.getPannelloScacchiera().inizializzaTimer(ConfigurazioneParametri.getInstance().getTimer());
            framePrincipale.getSliderTime().setValue(ConfigurazioneParametri.getInstance().getTimer());
            framePrincipale.getPannelloScacchiera().setLarghezzaColonne(ConfigurazioneParametri.getInstance().getColonne());
            framePrincipale.pack();
            framePrincipale.getPannelloScacchiera().abilitaMouseListenerTabella();
        }catch(Exception e)   {
            log.error("Errore nel caricamento della configurazione esempio"+((JButton)eo.getSource()).getName()+" \n "+e);
        }
    }

    @Override
    public boolean abilita(Integer statusId) {
        if(statusId != Costanti.STATO_START_TIMER)return true;
        return false;
    }

    @Override
    public boolean disabilita(Integer statusId) {
        if(statusId == Costanti.STATO_START_TIMER)return true;
        return false;
    }       

    /**
     * @param framePrincipale the framePrincipale to set
     */
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }
}
