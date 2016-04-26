/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.vista;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class PannellolRenderChat extends JPanel  {
    
    private JLabel label;
    
    
    public PannellolRenderChat(String messaggio,Color c)   {
        Border bordo = BorderFactory.createLineBorder(Color.BLUE);
        this.setBorder(bordo);
        this.setBackground(c);
        initLabel();
        label.setText("<html><p>"+messaggio+"<p></html>");
    }
    
    private void initLabel()   {
        label = new JLabel();
        label.setFont(new java.awt.Font("Tahoma", 1, 10));
        this.add(label);
    }
}
