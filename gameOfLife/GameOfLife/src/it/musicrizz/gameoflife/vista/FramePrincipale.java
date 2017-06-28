package it.musicrizz.gameoflife.vista;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.azioni.AzioneInformazioniApplicazione;
import it.unibas.ping.azioni.AzioneInformazioniPing;
import it.unibas.ping.binding.osservatori.OsservatoreLabel;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.FramePing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.controllo.AzioneCaricaEsempio;
import it.musicrizz.gameoflife.controllo.AzioneCaricaMondo;
import it.musicrizz.gameoflife.controllo.AzioneFinestraCaricaDB;
import it.musicrizz.gameoflife.controllo.AzioneSalvaMondo;
import it.musicrizz.gameoflife.controllo.AzioneSalvaDB;
import it.musicrizz.gameoflife.controllo.AzioneFinestraNuovoMondo;
import it.musicrizz.gameoflife.controllo.AzioneGraphics2D;
import it.musicrizz.gameoflife.controllo.AzioneTabSwing;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
@SuppressWarnings("unchecked")
public class FramePrincipale extends FramePing {
    
    private JMenu jMenu1File;
    private JMenu jMenu2Info;
    private JMenu jMenu3View;
    private JMenu jMenu4Edit;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItemIstruzioni;
    private JMenuItem jMenuItem1InfoApp;
    private JMenuItem jMenuItem2InfoPing;
    private JMenuItem jMenuItem3NuovoMondo;
    private JMenuItem jMenuItem4CaricaMondo;
    private JMenuItem jMenuItem5SalvaMondo;
    private JMenuItem jMenuItem8CaricaDaDB;
    private JMenuItem jMenuItem9SalvaSuDB;
    private JMenuItem jMenuItem6Esci;
    private JMenu jSottoMenuExsample;
    private JMenuItem jMenuItemCannoneAlianti;
    private JMenuItem jMenuItemLightSpaceShip;  
    private ButtonGroup viewModeGroup;
    private JRadioButtonMenuItem view2D;
    private JRadioButtonMenuItem viewTab;
    
    private JPopupMenu.Separator jSeparator1;
    private JPopupMenu.Separator jSeparator2;
    private JPopupMenu.Separator JsepSeparator3;
    private JPopupMenu.Separator JSeparator5;
    private JLabel etichettaStato;
    private JToolBar toolbar;
    private JFileChooser fileChooser;
    private PannelloIniziale pannelloIniziale;
    private PannelloScacchiera pannelloScacchiera;
    private JButton jButtonNew;
    private JButton jButtonOpen;
    private JButton jButtonSave;
    private JCheckBoxMenuItem jCheckBoxMenuToolbar;
    private JCheckBoxMenuItem jCheckBoxMenuExample;
    private JButton JButtonTabwSing;
    private JButton JButtonGraphics2D;
    private Image img;
    private JSlider sliderTime;
    private JLabel labelModVisDesc;
    
    private static Log log = LogFactory.getLog(FramePrincipale.class);
    
    static{
        setDefaultLookAndFeelDecorated(true);
    }
    
