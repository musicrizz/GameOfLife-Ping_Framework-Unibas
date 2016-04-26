/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.persistenza;

import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.ConfigurazioneParametri;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class DAOMondoSQL {
    
    private static Log log = LogFactory.getLog(DAOMondoSQL.class);
    
    private DAOMondoSQL(){}
    
    
    public static void doInsertConfigurazioneMondo(String nome) throws DAOException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSource.getConnection(ConfigurazioneDatabase.getInstance().getTipoDB());
            statement = connection.createStatement();
            String query = "insert into configurazioni values (" +
                            "'" + nome + "'," +
                            ConfigurazioneParametri.getInstance().getRighe() + "," +
                            ConfigurazioneParametri.getInstance().getColonne() + "," +
                            ConfigurazioneParametri.getInstance().getTimer() + ")";
            log.debug("doInsertConfigurazione query -> "+query);
            statement.executeUpdate(query);
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            DataSource.closeStatement(statement);
            DataSource.closeConnection(connection);
        }
    }
    
    public static void doInsertCellula(Cellula c,String nomeConf)  throws DAOException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSource.getConnection(ConfigurazioneDatabase.getInstance().getTipoDB());
            statement = connection.createStatement();
            String query =
                    "insert into cellule values (" + c.getPosX() + ", " +
                                                     c.getPosY() + ", " +
                                                "'" + nomeConf + "')";
            log.debug("doInsertCellula query -> "+query);
            statement.executeUpdate(query);
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            DataSource.closeStatement(statement);
            DataSource.closeConnection(connection);
        }
    }
    
    public static boolean doSelectLookUp(String nome) throws DAOException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean trovato = false;
        try {
            connection = DataSource.getConnection(ConfigurazioneDatabase.getInstance().getTipoDB());
            statement = connection.createStatement();
            String query = "select * from configurazioni "+
                           "where nome = '" + nome + "'";
            log.debug("doSelectLookUp -> "+query);
            resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                trovato = true;
            }
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            DataSource.closeResultSet(resultSet);
            DataSource.closeStatement(statement);
            DataSource.closeConnection(connection);
        }
        return trovato;
    }
    
    public static boolean doSelectNomeMondo(String nome) throws DAOException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean effettuato = false;
        try {
            connection = DataSource.getConnection(ConfigurazioneDatabase.getInstance().getTipoDB());
            statement = connection.createStatement();
            String query = "select * from configurazioni "+
                            "where nome = '" + nome + "'";
            log.debug("doSelectNome query -> "+query);
            resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                ConfigurazioneParametri.getInstance().setRighe(resultSet.getInt("righe"));
                ConfigurazioneParametri.getInstance().setColonne(resultSet.getInt("colonne"));
                ConfigurazioneParametri.getInstance().setTimer(resultSet.getInt("timer"));
                effettuato = true;
            }
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            DataSource.closeResultSet(resultSet);
            DataSource.closeStatement(statement);
            DataSource.closeConnection(connection);
        }
        return effettuato;
    }
    
    public static List<Descrizione> doSelectAllMondiDescrizione() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Descrizione> listaConfigurazioni = new ArrayList<Descrizione>();
        try {
            connection = DataSource.getConnection(ConfigurazioneDatabase.getInstance().getTipoDB());
            statement = connection.createStatement();
            String query = "select * from configurazioni";
            log.debug("doSelectALLMondi query -> "+query);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String nome = resultSet.getString("nome");
                int righe =   resultSet.getInt("righe");
                int colonne = resultSet.getInt("colonne");
                int timer = resultSet.getInt("timer");
                Descrizione descrizione = new Descrizione(nome, righe, colonne, timer);
                listaConfigurazioni.add(descrizione);
            }
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            DataSource.closeResultSet(resultSet);
            DataSource.closeStatement(statement);
            DataSource.closeConnection(connection);
        }
        return listaConfigurazioni;
    }
        
        public static Map<String,Cellula> doSelectCellule(String nome) throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String,Cellula> listaCellule = new HashMap<String,Cellula>();
        try {
            connection = DataSource.getConnection(ConfigurazioneDatabase.getInstance().getTipoDB());
            statement = connection.createStatement();
            String query = "select * from cellule where configurazione = '"+nome+"'";
            log.debug("doSelectCellule -> "+query);
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Cellula c = new Cellula();
                c.setPosX(resultSet.getInt("posX"));
                c.setPosY(resultSet.getInt("posY"));
                c.setStatoCorrente(true);
                c.setStatoFuturo(false);
                listaCellule.put(c.getPosX()+","+c.getPosY(),c);
                log.debug("Cellula posX:"+c.getPosX()+" posY:"+c.getPosY()+" aggiunta alla mappaTMP");
            }
        } catch (SQLException sqle) {
            log.error(sqle);
            throw new DAOException(sqle);
        } finally {
            DataSource.closeResultSet(resultSet);
            DataSource.closeStatement(statement);
            DataSource.closeConnection(connection);
        }
        return listaCellule;
    }
}
