package it.musicrizz.gameoflife.vista;

import it.musicrizz.gameoflife.persistenza.DescrizioneMondiDB;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */

public class CellRenderMondi extends DefaultListCellRenderer   {
    
        @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        DescrizioneMondiDB descrizione = (DescrizioneMondiDB)value;
        PannelloRenderDescMondi p = new PannelloRenderDescMondi(descrizione);
        if(isSelected)p.setBackground(Color.RED);
        if(!isSelected)p.setBackground(Color.WHITE);
        return p;
     }
        
        
}
