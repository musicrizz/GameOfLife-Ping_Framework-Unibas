/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.controllo;

import it.unibas.ping.azioni.AzionePingAstratta;
import it.musicrizz.gameoflife.Language;
import it.musicrizz.gameoflife.vista.FinestraConfDataBase;
import it.musicrizz.gameoflife.vista.FinestraConfChat;
import it.musicrizz.gameoflife.vista.FinestraIstruzioni;
import it.musicrizz.gameoflife.vista.FinestraNuovoMondo;
import it.musicrizz.gameoflife.vista.FinestraSceltaMondiDataBase;
import it.musicrizz.gameoflife.vista.FramePrincipale;
import java.util.EventObject;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class AzioneLanguage extends AzionePingAstratta   {
    
    private FramePrincipale framePrincipale;
    private FinestraConfDataBase finestraConfDataBase;
    private FinestraConfChat finestraConfChat;
    private FinestraIstruzioni finestraIstruzioni;
    private FinestraNuovoMondo finestraNuovoMondo;
    private FinestraSceltaMondiDataBase finestraSceltaMondiDataBase;
    
    @Override
    public void esegui(EventObject eo) {
         
        if(framePrincipale.isRadioMenuIT())   {
            framePrincipale.getJMenu5Language().setText("Lingua");
            framePrincipale.setTitle("Gioco della vita");
            framePrincipale.getjMenuItem3NuovoMondo().setText(Language.FRAME_P_TEXT_MENU_NEW_IT);
            framePrincipale.getjMenuItem3NuovoMondo().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_NEW_IT);
            framePrincipale.getjButtonNew().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_NEW_IT);
            
            framePrincipale.getjMenuItem4CaricaMondo().setText(Language.FRAME_P_TEXT_MENU_OPEN_IT);
            framePrincipale.getjMenuItem4CaricaMondo().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_OPEN_IT);
            framePrincipale.getjButtonOpen().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_OPEN_IT);
            
            framePrincipale.getjMenuItem5SalvaMondo().setText(Language.FRAME_P_TEXT_MENU_SAVE_IT);
            framePrincipale.getjMenuItem5SalvaMondo().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_SAVE_IT);
            framePrincipale.getjButtonSave().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_SAVE_IT);
            
            framePrincipale.getjMenuItem9SalvaSuDB().setText(Language.FRAME_P_TEXT_MENU_SAVE_DB_IT);
            framePrincipale.getjMenuItem9SalvaSuDB().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_SAVE_DB_IT);
            
            framePrincipale.getjMenuItem8CaricaDaDB().setText(Language.FRAME_P_TEXT_MENU_OPEN_DB_IT);
            framePrincipale.getjMenuItem8CaricaDaDB().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_OPEN_DB_IT);
            
            framePrincipale.getjMenuItem6Esci().setText(Language.FRAME_P_TEXT_MENU_EXIT_IT);
            
            framePrincipale.getjCheckBoxMenuExample().setText(Language.FRAME_P_TEXT_BUTTON_EXAMPLES_IT);
            
            framePrincipale.getjMenuItem10ConfDB().setText(Language.FRAME_P_TEXT_BUTTON_CONF_DB_IT);
            framePrincipale.getjMenuItem10ConfDB().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_DB_IT);
            
            framePrincipale.getjMenuItem6ConfChat().setText(Language.FRAME_P_TEXT_BUTTON_CONF_CHAT_IT);
            framePrincipale.getjMenuItem6ConfChat().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_CHAT_IT);
            
            framePrincipale.getjMenuItemIstruzioni().setText(Language.FRAME_P_TEXT_BUTTON_ISTRUZIONI_IT);
            framePrincipale.getjMenuItemIstruzioni().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_ISTRUZIONI_IT);
            
            framePrincipale.getjButtonAstronave().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_ASTRONAVE_IT);
            framePrincipale.getjButtonCannoneAlianti().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CANNONE_ALIANTI_IT);
            framePrincipale.getjButtonFreccia().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_FRECCIA_IT);
            framePrincipale.getjButtonLampeggiatore().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_LAMPEGGIATORE_IT);
            framePrincipale.getjButtonRospo().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_RANA_IT);
            
            framePrincipale.getjButtonConfDB().setToolTipText(Language.FRAME_P_TEXT_BUTTON_CONF_DB_IT);
            framePrincipale.getJbuttonConfChat().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_CHAT_IT);
            
            finestraNuovoMondo.getjLabel1NumRighe().setText(Language.FINES_MONDO_TEXT_LABEL_RIGHE_IT);
            finestraNuovoMondo.getjLabel2NumColonne().setText(Language.FINES_MONDO_TEXT_LABEL_COLONNE_IT);
            finestraNuovoMondo.setTitleBorder(Language.FINES_MONDO_TEXT_TITLE_BORDER_IT);
            finestraNuovoMondo.pack();
            
            finestraConfDataBase.setTitle(Language.FINES_CONF_DB_TITLE_IT);
            finestraConfDataBase.getjLabel5Type().setText(Language.FINES_CONF_DB_LABEL_TYPE_IT);
            finestraConfDataBase.getjLabel1DBName().setText(Language.FINES_CONF_DB_LABEL_DB_NAME_IT);
            finestraConfDataBase.getjLabel2Port().setText(Language.FINES_CONF_DB_LABEL_PORT_IT);
            finestraConfDataBase.getjLabel3User().setText(Language.FINES_CONF_DB_LABEL_USER_IT);
            finestraConfDataBase.setTitleBorder(Language.FINES_CONF_DB_TITLE_BORDER_EN);
            finestraConfDataBase.pack();
            
            finestraConfChat.setTitle(Language.FINES_CONF_CHAT_TITLE_IT);
            finestraConfChat.setTitlePanel(Language.FINES_CONF_CHAT_TITLE_PANEL_IT);
            finestraConfChat.getjLabel5HostName().setText(Language.FINES_CONF_CHAT_LABEL_REC_HOST_NAME_IT);
            finestraConfChat.getjLabel2HostPort().setText(Language.FINES_CONF_CHAT_LABEL_REC_PORT_IT);
            finestraConfChat.getjLabel3ServerPort().setText(Language.FINES_CONF_CHAT_LABEL_SERVER_PORT_IT);
            finestraConfChat.pack();
            
            finestraIstruzioni.setTitle(Language.FINES_ISTRUZIONI_TITLE_IT);
            finestraIstruzioni.pack();
            
            framePrincipale.getPannelloChat().setTitleBorder(Language.PANEL_CHAT_TITLE_IT);
            framePrincipale.getPannelloChat().setTextButtonCancConv(Language.PANEL_CHAT_BUTTON_CANC_CONV_IT);
            framePrincipale.getPannelloChat().setTextButtonSend(Language.PANEL_CHAT_BUTTON_SEND_IT);
            framePrincipale.pack();
            
            finestraSceltaMondiDataBase.setTitle(Language.FINES_MONDI_DB_TITLE_IT);
            
            
        }else if(framePrincipale.isRadioMenuEN())   {
            framePrincipale.setTitle("Game of life");
            framePrincipale.getJMenu5Language().setText("Language");
            framePrincipale.getjMenuItem3NuovoMondo().setText(Language.FRAME_P_TEXT_MENU_NEW_EN);
            framePrincipale.getjMenuItem3NuovoMondo().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_NEW_EN);
            framePrincipale.getjButtonNew().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_NEW_EN);
            
            framePrincipale.getjMenuItem4CaricaMondo().setText(Language.FRAME_P_TEXT_MENU_OPEN_EN);
            framePrincipale.getjMenuItem4CaricaMondo().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_OPEN_EN);
            framePrincipale.getjButtonOpen().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_OPEN_EN);
            
            framePrincipale.getjMenuItem5SalvaMondo().setText(Language.FRAME_P_TEXT_MENU_SAVE_EN);
            framePrincipale.getjMenuItem5SalvaMondo().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_SAVE_EN);
            framePrincipale.getjButtonSave().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_SAVE_EN);
            
            framePrincipale.getjMenuItem9SalvaSuDB().setText(Language.FRAME_P_TEXT_MENU_SAVE_DB_EN);
            framePrincipale.getjMenuItem9SalvaSuDB().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_SAVE_DB_EN);
            
            framePrincipale.getjMenuItem8CaricaDaDB().setText(Language.FRAME_P_TEXT_MENU_OPEN_DB_EN);
            framePrincipale.getjMenuItem8CaricaDaDB().setToolTipText(Language.FRAME_P_TOOLTIP_MENU_OPEN_DB_EN);
            
            framePrincipale.getjMenuItem6Esci().setText(Language.FRAME_P_TEXT_MENU_EXIT_EN);
            
            framePrincipale.getjCheckBoxMenuExample().setText(Language.FRAME_P_TEXT_BUTTON_EXAMPLES_EN);
            
            framePrincipale.getjMenuItem10ConfDB().setText(Language.FRAME_P_TEXT_BUTTON_CONF_DB_EN);
            framePrincipale.getjMenuItem10ConfDB().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_DB_EN);
            
            framePrincipale.getjMenuItem6ConfChat().setText(Language.FRAME_P_TEXT_BUTTON_CONF_CHAT_EN);
            framePrincipale.getjMenuItem6ConfChat().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_CHAT_EN);
            
            framePrincipale.getjMenuItemIstruzioni().setText(Language.FRAME_P_TEXT_BUTTON_ISTRUZIONI_EN);
            framePrincipale.getjMenuItemIstruzioni().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_ISTRUZIONI_EN);
            
            framePrincipale.getjButtonAstronave().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_ASTRONAVE_EN);
            framePrincipale.getjButtonCannoneAlianti().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CANNONE_ALIANTI_EN);
            framePrincipale.getjButtonFreccia().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_FRECCIA_EN);
            framePrincipale.getjButtonLampeggiatore().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_LAMPEGGIATORE_EN);
            framePrincipale.getjButtonRospo().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_RANA_EN);
            
            framePrincipale.getjButtonConfDB().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_DB_EN);
            framePrincipale.getJbuttonConfChat().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_CHAT_EN);
            
            finestraNuovoMondo.getjLabel1NumRighe().setText(Language.FINES_MONDO_TEXT_LABEL_RIGHE_EN);
            finestraNuovoMondo.getjLabel2NumColonne().setText(Language.FINES_MONDO_TEXT_LABEL_COLONNE_EN);
            finestraNuovoMondo.setTitleBorder(Language.FINES_MONDO_TEXT_TITLE_BORDER_EN);
            finestraNuovoMondo.pack();
            
            finestraConfDataBase.setTitle(Language.FINES_CONF_DB_TITLE_EN);
            finestraConfDataBase.getjLabel5Type().setText(Language.FINES_CONF_DB_LABEL_TYPE_EN);
            finestraConfDataBase.getjLabel1DBName().setText(Language.FINES_CONF_DB_LABEL_DB_NAME_EN);
            finestraConfDataBase.getjLabel2Port().setText(Language.FINES_CONF_DB_LABEL_PORT_EN);
            finestraConfDataBase.getjLabel3User().setText(Language.FINES_CONF_DB_LABEL_USER_EN);
            finestraConfDataBase.setTitleBorder(Language.FINES_CONF_DB_TITLE_BORDER_EN);
            finestraConfDataBase.pack();
            
            finestraConfChat.setTitle(Language.FINES_CONF_CHAT_TITLE_EN);
            finestraConfChat.setTitlePanel(Language.FINES_CONF_CHAT_TITLE_PANEL_EN);
            finestraConfChat.getjLabel5HostName().setText(Language.FINES_CONF_CHAT_LABEL_REC_HOST_NAME_EN);
            finestraConfChat.getjLabel2HostPort().setText(Language.FINES_CONF_CHAT_LABEL_REC_PORT_EN);
            finestraConfChat.getjLabel3ServerPort().setText(Language.FINES_CONF_CHAT_LABEL_SERVER_PORT_EN);
            finestraConfChat.pack();
            
            finestraIstruzioni.setTitle(Language.FINES_ISTRUZIONI_TITLE_IT);
            finestraIstruzioni.pack();
            
            framePrincipale.getPannelloChat().setTitleBorder(Language.PANEL_CHAT_TITLE_EN);
            framePrincipale.getPannelloChat().setTextButtonCancConv(Language.PANEL_CHAT_BUTTON_CANC_CONV_EN);
            framePrincipale.getPannelloChat().setTextButtonSend(Language.PANEL_CHAT_BUTTON_SEND_EN);
            framePrincipale.pack();
            
            finestraSceltaMondiDataBase.setTitle(Language.FINES_MONDI_DB_TITLE_EN);
            
        }
    
    }

    /**
     * @param framePrincipale the framePrincipale to set
     */
    public void setFramePrincipale(FramePrincipale framePrincipale) {
        this.framePrincipale = framePrincipale;
    }

    /**
     * @param finestraConfDataBase the finestraConfDataBase to set
     */
    public void setFinestraConfDataBase(FinestraConfDataBase finestraConfDataBase) {
        this.finestraConfDataBase = finestraConfDataBase;
    }

    /**
     * @param finestraConfChat the finestraConfChat to set
     */
    public void setFinestraConfNet(FinestraConfChat finestraConfNet) {
        this.setFinestraConfChat(finestraConfNet);
    }

    /**
     * @param finestraIstruzioni the finestraIstruzioni to set
     */
    public void setFinestraIstruzioni(FinestraIstruzioni finestraIstruzioni) {
        this.finestraIstruzioni = finestraIstruzioni;
    }

    /**
     * @param finestraNuovoMondo the finestraNuovoMondo to set
     */
    public void setFinestraNuovoMondo(FinestraNuovoMondo finestraNuovoMondo) {
        this.finestraNuovoMondo = finestraNuovoMondo;
    }

    /**
     * @param finestraConfChat the finestraConfChat to set
     */
    public void setFinestraConfChat(FinestraConfChat finestraConfChat) {
        this.finestraConfChat = finestraConfChat;
    }

    /**
     * @param finestraSceltaMondiDataBase the finestraSceltaMondiDataBase to set
     */
    public void setFinestraSceltaMondiDataBase(FinestraSceltaMondiDataBase finestraSceltaMondiDataBase) {
        this.finestraSceltaMondiDataBase = finestraSceltaMondiDataBase;
    }
    
    
}
