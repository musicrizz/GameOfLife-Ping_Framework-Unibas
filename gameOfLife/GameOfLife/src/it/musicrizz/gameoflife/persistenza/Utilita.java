/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.persistenza;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class Utilita {
    
    private static Log log = LogFactory.getLog(Utilita.class);
    
    private Utilita(){}
    
    public static Properties caricaProperties(File f) throws DAOException  {
        Properties properties;
        File file = null;
        FileInputStream inStream = null;
        try{
            log.debug("Inizio caricamento properties");
            properties = new Properties();
            file = f;
            log.debug("java.io.File caricato");
            inStream = new FileInputStream(file);
            log.debug("Stream Caricato");
            properties.load(inStream);
            log.debug("properties.load(inStream) eseguito");
            return properties;
        }catch(Exception e)   {
            throw new DAOException("Errore nel caricamento del file .properties "+e);
        }finally{
            try{
                if(inStream != null) inStream.close();
            }catch(Exception e)   {
                throw new DAOException("Errore nella chiusura dello stream del file "+e);
            }
        }
    }
    
    public static Properties caricaProperties(String f) throws DAOException  {
        Properties properties;
        InputStream inStream = null;
        try{
            log.debug("Inizio caricamento properties");
            properties = new Properties();
            inStream = Utilita.class.getResourceAsStream(f);
            log.debug("Stream Caricato");
            properties.load(inStream);
            log.debug("properties.load(inStream) eseguito");
            return properties;
        }catch(Exception e)   {
            throw new DAOException("Errore nel caricamento del file .properties "+e);
        }finally{
            try{
                if(inStream != null) inStream.close();
            }catch(Exception e)   {
                throw new DAOException("Errore nella chiusura dello stream del file "+e);
            }
        }
    }
}
