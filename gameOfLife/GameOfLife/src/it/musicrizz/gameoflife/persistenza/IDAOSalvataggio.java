/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.modello.Sistema;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public interface IDAOSalvataggio {
    public void salva(Sistema s,String nomeFile) throws DAOException;
    
}
