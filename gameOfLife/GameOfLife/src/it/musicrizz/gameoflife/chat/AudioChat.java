/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.chat;

import it.musicrizz.gameoflife.Costanti;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class AudioChat {
    
    private AudioInputStream ais;
    private AudioFormat af;
    private DataLine.Info info;
    private Clip suono;
    
    private static Log log = LogFactory.getLog(AudioChat.class);
    
    public AudioChat()   {
        try{
            ais = AudioSystem.getAudioInputStream(AudioChat.class.getResourceAsStream(Costanti.AUDIO_CHAT));
            log.debug(("AudioInputStream eseguito"));
            af = ais.getFormat();
            log.debug(("AudioFOrmat eseguito"));
            info = new DataLine.Info(Clip.class, ais.getFormat(), ((int)ais.getFrameLength() * af.getFrameSize()));
            log.debug("DataLIne.Info eseguito");
            suono = (Clip) AudioSystem.getLine(info);
            log.debug(("Clip creato"));
            suono.open(ais);
            log.debug(("Clip aperto"));
            
        }catch(Exception e)   {
            log.error(e);
        }
    }
    
    public void eseguiCLip ()   {
        try{
            suono.start();
        }catch(Exception e)   {
            log.error("EseguiCLip -> "+e);
        }finally {
            
        }
    }
}
