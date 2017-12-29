package it.musicrizz.gameoflife.persistenza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public class DataSourceDerbyEmbedded implements IDataSource {
    
    private static Log log = LogFactory.getLog(DataSourceDerbyEmbedded.class);
    private String connectionURL;
    
    public DataSourceDerbyEmbedded(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public Connection getConnection() throws DAOException {
        try{
            return DriverManager.getConnection(connectionURL);
        }catch(SQLException sqlEx)   {
            log.error("Eccezione creazione connessione", sqlEx);
            throw  new DAOException("Eccezione creazione connessione",sqlEx);
        }
    }

    public void close(Connection connection) {
        try{
            if(connection != null) connection.close();
        }catch(SQLException sqlex)   {
            log.error("Eccezione chiusura connessione", sqlex);
        }
    }

    public void close(ResultSet resultset) {
        try{
            if(resultset != null) resultset.close();
        }catch(SQLException sqlex)   {
            log.error("Eccezione chiusura resultset", sqlex);
        }
    }

    public void close(Statement statement) {
        try{
            if(statement != null) statement.close();
        }catch(SQLException sqlex)   {
            log.error("Eccezione chiusura statement", sqlex);
        }
    }
    
}