package it.musicrizz.gameoflife.controllo;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@hotmail.com>
 * Classe singleton , viene creata una sola volta.
 * mantiene la configurazione corrente impostata dall' utente o dal caricamento da file
 */
public class ConfigurazioneParametri {
    
    private static ConfigurazioneParametri singleton = new ConfigurazioneParametri();
    
    private int righe;
    private int colonne;
    private int timer;
    
    private ConfigurazioneParametri(){}   
    
    public static ConfigurazioneParametri getInstance() {
        return singleton;
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
