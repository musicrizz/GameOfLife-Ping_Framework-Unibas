package it.musicrizz.gameoflife.persistenza;

/**
 *
  * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class Descrizione {
    
    private String nome;
    private int righe;
    private int colonne;
    private int timer;

    public Descrizione(String nome, int righe, int colonne,int timer) {
        this.nome = nome;
        this.righe = righe;
        this.colonne = colonne;
        this.timer = timer;
    }
    
    public String toStringIT()   {
        return "<html><p>Nome : "+getNome()+"<br></br>"+
                "Righe : "+getRighe()+"<br></br>"+
                "Colonne : "+getColonne()+"<br></br>"+
                "Timer : " +getTimer()+"</p></html>";
    }
    
    public String toStringEN()   {
        return "<html><p>Name : "+getNome()+"<br></br>"+
                "Rows : "+getRighe()+"<br></br>"+
                "Columns : "+getColonne()+"<br></br>"+
                "Timer : " +getTimer()+"</p></html>";       
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
}
