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
@IconaSwing(Costanti.ICONA_BOTTONE_GRAPHICS2D)
public class AzioneGraphics2D extends AzionePingAstratta {
    
    private static Log log = LogFactory.getLog(AzioneGraphics2D.class);
    
    private FramePrincipale framePrincipale;
    
    @Override
    public void esegui(EventObject eo) {
        if(framePrincipale.getPannelloScacchiera() == null) return;
        if(framePrincipale.getPannelloScacchiera().getTabella() != null)  {
            int w = framePrincipale.getSize().width;
            int h = framePrincipale.getSize().height;
            Dimension d = new Dimension(w, h);
            framePrincipale.getPannelloScacchiera().rimuoviTabella();
            framePrincipale.getPannelloScacchiera().initPannello2D();
            framePrincipale.getPannelloScacchiera().getPannello2D().enablePainting();
            framePrincipale.getPannelloScacchiera().getPannello2D().ridisegna();
            framePrincipale.getPannelloScacchiera().abilitaMouseListenerPann2D();
            framePrincipale.setSize(d);
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
