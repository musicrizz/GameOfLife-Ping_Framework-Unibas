/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.modello.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Sistema;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
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
                pw.println("NumeroRighe="+ConfigurazioneParametri.getInstance().getRighe());
                pw.println("NumeroColonne="+ConfigurazioneParametri.getInstance().getColonne());
                pw.println("Timer="+ConfigurazioneParametri.getInstance().getTimer());
                pw.println(" ");
                for (int i=0;i< ConfigurazioneParametri.getInstance().getRighe();i++)   {
                    for(int j=0;j<ConfigurazioneParametri.getInstance().getColonne();j++)   {
                        if((s.getCellula(i, j) != null) && (s.getCellula(i, j).isStatoCorrente()))   {
                            pw.println("Cellula"+cont+"X="+i);
                            pw.println("Cellula"+cont+"Y="+j);
                            log.debug("Cellula viva "+cont+" scritta nel file con pos -> "+i+","+j);
                            cont++;
                        }
                    }
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
