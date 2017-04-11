package it.musicrizz.gameoflife;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 * 
 */
public class Costanti {
    
    //AUDIO
    public static final String AUDIO = "audio";
    public static final String AUDIO_CHAT = "/res/images/chat.wav";
    
    //SPLASH SCREEN
    public static final String SPLASH_SCREEN = "/res/images/titolo.jpg";
    
    //ICONE
    public static final String ICONA_PANNELLO_INIT = "/res/images/pannelloIniziale.jpg";
    public static final String ICONA_FRAME = "/res/images/frame.jpg";
    public static final String ICONA_FRAME_16 = "/res/images/frame16.jpg";
    public static final String ICONA_BOTTONE_NEW = "/res/images/new.jpg";
    public static final String ICONA_BOTTONE_OPEN = "/res/images/open.jpg";
    public static final String ICONA_BOTTONE_SAVE = "/res/images/save.jpg";
    public static final String ICONA_BOTTONE_CONF_DB = "/res/images/database.jpg";
    public static final String ICONA_BOTTONE_CONF_CHAT = "/res/images/network.jpg";
    public static final String ICONA_BOTTONE_EXIT = "/res/images/exit.jpg";
    public static final String ICONA_MENU_LANG_IT = "/res/images/ita.jpg";
    public static final String ICONA_MENU_LANG_EN = "/res/images/eng.jpg";
    public static final String ICONA_MENU_EXAMPLE = "/res/images/examples.jpg";
    public static final String ICONA_MENU_CHAT = "/res/images/chat.jpg";
    public static final String ICONA_TOOL_BAR = "/res/images/toolbar.jpg";
    public static final String ICONA_MENU_TOOL = "/res/images/tool.jpg";
    public static final String ICONA_MENU_LANGUAGE = "/res/images/language.jpg";
    
    
    public static final String ICONA_BOTTONE_CANNONE_ALIANTE = "/res/images/aliante.jpg";
    public static final String ICONA_BOTTONE_ASTRONAVE = "/res/images/astronave.jpg";
    public static final String ICONA_BOTTONE_LAMPEGGIATORE = "/res/images/lampeggiatore.jpg";
    public static final String ICONA_BOTTONE_ROSPO = "/res/images/rospo.jpg";
    public static final String ICONA_BOTTONE_FRECCIA = "/res/images/freccia.jpg";
    public static final String ICONA_BOTTONE_TAB_SWING = "/res/images/tabswing.jpg";
    public static final String ICONA_BOTTONE_GRAPHICS2D = "/res/images/2d.jpg";
     public static final String ICONA_BOTTONE_OPENGL = "/res/images/opengl.jpg";
    
    //ESEMPI
    public static final String CONFIGURAZIONE = "ESEMPIO_CONFIGURAZIONE";
    public static final String ESEMPIO_CANNONE_ALIANTE = "/res/images/CannoneDiAlianti.properties";
    public static final String ESEMPIO_ASTRONAVE = "/res/images/AstronaveLeggera.properties";
    public static final String ESEMPIO_LAMPEGGIATORE = "/res/images/Lampeggiatore.properties";
    public static final String ESEMPIO_ROSPO = "/res/images/Rospo.properties";
    public static final String ESEMPIO_FRECCIA = "/res/images/Freccia.properties";
     public static final String ESEMPIO_NULLO = "...";
    
    //STATO APPLICAZIONE
    public static final int STATO_INIZIALE = 0;
    public static final int STATO_NUOVA_CONFIGURAZIONE = 1;
    public static final int STATO_START_TIMER = 2;
    public static final int STATO_STOP_TIMER = 3;
    
    //Sistema
    public static final String SISTEMA = "mondoOO";
    public static final String MONDO_MATRICE = "matricedELmondo";
    
    //Chat
    public static final String SERVER = "serverChat";
    public static final String MESSAGGI_CHAT = "msgClass";
    public static final String MESSAGGI_CHAT_LISTA = "msgList";
    
    
    //Configurazione Parametri
    public static final String CONF = "ConfigurazParam";
    
