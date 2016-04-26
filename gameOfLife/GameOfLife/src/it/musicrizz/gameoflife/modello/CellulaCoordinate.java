package it.musicrizz.gameoflife.modello;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class CellulaCoordinate {
    private int posX;
    private int posY;

    public CellulaCoordinate(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.posX;
        hash = 97 * hash + this.posY;
        return hash;
    }

    /*@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CellulaCoordinate other = (CellulaCoordinate) obj;
        return true;
    }*/
   
}
