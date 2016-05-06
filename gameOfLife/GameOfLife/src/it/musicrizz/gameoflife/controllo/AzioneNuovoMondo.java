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
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.vista.FinestraNuovoMondo;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.util.EventObject;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
@NomeSwing("OK")
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
                vista.finestraErrore("Righe e colonne devono essere numeri positivi");
                return;
            }else{
                conf.setRighe(righe);
                conf.setColonne(colonne);
                conf.setTimer(timer);
                Sistema s = new Sistema();
                modello.putBean(Costanti.SISTEMA, s);
                modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_NUOVA_CONFIGURAZIONE));
                modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Crea la tua configurazione e fai partire il Timer"));
                finestraC.nascondi();
                framePrincipale.cambiaPannello();
                framePrincipale.getPannelloScacchiera().inizializzaTimer(conf.getTimer());
                framePrincipale.getSliderTime().setValue(conf.getTimer());
                framePrincipale.getPannelloScacchiera().setLarghezzaColonne(conf.getColonne());
                framePrincipale.pack();
                framePrincipale.getPannelloScacchiera().abilitaMouseListenerTabella();
            }
        }catch(Exception e)   {
            vista.finestraErrore("Righe e Colonne devono essere numeri interi positivi"+e);
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
