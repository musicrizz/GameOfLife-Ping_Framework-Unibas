/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.persistenza.ConfigurazioneDatabase;
import it.musicrizz.gameoflife.vista.FinestraConfDataBase;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
@NomeSwing("OK")
public class AzioneOKParametriDB extends AzionePingAstratta   {

    private FinestraConfDataBase finestra ;
    
    @Override
    public void esegui(EventObject eo) {
        
        List<String> errori = new ArrayList<String>();
        int porta = -1;
        
        try{
            String tipo = finestra.getSelectedDB().trim();
            String dbName = finestra.getDBNAme();
            if(dbName.isEmpty()) errori.add("Nome DB nullo \n");
            
            String p = finestra.getPortaDB().trim();
            try{
                porta = Integer.parseInt(p.trim());
                if(porta<0 || porta>65535) errori.add("Porta non corretta 0 - 65535\n");
            }catch(Exception e)   {
                errori.add("La porta deve essere un numero intero \n");
            }
            
            String user = finestra.getUser();
            if(user.isEmpty())errori.add("User nullo \n");
            
            String passw = finestra.getPassw();
            if(passw.isEmpty()) errori.add("password nulla \n");
            
            if(errori.size()>0) throw new Exception();
            
            ConfigurazioneDatabase.getInstance().setTipoDB(tipo);
            ConfigurazioneDatabase.getInstance().setDbName(dbName);
            ConfigurazioneDatabase.getInstance().setPortaServerSql(porta);
            ConfigurazioneDatabase.getInstance().setUser(user);
            ConfigurazioneDatabase.getInstance().setPassw(passw);
            ConfigurazioneDatabase.getInstance().setEnableDB(true);
            
            modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Parametri DataBase Settati"));
            
            StatoPing stato = (StatoPing)modello.getBean(Controllo.STATO);
            modello.putBean(Controllo.STATO, stato);
            finestra.nascondi();
        }catch(Exception e)   {
            StringBuffer sb = new StringBuffer();
            for(String s : errori)   {
                sb.append(s);
            }
            errori.clear();
            JOptionPane.showMessageDialog(finestra, sb.toString(), "OPS||||", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param finestra the finestra to set
     */
    public void setFinestra(FinestraConfDataBase finestra) {
        this.finestra = finestra;
    }
    
}
