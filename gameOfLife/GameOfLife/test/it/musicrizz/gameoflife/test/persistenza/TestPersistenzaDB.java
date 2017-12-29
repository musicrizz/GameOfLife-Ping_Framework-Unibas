package it.musicrizz.gameoflife.test.persistenza;

import it.musicrizz.gameoflife.controllo.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Cellula;
import it.musicrizz.gameoflife.modello.Mondo;
import it.musicrizz.gameoflife.persistenza.DAOException;
import it.musicrizz.gameoflife.persistenza.DAOMondoSQL;
import it.musicrizz.gameoflife.persistenza.DataSourceFactory;
import it.musicrizz.gameoflife.persistenza.DescrizioneMondiDB;
import it.musicrizz.gameoflife.persistenza.IDBroker;
import it.musicrizz.gameoflife.persistenza.IDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public class TestPersistenzaDB extends TestCase   {

    private IDataSource datasource;
    private Connection connection;
    private PreparedStatement select;
    private ResultSet result;
    private Mondo mondo;
    private Set<Cellula> matrice = new HashSet<Cellula>();
    
    private static Log log = LogFactory.getLog(TestPersistenzaDB.class);
            
    @Override
    protected void setUp() throws Exception {
        datasource = DataSourceFactory.getInstance().getDataSource();
        connection = null;
        select = null;
        result = null;
        
        mondo = new Mondo();
        matrice.add(new Cellula(4, 5));
        matrice.add(new Cellula(4, 6));
        matrice.add(new Cellula(4, 7));
        matrice.add(new Cellula(3, 7));
        matrice.add(new Cellula(2, 6));
        mondo.addCellule(matrice);
        
        ConfigurazioneParametri.getInstance().setNome("MONDO_TEST");
        ConfigurazioneParametri.getInstance().setRighe(666);
        ConfigurazioneParametri.getInstance().setColonne(777);
        ConfigurazioneParametri.getInstance().setTimer(999);
    }
    
    
    public void testDataSourceFactory()   {
        try{
            String query = "select * from ID";
            connection = datasource.getConnection();
            select = connection.prepareStatement(query);
            result = select.executeQuery();
            if(result.next())   {
                long high = result.getLong(1);
                int low = result.getInt(2);
                datasource.close(connection);
                assertTrue("check high", high > 0);
                assertTrue("Check maxlow", low == 10);
            }else{
                assertTrue("ResultSet vuoto", false);
            }
        }catch(SQLException sqle)   {
            StringBuffer sb = new StringBuffer(sqle.toString());
            sb.append("\n");
            sb.append("error code : ");sb.append(sqle.getErrorCode());sb.append("\n");
            sb.append("sql state : ");sb.append(sqle.getSQLState());sb.append("\n");
            log.error(sb.toString());
            assertTrue("Eccezione SQLException", false);
        }catch(DAOException daoex)   {
            log.error(daoex);
            assertTrue("Eccezione DAOException", false);
        }finally{
            try{
                datasource.close(result);
                datasource.close(select);
                datasource.close(connection);
            }catch(Exception e){}
        }
    }
    
    public void testInsertDelete()   {
        try{
            long id = IDBroker.getInstance().getId();
            log.debug("Creazione ID");
            DAOMondoSQL.doInsertMondo(id, ConfigurazioneParametri.getInstance().getNome(), mondo);
            log.debug("Mondo inserito");
            
            log.debug("Creazione statemet");
            String query1 = "select * from MONDO";
            String query2 = "select count(*) from CELLULE";
            connection = datasource.getConnection();
            Statement select_mondo = connection.createStatement();
            Statement select_cellule = connection.createStatement();
            log.debug("Statement eseguiti");
            ResultSet result_mondo = select_mondo.executeQuery(query1);
            ResultSet result_cellule = select_cellule.executeQuery(query2);
            
            log.debug("Check");
            int cont_mondi=0;
            while(result_mondo.next())   {
                cont_mondi++;             
                assertTrue("Controllo mondo id", result_mondo.getLong("id") == id);
            }
            log.debug("Contatore mondi : "+cont_mondi);
            
            while(result_cellule.next())  {
                int tmp = result_cellule.getInt(1);
                assertTrue("Controllo num Cellule", tmp == 5);
                log.debug("COUNT CELLULE : "+tmp);
            }
            
            
            
            log.debug("Chiusura connessine e statement");
            datasource.close(result_mondo);
            datasource.close(result_cellule);
            datasource.close(select_mondo);
            datasource.close(select_cellule);
            datasource.close(connection);
            
            log.debug("Controllo doSelectLookupNome");
            assertTrue("Controllo doSelectNome",DAOMondoSQL.doSelectLookupNome(ConfigurazioneParametri.getInstance().getNome()));
  
            log.debug("Controllo doSelectLookupID");
            assertTrue("Controllo doSelectLookupID",DAOMondoSQL.doSelectLookupID(id));
            
            log.debug("Controllo doSelectAllDescrizione");
            List<DescrizioneMondiDB> list = DAOMondoSQL.doSelectAllDescrizione();
            assertTrue("Controllo size lista", list.size() == 1);
            assertTrue("Controllo num Cellule descrizione", list.get(0).getNumCellule() == 5);
            
            log.debug("Controllo doSelectCellule");
            Set<Cellula> setCellule = DAOMondoSQL.doSelectCellule(id);
            assertTrue("Controllo size setCellule", setCellule.size() == 5);
            
            log.debug("Inizio delete");
            DAOMondoSQL.doDeleteMondo(id);
            log.debug("delete Ok");
        }catch(SQLException sqle)   {
            log.error(sqle);
            assertTrue("ERRORE sqlex", false);
        }catch(DAOException daoe)   {
            log.error(daoe);
            assertTrue("ERRORE daoex", false);
        }
    } 

    @Override
    protected void tearDown() throws Exception {
        try{
            if(datasource!= null);
            
        }catch(Exception e)   {
            log.error("Errore eliminazione DataBase -> ",e);
        }
    }
    
    
}
