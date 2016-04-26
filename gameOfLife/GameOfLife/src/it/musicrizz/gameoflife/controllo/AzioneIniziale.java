/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.azioni.IAzioneIniziale;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.chat.AudioChat;
import it.musicrizz.gameoflife.chat.MessaggiChat;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class AzioneIniziale implements IAzioneIniziale   {
    
    public void esegui(Controllo c)   {
        c.getModello().putBean(Controllo.STATO, new StatoPing(Costanti.STATO_INIZIALE));
        c.getModello().putBean(Costanti.MESSAGGI_CHAT, new MessaggiChat());
        c.getModello().putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("scegli una configurazione, carica o creane una"));
    }
    
}
