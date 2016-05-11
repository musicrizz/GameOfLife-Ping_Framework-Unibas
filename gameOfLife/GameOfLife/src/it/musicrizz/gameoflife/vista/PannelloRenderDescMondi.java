/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.vista;

import it.unibas.ping.framework.Applicazione;
import it.unibas.ping.framework.Controllo;
import it.musicrizz.gameoflife.persistenza.Descrizione;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class PannelloRenderDescMondi extends JPanel   {
    
    private JLabel label;
    private Controllo controllo = Applicazione.getInstance().getControllo();
    
    public PannelloRenderDescMondi(Descrizione descrizione)   {
        Border bordo = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(bordo);
        initLabel();
        FramePrincipale frame = (FramePrincipale)controllo.getVista().getSottoVista(FramePrincipale.class.getName());
        //if(frame.isRadioMenuIT()) label.setText(descrizione.toStringIT());
        //if(frame.isRadioMenuEN()) label.setText(descrizione.toStringEN());
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
