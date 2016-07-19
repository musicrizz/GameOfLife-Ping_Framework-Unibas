package it.musicrizz.gameoflife.vista;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.azioni.AzioneInformazioniApplicazione;
import it.unibas.ping.azioni.AzioneInformazioniPing;
import it.unibas.ping.binding.osservatori.OsservatoreLabel;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.FramePing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.Language;
import it.musicrizz.gameoflife.controllo.AzioneCaricaMondo;
import it.musicrizz.gameoflife.controllo.AzioneFinestraCaricaDB;
import it.musicrizz.gameoflife.controllo.AzioneSalvaMondo;
import it.musicrizz.gameoflife.controllo.AzioneSalvaDB;
import it.musicrizz.gameoflife.controllo.AzioneFinestraNuovoMondo;
import it.musicrizz.gameoflife.controllo.ConfigurazioneParametri;
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
    private JMenuItem jMenuItem6ConfChat;
    private JMenuItem JMenuItem7Chat;
    private JMenuItem jMenuItem8CaricaDaDB;
    private JMenuItem jMenuItem9SalvaSuDB;
    private JMenuItem jMenuItem10ConfDB;
    private JMenuItem jMenuItem6Esci;
    
    private JMenu jSottoMenuExsample;
    private JMenuItem jMenuItemCannoneAlianti;
    private JMenuItem jMenuItemArrow;
    
    private JPopupMenu.Separator jSeparator1;
    private JPopupMenu.Separator jSeparator2;
    private JPopupMenu.Separator JsepSeparator3;
    private JPopupMenu.Separator JSeparator4;
    private JPopupMenu.Separator JSeparator5;
    private JLabel etichettaStato;
    private JToolBar toolbar;
    private JFileChooser fileChooser;
    private JButton jButtonCannoneAlianti;
    private JButton jButtonAstronave;
    private JButton jButtonRospo;
    private JButton jButtonLampeggiatore;
    private JButton jButtonFreccia;
    private PannelloIniziale pannelloIniziale;
    private PannelloScacchiera pannelloScacchiera;
    private JButton jButtonNew;
    private JButton jButtonOpen;
    private JButton jButtonSave;
    private JButton jButtonConfDB;
    private JButton JbuttonConfChat;
    private JCheckBoxMenuItem jCheckBoxMenuToolbar;
    private JCheckBoxMenuItem jCheckBoxMenuExample;
    private JCheckBoxMenuItem jCheckBoxMenuChat;
    private JButton JButtonTabwSing;
    private JButton JButtonGraphics2D;
    private PannelloChat pannelloChat;  
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
        pannelloChat = new PannelloChat(controllo);
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
        setjMenuItem6ConfChat(new JMenuItem());
        JsepSeparator3 = new JPopupMenu.Separator();
        setjMenuItem6Esci(new javax.swing.JMenuItem());
        
        jMenu2Info = new javax.swing.JMenu();
        setjMenuItem1InfoApp(new javax.swing.JMenuItem());
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        setjMenuItem2InfoPing(new javax.swing.JMenuItem());
        setJMenuItem7Chat(new JMenuItem());
        setjMenuItem8CaricaDaDB(new JMenuItem());
        setjMenuItem9SalvaSuDB(new JMenuItem());
        setjMenuItem10ConfDB(new JMenuItem());
        JSeparator4 = new JPopupMenu.Separator();
        JSeparator5 = new JPopupMenu.Separator();
        jMenu3View = new JMenu();
        
        jMenu4Edit = new JMenu();
        setjMenuItemIstruzioni(new JMenuItem());
        jCheckBoxMenuToolbar = new JCheckBoxMenuItem();
        jCheckBoxMenuExample = new JCheckBoxMenuItem();
        jCheckBoxMenuChat = new JCheckBoxMenuItem();

        jSottoMenuExsample = new JMenu(Bundle.getString(Costanti.B_FRAME_P_MENU_FILE_EXAMPLE));
        try{
                jSottoMenuExsample.setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_FRAME))));
            }catch(Exception e)   {
                log.error("Errore caricamento immagine menuConfDB ->\n"+e);
            }
        setjMenuItemCannoneAlianti(new JMenuItem());
        setjMenuItemArrow(new JMenuItem());
        //TODO add other exsample
        

        jMenu1File.setText(Bundle.getString(Costanti.B_FRAME_P_MENU_FILE));

            getjMenuItem3NuovoMondo().setAction(controllo.getAzioneSwing(AzioneFinestraNuovoMondo.class.getName()));
            getjMenuItem3NuovoMondo().setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
            jMenu1File.add(getjMenuItem3NuovoMondo());

            getjMenuItem4CaricaMondo().setAction(controllo.getAzioneSwing(AzioneCaricaMondo.class.getName()));
            getjMenuItem4CaricaMondo().setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
            jMenu1File.add(getjMenuItem4CaricaMondo());

            getjMenuItem5SalvaMondo().setAction(controllo.getAzioneSwing(AzioneSalvaMondo.class.getName()));
            getjMenuItem5SalvaMondo().setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
            jMenu1File.add(getjMenuItem5SalvaMondo());
            jMenu1File.add(jSeparator2);
        
            getjMenuItem8CaricaDaDB().setAction(controllo.getAzioneSwing(AzioneFinestraCaricaDB.class.getName()));
            getjMenuItem8CaricaDaDB().setAccelerator(KeyStroke.getKeyStroke("ctrl shift O"));
            jMenu1File.add(getjMenuItem8CaricaDaDB());
        
            getjMenuItem9SalvaSuDB().setAction(controllo.getAzioneSwing(AzioneSalvaDB.class.getName()));
            getjMenuItem9SalvaSuDB().setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
            jMenu1File.add(getjMenuItem9SalvaSuDB());
        
            jMenu1File.add(JSeparator5); 
            jMenu1File.add(jSottoMenuExsample);
            //TODO add exsample
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
                    int num = JOptionPane.showConfirmDialog(FramePrincipale.this, Bundle.getString(Costanti.B_FRAME_P_MSG_CHIUSURA_FRAME));
                    if(num == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }else{
                        return;
                    }
                }
             });
            jMenu1File.add(getjMenuItem6Esci());

            jMenuBar1.add(jMenu1File);
        
        jMenu3View.setText(Bundle.getString(Costanti.B_FRAME_P_MENU_VIEW));       
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
        jMenu3View.addSeparator();
        getjCheckBoxMenuChat().setText(Bundle.getString(Costanti.B_FRAME_P_MENU_VIEW_CHAT));
        try{
            getjCheckBoxMenuChat().setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_MENU_CHAT))));
        }catch(Exception e)   {
            log.error("Errore caricamento immagine menu Chat ->\n"+e);
        }
            getjCheckBoxMenuChat().addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if(getjCheckBoxMenuChat().isSelected())   {
                        visualizzaChat();
                    }else{
                        nascondiChat();
                    }
                }
            });
            jMenu3View.add(getjCheckBoxMenuChat());
            
        jMenuBar1.add(jMenu3View);
        
        jMenu4Edit.setText(Bundle.getString(Costanti.B_FRAME_P_MENU_EDIT));
            getjMenuItem10ConfDB().setText(Language.FRAME_P_TEXT_BUTTON_CONF_DB_IT);
            getjMenuItem10ConfDB().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_DB_IT);
             getjMenuItem10ConfDB().setAccelerator(KeyStroke.getKeyStroke("ctrl shift D"));
            try{
                getjMenuItem10ConfDB().setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_CONF_DB))));
            }catch(Exception e)   {
                log.error("Errore caricamento immagine menuConfDB ->\n"+e);
            }
            getjMenuItem10ConfDB().addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    vista.visualizzaSottoVista(FinestraConfDataBase.class.getName());
                }
            });
            jMenu4Edit.add(getjMenuItem10ConfDB());
        
            jMenu4Edit.add(JSeparator4);
        
            getjMenuItem6ConfChat().setText(Language.FRAME_P_TEXT_BUTTON_CONF_CHAT_IT);
            getjMenuItem6ConfChat().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_CHAT_IT);
            getjMenuItem6ConfChat().setAccelerator(KeyStroke.getKeyStroke("ctrl shift C"));
            try{
                getjMenuItem6ConfChat().setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_CONF_CHAT))));
            }catch(Exception e)   {
                log.error("Errore caricamento immagine menuConfChat ->\n"+e);
            }
            getjMenuItem6ConfChat().addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    vista.visualizzaSottoVista(FinestraConfChat.class.getName());
                }
            });
        jMenu4Edit.add(getjMenuItem6ConfChat());
     
        jMenuBar1.add(jMenu4Edit);
        
            
        jMenu2Info.setText("?");
            getjMenuItemIstruzioni().setText(Language.FRAME_P_TEXT_BUTTON_ISTRUZIONI_IT);
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
        jButtonNew = new JButton();
        jButtonOpen = new JButton();
        jButtonSave = new JButton();
        jButtonConfDB = new JButton();
        JbuttonConfChat = new JButton();
        JButtonGraphics2D = new JButton(); 
        JButtonTabwSing = new JButton();
        
        try{
            JButtonTabwSing.setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_TAB_SWING))));
        }catch(Exception e)   {
            log.error("Errore caricamento immagine bottoneTabSwing");
        }
        
        try{
            JButtonGraphics2D.setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_GRAPHICS2D))));
        }catch(Exception e)   {
            log.error("Errore caricamento immagine bottoneTabSwing");
        }       
        
        toolbar = new JToolBar();
        
        getjButtonNew().setAction(controllo.getAzioneSwing(AzioneFinestraNuovoMondo.class.getName()));
        getjButtonNew().setText("");

        
        getjButtonOpen().setAction(controllo.getAzioneSwing(AzioneCaricaMondo.class.getName()));
        getjButtonOpen().setText("");

        
        getjButtonSave().setAction(controllo.getAzioneSwing(AzioneSalvaMondo.class.getName()));
        getjButtonSave().setText("");
        
        
        getjButtonConfDB().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                vista.visualizzaSottoVista(FinestraConfDataBase.class.getName());
            }
        });
        try{
            getjButtonConfDB().setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_CONF_DB))));
        }catch(Exception e)   {
            log.error("Errore caricamento immagine ButtonConfDB");
        }
        getjButtonConfDB().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_DB_IT);
        
        getJbuttonConfChat().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                vista.visualizzaSottoVista(FinestraConfChat.class.getName());
            }
        });
        try{
            getJbuttonConfChat().setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_CONF_CHAT))));
        }catch(Exception e)   {
            log.error("Errore caricamento immagine ButtonConfCHAT");
        }
        getJbuttonConfChat().setToolTipText(Language.FRAME_P_TOOLTIP_BUTTON_CONF_CHAT_IT);
        
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
        
        JButtonTabwSing.setToolTipText("Visualizzazzione con JTable swing");
        JButtonTabwSing.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(pannelloScacchiera == null) return;
                if(pannelloScacchiera.getPannello2D() != null)   {
                    pannelloScacchiera.disabilitaMouseListeberPann2D();
                    pannelloScacchiera.getPannello2D().disablePainting();
                    pannelloScacchiera.rimuoviPannello2D();
                    pannelloScacchiera.initTabella();
                    pannelloScacchiera.abilitaMouseListenerTabella();
                    pannelloScacchiera.setLarghezzaColonne(ConfigurazioneParametri.getInstance().getColonne());
                    FramePrincipale.this.pack();
                    
                }
            }
        });
        
        JButtonGraphics2D.setToolTipText("Visualizzazione con java 2D");
        JButtonGraphics2D.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(pannelloScacchiera == null) return;
                if(pannelloScacchiera.getTabella() != null)  {
                    int w = FramePrincipale.this.getSize().width+50;
                    int h = FramePrincipale.this.getSize().height+50;
                    Dimension d = new Dimension(w, h);
                    pannelloScacchiera.rimuoviTabella();
                    pannelloScacchiera.initPannello2D();
                    pannelloScacchiera.getPannello2D().enablePainting();
                    pannelloScacchiera.getPannello2D().ridisegna();
                    pannelloScacchiera.abilitaMouseListenerPann2D();
                    FramePrincipale.this.setSize(d);
                }
            }
        });
        
        try{
            JButtonTabwSing.setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_TAB_SWING))));
        }catch(Exception e)   {
            log.error("Errore caricamento immagine bottoneTabSwing");
        }
        
        try{
            JButtonGraphics2D.setIcon(new ImageIcon(ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_BOTTONE_GRAPHICS2D))));
        }catch(Exception e)   {
            log.error("Errore caricamento immagine bottoneTabSwing");
        }
     
        toolbar.add(getjButtonNew());toolbar.addSeparator();
        toolbar.add(getjButtonOpen());toolbar.addSeparator();
        toolbar.add(getjButtonSave());toolbar.addSeparator();
        toolbar.add(getjButtonConfDB());toolbar.addSeparator();
        toolbar.add(getJbuttonConfChat());toolbar.addSeparator();
        toolbar.add(getSliderTime());
        toolbar.addSeparator();
        labelModVisDesc = new JLabel("Display mode");
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
    
    private void visualizzaChat()   {
        getContentPane().add(getPannelloChat(),BorderLayout.EAST);
        pack();
    }
    
    private void nascondiChat()   {
        getContentPane().remove(getPannelloChat());
        pack();
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
    
    public String getMessageChat()   {
        return getPannelloChat().getMessage();
    }
    
    public void pulisciTextAreaChat()   {
        getPannelloChat().pulisciTextArea();
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
     * @return the jMenuItem6ConfChat
     */
    public JMenuItem getjMenuItem6ConfChat() {
        return jMenuItem6ConfChat;
    }

    /**
     * @param jMenuItem6ConfChat the jMenuItem6ConfChat to set
     */
    public void setjMenuItem6ConfChat(JMenuItem jMenuItem6ConfChat) {
        this.jMenuItem6ConfChat = jMenuItem6ConfChat;
    }

    /**
     * @return the JMenuItem7Chat
     */
    public JMenuItem getJMenuItem7Chat() {
        return JMenuItem7Chat;
    }

    /**
     * @param JMenuItem7Chat the JMenuItem7Chat to set
     */
    public void setJMenuItem7Chat(JMenuItem JMenuItem7Chat) {
        this.JMenuItem7Chat = JMenuItem7Chat;
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

    /**
     * @return the jMenuItem10ConfDB
     */
    public JMenuItem getjMenuItem10ConfDB() {
        return jMenuItem10ConfDB;
    }

    /**
     * @param jMenuItem10ConfDB the jMenuItem10ConfDB to set
     */
    public void setjMenuItem10ConfDB(JMenuItem jMenuItem10ConfDB) {
        this.jMenuItem10ConfDB = jMenuItem10ConfDB;
    }

    /**
     * @return the jMenuItem6Esci
     */
    public JMenuItem getjMenuItem6Esci() {
        return jMenuItem6Esci;
    }

    /**
     * @param jMenuItem6Esci the jMenuItem6Esci to set
     */
    public void setjMenuItem6Esci(JMenuItem jMenuItem6Esci) {
        this.jMenuItem6Esci = jMenuItem6Esci;
    }

    /**
     * @return the jButtonCannoneAlianti
     */
    public JButton getjButtonCannoneAlianti() {
        return jButtonCannoneAlianti;
    }

    /**
     * @return the jButtonAstronave
     */
    public JButton getjButtonAstronave() {
        return jButtonAstronave;
    }

    /**
     * @return the jButtonRospo
     */
    public JButton getjButtonRospo() {
        return jButtonRospo;
    }

    /**
     * @return the jButtonLampeggiatore
     */
    public JButton getjButtonLampeggiatore() {
        return jButtonLampeggiatore;
    }

    /**
     * @return the jButtonFreccia
     */
    public JButton getjButtonFreccia() {
        return jButtonFreccia;
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
     * @return the jButtonConfDB
     */
    public JButton getjButtonConfDB() {
        return jButtonConfDB;
    }

    /**
     * @return the JbuttonConfChat
     */
    public JButton getJbuttonConfChat() {
        return JbuttonConfChat;
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
     * @return the jCheckBoxMenuChat
     */
    public JCheckBoxMenuItem getjCheckBoxMenuChat() {
        return jCheckBoxMenuChat;
    }

    /**
     * @return the pannelloChat
     */
    public PannelloChat getPannelloChat() {
        return pannelloChat;
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

    /**
     * @return the jMenuItemCannoneAlianti
     */
    public JMenuItem getjMenuItemCannoneAlianti() {
        return jMenuItemCannoneAlianti;
    }

    /**
     * @param jMenuItemCannoneAlianti the jMenuItemCannoneAlianti to set
     */
    public void setjMenuItemCannoneAlianti(JMenuItem jMenuItemCannoneAlianti) {
        this.jMenuItemCannoneAlianti = jMenuItemCannoneAlianti;
    }

    /**
     * @return the jMenuItemArrow
     */
    public JMenuItem getjMenuItemArrow() {
        return jMenuItemArrow;
    }

    /**
     * @param jMenuItemArrow the jMenuItemArrow to set
     */
    public void setjMenuItemArrow(JMenuItem jMenuItemArrow) {
        this.jMenuItemArrow = jMenuItemArrow;
    }
}
