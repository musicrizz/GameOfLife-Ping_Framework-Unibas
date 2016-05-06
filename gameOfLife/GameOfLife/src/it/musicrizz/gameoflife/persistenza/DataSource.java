package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.Costanti;
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
 */
public class DataSource {
    
    private static Log log = LogFactory.getLog(DataSource.class);
    
    static{
        try{
            Class.forName("org.postgresql.Driver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e)   {
            log.error(e);
        }
    }
    
    private DataSource() {}
    
    public static Connection getConnection(String sottProt) throws DAOException   {
        Connection con = null;
        try{
            if(sottProt.equals(Costanti.DRIVER_SQL_SERVER))   {
                String connectionURL ="jdbc:"+sottProt+"://localhost:"+ConfigurazioneDatabase.getInstance().getPortaServerSql()+";"+
                                                  "databaseName="+ConfigurazioneDatabase.getInstance().getDbName()+";"+
                                                  "user="+ConfigurazioneDatabase.getInstance().getUser()+";"+
                                                  "password="+ConfigurazioneDatabase.getInstance().getPassw()+";";
                log.debug("getConnection() URL Connessione -> "+connectionURL);
            
                con = DriverManager.getConnection(connectionURL);
                return con;
            }else if(sottProt.equals(Costanti.DRIVER_POSTGRESQL))   {
                return null;
            }
        }catch(SQLException e)  {
            log.error("GetConnection -> "+e);
            throw new DAOException("getConnection -> "+e);
        }
        return con;
    }
    
    
    public static void closeConnection(Connection con) throws DAOException  {
        try{
            if(con != null) {
                con.close();
                log.debug("closeConnection(con) -> connessione chiusa");
            }
        }catch(SQLException e)   {
            log.error("closeConnection -> "+e);
            throw new DAOException("closeConnection -> "+e);
        }
    }
    
    public static void closeStatement(Statement statement)throws DAOException {
        try {
            if (statement != null) {
                statement.close();
                log.debug("closeStatement(con) -> statement chiuso");
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DAOException("closeStatement -> "+e);
        }
    }

    public static void closeResultSet(ResultSet resultSet) throws DAOException  {
        try {
            if (resultSet != null) {
                resultSet.close();
                log.debug("closeResultSet(con) -> resultsete chiuso");
            }
            
        } catch (SQLException e) {
            log.error(e);
            throw new DAOException("closeResultSet -> "+e);
        }
    }
    
}
