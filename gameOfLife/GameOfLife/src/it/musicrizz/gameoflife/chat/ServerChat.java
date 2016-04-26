/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.chat;

import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class ServerChat extends Thread   {
    
    private Log log = LogFactory.getLog(ServerChat.class);
    
    ServerSocket welcomeSok;
    String messaggio;
    
    @Override
    public void run()   {
         try{
            log.debug("Entriamo nel loop");
            while(true)   {
                log.debug("In Attesa di connessione dal client");
                Socket sok = welcomeSok.accept();
                try{
                    sleep(100);
                }catch(Exception e)   {
                    log.error(e);
                }
                log.debug("Inizio GestioneConnessione(sok)");
                new GestioneConnessione(sok).start();
            }
        }catch(Exception e)   {
            log.error("ServerChat - Metodo run() - Eccezione -> \n"+e);
        }finally{
            try{
                if(welcomeSok!=null) welcomeSok.close();
            }catch(Exception e)   {
                log.error(e);
            }
        }
    }
    
    public void chiudiSocket()   {
        try{
            log.debug("Chiusura Socket");
           if(welcomeSok!=null) welcomeSok.close();
        }catch(Exception e)   {
           log.error("chiusura Socket -> "+e);
        }
    }
    
    public void apriSocket()    {
        try{
            log.debug("Creazione ServerSocket");
            welcomeSok = new ServerSocket(ConfigurazioneChat.getInstance().getPortaMioServer());
        }catch(Exception e)   {
            log.error("apertura Socket -> "+e);
        }
    }
    
}