    //BUNDLE
    
          //AzioneFinestraNuovoMondo
    public static final String FRAME_P_TEXT_MENU_NEW = "FRAME_P_TEXT_MENU_NEW";
    public static final String FRAME_P_TOOLTIP_MENU_NEW= "FRAME_P_TOOLTIP_MENU_NEW";
    
         //AzioneNuovoMondo
    public static final String FRAME_P_TEXT_MENU_SAVE = "FRAME_P_TEXT_MENU_SAVE";
    public static final String FRAME_P_TOOLTIP_MENU_SAVE = "FRAME_P_TOOLTIP_MENU_SAVE";
    public static final String AZIONE_NUOVO_MONDO_ERRORE = "AZIONE_NUOVO_MONDO_ERRORE";
    public static final String AZIONE_NUOVO_MONDO_MSG = "AZIONE_NUOVO_MONDO_MSG";
    
        //AzioneCaricaMondo
    public static final String FRAME_P_TEXT_MENU_OPEN = "FRAME_P_TEXT_MENU_OPEN";
    public static final String FRAME_P_TOOLTIP_MENU_OPEN = "FRAME_P_TOOLTIP_MENU_OPEN";
    public static final String AZIONE_CARICA_FILE_WRONG = "AZIONE_CARICA_FILE_WRONG";
    public static final String AZIONE_CARICA_FILE_MSG = "AZIONE_CARICA_FILE_MSG";
    public static final String AZIONE_CARICA_FILE_ERROR = "AZIONE_CARICA_FILE_ERROR";

        //Azione Iniziale
    public static final String B_MSG_STATO_CONF_VUOTA = "MSG_STATO_CONF_VUOTA";
    
        //MouseListener Tabella & 2D 
    public static final String B_MSG_NUOVA_CELL = "MSG_NUOVA_CELL";
    public static final String B_MSG_CELL_RIMOSSA = "MSG_CELL_RIMOSSA";
    
        //AzioneTimer
    public static final String B_BUTTON_LABEL_TIMER_START = "BUTTON_LABEL_TIMER_START";
    public static final String B_BUTTON_LABEL_TIMER_STOP = "BUTTON_LABEL_TIMER_STOP";
    public static final String B_MSG_STATO_TIMER_START = "MSG_STATO_TIMER_START";
    public static final String B_MSG_STATO_TIMER_STOP = "MSG_STATO_TIMER_STOP";
    
        //PannelloScacchiera
    public static final String B_LABEL_GENERAZIONI = "LABEL_GENERAZIONI";
    public static final String B_LABEL_POPOLAZIONE = "LABEL_POPOLAZIONE";
    
        //Frame Principale
    public static final String B_FRAME_P_TITLE = "FRAME_P_TITLE";
    public static final String B_FRAME_P_MSG_CHIUSURA_FRAME = "FRAME_P_MSG_CHIUSURA_FRAME";
    public static final String B_FRAME_P_MENU_FILE = "FRAME_P_MENU_FILE";
    public static final String B_FRAME_P_MENU_FILE_EXAMPLE = "FRAME_P_MENU_FILE_EXAMPLE";
    public static final String B_FRAME_P_MENU_FILE_EXIT = "FRAME_P_MENU_FILE_EXIT";
    public static final String B_FRAME_P_MENU_VIEW_TOOLB = "FRAME_P_MENU_VIEW_TOOLB";
    public static final String B_FRAME_P_MENU_VIEW = "FRAME_P_MENU_VIEW";
    public static final String B_FRAME_P_MENU_VIEW_CHAT = "FRAME_P_MENU_VIEW_CHAT";
    public static final String B_FRAME_P_MENU_EDIT = "FRAME_P_MENU_EDIT";
    public static final String B_FRAME_P_MENU_INFO_APP = "FRAME_P_MENU_INFO_APP";
    public static final String B_FRAME_P_MENU_INFO_PING = "FRAME_P_MENU_INFO_PING";
    
    //AZIONI PING VALORI COSTANTI
    

            
            //
    
    
    
}
