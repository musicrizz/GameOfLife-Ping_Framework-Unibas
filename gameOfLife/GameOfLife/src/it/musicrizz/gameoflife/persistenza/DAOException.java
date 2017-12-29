package it.musicrizz.gameoflife.persistenza;

/**
 *
 * @author Grandinetti Giovanni <gr4andinetti.giovanni13@gmail.com>
 * 
 */

public class DAOException extends Exception   {

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
    
    
    
}
