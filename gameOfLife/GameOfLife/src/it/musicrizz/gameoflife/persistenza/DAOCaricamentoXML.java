package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.controllo.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Sistema;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class DAOCaricamentoXML implements IDAOCaricamento   {
    
    private static Log log = LogFactory.getLog(DAOCaricamentoXML.class);
    
    public DAOCaricamentoXML(){}

    public Sistema carica(File file) throws DAOException {
        return caricaXML(file);
    }
  
    public Sistema carica(String file) throws DAOException {
        File f = null;
        try{
            f = new File(file);
            return caricaXML(f);
        }catch(DAOException dao)   {
            throw dao;
        }
    }
    
    private Sistema caricaXML(File file) throws DAOException  {
        Sistema s = null;
        SAXBuilder builder = new SAXBuilder();
        builder.setValidation(false);
        Document doc = null;
        try{
            s = new Sistema();
            
            doc = builder.build(file);
            Element root = doc.getRootElement();
            
            Element numeroRighe = root.getChild("numeroRighe");
            Element numeroColonne = root.getChild("numeroColonne");
            Element timer = root.getChild("timer");
            
            log.debug("Inizio caricamento Configurazione");
            ConfigurazioneParametri.getInstance().setRighe(Integer.parseInt(numeroRighe.getAttributeValue("num").trim()));
            log.debug("Righe settate");
            ConfigurazioneParametri.getInstance().setColonne(Integer.parseInt(numeroColonne.getAttributeValue("num").trim()));
            log.debug("colonne settate");
            ConfigurazioneParametri.getInstance().setTimer(Integer.parseInt(timer.getAttributeValue("time").trim()));
            log.debug("Timer Settato");
            
            Element lista = root.getChild("listaCellule");
            
            List<Element> listaC =  lista.getChildren();
            
            for(Element e : listaC)   {
                int posX = Integer.parseInt(e.getAttributeValue("posX").trim());
                int posY = Integer.parseInt(e.getAttributeValue("posY").trim());
                s.addCellula(posX, posY);
                log.debug("Cellula aggiunta al sistema x-> "+posX+"  y-> "+posY);
            }
            return s;
        }catch(JDOMException e)   {
            throw new DAOException("Exception JDOM nel caricamento(string) -> \n" +e);
        }catch(IOException e)   {
            throw new DAOException("IOException nel caricamento(string) -> \n" +e);
        }catch(Exception e)  {
            throw new DAOException("Exception nel caricamento(string) -> \n" +e);
        }
    }
}
