package it.musicrizz.gameoflife.persistenza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public class IDBroker   {
    
    private long high = 0;
    private int low = 0, maxlow = 0;
    
    private static Log log = LogFactory.getLog(IDBroker.class);
    
    private static IDBroker singleton = new IDBroker();
    
    public static IDBroker getInstance()   {
        return  singleton;
    }
    
    public long getId() throws DAOException  {
        if(this.low == this.maxlow)   {
            getIDBlock();
        }
        return this.high + this.low++;
    }
    
    private void getIDBlock() throws DAOException  {
        Connection conn = null;
        PreparedStatement select = null, update = null;
        ResultSet result = null;
        String selectSQL = "select * from ID for update";
        String updateSQL = "update ID set high = high + maxlow";
        try{
            conn = DataSourceFactory.getInstance().getDataSource().getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            select = conn.prepareStatement(selectSQL);
            log.debug("select preparedStatement -> "+selectSQL);
            update = conn.prepareStatement(updateSQL);
            log.debug("update preparedStatement -> "+updateSQL);
            
            result = select.executeQuery();result.next();
            this.high = result.getLong("high");
            this.maxlow = result.getInt("maxlow");
            this.low = 0;
            
            update.executeUpdate();
            
            conn.commit();
            log.debug("Blocco di id da : "+this.high+" a "+(this.high+this.maxlow));
        }catch(Exception e)   {
            log.debug("eccezione getIDBloc",e);
            try{
                conn.rollback();
                log.debug("Rollback");
            }catch(Exception sqle){}
            throw new DAOException("Errore getIDBlock ->", e);
        }finally{
            if(conn !=null) {
                try{    
                    conn.setAutoCommit(true);
                    DataSourceFactory.getInstance().getDataSource().close(select);
                    DataSourceFactory.getInstance().getDataSource().close(update);
                    DataSourceFactory.getInstance().getDataSource().close(result);
                    DataSourceFactory.getInstance().getDataSource().close(conn);
                }catch(Exception e){
                    log.debug("Eccezione chiusura risorse");
                }
            }
        }
    }
}
