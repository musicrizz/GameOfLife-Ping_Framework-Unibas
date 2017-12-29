package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.azioni.AzionePingAstratta;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.persistenza.DAOMondoSQL;
import it.musicrizz.gameoflife.vista.FinestraSceltaMondiDataBase;
import it.unibas.ping.annotazioni.IconaSwing;
import java.util.EventObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <giovanni.grandinetti13@gmail.com>
 * 
 */

@IconaSwing(Costanti.ICONA_BOTTONE_LOAD_DB)
public class AzioneFinestraCaricaDB extends AzionePingAstratta   {
    
    private Log log = LogFactory.getLog(AzioneFinestraCaricaDB.class.getName());
    private FinestraSceltaMondiDataBase finestraSceltaMondiDataBase;
    
    @Override
    public void esegui(EventObject eo) {     
        try{  
            finestraSceltaMondiDataBase.initJList(DAOMondoSQL.doSelectAllDescrizione());
            finestraSceltaMondiDataBase.visualizza();
        }catch(Exception e)    {
            log.error(e);
        }
    }

    @Override
    public boolean abilita(Integer statusId) {
        if(statusId != Costanti.STATO_INIZIALE)return true;
        return false;
    }

    @Override
    public boolean disabilita(Integer statusId) {
        if(Costanti.STATO_START_TIMER == statusId)return true;
        return false;
    }

    /**
     * @param finestraSceltaMondiDataBase the finestraSceltaMondiDataBase to set
     */
    public void setFinestraSceltaMondiDataBase(FinestraSceltaMondiDataBase finestraSceltaMondiDataBase) {
        this.finestraSceltaMondiDataBase = finestraSceltaMondiDataBase;
    }

}
