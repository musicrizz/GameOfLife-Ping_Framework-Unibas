/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.annotazioni.DescrizioneSwing;
import it.unibas.ping.annotazioni.IconaSwing;
import it.unibas.ping.annotazioni.NomeSwing;
import it.unibas.ping.azioni.AzionePingAstratta;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.vista.FinestraNuovoMondo;
import java.util.EventObject;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
@NomeSwing(Costanti.FRAME_P_TEXT_MENU_NEW)
@DescrizioneSwing(Costanti.FRAME_P_TOOLTIP_MENU_NEW)
@IconaSwing(Costanti.ICONA_BOTTONE_NEW)
public class AzioneFinestraNuovoMondo extends AzionePingAstratta  {
    
    @Override
    public void esegui(EventObject eo) {
        vista.visualizzaSottoVista(FinestraNuovoMondo.class.getName());
    }

    @Override
    public boolean disabilita(Integer statusId) {
        if(Costanti.STATO_START_TIMER == statusId) return true;
        return false;
    }

    @Override
    public boolean abilita(Integer statusId) {
        if(statusId != Costanti.STATO_INIZIALE)return true;
        return false;
    }
    
    
    
}
