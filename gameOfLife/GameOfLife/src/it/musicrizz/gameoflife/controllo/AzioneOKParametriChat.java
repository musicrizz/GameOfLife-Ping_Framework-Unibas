/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;


import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.chat.ConfigurazioneChat;
import it.musicrizz.gameoflife.chat.ServerChat;
import it.musicrizz.gameoflife.vista.FinestraConfChat;
import java.util.EventObject;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
@NomeSwing("OK")
public class AzioneOKParametriChat extends AzionePingAstratta   {
    
    private Log log = LogFactory.getLog(AzioneOKParametriChat.class);
    private ServerChat server = null;
    private FinestraConfChat finestra;
    
    @Override
    public void esegui(EventObject eo) {
        try{
            if(server != null)  server.chiudiSocket();

            String nikName = finestra.getNickName();
            String hostName = finestra.getHostName();
            int portaRicevente = Integer.parseInt(finestra.getPortaRicevente().trim());
            int miaPorta = Integer.parseInt(finestra.getPortaServer().trim());
            

                ConfigurazioneChat.getInstance().setNickName(nikName);
                ConfigurazioneChat.getInstance().setHostNameRicevente(hostName);
                ConfigurazioneChat.getInstance().setPortaHostRicevente(portaRicevente);
                ConfigurazioneChat.getInstance().setPortaMioServer(miaPorta);
                ConfigurazioneChat.getInstance().setAttivaChat(true);
                log.debug("ConfigurazioneChat -> \n"+ConfigurazioneChat.getInstance().getHostNameRicevente()+"\n"+
                          "Porta Ricevente -> "+ConfigurazioneChat.getInstance().getPortaHostRicevente()+"\n"+
                          "Porta Mio Sever -> "+ConfigurazioneChat.getInstance().getPortaMioServer()+"\n");
                server = new ServerChat();
                server.apriSocket();
                server.start();
            modello.putBean(Costanti.SERVER, server);
            modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Configurazione Chat Settata e Server avviato"));
            
            StatoPing stato = (StatoPing)modello.getBean(Controllo.STATO);
            modello.putBean(Controllo.STATO, stato);
            finestra.nascondi();
        }catch(Exception e)   {
            JOptionPane.showMessageDialog(finestra, "HostName e' una Stringa \n"
                                                   +"Le Porte sono numeri interi \n", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param server the server to set
     */
    @Inietta(Costanti.SERVER)
    public void setServer(ServerChat server) {
        this.server = server;
    }

    /**
     * @param finestra the finestra to set
     */
    public void setFinestra(FinestraConfChat finestra) {
        this.finestra = finestra;
    }
    
    
}
