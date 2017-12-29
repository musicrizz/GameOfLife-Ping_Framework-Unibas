package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.controllo.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.Mondo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public class DAOMondoSQL {
    
    private static Log log = LogFactory.getLog(DAOMondoSQL.class);
    
    private DAOMondoSQL(){}
    
    public static boolean doInsertMondo(long id, String nome, Mondo m) throws DAOException {
        Connection connection = null;
        PreparedStatement insertMondo = null, insertCellula = null;
        IDataSource datasource = null;
        String query_insertMondo = "insert into MONDO values (?, ?, ?, ?, ?)";
        String query_insertCellula = "insert into CELLULE (posX, posY, mondoID) values (?, ?, ?)";
        boolean flag_insertMondo = false, flag_inserCellula = false;
        try {
            datasource = DataSourceFactory.getInstance().getDataSource();
            connection = datasource.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            
            insertMondo = connection.prepareStatement(query_insertMondo);
            insertCellula = connection.prepareStatement(query_insertCellula);
            
            insertMondo.setLong(1, id);
            insertMondo.setString(2, nome);
            insertMondo.setInt(3, ConfigurazioneParametri.getInstance().getRighe());
            insertMondo.setInt(4, ConfigurazioneParametri.getInstance().getColonne());
            insertMondo.setInt(5, ConfigurazioneParametri.getInstance().getTimer());
            
            flag_insertMondo = (insertMondo.executeUpdate() > 0);
            if(!flag_insertMondo)   {
                connection.rollback();
                throw new DAOException("Errore inserimento mondo");
            }
            
            for(Cellula c : m.getGenerazione())   {
                flag_inserCellula = doInsertCellula(c, insertCellula, id);
                if(!flag_inserCellula)   {
                    connection.rollback();
                    throw new DAOException("Errore inserimento cellule");
                }
            }
            connection.commit();
        } catch (SQLException sqle) {
            try{
                connection.rollback();
            }catch(SQLException rollback)   {
                log.error("Error rollback",rollback);
            }
            log.error(sqle);
            throw new DAOException(sqle);
        }catch(DAOException daoe)  {
            try{
                connection.rollback();
            }catch(SQLException rollback)   {
                log.error("Error rollback",rollback);
            }
            log.error(daoe);
            throw daoe;
        } finally {
            try{
                connection.setAutoCommit(true);
                datasource.close(insertMondo);
                datasource.close(insertCellula);
                datasource.close(connection);
            }catch(Exception e)   {
                
            }
        }
        return flag_insertMondo;
    }
    
    private static boolean doInsertCellula(Cellula c, PreparedStatement insert, long mondoID)  throws SQLException {
        try {
            insert.setInt(1, c.getX());
            insert.setInt(2, c.getY());
            insert.setLong(3, mondoID);
            return (insert.executeUpdate() > 0);
        } catch (SQLException sqle) {
            log.error("Eccezione inserimento cellula",sqle);
            throw sqle;
        }
    }
    
    public static boolean doDeleteMondo(long id) throws DAOException  {
        Connection connection = null;
        PreparedStatement deleteMondo = null;
        IDataSource datasource = null;
        String query_deleteMondo = "delete from MONDO where id = ? ";
        try{
            datasource = DataSourceFactory.getInstance().getDataSource();
            connection = datasource.getConnection();
            deleteMondo = connection.prepareStatement(query_deleteMondo);
            deleteMondo.setLong(1, id);
            return (deleteMondo.executeUpdate() > 0);
        }catch(SQLException sqle)   {
            log.error("Errore delete mondo", sqle);
            throw new DAOException("Errore delete mondo", sqle);
        }finally{
            datasource.close(deleteMondo);
            datasource.close(connection);
        }
    }
    
    public static boolean doSelectLookupNome(String nome) throws DAOException{
        IDataSource datasource = null;
        Connection connection = null;
        PreparedStatement select = null;
        ResultSet resultSet = null;
        String query = "select * from MONDO where nome = ?";
        try {
            datasource = DataSourceFactory.getInstance().getDataSource();
            connection = datasource.getConnection();
            select = connection.prepareStatement(query);
            select.setString(1, nome);     
            resultSet = select.executeQuery();
            if (resultSet.next())return true;
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            datasource.close(resultSet);
            datasource.close(select);
            datasource.close(connection);
        }
        return false;
    }
    
        public static boolean doSelectLookupID(long id) throws DAOException{
        IDataSource datasource = null;
        Connection connection = null;
        PreparedStatement select = null;
        ResultSet resultSet = null;
        String query = "select * from MONDO where id = ?";
        try {
            datasource = DataSourceFactory.getInstance().getDataSource();
            connection = datasource.getConnection();
            select = connection.prepareStatement(query);
            select.setLong(1, id);     
            resultSet = select.executeQuery();
            if (resultSet.next())return true;
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            datasource.close(resultSet);
            datasource.close(select);
            datasource.close(connection);
        }
        return false;
    }
    
    public static List<DescrizioneMondiDB> doSelectAllDescrizione() throws DAOException {
        IDataSource datasource = null;
        Connection connection = null;
        Statement select_statement = null;
        PreparedStatement count_p_statement = null;
        ResultSet resultSelect = null, resultCount = null;
        String query_select = "select * from MONDO";
        String query_count = "select count(*) from CELLULE where mondoID = ?";
        List<DescrizioneMondiDB> listaConfigurazioni = new ArrayList<DescrizioneMondiDB>();
        try {
            
            datasource = DataSourceFactory.getInstance().getDataSource();
            connection = datasource.getConnection();
            select_statement = connection.createStatement();
            count_p_statement = connection.prepareStatement(query_count);
            
            resultSelect = select_statement.executeQuery(query_select);
            while (resultSelect.next()){
                long id = resultSelect.getLong("id");
                String nome = resultSelect.getString("nome");
                int righe =   resultSelect.getInt("numRighe");
                int colonne = resultSelect.getInt("numColonne");
                int timer = resultSelect.getInt("timer");
                count_p_statement.setLong(1, id);
                resultCount = count_p_statement.executeQuery();
                resultCount.next();
                listaConfigurazioni.add(new DescrizioneMondiDB(id, nome, righe, colonne, timer, resultCount.getInt(1)));
            }
            
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            datasource.close(resultSelect);
            datasource.close(resultCount);
            datasource.close(select_statement);
            datasource.close(count_p_statement);
            datasource.close(connection);
        }
        return listaConfigurazioni;
    }
    
    
        
    public static Set<Cellula> doSelectCellule(long mondoId) throws DAOException {
        IDataSource datasource = null;
        Connection connection = null;
        PreparedStatement select = null;
        ResultSet result = null;
        Set<Cellula> setCellule = new HashSet<Cellula>();
        String query = "select * from CELLULE where mondoID = ?";
        try {
            datasource = DataSourceFactory.getInstance().getDataSource();
            connection = datasource.getConnection();
            select = connection.prepareStatement(query);      
            select.setLong(1, mondoId);
            result = select.executeQuery();
            
            while (result.next()){
                Cellula c = new Cellula(result.getInt("posX"),result.getInt("posY"));
                setCellule.add(c);
                log.debug("Cellula posX:"+c.getX()+" posY:"+c.getY()+" aggiunta alla generazione_TMP");
            }
            
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            datasource.close(result);
            datasource.close(select);
            datasource.close(connection);
        }
        return setCellule;
    }
}
