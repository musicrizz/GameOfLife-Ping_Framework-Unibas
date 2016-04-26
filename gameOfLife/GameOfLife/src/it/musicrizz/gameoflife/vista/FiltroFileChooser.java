/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.vista;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class FiltroFileChooser extends FileFilter   {
    
    @Override
    public String getDescription() {
        return ""+"\"*.properties\"; \"*.xml\"";
    }

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String estenzione = Utils.getEstenzione(f);
        if (estenzione != null) {
            if (estenzione.equals(Utils.properties) ||
                estenzione.equals(Utils.xml)) {
                return true;
            } else {
                return false;
            }
        }

    return false;
}
}

class Utils {

    public final static String properties = "properties";
    public final static String xml = "xml";
 
    public static String getEstenzione(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}