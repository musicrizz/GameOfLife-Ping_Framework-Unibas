package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.modello.Sistema;
import java.io.File;

/**
 *
  * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public interface IDAOCaricamento {
    
    public Sistema carica(File file) throws DAOException;
    public Sistema carica(String file) throws DAOException;
    
}
