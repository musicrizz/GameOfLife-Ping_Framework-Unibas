package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.io.IOException;
import java.util.EventObject;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@hotmail.com>
 */
@NomeSwing("Start Timer")
public class AzioneTimer extends AzionePingAstratta   {
    
    private FramePrincipale framePrincipale;
    private static boolean flagStart = false;
    
    public void esegui(EventObject o)   {
        if(!flagStart)  {
        
            try{
                framePrincipale.getjButtonPlay_Pause().setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_PAUSE))));
            }catch(IOException ioe){}
            
            framePrincipale.getPannelloScacchiera().startTimer();
            flagStart = true;
            modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_START_TIMER));
            modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing(Bundle.getString(Costanti.B_MSG_STATO_TIMER_START)));
        }else{
            
            try{
                framePrincipale.getjButtonPlay_Pause().setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_PLAY))));
            }catch(IOException ioe){}
            framePrincipale.getPannelloScacchiera().stopTimer();
            flagStart = false;
            modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_STOP_TIMER));
            modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing(Bundle.getString(Costanti.B_MSG_STATO_TIMER_STOP)));
        }
    }


    @Override
    public boolean abilita(Integer statusId) {
        if(statusId >= Costanti.STATO_NUOVA_CONFIGURAZIONE)return true;
        return false;
    }

    @Override
    public boolean disabilita(Integer statusId) {
       if(statusId < Costanti.STATO_NUOVA_CONFIGURAZIONE)return true;
        return false;
    }
    
    

    /**
     * @param framePrincipale the framePrincipale to set
     */
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }
       
}
