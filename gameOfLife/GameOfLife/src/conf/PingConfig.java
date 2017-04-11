/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;

import it.musicrizz.gameoflife.Costanti;
import it.unibas.ping.configurazione.Configurazione;
import it.musicrizz.gameoflife.controllo.AzioneIniziale;
import it.musicrizz.gameoflife.vista.FinestraConfDataBase;
import it.musicrizz.gameoflife.vista.FinestraConfChat;
import it.musicrizz.gameoflife.vista.FinestraNuovoMondo;
import it.musicrizz.gameoflife.vista.FinestraIstruzioni;
import it.musicrizz.gameoflife.vista.FinestraSceltaMondiDataBase;
import it.musicrizz.gameoflife.vista.FramePrincipale;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class PingConfig extends Configurazione  {
     
    public PingConfig()   {
        setAutore("Grandinetti Giovanni");
        setNomeApplicazione("Game Of Life");
        setNomeFileSplashScreen(Costanti.SPLASH_SCREEN);
        
        setPackageViste("it.musicrizz.gameoflife.vista");
        setVistaPrincipale(FramePrincipale.class.getName());
        addSottoVista(FinestraNuovoMondo.class.getName());
        addSottoVista(FinestraConfChat.class.getName());
        addSottoVista(FinestraConfDataBase.class.getName());
        addSottoVista(FinestraIstruzioni.class.getName());
        addSottoVista(FinestraSceltaMondiDataBase.class.getName());
        
        setPackageAzioni("it.musicrizz.gameoflife.controllo");
        setAzioneIniziale(AzioneIniziale.class.getName());
        
        this.setRegistraOsservatori(true);
    }
    
}
