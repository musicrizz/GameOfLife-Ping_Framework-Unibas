package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.vista.FinestraNuovoMondo;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.util.EventObject;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */

public class AzioneNuovoMondo extends AzionePingAstratta   {
    
    private FinestraNuovoMondo finestraC;
    private FramePrincipale framePrincipale;
    
    public void esegui(EventObject o)   {
        ConfigurazioneParametri conf = ConfigurazioneParametri.getInstance();
        try{
            int righe = Integer.parseInt(finestraC.getRighe());
            int colonne = Integer.parseInt(finestraC.getColonne());
            int timer = finestraC.getTimer();
            if(righe < 0 || colonne < 0)   {
                vista.finestraErrore(Bundle.getString(Costanti.AZIONE_NUOVO_MONDO_ERRORE));
                return;
            }else{
                conf.setRighe(righe);
                conf.setColonne(colonne);
                conf.setTimer(timer);
                Sistema s = new Sistema();
                modello.putBean(Costanti.SISTEMA, s);
                modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_NUOVA_CONFIGURAZIONE));
                modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing(Bundle.getString(Costanti.AZIONE_NUOVO_MONDO_MSG)));
                finestraC.nascondi();
                framePrincipale.cambiaPannello();
                framePrincipale.getPannelloScacchiera().inizializzaTimer(conf.getTimer());
                framePrincipale.getSliderTime().setValue(conf.getTimer());
                framePrincipale.getPannelloScacchiera().setLarghezzaColonne(conf.getColonne());
                framePrincipale.pack();
                framePrincipale.getPannelloScacchiera().abilitaMouseListenerTabella();
            }
        }catch(Exception e)   {
            vista.finestraErrore(Bundle.getString(Costanti.AZIONE_NUOVO_MONDO_ERRORE));
        }
    }


    /**
     * @param finestraC the finestraC to set
     */
    public void setFinestraC(FinestraNuovoMondo finestraC) {
        this.finestraC = finestraC;
    }

    /**
     * @param framePrincipale the framePrincipale to set
     */
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }
    
    
}
