package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.MessaggioPing;
import it.unibas.ping.framework.StatoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.persistenza.DAOMondoSQL;
import it.musicrizz.gameoflife.persistenza.DescrizioneMondiDB;
import it.musicrizz.gameoflife.vista.FinestraSceltaMondiDataBase;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.util.EventObject;
import java.util.Set;
import javax.swing.JOptionPane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

@NomeSwing("OK")
public class AzioneOKSceltaMondoDB extends AzionePingAstratta   {
    
    private Log log = LogFactory.getLog(AzioneOKSceltaMondoDB.class.getName());
    private FinestraSceltaMondiDataBase finestraSceltaMondiDataBase;
    private FramePrincipale framePrincipale;
    
    
    @Override
    public void esegui(EventObject eo) {
        DescrizioneMondiDB descrizione = (DescrizioneMondiDB)finestraSceltaMondiDataBase.getSelectedValue();
        if (descrizione == null)   {
            JOptionPane.showMessageDialog(framePrincipale, 
                    Bundle.getString(Costanti.INFO_SELEZIONE_DB), 
                    Bundle.getString(Costanti.INFO_SELEZIONE_DB_TITLE), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try{
            if(DAOMondoSQL.doSelectLookupID(descrizione.getId()))  {
                log.debug("Inizio caricamento cellule");
                Set<Cellula> setCellule = DAOMondoSQL.doSelectCellule(descrizione.getId());
                
                ConfigurazioneParametri.getInstance().setNome(descrizione.getNome());
                ConfigurazioneParametri.getInstance().setRighe(descrizione.getRighe());
                ConfigurazioneParametri.getInstance().setColonne(descrizione.getColonne());
                ConfigurazioneParametri.getInstance().setTimer(descrizione.getTimer());
                
                Sistema sistema = new Sistema();
                sistema.addCellule(setCellule);
                modello.putBean(Costanti.SISTEMA, sistema);
                log.debug("Sistema inserito nel bean");
                
                modello.putBean(Controllo.STATO, new StatoPing(Costanti.STATO_NUOVA_CONFIGURAZIONE));
                modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing(Bundle.getString(Costanti.AZIONE_CARICA_FILE_MSG)));
                
                framePrincipale.cambiaPannello();
                framePrincipale.getPannelloScacchiera().inizializzaTimer(ConfigurazioneParametri.getInstance().getTimer());
                framePrincipale.getSliderTime().setValue(ConfigurazioneParametri.getInstance().getTimer());            
                framePrincipale.getPannelloScacchiera().setLarghezzaColonne(ConfigurazioneParametri.getInstance().getColonne());
                framePrincipale.pack();
                framePrincipale.getPannelloScacchiera().abilitaMouseListenerTabella();
                finestraSceltaMondiDataBase.nascondi();
            }else{
                JOptionPane.showMessageDialog(framePrincipale, 
                        Bundle.getString(Costanti.INFO_MONDO_NON_TROVATO),
                        Bundle.getString(Costanti.INFO_SELEZIONE_DB_TITLE), 
                        JOptionPane.INFORMATION_MESSAGE);
                finestraSceltaMondiDataBase.nascondi();
                return;
            }
        }catch(Exception e)   {
            log.error("AzioneOKSceltaMondi -> "+e);
        }
    }
    
    /**
     * @param finestraSceltaMondiDataBase the finestraSceltaMondiDataBase to set
     */
    public void setFinestraSceltaMondiDataBase(FinestraSceltaMondiDataBase finestraSceltaMondiDataBase) {
        this.finestraSceltaMondiDataBase = finestraSceltaMondiDataBase;
    }

    /**
     * @param framePrincipale the framePrincipale to set
     */
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }

}
