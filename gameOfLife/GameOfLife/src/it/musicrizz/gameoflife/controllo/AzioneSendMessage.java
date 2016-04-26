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
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.chat.ClientChat;
import it.musicrizz.gameoflife.chat.ConfigurazioneChat;
import it.musicrizz.gameoflife.chat.MessaggiChat;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
@NomeSwing("Invia")
public class AzioneSendMessage extends AzionePingAstratta   {
    
    private static Log log = LogFactory.getLog(AzioneSendMessage.class.getName());
    
    private FramePrincipale finestra;
    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.ITALIAN);
    private MessaggiChat messaggi;
    
    @Override
    public void esegui(EventObject eo) {
        String messaggio = finestra.getMessageChat();
        if(messaggio.isEmpty()) {
            JOptionPane.showMessageDialog(finestra, "Messaggio vuoto", "OPS!!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            log.debug(messaggio);
            try{
                ClientChat cliCh = new ClientChat();
                cliCh.inviaMessaggio(ConfigurazioneChat.getInstance().getNickName()+"<br></br>"+messaggio);
                
                messaggi.addMessaggio(df.format(new Date())+"<br></br>"+"Inviato <br></br>"+
                                        ConfigurazioneChat.getInstance().getNickName()+"<br></br>"+messaggio);
                log.debug("MESSAGGIO INVIATO AGGIUNTO ALLA LISTA");
                finestra.pulisciTextAreaChat();
                modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Messaggio Inviato -> "+df.format(new Date())));
            }catch(Exception e)   {
                JOptionPane.showMessageDialog(finestra, "Invio messaggio non riuscito \n"+e, "OPS!!!!!", JOptionPane.ERROR_MESSAGE);
            }      
        }
    }

    @Override
    public boolean abilita(Integer statusId) {
        if(ConfigurazioneChat.getInstance().isAttivaChat())return true;
        return false;
    }

    @Override
    public boolean disabilita(Integer statusId) {
        if(!ConfigurazioneChat.getInstance().isAttivaChat())return true;
        return false;
    }

    /**
     * @param finestra the finestra to set
     */
    public void setFinestra(FramePrincipale finestra) {
        this.finestra = finestra;
    }

    /**
     * @param messaggi the messaggi to set
     */
    @Inietta(Costanti.MESSAGGI_CHAT)
    public void setMessaggi(MessaggiChat messaggi) {
        this.messaggi = messaggi;
    }
    

}
