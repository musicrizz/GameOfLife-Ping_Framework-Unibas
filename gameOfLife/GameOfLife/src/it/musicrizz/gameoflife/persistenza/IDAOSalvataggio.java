package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.modello.Sistema;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public interface IDAOSalvataggio {
    public void salva(Sistema s,String nomeFile) throws DAOException;
    
}
