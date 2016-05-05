package it.musicrizz.gameoflife.modello;
/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */
public class Cellula {
    
    private int X;
    private int Y;

    public Cellula() {
    }

    public Cellula(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.X;
        hash = 67 * hash + this.Y;
        return hash;
    }

    @Override
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
        final Cellula other = (Cellula) obj;
        if (this.X != other.X) {
            return false;
        }
        if (this.Y != other.Y) {
            return false;
        }
        return true;
    }

}
