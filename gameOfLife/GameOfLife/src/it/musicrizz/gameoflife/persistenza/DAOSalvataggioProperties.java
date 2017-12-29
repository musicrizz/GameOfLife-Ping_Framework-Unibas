package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.controllo.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.Sistema;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public class DAOSalvataggioProperties implements IDAOSalvataggio{
    
    private static Log log = LogFactory.getLog(DAOSalvataggioProperties.class);
    
    public DAOSalvataggioProperties(){}
    
    public void salva(Sistema s,String nomeFile)  throws DAOException {
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileWriter(nomeFile));
            log.debug("Creazione del PrintWriter");
            if(s != null)   {
                int cont = 0;
                pw.println("Nome="+ConfigurazioneParametri.getInstance().getNome());
                pw.println("NumeroRighe="+ConfigurazioneParametri.getInstance().getRighe());
                pw.println("NumeroColonne="+ConfigurazioneParametri.getInstance().getColonne());
                pw.println("Timer="+ConfigurazioneParametri.getInstance().getTimer());
                pw.println(" ");
                for(Cellula cell : s.getMondoMatrice())   {
                    pw.println("Cellula"+cont+"X="+cell.getX());
                    pw.println("Cellula"+cont+"Y="+cell.getY());
                    log.debug("Cellula viva "+cont+" scritta nel file con pos -> "+cell.getX()+","+cell.getY());
                    cont++;
                }
                pw.println("NumeroCellule="+cont);
            }
        }catch(Exception e)   {
            throw new DAOException("Errore nel salvataggio del file "+nomeFile+" -> "+e);
        }finally{
            try{
                if(pw != null) pw.close();
            }catch(Exception e)   {
                throw new DAOException("Errore nella chiusura del PrintWriter -> "+e);
            }
        }
    }
    
}
