package it.musicrizz.gameoflife.modello;

import it.unibas.ping.annotazioni.BindingPing;
import it.unibas.ping.framework.Applicazione;
import it.unibas.ping.framework.Modello;
import it.musicrizz.gameoflife.Costanti;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 * La Classe Sistema. incapsula la classe Mondo 
 * Ha i metodi per segnalare al Modello la modifica dei Bean.
 * 
 */

public class Sistema {
    
    private static final Log log = LogFactory.getLog(Sistema.class);
    
    private Modello modello = Applicazione.getInstance().getModello();
    private Mondo mondo;
    
    public Sistema()   {
        mondo = new Mondo();
    }
    
    
    @BindingPing(Costanti.MONDO_MATRICE)
    public Set<Cellula> getMondoMatrice()   {
        return this.mondo.getGenerazione();
    }
    public boolean isCellula(Cellula c)   {
        return mondo.getGenerazione().contains(c);
    }
    
    public void addCellula(int x, int y)   {
        this.mondo.addCellula(new Cellula(x, y));
        modello.notificaModificaCella(this, Costanti.MONDO_MATRICE, x, y);
        log.debug("Cellula Creata in : "+x+","+y);
    }
    
    public void removeCellula(int x,int y)   {
        this.mondo.removeCellula(new Cellula(x, y));
        modello.notificaModificaCella(this, Costanti.MONDO_MATRICE, x, y);
        log.debug("Cellula Creata in : "+x+","+y);
    }
    
    public void addCellule(Set<Cellula> mappa)   {
        this.mondo.addCellule(mappa);
        log.debug("Mappa cellule caricata");
    }
    
    public void addCellule(List<Cellula> mappa)   {
        this.mondo.addCellule(mappa);
        log.debug("Mappa cellule caricata");
    }
   
    public Mondo getMondo()   {
        return this.mondo;
    }
    
    public void analizzaMondo()  {
        mondo.analizzaMondo();
    }
    
    public int getPopolazione()   {
        return mondo.getPopolazione();
    }
}
