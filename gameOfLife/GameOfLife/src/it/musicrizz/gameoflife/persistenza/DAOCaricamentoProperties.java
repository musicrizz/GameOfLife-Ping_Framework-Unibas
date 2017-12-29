package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.controllo.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Sistema;
import java.io.File;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public class DAOCaricamentoProperties implements IDAOCaricamento{
    
    private static Log log = LogFactory.getLog(DAOCaricamentoProperties.class);
    
    public DAOCaricamentoProperties(){}
    
    public  Sistema carica(File file) throws DAOException  {
        Properties p = null;
        try{
            p = Utilita.caricaProperties(file);
            return caricaProperties(p);
        }catch(DAOException dao)   {
            throw dao;
        }
    }
    
    public Sistema carica(String file) throws DAOException  {
        Properties p = null;
        try{
            p = Utilita.caricaProperties(file);
            return caricaProperties(p);
        }catch(DAOException dao)   {
            throw dao;
        }
    }
    
    private Sistema caricaProperties(Properties p)  throws DAOException {
        Sistema sistema = null;
        try{
            log.debug("Properties Caricate");
            String nome = p.getProperty("Nome");
            log.debug("Nome caricato "+nome);
            int numCellule = Integer.parseInt(p.getProperty("NumeroCellule"));
            log.debug("Numero Cellule caricato "+numCellule);
            int righe = Integer.parseInt(p.getProperty("NumeroRighe"));
            log.debug("Numero righe caricato "+righe);
            int colonne = Integer.parseInt(p.getProperty("NumeroColonne"));
            log.debug("Numero Colonne caricato "+colonne);
            int timer = Integer.parseInt(p.getProperty("Timer"));
            log.debug("Timer caricato "+timer);
            ConfigurazioneParametri.getInstance().setNome(nome);
            ConfigurazioneParametri.getInstance().setRighe(righe);
            ConfigurazioneParametri.getInstance().setColonne(colonne);
            ConfigurazioneParametri.getInstance().setTimer(timer);
            log.debug("Configurazione settata");
            sistema = new Sistema();
            log.debug("Sistema creato");
            for(int i=0;i<numCellule;i++)   {
                int x = Integer.parseInt(p.getProperty("Cellula"+i+"X").trim());
                int y = Integer.parseInt(p.getProperty("Cellula"+i+"Y").trim());
                log.debug("Siamo nel for -> X="+x+" Y="+y+" Cellula Num= "+i);
                sistema.addCellula(x, y);
                log.debug("Cellula settata con pos -> "+x+","+y);
            }
            return sistema;
        }catch(Exception e)   {
            throw new DAOException("Errore nel caricamento del file --> "+e);
        }
    }

}
