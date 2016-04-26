/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class CellRenderChat extends DefaultListCellRenderer   {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String messaggio = (String)value;
        String messaggioHTML = messaggio.replaceAll("\n", "<br></br>");
        int controlloInviato = messaggio.indexOf("Inviato");
        if(controlloInviato >= 0)   {
            PannellolRenderChat p = new PannellolRenderChat(messaggioHTML, Color.WHITE);
            return p;
        }else{
            PannellolRenderChat p = new PannellolRenderChat(messaggioHTML,Color.RED);
            return p;
        }
    }
    
    
}
