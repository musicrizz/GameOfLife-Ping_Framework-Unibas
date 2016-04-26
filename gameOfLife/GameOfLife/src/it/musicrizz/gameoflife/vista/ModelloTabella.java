/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.vista;

import it.unibas.ping.binding.osservatori.ModelloTabellaPing;
import it.musicrizz.gameoflife.modello.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Sistema;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class ModelloTabella extends ModelloTabellaPing   {
    
    
    public Object getValueAt(int x,int y)   {
        Sistema s = (Sistema)super.getBean();
        if(s.getCellula(x, y) == null || (s.getCellula(x, y) != null && !s.getCellula(x, y).isStatoCorrente()))   {
            return "";
        }else{
            try{
                ImageIcon img = new ImageIcon(ModelloTabella.class.getResource("/res/images/cellula.jpg"));
                if(img != null) return img;
            }catch(Exception e)   {
                return "errore caricamento immagine";
            }
        }
        return null;
    }
    
    @Override
   public Class getColumnClass(int i){
       return ImageIcon.class;
    }
    
    public int getColumnCount()   {
        Sistema s = (Sistema)super.getBean();
        if(s != null) return ConfigurazioneParametri.getInstance().getColonne();
        return 0;
    }
    
    public int getRowCount()   {
        Sistema s = (Sistema)super.getBean();
        if(s != null) return ConfigurazioneParametri.getInstance().getRighe();
        return 0;
    }
}
