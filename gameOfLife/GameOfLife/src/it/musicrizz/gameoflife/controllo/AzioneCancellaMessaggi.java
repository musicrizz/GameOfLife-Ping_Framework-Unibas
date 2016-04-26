/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.chat.MessaggiChat;
import java.util.Collections;
import java.util.EventObject;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
@NomeSwing("Cancella Conversazione")
@DescrizioneSwing("Cancella tutti i messaggi inviati e ricevuti")
public class AzioneCancellaMessaggi extends AzionePingAstratta   {
    
    private MessaggiChat messaggi;

    @Override
    public void esegui(EventObject eo) {
        if(!messaggi.getListaMessaggi().isEmpty()) messaggi.clearAll();
    }

    /**
     * @param messaggi the messaggi to set
     */
    @Inietta(Costanti.MESSAGGI_CHAT)
    public void setMessaggi(MessaggiChat messaggi) {
        this.messaggi = messaggi;
    }
    
    
}