    public void inizializza()   {
        this.setTitle(Bundle.getString(Costanti.B_FRAME_P_TITLE));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int num = JOptionPane.showConfirmDialog(FramePrincipale.this, Bundle.getString(Costanti.B_FRAME_P_MSG_CHIUSURA_FRAME));
                if(num == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }else{
                    return;
                }
            }
        });
        
        try{
            img = ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_FRAME));
            if(img != null) this.setIconImage(img);
        }catch(Exception e)   {
            log.error("Errore nel caricamento dell' immagine del frame -> "+e);
        }      
        Toolkit tol = Toolkit.getDefaultToolkit();
        Dimension d = tol.getScreenSize();
        int altezza = d.height;
        int larghezza = d.width;
        setLocation(larghezza/4, altezza/4); 
        initMenu();
        inizializzaFileChooser();
        pannelloIniziale = new PannelloIniziale();
        this.getContentPane().add(pannelloIniziale,BorderLayout.CENTER);
        initMenuToolBar();
        initEtichettaStato();
        this.pack();        
    }
    
    private void initMenu()   {
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1File = new javax.swing.JMenu();
        setjMenuItem3NuovoMondo(new javax.swing.JMenuItem());
        setjMenuItem4CaricaMondo(new javax.swing.JMenuItem());
        setjMenuItem5SalvaMondo(new javax.swing.JMenuItem());
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        setjMenuItem8CaricaDaDB(new JMenuItem());
        setjMenuItem9SalvaSuDB(new JMenuItem());
        JsepSeparator3 = new JPopupMenu.Separator();
        jSottoMenuExsample = new JMenu(Bundle.getString(Costanti.B_FRAME_P_MENU_FILE_EXAMPLE));
        try{
            jSottoMenuExsample.setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_FRAME_16))));
        }catch(Exception e)   {
            log.error("Errore caricamento immagine menuConfDB ->\n"+e);
        }
        setjMenuItem6Esci(new javax.swing.JMenuItem());
        
        jMenu2Info = new javax.swing.JMenu();
        setjMenuItem1InfoApp(new javax.swing.JMenuItem());
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        setjMenuItem2InfoPing(new javax.swing.JMenuItem());
                
        JSeparator5 = new JPopupMenu.Separator();
        jMenu3View = new JMenu();
        
        jMenu4Edit = new JMenu();
        setjMenuItemIstruzioni(new JMenuItem());
        jCheckBoxMenuToolbar = new JCheckBoxMenuItem();

        //MENU FILE
        jMenu1File.setText(Bundle.getString(Costanti.B_FRAME_P_MENU_FILE));
        //menu item new
            getjMenuItem3NuovoMondo().setAction(controllo.getAzioneSwing(AzioneFinestraNuovoMondo.class.getName()));
            getjMenuItem3NuovoMondo().setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
            getjMenuItem3NuovoMondo().setText(Bundle.getString(Costanti.FRAME_P_TEXT_MENU_NEW));
            getjMenuItem3NuovoMondo().setToolTipText(Bundle.getString(Costanti.FRAME_P_TOOLTIP_MENU_NEW));
            jMenu1File.add(getjMenuItem3NuovoMondo());
        //menu item load file
            getjMenuItem4CaricaMondo().setAction(controllo.getAzioneSwing(AzioneCaricaMondo.class.getName()));
            getjMenuItem4CaricaMondo().setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
            getjMenuItem4CaricaMondo().setText(Bundle.getString(Costanti.FRAME_P_TEXT_MENU_OPEN));
            getjMenuItem4CaricaMondo().setToolTipText(Bundle.getString(Costanti.FRAME_P_TOOLTIP_MENU_OPEN));
            jMenu1File.add(getjMenuItem4CaricaMondo());
        //menu utem save file
            getjMenuItem5SalvaMondo().setAction(controllo.getAzioneSwing(AzioneSalvaMondo.class.getName()));
            getjMenuItem5SalvaMondo().setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
            getjMenuItem5SalvaMondo().setText(Bundle.getString(Costanti.FRAME_P_TEXT_MENU_SAVE));
            getjMenuItem5SalvaMondo().setToolTipText(Bundle.getString(Costanti.FRAME_P_TOOLTIP_MENU_SAVE));
            jMenu1File.add(getjMenuItem5SalvaMondo());
            
            jMenu1File.add(jSeparator2);
        
            //TODO load from db
            getjMenuItem8CaricaDaDB().setAction(controllo.getAzioneSwing(AzioneFinestraCaricaDB.class.getName()));
            getjMenuItem8CaricaDaDB().setAccelerator(KeyStroke.getKeyStroke("ctrl shift O"));
            getjMenuItem8CaricaDaDB().setText(Bundle.getString(Costanti.FRAME_P_TEXT_MENU_OPEN_DB));
            getjMenuItem8CaricaDaDB().setToolTipText(Bundle.getString(Costanti.FRAME_P_TOOLTIP_MENU_OPEN_DB));
            jMenu1File.add(getjMenuItem8CaricaDaDB());
            //TODO save to db
            getjMenuItem9SalvaSuDB().setAction(controllo.getAzioneSwing(AzioneSalvaDB.class.getName()));
            getjMenuItem9SalvaSuDB().setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
            getjMenuItem9SalvaSuDB().setText(Bundle.getString(Costanti.FRAME_P_TEXT_MENU_SAVE_DB));
            getjMenuItem9SalvaSuDB().setToolTipText(Bundle.getString(Costanti.FRAME_P_TOOLTIP_MENU_SAVE_DB));
            jMenu1File.add(getjMenuItem9SalvaSuDB());
        
            jMenu1File.add(JSeparator5); 
            
            //menu item example
            jMenu1File.add(jSottoMenuExsample);
                jMenuItemCannoneAlianti = new JMenuItem();
                jMenuItemCannoneAlianti.setName(Costanti.ESEMPIO_CANNONE_ALIANTE_ID);
                jMenuItemCannoneAlianti.setAction(controllo.getAzioneSwing(AzioneCaricaEsempio.class.getName()));
                jMenuItemCannoneAlianti.setText(Bundle.getString(Costanti.FRAME_P_EXAMPLE_ALIANTE));
                jMenuItemCannoneAlianti.setToolTipText(Bundle.getString(Costanti.FRAME_P_TOOLTIP_EXAMPLE));
                try{
                    jMenuItemCannoneAlianti.setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_EXAMPLE_CANN_GLINDER))));
                }catch(Exception e)   {
                    log.error("Errore caricamento immagine menu esempio cannone alianti ->\n"+e);
                }
                jSottoMenuExsample.add(jMenuItemCannoneAlianti);
                
                jMenuItemLightSpaceShip = new JMenuItem();
                jMenuItemLightSpaceShip.setName(Costanti.ESEMPIO_ASTRONAVE_LEGGERA_ID);
                jMenuItemLightSpaceShip.setAction(controllo.getAzioneSwing(AzioneCaricaEsempio.class.getName()));
                jMenuItemLightSpaceShip.setText(Bundle.getString(Costanti.FRAME_P_EXAMPLE_ASTRONAVE));
                jMenuItemLightSpaceShip.setToolTipText(Bundle.getString(Costanti.FRAME_P_TOOLTIP_EXAMPLE));
                try{
                    jMenuItemLightSpaceShip.setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_EXAMPLE_CANN_GLINDER))));
                }catch(Exception e)   {
                    log.error("Errore caricamento immagine menu esempio astonave leggera ->\n"+e);
                }
                jSottoMenuExsample.add(jMenuItemLightSpaceShip);
                
            jMenu1File.add(JsepSeparator3);
        
            getjMenuItem6Esci().setText(Bundle.getString(Costanti.B_FRAME_P_MENU_FILE_EXIT));
            getjMenuItem6Esci().setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
            
            try{
                getjMenuItem6Esci().setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_EXIT))));
            }catch(Exception e)   {
                log.error("Errore caricamento immagine menu Esci ->\n"+e);
            }
            getjMenuItem6Esci().addActionListener(new ActionListener() {

              public void actionPerformed(ActionEvent e) {                
                    int num = JOptionPane.showConfirmDialog(FramePrincipale.this,
                            Bundle.getString(Costanti.B_FRAME_P_MSG_CHIUSURA_FRAME),
                            "Exit",
                            JOptionPane.YES_NO_OPTION);
                    if(num == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }else{
                        return;
                    }
                }
             });
            jMenu1File.add(getjMenuItem6Esci());

        jMenuBar1.add(jMenu1File);
        
        //MENU VIEW
        jMenu3View.setText(Bundle.getString(Costanti.B_FRAME_P_MENU_VIEW));      
            viewModeGroup = new ButtonGroup();
            viewTab = new JRadioButtonMenuItem();
            view2D = new JRadioButtonMenuItem();
            viewTab.setAction(controllo.getAzioneSwing(AzioneTabSwing.class.getName()));
            viewTab.setText(Bundle.getString(Costanti.VIEW_TAB_MENU_LABEL));
            viewTab.setToolTipText(Bundle.getString(Costanti.VIEW_MODE_TABSWING_TOOLTIP));
            view2D.setAction(controllo.getAzioneSwing(AzioneGraphics2D.class.getName()));
            view2D.setText(Bundle.getString(Costanti.VIEW_2D_MENU_LABEL));
            view2D.setToolTipText(Bundle.getString(Costanti.VIEW_MODE_TABSWING_TOOLTIP));
            viewModeGroup.add(viewTab);
            viewModeGroup.add(view2D);
            viewTab.setSelected(true);
            jMenu3View.add(viewTab);
            jMenu3View.add(view2D);
            
            jMenu3View.addSeparator();
         
            getjCheckBoxMenuToolbar().setText(Bundle.getString(Costanti.B_FRAME_P_MENU_VIEW_TOOLB));
            try{
                getjCheckBoxMenuToolbar().setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_TOOL_BAR))));
            }catch(Exception e)   {
                log.error("Errore caricamento immagine toolBarMenu ->\n"+e);
            }
            getjCheckBoxMenuToolbar().setSelected(true);
            getjCheckBoxMenuToolbar().addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    if(getjCheckBoxMenuToolbar().isSelected())   {
                        visualizzaMenuToolBar();
                    }else{
                        nascondiMenuToolBar();
                    }
                }
            });
            jMenu3View.add(getjCheckBoxMenuToolbar());
                
            jMenuBar1.add(jMenu3View);
            
        //MENU EDIT
     
        jMenuBar1.add(jMenu4Edit);
        
            
        jMenu2Info.setText("?");
            getjMenuItemIstruzioni().setText(Bundle.getString(Costanti.FINES_ISTRUZIONI_TITLE));
            getjMenuItemIstruzioni().setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
            getjMenuItemIstruzioni().addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    vista.visualizzaSottoVista(FinestraIstruzioni.class.getName());
                }
            });
         jMenu2Info.add(getjMenuItemIstruzioni());
        
            getjMenuItem1InfoApp().setText(Bundle.getString(Costanti.B_FRAME_P_MENU_INFO_APP));
            getjMenuItem1InfoApp().setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        
            getjMenuItem1InfoApp().addActionListener(controllo.getAzioneSwing(AzioneInformazioniApplicazione.class.getName()));
            jMenu2Info.add(getjMenuItem1InfoApp());
            jMenu2Info.add(jSeparator1);

            getjMenuItem2InfoPing().setText(Bundle.getString(Costanti.B_FRAME_P_MENU_INFO_PING));
            getjMenuItem2InfoPing().setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
            getjMenuItem2InfoPing().addActionListener(controllo.getAzioneSwing(AzioneInformazioniPing.class.getName()));
            jMenu2Info.add(getjMenuItem2InfoPing());

            jMenuBar1.add(jMenu2Info);

            setJMenuBar(jMenuBar1);
    }
    
    private void initEtichettaStato()   {
        etichettaStato = new JLabel();
        this.getContentPane().add(etichettaStato, BorderLayout.SOUTH);
        new OsservatoreLabel(etichettaStato, Controllo.MESSAGGIO_STATO, Controllo.VALORE_MESSAGGIO);
    }
    
    private void initMenuToolBar()   {
        toolbar = new JToolBar();
        
        jButtonNew = new JButton();
        jButtonOpen = new JButton();
        jButtonSave = new JButton();
        JButtonGraphics2D = new JButton(); 
        JButtonTabwSing = new JButton();
        
        getjButtonNew().setAction(controllo.getAzioneSwing(AzioneFinestraNuovoMondo.class.getName()));
        getjButtonNew().setText("");
        
        getjButtonOpen().setAction(controllo.getAzioneSwing(AzioneCaricaMondo.class.getName()));
        getjButtonOpen().setText("");
  
        getjButtonSave().setAction(controllo.getAzioneSwing(AzioneSalvaMondo.class.getName()));
        getjButtonSave().setText("");      
        
        setSliderTime(new JSlider());
        getSliderTime().setMinimum(100);
        getSliderTime().setMaximum(1000);
        getSliderTime().setMajorTickSpacing(100);
        getSliderTime().setMinorTickSpacing(50);
        getSliderTime().setPaintTicks(true);
        getSliderTime().setSnapToTicks(true);
        getSliderTime().setPaintLabels(true);
        Hashtable label = new Hashtable();
        label.put(new Integer(100), new JLabel("100ms"));
        label.put(new Integer(1000),new JLabel("1000ms"));
        getSliderTime().setLabelTable(label);
        getSliderTime().addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if(getPannelloScacchiera()==null)return;
                getPannelloScacchiera().setDelayTimer(getSliderTime().getValue());
            }
        }); 
        
        JButtonTabwSing.setAction(controllo.getAzioneSwing(AzioneTabSwing.class.getName()));
        JButtonTabwSing.setText("");
        JButtonTabwSing.setToolTipText(Bundle.getString(Costanti.VIEW_MODE_TABSWING_TOOLTIP));
        
        JButtonGraphics2D.setAction(controllo.getAzioneSwing(AzioneGraphics2D.class.getName()));
        JButtonTabwSing.setText("");
        JButtonGraphics2D.setToolTipText(Bundle.getString(Costanti.VIEW_MODE_2D_TOOLTIP));
     
        toolbar.add(getjButtonNew());toolbar.addSeparator();
        toolbar.add(getjButtonOpen());toolbar.addSeparator();
        toolbar.add(getjButtonSave());toolbar.addSeparator();
        toolbar.add(getSliderTime());
        toolbar.addSeparator();
        labelModVisDesc = new JLabel(Bundle.getString(Costanti.VIEW_MODE_LABEL));
        toolbar.add(labelModVisDesc);
        toolbar.addSeparator();
        toolbar.add(JButtonTabwSing);
        toolbar.addSeparator();
        toolbar.add(JButtonGraphics2D);
        toolbar.addSeparator();
        visualizzaMenuToolBar();
        
    }
    
    public void cambiaPannello()   {
        try{
            this.getContentPane().remove(pannelloScacchiera);
            pannelloScacchiera = new PannelloScacchiera(this.controllo);
            this.getContentPane().remove(pannelloIniziale);
            this.getContentPane().add(pannelloScacchiera,BorderLayout.CENTER);
            pack();
        }catch(Exception e)   {
            pannelloScacchiera = new PannelloScacchiera(this.controllo);
            this.getContentPane().remove(pannelloIniziale);
            this.getContentPane().add(pannelloScacchiera,BorderLayout.CENTER);
            pack();
        }

    }
    
    private void visualizzaMenuToolBar()   {
        getContentPane().add(toolbar,BorderLayout.NORTH);
        pack();
    }
    
    private void nascondiMenuToolBar()   {
        getContentPane().remove(toolbar);
        pack();
    }
    
    public PannelloScacchiera getPannelloScacchiera()   {
        return  pannelloScacchiera;
    } 
    
    private void inizializzaFileChooser()   {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Properties (default)", "properties"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML", "xml"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        
    }
    
    public JFileChooser getFileChooser()   {
        return this.fileChooser;
    }
    
    /**
     * @return the jMenuItemIstruzioni
     */
    public JMenuItem getjMenuItemIstruzioni() {
        return jMenuItemIstruzioni;
    }

    /**
     * @param jMenuItemIstruzioni the jMenuItemIstruzioni to set
     */
    public void setjMenuItemIstruzioni(JMenuItem jMenuItemIstruzioni) {
        this.jMenuItemIstruzioni = jMenuItemIstruzioni;
    }

    /**
     * @return the jMenuItem1InfoApp
     */
    public JMenuItem getjMenuItem1InfoApp() {
        return jMenuItem1InfoApp;
    }

    /**
     * @param jMenuItem1InfoApp the jMenuItem1InfoApp to set
     */
    public void setjMenuItem1InfoApp(JMenuItem jMenuItem1InfoApp) {
        this.jMenuItem1InfoApp = jMenuItem1InfoApp;
    }

    /**
     * @return the jMenuItem2InfoPing
     */
    public JMenuItem getjMenuItem2InfoPing() {
        return jMenuItem2InfoPing;
    }

    /**
     * @param jMenuItem2InfoPing the jMenuItem2InfoPing to set
     */
    public void setjMenuItem2InfoPing(JMenuItem jMenuItem2InfoPing) {
        this.jMenuItem2InfoPing = jMenuItem2InfoPing;
    }

    /**
     * @return the jMenuItem3NuovoMondo
     */
    public JMenuItem getjMenuItem3NuovoMondo() {
        return jMenuItem3NuovoMondo;
    }

    /**
     * @param jMenuItem3NuovoMondo the jMenuItem3NuovoMondo to set
     */
    public void setjMenuItem3NuovoMondo(JMenuItem jMenuItem3NuovoMondo) {
        this.jMenuItem3NuovoMondo = jMenuItem3NuovoMondo;
    }

    /**
     * @return the jMenuItem4CaricaMondo
     */
    public JMenuItem getjMenuItem4CaricaMondo() {
        return jMenuItem4CaricaMondo;
    }

    /**
     * @param jMenuItem4CaricaMondo the jMenuItem4CaricaMondo to set
     */
    public void setjMenuItem4CaricaMondo(JMenuItem jMenuItem4CaricaMondo) {
        this.jMenuItem4CaricaMondo = jMenuItem4CaricaMondo;
    }

    /**
     * @return the jMenuItem5SalvaMondo
     */
    public JMenuItem getjMenuItem5SalvaMondo() {
        return jMenuItem5SalvaMondo;
    }

    /**
     * @param jMenuItem5SalvaMondo the jMenuItem5SalvaMondo to set
     */
    public void setjMenuItem5SalvaMondo(JMenuItem jMenuItem5SalvaMondo) {
        this.jMenuItem5SalvaMondo = jMenuItem5SalvaMondo;
    }

    /**
     * @return the jMenuItem8CaricaDaDB
     */
    public JMenuItem getjMenuItem8CaricaDaDB() {
        return jMenuItem8CaricaDaDB;
    }

    /**
     * @param jMenuItem8CaricaDaDB the jMenuItem8CaricaDaDB to set
     */
    public void setjMenuItem8CaricaDaDB(JMenuItem jMenuItem8CaricaDaDB) {
        this.jMenuItem8CaricaDaDB = jMenuItem8CaricaDaDB;
    }

    /**
     * @return the jMenuItem9SalvaSuDB
     */
    public JMenuItem getjMenuItem9SalvaSuDB() {
        return jMenuItem9SalvaSuDB;
    }

    /**
     * @param jMenuItem9SalvaSuDB the jMenuItem9SalvaSuDB to set
     */
    public void setjMenuItem9SalvaSuDB(JMenuItem jMenuItem9SalvaSuDB) {
        this.jMenuItem9SalvaSuDB = jMenuItem9SalvaSuDB;
    }
    
    public JMenuItem getjMenuItem6Esci() {
        return jMenuItem6Esci;
    }

    public void setjMenuItem6Esci(JMenuItem jMenuItem6Esci) {
        this.jMenuItem6Esci = jMenuItem6Esci;
    }

    
    /**
     * @return the jButtonNew
     */
    public JButton getjButtonNew() {
        return jButtonNew;
    }

    /**
     * @return the jButtonOpen
     */
    public JButton getjButtonOpen() {
        return jButtonOpen;
    }

    /**
     * @return the jButtonSave
     */
    public JButton getjButtonSave() {
        return jButtonSave;
    }

    /**
     * @return the jCheckBoxMenuToolbar
     */
    public JCheckBoxMenuItem getjCheckBoxMenuToolbar() {
        return jCheckBoxMenuToolbar;
    }

    /**
     * @return the jCheckBoxMenuExample
     */
    public JCheckBoxMenuItem getjCheckBoxMenuExample() {
        return jCheckBoxMenuExample;
    }

    /**
     * @return the sliderTime
     */
    public JSlider getSliderTime() {
        return sliderTime;
    }

    /**
     * @param sliderTime the sliderTime to set
     */
    public void setSliderTime(JSlider sliderTime) {
        this.sliderTime = sliderTime;
    }

    /**
     * @return the modVisDesc
     */
    public JLabel getModVisDesc() {
        return labelModVisDesc;
    }

    /**
     * @param modVisDesc the modVisDesc to set
     */
    public void setModVisDesc(JLabel modVisDesc) {
        this.labelModVisDesc = modVisDesc;
    }


}
