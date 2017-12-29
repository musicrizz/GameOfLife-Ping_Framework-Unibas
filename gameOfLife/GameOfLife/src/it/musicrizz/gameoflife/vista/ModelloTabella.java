package it.musicrizz.gameoflife.vista;

import it.musicrizz.gameoflife.controllo.ConfigurazioneParametri;
import it.musicrizz.gameoflife.modello.Cellula;
import it.unibas.ping.binding.osservatori.ModelloTabellaPing;
import it.musicrizz.gameoflife.modello.Sistema;
import javax.swing.ImageIcon;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovannni13@gmail.com>
 * 
 */

public class ModelloTabella extends ModelloTabellaPing   {
    
    private ImageIcon img;
    
    {
        try{
            img = new ImageIcon(ModelloTabella.class.getResource("/res/images/cellula.jpg"));             
        }catch(Exception e)   {}
    }
    
    public Object getValueAt(int x,int y)   {
        Sistema s = (Sistema)super.getBean();
        if(!s.isCellula(new Cellula(x,y)))   {
            return "";
        }else{
            if(img != null) {
                return img;
            }else{
                return "error";
            }
        }
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
