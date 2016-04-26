/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.modello.Sistema;
import java.io.File;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public interface IDAOCaricamento {
    
    public Sistema carica(File file) throws DAOException;
    public Sistema carica(String file) throws DAOException;
    
}
