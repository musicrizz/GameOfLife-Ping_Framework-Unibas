package it.musicrizz.gameoflife.vista;

import it.musicrizz.gameoflife.persistenza.DescrizioneMondiDB;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */

public class PannelloRenderDescMondi extends JPanel   {
    
    private JLabel label;
    
    public PannelloRenderDescMondi(DescrizioneMondiDB descrizione)   {
        Border bordo = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(bordo);
        initLabel();
        label.setText(descrizione.toString());
    }
    
    private void initLabel()   {
        label = new JLabel();
        label.setFont(new java.awt.Font("Tahoma", 1, 10));
        this.add(label);
    }
    
    public void changeColorLabel(Color c)   {
        label.setBackground(c);
    }
    
}
