package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.Bundle;
import it.musicrizz.gameoflife.Costanti;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public class DescrizioneMondiDB {
    
    private long id;
    private String nome;
    private int righe;
    private int colonne;
    private int timer;
    private int numCellule;

    public DescrizioneMondiDB(long id, String nome, int righe, int colonne,int timer, int numCellule) {
        this.id = id;
        this.nome = nome;
        this.righe = righe;
        this.colonne = colonne;
        this.timer = timer;
        this.numCellule = numCellule;
    }
    
    @Override
    public String toString()   {
        StringBuffer sb = new StringBuffer("<html><p>");
        sb.append(Bundle.getString(Costanti.DESC_DB_NOME));
        sb.append(getNome());
        sb.append("<br></br>");
        
        sb.append(Bundle.getString(Costanti.DESC_DB_RIGHE));
        sb.append(getRighe());
        sb.append("<br></br>");
        
        sb.append(Bundle.getString(Costanti.DESC_DB_COLONNE));
        sb.append(getColonne());
        sb.append("<br></br>");
        
        sb.append(Bundle.getString(Costanti.DESC_DB_TIMER));
        sb.append(getTimer());
        sb.append("<br></br>");
        
        sb.append(Bundle.getString(Costanti.DESC_DB_NUM_CELLULE));
        sb.append(getNumCellule());
        sb.append("<br></br>");
                
        return sb.toString();
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the righe
     */
    public int getRighe() {
        return righe;
    }

    /**
     * @param righe the righe to set
     */
    public void setRighe(int righe) {
        this.righe = righe;
    }

    /**
     * @return the colonne
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * @param colonne the colonne to set
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * @return the timer
     */
    public int getTimer() {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * @return the numCellule
     */
    public int getNumCellule() {
        return numCellule;
    }

    /**
     * @param numCellule the numCellule to set
     */
    public void setNumCellule(int numCellule) {
        this.numCellule = numCellule;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
