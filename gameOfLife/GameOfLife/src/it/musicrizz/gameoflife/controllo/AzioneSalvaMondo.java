package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.annotazioni.IconaSwing;
import it.unibas.ping.annotazioni.Inietta;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.modello.Sistema;
import it.musicrizz.gameoflife.persistenza.DAOSalvataggioProperties;
import it.musicrizz.gameoflife.persistenza.DAOSalvataggioXML;
import it.musicrizz.gameoflife.persistenza.IDAOSalvataggio;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.io.File;
import java.util.EventObject;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
@IconaSwing(Costanti.ICONA_BOTTONE_SAVE)
public class AzioneSalvaMondo extends AzionePingAstratta   {
    
    private FramePrincipale framePrincipale;
    private Sistema sistema;
    private IDAOSalvataggio dao;
    private File file;
    
    public void esegui(EventObject o)   {
        try{
            int opt = framePrincipale.getFileChooser().showSaveDialog(framePrincipale);
            if(opt == JFileChooser.APPROVE_OPTION)  {
                file = framePrincipale.getFileChooser().getSelectedFile();

                if(file.exists())   {   
                    int reply = JOptionPane.showConfirmDialog(framePrincipale, 
                            Bundle.getString(Costanti.AZIONE_SALVA_FILE_EXIST,file.getName()), "File already exist!!", JOptionPane.YES_NO_OPTION);
                    if(reply == JOptionPane.NO_OPTION)return;
                }
                
                if(file.getName().endsWith(".properties"))   {
                        dao = new DAOSalvataggioProperties();            
                }else if(file.getName().endsWith(".xml"))   {
                        dao = new DAOSalvataggioXML();            
                }else{
                    file = new File(file.getPath()+".properties");
                    dao = new DAOSalvataggioProperties(); 
                }                     
                dao.salva(sistema, file.getAbsolutePath());           
            }
        }catch(Exception e)   {
            JOptionPane.showMessageDialog(framePrincipale, Bundle.getString(Costanti.AZIONE_SALVA_FILE_ERROR), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param framePrincipale the framePrincipale to set
     */
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }

    /**
     * @param sistema the sistema to set
     */
    @Inietta(Costanti.SISTEMA)
    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }
    
    @Override
    public boolean disabilita(Integer statusId) {
        if(Costanti.STATO_START_TIMER == statusId || Costanti.STATO_INIZIALE == statusId) return true;
        return false;
    }
    
    @Override
    public boolean abilita(Integer statusId) {
        if(statusId != Costanti.STATO_INIZIALE || statusId != Costanti.STATO_START_TIMER)return true;
        return false;
    }
}
