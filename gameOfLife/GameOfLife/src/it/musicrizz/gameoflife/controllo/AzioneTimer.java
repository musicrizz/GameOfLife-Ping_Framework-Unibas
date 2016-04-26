/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import it.musicrizz.gameoflife.vista.PannelloScacchiera;
import java.util.EventObject;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
@NomeSwing("Start Timer")
public class AzioneTimer extends AzionePingAstratta   {
    
    private FramePrincipale framePrincipale;

    
    public void esegui(EventObject o)   {
        String text = framePrincipale.getPannelloScacchiera().getTextButtonTimer();
        if(text.equalsIgnoreCase("Start Timer"))  {
            framePrincipale.getPannelloScacchiera().setTextButtonTimer("Stop Timer");
            
            if(framePrincipale.getPannelloScacchiera().getTabella() != null)   {
                framePrincipale.getPannelloScacchiera().disabilitaMouseListenerTabella();
                framePrincipale.getPannelloScacchiera().startTimer();
            }else if(framePrincipale.getPannelloScacchiera().getPannello2D() != null)   {
                framePrincipale.getPannelloScacchiera().disabilitaMouseListeberPann2D();
                framePrincipale.getPannelloScacchiera().startTimer();
            }
            modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_START_TIMER));
            modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Timer partito per aggiungere o rimuovere Cellule FERMA IL TIMER"));
        }else{
            framePrincipale.getPannelloScacchiera().setTextButtonTimer("Start Timer");
            if(framePrincipale.getPannelloScacchiera().getTabella()!=null)   {
                framePrincipale.getPannelloScacchiera().abilitaMouseListenerTabella();
                framePrincipale.getPannelloScacchiera().stopTimer();
            }else if(framePrincipale.getPannelloScacchiera().getPannello2D() != null)   {
                framePrincipale.getPannelloScacchiera().abilitaMouseListenerPann2D();
                framePrincipale.getPannelloScacchiera().stopTimer();
            }
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
