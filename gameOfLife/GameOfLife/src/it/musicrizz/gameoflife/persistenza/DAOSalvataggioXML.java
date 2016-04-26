/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.modello.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Sistema;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class DAOSalvataggioXML implements IDAOSalvataggio   {
    
    private static Log log = LogFactory.getLog(DAOSalvataggioXML.class);
    
    public DAOSalvataggioXML() {
    }

    public void salva(Sistema s, String nomeFile) throws DAOException {
        try{
            if(s!=null)   {
                Element root = new Element("mondo");
                root.setAttribute("nome", "conf");
            
                Element numeroRighe = new Element("numeroRighe");
                numeroRighe.setAttribute("num",""+ConfigurazioneParametri.getInstance().getRighe());
                root.addContent(numeroRighe);
                
                Element numeroColonne = new Element("numeroColonne");
                numeroColonne.setAttribute("num",""+ConfigurazioneParametri.getInstance().getColonne());
                root.addContent(numeroColonne);
                
                Element timer = new Element("timer");
                timer.setAttribute("time", ""+ConfigurazioneParametri.getInstance().getTimer());
                root.addContent(timer);
                
                Element listaCellule = new Element("listaCellule");
                
                for (int i=0;i< ConfigurazioneParametri.getInstance().getRighe();i++)   {
                    for(int j=0;j<ConfigurazioneParametri.getInstance().getColonne();j++)   {
                        if((s.getCellula(i, j) != null) && (s.getCellula(i, j).isStatoCorrente()))   {
                            Element cellula = new Element("cellula");
                            cellula.setAttribute("posX",""+s.getCellula(i, j).getPosX());
                            cellula.setAttribute("posY", ""+s.getCellula(i, j).getPosY());
                            listaCellule.addContent(cellula);
                            log.debug("Cellula viva  scritta nel file con pos -> "+i+","+j);
                        }
                    }
                }
                root.addContent(listaCellule);
                Document doc = new Document(root);
                
                XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
                xmlOut.output(doc, new FileOutputStream(new File(nomeFile)));
            }
        }catch(Exception e)   {
            throw new DAOException("errore nel Salvataggio XML  -> \n" + e);
        }
    }
    
}
