package it.musicrizz.gameoflife.chat;

import it.unibas.ping.framework.Applicazione;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.Modello;
import it.musicrizz.gameoflife.Costanti;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class GestioneConnessione extends Thread{
    

    private String messaggio;
    private static Socket sok;
    private static Log log = LogFactory.getLog(GestioneConnessione.class);
    private Modello modello = Applicazione.getInstance().getModello();
    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.ITALIAN);
    
    
    public GestioneConnessione(Socket sok)   {
        this.sok = sok;
    }
    
    @Override
    public void run()   {
        try{
             log.debug("Connessione Client Accettata -> "+sok.toString());
                
             BufferedReader fromClinet = new BufferedReader(new InputStreamReader(sok.getInputStream()));
             log.debug("Creazione BufferedReader dal Clienti");
                
                
             messaggio = fromClinet.readLine();
             log.debug("Dati dal client Ricevuti -> "+messaggio);
             sok.close();
             
             MessaggiChat messaggi = (MessaggiChat)modello.getBean(Costanti.MESSAGGI_CHAT);
             messaggi.addMessaggio(df.format(new Date())+"<br></br>"+"Ricevuto da <br></br>"+messaggio);
             log.debug(("MESSAGGIO RICEVUTO AGGIUNTO ALLA LISTA"));
             
             new AudioChat().eseguiCLip();
             modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Messaggio Ricevuto -> "+df.format(new Date())));
             
             
       }catch(Exception e)   {
           log.error("ERRORE -> "+e);
       }finally{
           try{
               if(sok!=null)sok.close();
           }catch(Exception e)  {
               log.error(e);
           }
       }
        
    }
    
}
