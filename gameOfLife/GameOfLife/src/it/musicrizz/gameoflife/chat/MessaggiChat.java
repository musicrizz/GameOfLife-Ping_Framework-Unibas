/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.chat;

import it.unibas.ping.annotazioni.BindingPing;
import it.unibas.ping.framework.Applicazione;
import it.unibas.ping.framework.Modello;
import it.musicrizz.gameoflife.Costanti;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class MessaggiChat {
    
    private Modello modello = Applicazione.getInstance().getModello();
    
        //Messaggi della Chat
    private List<String> listaMessaggi = new ArrayList<String>();
    
    @BindingPing(Costanti.MESSAGGI_CHAT_LISTA)
    public List<String> getListaMessaggi()   {
        return listaMessaggi;
    }
    
    public void addMessaggio(String msg)    {
        this.listaMessaggi.add(msg);
        modello.notificaModificaCollezione(this, Costanti.MESSAGGI_CHAT_LISTA, listaMessaggi.size()-1, Modello.AGGIUNTA);
    }
    
    public void clearAll()   {
        this.listaMessaggi.clear();
        modello.notificaModificaCollezione(this, Costanti.MESSAGGI_CHAT_LISTA, 0, listaMessaggi.size()-1, Modello.ELIMINAZIONE);
    }
    
}
