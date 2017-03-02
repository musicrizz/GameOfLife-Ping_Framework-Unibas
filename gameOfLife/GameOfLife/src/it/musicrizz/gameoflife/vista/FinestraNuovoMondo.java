package it.musicrizz.gameoflife.vista;

import it.unibas.ping.framework.FinestraDiDialogoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.controllo.AzioneNuovoMondo;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class FinestraNuovoMondo extends FinestraDiDialogoPing {
    
    private static Log log = LogFactory.getLog(FinestraNuovoMondo.class);
    
    private Image img;
    
    static {
        setDefaultLookAndFeelDecorated(true);
    }
    
    public void inizializza()   {
        this.setModal(true);
        try{
            img = ImageIO.read(FinestraNuovoMondo.class.getResource(Costanti.ICONA_FRAME));
            if(img != null) this.setIconImage(img);
        }catch(Exception e)   {
            log.debug("Errore nel caricamento dell' immagine del frame -> ",e);
        }
        initComponents();
        initJSlider();
        initButton();
        initActionTextField();
        pack();
    }
    
    private void initButton()   {
        JbuttonOk.setAction(controllo.getAzioneSwing(AzioneNuovoMondo.class.getName()));
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nascondi();
            }
        });
    }
    
    private void initActionTextField()   {
        jTextField1Righe.setAction(controllo.getAzioneSwing(AzioneNuovoMondo.class.getName()));
        jTextField2Colonne.setAction(controllo.getAzioneSwing(AzioneNuovoMondo.class.getName()));
    }
    
    
    @SuppressWarnings("unchecked")
    private void initJSlider()   {
        jSlider1.setMinimum(100);
        jSlider1.setMaximum(1000);
        jSlider1.setMajorTickSpacing(100);
        jSlider1.setMinorTickSpacing(50);
        jSlider1.setPaintTicks(true);
        jSlider1.setSnapToTicks(true);
        jSlider1.setPaintLabels(true);
        Hashtable label = new Hashtable();
        label.put(new Integer(100), new JLabel("100ms"));
        label.put(new Integer(1000),new JLabel("1000ms"));
        jSlider1.setLabelTable(label);
    }
    
    public String getRighe()   {
        return jTextField1Righe.getText();
    }
    
    public String getColonne()   {
        return jTextField2Colonne.getText();
    }
    
    public int getTimer()   {
        return jSlider1.getValue();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1NumRighe = new javax.swing.JLabel();
        jLabel2NumColonne = new javax.swing.JLabel();
        jTextField1Righe = new javax.swing.JTextField();
        jTextField2Colonne = new javax.swing.JTextField();
        jSlider1 = new javax.swing.JSlider();
        JbuttonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imposta i parametri del Mondo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel1NumRighe.setText("Numero Righe : ");

        jLabel2NumColonne.setText("Numero Colonne : ");

        JbuttonOk.setText("jButton1");

        jButtonCancel.setText("jButton2");

        jLabel3.setText("Timer mill");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1NumRighe)
                                    .addComponent(jLabel2NumColonne))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2Colonne)
                                    .addComponent(jTextField1Righe)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonCancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JbuttonOk)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1NumRighe)
                    .addComponent(jTextField1Righe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2NumColonne)
                    .addComponent(jTextField2Colonne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JbuttonOk)
                    .addComponent(jButtonCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbuttonOk;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JLabel jLabel1NumRighe;
    private javax.swing.JLabel jLabel2NumColonne;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jTextField1Righe;
    private javax.swing.JTextField jTextField2Colonne;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jLabel1NumRighe
     */
    public javax.swing.JLabel getjLabel1NumRighe() {
        return jLabel1NumRighe;
    }

    /**
     * @return the jLabel2NumColonne
     */
    public javax.swing.JLabel getjLabel2NumColonne() {
        return jLabel2NumColonne;
    }
    
    public void setTitleBorder(String title)   {
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, 
                title, 
                javax.swing.border.TitledBorder.CENTER, 
                javax.swing.border.TitledBorder.ABOVE_TOP, 
                null, 
                new java.awt.Color(0, 0, 204)));
    }
}
