package it.musicrizz.gameoflife.modello;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */
public class Mondo {
    
    private Log log = LogFactory.getLog(Mondo.class);
    
    private Map<String,Cellula> matrice = new HashMap<String, Cellula>();
    
    public Mondo()   {}
    
    public Map<String,Cellula> getMatrice()   {
        return this.matrice;
    }
    
    public Cellula getCellula(int x,int y)   {
        return this.matrice.get(x+","+y);
    }
    
    public void addCellula(int x, int y, boolean statoCorrente ,boolean statoFuturo)   {
        this.matrice.put(x+","+y,new Cellula(x,y,statoCorrente,statoFuturo));
    }
    
    public void addCellule(Map<String,Cellula> mappa)   {
        this.matrice.putAll(mappa);
    }
    
    public void removeCellula(int x,int y)   {
        this.matrice.remove(x+","+y);
    }
    
    public List<Cellula> getListaCellule()   {
        Collection<Cellula> value = matrice.values();
        return new ArrayList<Cellula>(value);
    }   
    /**
     * 
     * @param x
     * @param y
     * @return int
     * Analizza la mappe e cerca se esistono cellule vicine a quella selezionata dalla posizione x,y
     */
    private int analizzaIntornoMoore(int x, int y)   {
        int cont = 0;
        for(int i = (x-1);i<=(x+1);i++)   {
            for(int j = (y-1);j<=(y+1);j++)   {
                if((i == x)&&(j == y)) continue;
                if((i > ConfigurazioneParametri.getInstance().getRighe()-1)||(j > ConfigurazioneParametri.getInstance().getColonne()-1)) continue;
                if((i < 0) || (j < 0))continue;;
                if((getCellula(i, j) != null) && (getCellula(i, j).isStatoCorrente())) cont++;
                log.debug("Cellula "+x+","+y+" Parametri i,j : "+i+","+j+ "  intorno : "+cont);
            }
        }
        log.debug("Analizza Intorno --> cont: "+cont+" della cellula -> "+x+","+y);
        return cont;
    }
    
    public void analizzaMondo()   {
        for(int i=0;i<ConfigurazioneParametri.getInstance().getRighe();i++)   {
            for(int j=0;j<ConfigurazioneParametri.getInstance().getColonne();j++)   {
                int intorno = analizzaIntornoMoore(i, j);
                if((getCellula(i, j) == null) && (intorno == 3))  {
                    addCellula(i, j, false, true);
                    log.debug("AnalizzaMondo crea la cellula che nascerà alla prox generaz "+i+","+j);
                    continue;
                }
                if(((getCellula(i,j) != null) && (getCellula(i, j).isStatoCorrente())) && ((intorno < 2) || (intorno > 3))) {
                        getCellula(i, j).setStatoFuturo(false);//viene usato questi metodi per ridurre l' invio di eventi al modello
                        log.debug("AnalizzaMondo setta la cellula gia  esistente a morire nella prox generaz "+i+","+j);
                        continue;
                }
                if(((getCellula(i,j) != null) && getCellula(i, j).isStatoCorrente()) && ((intorno == 2) || (intorno == 3))) {
                        getCellula(i, j).setStatoFuturo(true);//viene usato questi metodi per ridurre l' invio di eventi al modello
                        log.debug("AnalizzaMondo setta la cellula gia esistente a restare viva "+i+","+j);
                        continue;
                }
             }
         }
        aggiornaMondo();
    }
    
    private void aggiornaMondo()   {
        for(int i=0;i<ConfigurazioneParametri.getInstance().getRighe();i++)   {
            for(int j=0;j<ConfigurazioneParametri.getInstance().getColonne();j++)   {
                if((getCellula(i, j) != null) && (!getCellula(i, j).isStatoFuturo()))   {
                    removeCellula(i, j);
                    log.debug("AggiornaMondo uccide la cellula -> "+i+","+j);
                    continue;
                }
                if(((getCellula(i, j)) != null) && (getCellula(i, j).isStatoFuturo()))   {
                    getCellula(i, j).setStatoCorrente(true);
                    getCellula(i, j).setStatoFuturo(false);
                    log.debug("AggiornaMondo setta  a false lo stato futuro ->  "+i+","+j);
                }
            }
        }
    }
}
