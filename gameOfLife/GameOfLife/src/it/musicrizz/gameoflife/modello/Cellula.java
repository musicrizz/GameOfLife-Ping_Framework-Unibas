package it.musicrizz.gameoflife.modello;
/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 * La Classe Cellula mantiene oltre a due boolean per sapere lo stato corrente e quello futuro,
 * anche due variabili x,y per sapere quale posizione occupa, in questa versione con matrice sparsa
 * abbiamo solo una Mappa di cellule vive
 */
public class Cellula {
    
    private int posX;
    private int posY;
    private boolean statoCorrente;
    private boolean statoFuturo;
    
    public Cellula() {
    }
   
    /**
     * 
     * @param posX
     * @param posY
     * @param statoCorrente
     * @param statoFuturo 
     */
    public Cellula(int posX, int posY,boolean statoCorrente, boolean statoFuturo) {
        this.posX = posX;
        this.posY = posY;
        this.statoCorrente = statoCorrente;
        this.statoFuturo = statoFuturo;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * @return the statoFuturo
     */
    public boolean isStatoFuturo() {
        return statoFuturo;
    }

    /**
     * @param statoFuturo the statoFuturo to set
     */
    public void setStatoFuturo(boolean statoFuturo) {
        this.statoFuturo = statoFuturo;
    }

    /**
     * @return the statoCorrente
     */
    public boolean isStatoCorrente() {
        return statoCorrente;
    }

    /**
     * @param statoCorrente the statoCorrente to set
     */
    public void setStatoCorrente(boolean statoCorrente) {
        this.statoCorrente = statoCorrente;
    }
    
}
