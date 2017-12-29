package it.musicrizz.gameoflife;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class Bundle {
    
    private final static ResourceBundle R = ResourceBundle.getBundle("LabelsButtonBundle", Locale.getDefault());
    
    public static String getString(String key)   {
        try {
            return R.getString(key);
        }catch(MissingResourceException e)   {
            return "Bundle key not found : " + key + "!!";
        }
    }
    
     public static String getString(String key, Object... params  ) {
        try {
            return MessageFormat.format(R.getString(key), params);
        } catch (MissingResourceException e) {
            return "Bundle key not found : " + key + "!!";
        }
    }
}
