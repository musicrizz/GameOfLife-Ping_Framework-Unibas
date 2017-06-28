/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.vista.PannelloGraphics2D;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Musicrizz
 */
public class ListenerMouseMove implements MouseMotionListener  {
    
    private static Log log = LogFactory.getLog(ListenerMouseMove.class.getName());
     
    private PannelloGraphics2D pannello;
    
    public ListenerMouseMove(PannelloGraphics2D p)   {
        pannello = p;
    }
        @Override
        public void mouseMoved(MouseEvent e) {
            if(pannello.contains(e.getPoint()))   {
                pannello.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }else{
                pannello.setCursor(Cursor.getDefaultCursor());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            log.debug(e.getPoint());
            System.out.println(e.getPoint());
        }   
        
        
}
