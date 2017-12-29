package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.azioni.IAzioneIniziale;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */
public class AzioneIniziale implements IAzioneIniziale   {
    
    public void esegui(Controllo c)   {
        c.getModello().putBean(Controllo.STATO, new StatoPing(Costanti.STATO_INIZIALE));
        c.getModello().putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing(Bundle.getString(Costanti.B_MSG_STATO_CONF_VUOTA)));
    }
    
}
