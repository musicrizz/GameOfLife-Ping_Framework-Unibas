/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.chat;

import it.unibas.ping.framework.Applicazione;
import it.unibas.ping.framework.Modello;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class ClientChat {
    
    private static Log log = LogFactory.getLog(ClientChat.class);
    
    public ClientChat() {}
    
    public void inviaMessaggio(String m) throws IOException  {
        Socket sok = null;
        try{
            sok = new Socket(ConfigurazioneChat.getInstance().getHostNameRicevente(), ConfigurazioneChat.getInstance().getPortaHostRicevente());
            log.debug("Socket Creata -> "+sok.toString());
            DataOutputStream out = new DataOutputStream(sok.getOutputStream());
            log.debug("DataOutPutStream creato ");;
            out.writeBytes(m+'\n');
            log.debug("Messaggio inviato");
            sok.close();
            log.debug("Socket Chiusa");
        }catch(Exception e)   {
            log.error("Eccezziione -> "+e);
            throw new IOException("Errore nell' invio del messaggio -> \n"+e);
        }finally{
            if(sok != null)   {
                sok.close();
            }
        }
    }
    
}
