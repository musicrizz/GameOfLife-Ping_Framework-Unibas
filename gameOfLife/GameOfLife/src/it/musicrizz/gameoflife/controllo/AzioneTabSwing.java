package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import it.unibas.ping.annotazioni.IconaSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import java.awt.Dimension;
import java.util.EventObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
@IconaSwing(Costanti.ICONA_BOTTONE_TAB_SWING)
public class AzioneTabSwing extends AzionePingAstratta  {
    
     private static Log log = LogFactory.getLog(AzioneTabSwing.class);
    
    private FramePrincipale framePrincipale;
    
    @Override
    public void esegui(EventObject eo) {
        if(framePrincipale.getPannelloScacchiera() == null) return;
        if(framePrincipale.getPannelloScacchiera().getPannello2D() != null)   {
            framePrincipale.getPannelloScacchiera().disabilitaMouseListeberPann2D();
            framePrincipale.getPannelloScacchiera().getPannello2D().disablePainting();
            framePrincipale.getPannelloScacchiera().rimuoviPannello2D();
            framePrincipale.getPannelloScacchiera().initTabella();
            framePrincipale.getPannelloScacchiera().abilitaMouseListenerTabella();
            framePrincipale.getPannelloScacchiera().setLarghezzaColonne(ConfigurazioneParametri.getInstance().getColonne());
            framePrincipale.pack();
        }         
    }
    
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }

    @Override
    public boolean disabilita(Integer statusId) {
        if(statusId == Costanti.STATO_INIZIALE ) return true;
        return false;
    }
    
    @Override
    public boolean abilita(Integer statusId) {
        if(statusId > Costanti.STATO_INIZIALE )return true;
        return false;
    }
}
