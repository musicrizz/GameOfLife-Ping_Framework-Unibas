package it.musicrizz.gameoflife.vista;

import it.musicrizz.gameoflife.Bundle;
import it.unibas.ping.binding.osservatori.OsservatoreTabellaBidim;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.Modello;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.controllo.AzioneTimer;
import it.musicrizz.gameoflife.controllo.ConfigurazioneParametri;
import it.musicrizz.gameoflife.controllo.ListenerMousePannello2D;
import it.musicrizz.gameoflife.controllo.ListenerMouseTabellaPing;
import it.musicrizz.gameoflife.modello.Sistema;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class PannelloScacchiera extends JPanel   {
    
    private Controllo controllo;
    private ListenerMouseTabellaPing mouseTabella;
    private ListenerMousePannello2D mousePannello2D;
    private JScrollPane jScrollPaneTabella;
    private JTable tabella;
    private JButton jButtonTimer;
    private JLabel labelGenerazioiText;
    private JLabel labelGenerazione;
    private JLabel labelPopolazioneText;
    private JLabel labelPopolazione;
    private JPanel pannelloGenerazini;
    private JScrollPane jScrollPanePannello2D;
    private PannelloGraphics2D pannello2D;
    private Timer timer;
    private int contGenerazioni;
    
    public PannelloScacchiera(Controllo c)   {
        controllo = c;
        setBorder(new LineBorder(Color.BLUE));
        setLayout(new BorderLayout(5, 5));
        initTabella();
        initLabelGenerazioni();
        intiButtonTimer();
    }
    
    public void initTabella()   {
        setTabella(new JTable());
        getTabella().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getTabella().setCellSelectionEnabled(true);
        getTabella().getTableHeader().setReorderingAllowed(false);
        getTabella().addMouseListener(null);
        getTabella().setBackground(Color.WHITE);
        getTabella().setForeground(Color.GREEN);
        getTabella().setRowHeight(15);
        getTabella().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPaneTabella = new JScrollPane();
        jScrollPaneTabella.setViewportView(tabella);
        //this.add(getTabella(),BorderLayout.CENTER);
        this.add(jScrollPaneTabella,BorderLayout.CENTER);
        new OsservatoreTabellaBidim(tabella, new ModelloTabella(), Costanti.SISTEMA, Sistema.class, Costanti.MONDO_MATRICE);
    }
    
    public void rimuoviTabella()   {
        if(tabella == null)  return;
        jScrollPaneTabella.remove(tabella);
        this.remove(jScrollPaneTabella);
        disabilitaMouseListenerTabella();
        tabella = null;
        jScrollPaneTabella = null;
        repaint();
    }    
    
    
    public void initPannello2D()   {
        setPannello2D(new PannelloGraphics2D(ConfigurazioneParametri.getInstance().getColonne(), 
                                             ConfigurazioneParametri.getInstance().getRighe(),
                                            (Sistema)controllo.getModello().getBean(Costanti.SISTEMA)));
        jScrollPanePannello2D = new JScrollPane();
        jScrollPanePannello2D.setViewportView(getPannello2D());    
        //this.add(getPannello2D(),BorderLayout.CENTER);
        this.add(jScrollPanePannello2D,BorderLayout.CENTER);
    }
    
    public void rimuoviPannello2D()   {
        if(pannello2D == null) return;
        jScrollPanePannello2D.remove(pannello2D);
        this.remove(jScrollPanePannello2D);
        pannello2D = null;
        jScrollPanePannello2D = null;
        repaint();
    }

    private void intiButtonTimer()   {
        jButtonTimer = new JButton();
        jButtonTimer.setAction(controllo.getAzioneSwing(AzioneTimer.class.getName()));
        add(jButtonTimer,BorderLayout.SOUTH);
    }
    
    private void initLabelGenerazioni()   {
        labelGenerazioiText = new JLabel(Bundle.getString(Costanti.B_LABEL_GENERAZIONI));
        labelGenerazione = new JLabel();
        labelPopolazioneText = new JLabel(Bundle.getString(Costanti.B_LABEL_POPOLAZIONE));
        labelPopolazione = new JLabel();
        pannelloGenerazini = new JPanel();
        pannelloGenerazini.add(labelGenerazioiText);
        pannelloGenerazini.add(labelGenerazione);
        pannelloGenerazini.add(labelPopolazioneText);
        pannelloGenerazini.add(labelPopolazione);
        this.add(pannelloGenerazini,BorderLayout.NORTH);
    }
    
    public void setTextLabelGenerazioni(String s)   {
        labelGenerazione.setText(s);
    }
    
    public void setTextLabelPopolazione(String s)   {
        labelPopolazione.setText(s);
    }
        
    public void abilitaMouseListenerTabella()   {
        try{
            if(getTabella() == null)return;
            if(mouseTabella == null) mouseTabella = new ListenerMouseTabellaPing();       
            getTabella().addMouseListener(mouseTabella);
            getTabella().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }catch(Exception e)   {
            //log debug
        }
    }
    
    public void disabilitaMouseListenerTabella()   {
        try{
            if(getTabella() == null)return;
            if(mouseTabella == null)return;
            getTabella().removeMouseListener(mouseTabella);
            getTabella().setCursor(Cursor.getDefaultCursor());
        }catch(Exception e){
            //log Debug
        }
    
    }
    
    public void abilitaMouseListenerPann2D()   {
        if(getPannello2D() == null) return ;
        try{
            if(mousePannello2D == null) mousePannello2D = new ListenerMousePannello2D(this,controllo);
            getPannello2D().abilitaMouseListener(mousePannello2D);
        }catch(Exception e)  {
            //log Debug
        }
    }
    
    public void disabilitaMouseListeberPann2D()   {
        if(getPannello2D() == null)return;
        try{
            if(mousePannello2D != null) getPannello2D().disabilitaMouseListener(mousePannello2D);
        }catch(Exception e)   {
            //log DEBUG
        }
    }
    
    public void setTextButtonTimer(String s)   {
        jButtonTimer.setText(s);
    }
    
    public String getTextButtonTimer()   {
        return jButtonTimer.getText();
    }
    
    public void setLarghezzaColonne(int numColonne)   {
        TableColumn column = null;
        for (int i = 0; i < numColonne; i++) {
            column = getTabella().getColumnModel().getColumn(i);
            column.setPreferredWidth(10);  
        }   
    }
    
    public void inizializzaTimer(int delay)   {
        contGenerazioni = 0;
        setTextLabelGenerazioni(0+"");
        final Sistema s = (Sistema)controllo.getModello().getBean(Costanti.SISTEMA);
        PannelloScacchiera.this.setTextLabelPopolazione(s.getPopolazione()+"");
        timer = new Timer(delay, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                s.analizzaMondo();
                if(getPannello2D() != null) getPannello2D().ridisegna();
                if(tabella != null) controllo.getModello()
                        .notificaModificaCella(s, Costanti.MONDO_MATRICE, Modello.TUTTE_LE_CELLE, Modello.TUTTE_LE_CELLE);
                PannelloScacchiera.this.setTextLabelGenerazioni(++contGenerazioni+"");
                PannelloScacchiera.this.setTextLabelPopolazione(s.getPopolazione()+"");
            }
        });
    }
    
    public void startTimer()   {
        timer.start();
    }
    
    public void stopTimer()   {
        timer.stop();
    }
    
    public void setDelayTimer(int delay)   {
        timer.setDelay(delay);
    }

    /**
     * @return the pannello2D
     */
    public PannelloGraphics2D getPannello2D() {
        return pannello2D;
    }

    /**
     * @param pannello2D the pannello2D to set
     */
    public void setPannello2D(PannelloGraphics2D pannello2D) {
        this.pannello2D = pannello2D;
    }

    /**
     * @return the tabella
     */
    public JTable getTabella() {
        return tabella;
    }

    /**
     * @param tabella the tabella to set
     */
    public void setTabella(JTable tabella) {
        this.tabella = tabella;
    }
}
