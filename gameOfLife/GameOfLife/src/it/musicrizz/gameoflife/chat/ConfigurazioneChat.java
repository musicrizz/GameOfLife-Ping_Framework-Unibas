/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.chat;


/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class ConfigurazioneChat {
    
   private static ConfigurazioneChat singleton = new ConfigurazioneChat();
   
   private String nickName;
   private  String hostNameRicevente;
   private int portaHostRicevente;
   private int PortaMioServer;
   private boolean attivaChat = false;
    
    private ConfigurazioneChat(){}   
    
    public static ConfigurazioneChat getInstance() {
        return singleton;
    }

    /**
     * @return the portaHostRicevente
     */
    public int getPortaHostRicevente() {
        return portaHostRicevente;
    }

    /**
     * @param portaHostRicevente the portaHostRicevente to set
     */
    public void setPortaHostRicevente(int portaServerRicevente) {
        this.portaHostRicevente = portaServerRicevente;
    }

    /**
     * @return the PortaMioServer
     */
    public int getPortaMioServer() {
        return PortaMioServer;
    }

    /**
     * @param PortaMioServer the PortaMioServer to set
     */
    public void setPortaMioServer(int PortaMioServer) {
        this.PortaMioServer = PortaMioServer;
    }

    /**
     * @return the hostNameRicevente
     */
    public String getHostNameRicevente() {
        return hostNameRicevente;
    }

    /**
     * @param hostNameRicevente the hostNameRicevente to set
     */
    public void setHostNameRicevente(String hostNameRicevente) {
        this.hostNameRicevente = hostNameRicevente;
    }

    /**
     * @return the attivaChat
     */
    public boolean isAttivaChat() {
        return attivaChat;
    }

    /**
     * @param attivaChat the attivaChat to set
     */
    public void setAttivaChat(boolean attivaChat) {
        this.attivaChat = attivaChat;
    }

    /**
     * @return the nomeDaVisualizzare
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nomeDaVisualizzare the nomeDaVisualizzare to set
     */
    public void setNickName(String nomeDaVisualizzare) {
        this.nickName = nomeDaVisualizzare;
    }


}
