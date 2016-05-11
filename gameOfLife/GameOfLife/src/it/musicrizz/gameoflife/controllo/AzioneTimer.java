/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.util.EventObject;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
@NomeSwing("Start Timer")
public class AzioneTimer extends AzionePingAstratta   {
    
    private FramePrincipale framePrincipale;
    private static boolean flagStart = false;
    
    public void esegui(EventObject o)   {
        String text = framePrincipale.getPannelloScacchiera().getTextButtonTimer();
        if(!flagStart)  {
            framePrincipale.getPannelloScacchiera().setTextButtonTimer("Stop Timer");   //ferma timer      
            framePrincipale.getPannelloScacchiera().startTimer();
            flagStart = true;
            modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_START_TIMER));
            modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("TIMER PARTITO"));
        }else{
            framePrincipale.getPannelloScacchiera().setTextButtonTimer("Start Timer"); //avvia timer
            framePrincipale.getPannelloScacchiera().stopTimer();
            flagStart = false;
            modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_STOP_TIMER));
            modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("TIMER FERMATO"));
        }
    }


    @Override
    public boolean abilita(Integer statusId) {
        if(statusId >= Costanti.STATO_NUOVA_CONFIGURAZIONE)return true;
        return false;
    }

    /**
     * @param framePrincipale the framePrincipale to set
     */
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }
       
}
