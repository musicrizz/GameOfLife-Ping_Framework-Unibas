/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.persistenza;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class ConfigurazioneDatabase {
    
   private static ConfigurazioneDatabase singleton = new ConfigurazioneDatabase();
    
    private String tipoDB;
    private int portaServerSql;
    private String dbName;
    private String user;
    private String passw;
    private boolean enableDB = false;
    
    private ConfigurazioneDatabase(){}   
    
    public static ConfigurazioneDatabase getInstance() {
        return singleton;
    }

    /**
     * @return the portaServerSql
     */
    public int getPortaServerSql() {
        return portaServerSql;
    }

    /**
     * @param portaServerSql the portaServerSql to set
     */
    public void setPortaServerSql(int portaServerSql) {
        this.portaServerSql = portaServerSql;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the passw
     */
    public String getPassw() {
        return passw;
    }

    /**
     * @param passw the passw to set
     */
    public void setPassw(String passw) {
        this.passw = passw;
    }

    /**
     * @return the enableDB
     */
    public boolean isEnableDB() {
        return enableDB;
    }

    /**
     * @param enableDB the enableDB to set
     */
    public void setEnableDB(boolean enableDB) {
        this.enableDB = enableDB;
    }

    /**
     * @return the tipoDB
     */
    public String getTipoDB() {
        return tipoDB;
    }

    /**
     * @param tipoDB the tipoDB to set
     */
    public void setTipoDB(String tipoDB) {
        this.tipoDB = tipoDB;
    }
}
