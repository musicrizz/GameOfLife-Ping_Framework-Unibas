package it.musicrizz.gameoflife.modello;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */
public class Mondo {
    
    private static final Log log = LogFactory.getLog(Mondo.class);
     
    private Set<Cellula> generazione = new HashSet<Cellula>();
    private Set<Cellula> newGenerazione = new HashSet<Cellula>();
    private Set<Cellula> memoized = new HashSet<Cellula>();
    
    public Mondo()   {}
    
    public Set<Cellula> getGenerazione()   {
        return this.generazione;
    }
    
    public int getPopolazione()   {
        return generazione.size();
    }
    
    public void addCellula(Cellula cell)   {
        this.generazione.add(cell);
    }
    
    public void addCellule(Set<Cellula> cells)   {
        this.generazione.addAll(cells);
    }
    
    public void addCellule(List<Cellula> cells)   {
        this.generazione.addAll(cells);
    }
    
    public void removeCellula(Cellula c)   {
        this.generazione.remove(c);
    }  
    
    private int analizzaIntornoMoore(Cellula c,boolean flag)   {
        int cont = 0;
        for(int i = (c.getX()-1);i<=(c.getX()+1);i++)   {
            for(int j = (c.getY()-1);j<=(c.getY()+1);j++)   {
                if((i == c.getX())&&(j == c.getY())) continue;
                Cellula cell = new Cellula(i, j);
                if(generazione.contains(cell)) {
                    cont++;
                }else if(!memoized.contains(cell) && flag)   {
                    memoized.add(cell);
                    controllaIntorno(cell, analizzaIntornoMoore(cell,false));
                }                      
            }
        }
        log.debug("AnalizzaIntorno --> cont: "+cont+" della cellula -> "+c.getX()+","+c.getY());
        return cont;
    }
    
    private void controllaIntorno(Cellula cell, int intorno)   {
        boolean isCell = generazione.contains(cell);
        if(!(isCell) && (intorno == 3))   {
            newGenerazione.add(cell);
            log.debug("controlla crea la cellula che nascera alla prox generaz "+cell.getX()+","+cell.getY());
            return;
        }
        if(isCell && ((intorno == 2) || (intorno == 3)))   {
            newGenerazione.add(cell);
            log.debug("controlla setta la cellula gia esistente a restare viva "+cell.getX()+","+cell.getY());
            return;
        }
        if(isCell && ((intorno < 2) || (intorno > 3)))   {
            log.debug("controlla setta la cellula gia  esistente a morire nella prox generaz "+cell.getX()+","+cell.getY());
            return;
        }
    }
    
    public void analizzaMondo()   {
        log.debug("Inizio analizza Mondo");
        for(Cellula c : generazione)   {
            log.debug("Controllo Cellula -> X:"+c.getX()+"  Y:"+c.getY());
            controllaIntorno(c, analizzaIntornoMoore(c,true));
        }
        nuovaGenerazione();
    }
    
    private void nuovaGenerazione()   {
        generazione = new HashSet<Cellula>(newGenerazione);
        newGenerazione.clear();
        memoized.clear();
    }
    
}
