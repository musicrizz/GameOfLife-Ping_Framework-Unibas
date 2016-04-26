package it.musicrizz.gameoflife.modello;

import it.unibas.ping.annotazioni.BindingPing;
import it.unibas.ping.framework.Applicazione;
import it.unibas.ping.framework.Modello;
import it.musicrizz.gameoflife.Costanti;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * La Classe Sistema. incapsula la classe Mondo 
 * Ha i metodi per segnalare al Modello la modifica dei Bean.
 */
public class Sistema {
    
    private static final Log log = LogFactory.getLog(Sistema.class);
    
    private Modello modello = Applicazione.getInstance().getModello();
    private Mondo mondo;
    
    public Sistema()   {
        mondo = new Mondo();
    }
    
    
    @BindingPing(Costanti.MONDO_MATRICE)
    public Map<String,Cellula> getMondoMatrice()   {
        return this.mondo.getMatrice();
    }
    
    public void addCellula(int x, int y, boolean statoCorrente, boolean statoFuturo)   {
        this.mondo.addCellula(x, y, statoCorrente, statoFuturo);
        modello.notificaModificaCella(this, Costanti.MONDO_MATRICE, x, y);
        log.debug("Cellula Creata in : "+x+","+y);
    }
    
    public void setCellulaStatoFuturo(int x,int y,boolean b)   {
        this.mondo.getCellula(x, y).setStatoFuturo(b);
        modello.notificaModificaCella(this, Costanti.MONDO_MATRICE, x, y);
    }
    
    public Cellula getCellula(int x, int y)   {
        //assert(x<=ConfigurazioneParametri.getInstance().getRighe()&&y<=ConfigurazioneParametri.getInstance().getColonne()) : 
        //     "getCellula(x,y) --> Errore nell'indice di riga o colonna  GetCellula(x,y)";
        return this.mondo.getCellula(x, y);
    }
    
    public void removeCellula(int x,int y)   {
        //assert(x<=ConfigurazioneParametri.getInstance().getRighe()&&y<=ConfigurazioneParametri.getInstance().getColonne()) : 
        //     "getCellula(x,y) --> Errore nell'indice di riga o colonna  GetCellula(x,y)";
        this.mondo.removeCellula(x, y);
        modello.notificaModificaCella(this, Costanti.MONDO_MATRICE, x, y);
        log.debug("Cellula Creata in : "+x+","+y);
    }
    
    public void addCellule(Map<String,Cellula> mappa)   {
        this.mondo.addCellule(mappa);
        //modello.notificaModificaCella(this, Costanti.MONDO_MATRICE, 0, 0);
        log.debug("Mappa cellule caricata");
    }
    
    public List<Cellula> getListaCellule()   {
        return mondo.getListaCellule();
    }
    
    public Mondo getMondo()   {
        return this.mondo;
    }
    
    public void analizzaMondo()  {
        mondo.analizzaMondo();
    }
    
}
