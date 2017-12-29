package it.musicrizz.gameoflife.persistenza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public class DataSourceFactory {
    
    private Log log = LogFactory.getLog(DataSourceFactory.class);
    
    private IDataSource dataSource;
    
    private static DataSourceFactory singleton = new DataSourceFactory();
    
    public static DataSourceFactory getInstance() {
        return singleton;
    }
    
    public IDataSource getDataSource()  {
        return this.dataSource;
    }
    
    private DataSourceFactory()   {
        try{
            init();
        }catch(DAOException daoe)   {
            log.error("Errore Inizializzazione DataSourceFactry !!! \n impossibile utilizzare i database");
        }
    }
    
    private void init()  throws DAOException    {
        String driver, dbName, connectionURL;
        driver = "org.apache.derby.jdbc.EmbeddedDriver";
        dbName = "gameoflife_DB";
        connectionURL = "jdbc:derby:" + dbName + ";create=true";
        Connection connection = null;       
        try{
            Class.forName(driver);
            log.debug("Driver Caricato");
            connection = DriverManager.getConnection(connectionURL);
            log.debug("Init connessine per "+dbName+" ottenuta");
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            log.debug("-----------------");
            log.debug("Inizio controllo tabelle");
            log.debug("-----------------");
            if(!TestTab_ID(connection)){
                log.debug("flag false nel testTab_ID cancellare il database");
                throw new DAOException("flag false nel testTab_ID cancellare il database");
            }
            log.debug("-----------------");
            if(!TestTab_MONDO(connection)){
                log.debug("flag false nel TestTab_MONDO cancellare il database");
                throw new DAOException("flag false nel TestTab_MONDO cancellare il database");
            }
            log.debug("-----------------");
            if(!TestTab_CELLULE(connection)){
                log.debug("flag false nel TestTab_CELLULE cancellare il database");
                throw new DAOException("flag false nel TestTab_CELLULE cancellare il database");
            }
            log.debug("-----------------");
            connection.commit();
            log.debug("Database "+dbName+" inizializzato OK");
            dataSource = new DataSourceDerbyEmbedded(connectionURL);
        }catch(ClassNotFoundException cnfex)   {
            log.error(cnfex);
            throw new DAOException("Driver non trovato !!", cnfex);
        }catch(SQLException sqlex)   {
            StringBuffer sb = new StringBuffer(sqlex.toString());
            sb.append("\n");
            sb.append("error code : ");sb.append(sqlex.getErrorCode());sb.append("\n");
            sb.append("sql state : ");sb.append(sqlex.getSQLState());sb.append("\n");
            log.error(sb.toString());
            try{
                connection.rollback();
            }catch(SQLException roll){
                log.error("Errore nel rollbak della connessione");
            }
            throw new DAOException("Eccezzione SQL !!", sqlex);
        }finally{
            try{
                connection.setAutoCommit(true);
                connection.close();
            }catch(Exception e){
                log.error("Errore chiusura connessione",e);
            }
        }
    }
    
    private boolean TestTab_ID(Connection connection) throws SQLException {
        String test = "select * from ID";
        String createTable = "create table ID (high bigint primary key, maxlow int)";
        String insert = "insert into ID values (?, ?)";
        PreparedStatement test_tabID = null, create_tabID = null, insert_tabID = null;
        boolean flag = false;
        try{
            log.debug("Controllo se la tabella ID esiste");
            test_tabID = connection.prepareStatement(test);
            test_tabID.execute();
            log.debug("tabella ID trovata");
            return true;
        }catch(SQLException sqlex){
            if(sqlex.getSQLState().equals("42X05"))  {
                try{
                    log.debug("tabella ID non trovata");
                    log.debug("Inizio creazione tabella ID");
                    create_tabID = connection.prepareStatement(createTable);
                    boolean flagCreate = (create_tabID.executeUpdate() >= 0);
                    log.debug("tabella ID CREATA");
                    log.debug("Inserimento valori 100,10");
                    insert_tabID = connection.prepareStatement(insert);
                    insert_tabID.setLong(1, 100);
                    insert_tabID.setInt(2, 10);
                    boolean falgInsert = (insert_tabID.executeUpdate() > 0);
                    log.debug("valori inseriti");
                    flag = flagCreate && falgInsert;
                }catch(SQLException sqlcrex)   {
                    throw sqlcrex;
                }
            }else{
                throw sqlex;
            }
        }finally{
            try{
                if(test_tabID != null) test_tabID.close();
                if(create_tabID != null) create_tabID.close();
            }catch(Exception e)   {
                log.error("Errore chiusura Statement");
            }         
        }
        return flag;
    }
    
    private boolean TestTab_MONDO(Connection connection) throws SQLException {
        String test = "select * from MONDO";
        StringBuffer createTable = new StringBuffer("create table MONDO ");
        createTable.append("(id bigint primary key,");
        createTable.append("nome varchar(50) not null,");
        createTable.append("numRighe int not null,");
        createTable.append("numColonne int not null,");
        createTable.append("timer int not null)");
        PreparedStatement test_tabMONDO = null, create_tabMONDO = null;
        boolean flag = false;
        try{
            log.debug("Controllo se la tabella MONDO esiste");
            test_tabMONDO = connection.prepareStatement(test);
            test_tabMONDO.execute();
            log.debug("tabella MONDO trovata");
            return true;
        }catch(SQLException sqlex){
            if(sqlex.getSQLState().equals("42X05"))  {
                try{
                    log.debug("tabella MONDO non trovata");
                    log.debug("Inizio creazione tabella MONDO");
                    create_tabMONDO = connection.prepareStatement(createTable.toString());
                    flag = (create_tabMONDO.executeUpdate() >= 0);
                    log.debug("tabella MONDO CREATA");
                }catch(SQLException sqlcrex)   {
                    throw sqlcrex;
                }
            }else{
                throw sqlex;
            }
        }finally{
            try{
                if(test_tabMONDO != null) test_tabMONDO.close();
                if(create_tabMONDO != null) create_tabMONDO.close();
            }catch(Exception e)   {
                log.error("Errore chiusura Statement");
            }         
        }
        return flag;
    }
    
    private boolean TestTab_CELLULE(Connection connection) throws SQLException {
        String test = "select * from CELLULE";
        StringBuffer createTable = new StringBuffer("create table CELLULE ");
        createTable.append("(id bigint primary key generated always as identity (start with 1, increment by 1),");
        createTable.append("posX int not null,");
        createTable.append("posY int not null,");
        createTable.append("mondoID bigint references MONDO(id) ");
        createTable.append("on delete cascade)");
        PreparedStatement test_tabCELLULE = null, create_tabCELLULE = null;
        boolean flag = false;
        try{
            log.debug("Controllo se la tabella CELLULE esiste");
            test_tabCELLULE = connection.prepareStatement(test);
            test_tabCELLULE.execute();
            log.debug("tabella CELLULE trovata");
            return true;
        }catch(SQLException sqlex){
            if(sqlex.getSQLState().equals("42X05"))  {
                try{
                    log.debug("tabella CELLULE non trovata");
                    log.debug("Inizio creazione tabella CELLULE");
                    create_tabCELLULE = connection.prepareStatement(createTable.toString());
                    flag = (create_tabCELLULE.executeUpdate() >= 0);
                    log.debug("tabella CELLULE CREATA");
                }catch(SQLException sqlcrex)   {
                    throw sqlcrex;
                }
            }else{
                throw sqlex;
            }
        }finally{
            try{
                if(test_tabCELLULE != null) test_tabCELLULE.close();
                if(create_tabCELLULE != null) create_tabCELLULE.close();
            }catch(Exception e)   {
                log.error("Errore chiusura Statement");
            }         
        }
        return flag;
    }
}
