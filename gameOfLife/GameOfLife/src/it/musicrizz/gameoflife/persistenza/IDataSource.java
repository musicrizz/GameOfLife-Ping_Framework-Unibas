package it.musicrizz.gameoflife.persistenza;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public interface IDataSource {
        
    public Connection getConnection() throws DAOException;
    
    public void close(Connection connection);
    
    public void close(Statement statement);
    
    public void close(ResultSet resultset);   
    
}
