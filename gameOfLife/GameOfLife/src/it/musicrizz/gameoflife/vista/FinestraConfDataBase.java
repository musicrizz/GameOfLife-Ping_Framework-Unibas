/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.vista;

import it.unibas.ping.framework.FinestraDiDialogoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.Language;
import it.musicrizz.gameoflife.controllo.AzioneOKParametriDB;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class FinestraConfDataBase extends FinestraDiDialogoPing    {
    
    private Image img;
    
    static {
        setDefaultLookAndFeelDecorated(true);
    }

    @Override
    public void inizializza() {
        setTitle(Language.FINES_CONF_DB_TITLE_IT);
        try{
            img = ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_FRAME));
            if(img != null) this.setIconImage(img);
        }catch(Exception e)   {
            System.out.println("Errore nel caricamento dell' immagine del frame -> "+e);
        }
                
        this.setModal(true);
        this.initComponents();
        initButton();
        initTextFiel();
    }
    
    private void initButton()   {
        jButton2Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nascondi();
            }
        });
         jButton1OK.setAction(controllo.getAzioneSwing(AzioneOKParametriDB.class.getName()));
    }
    
    private void initTextFiel()   {
        jComboBox1.setEditable(false);
        jPasswordField1.setText("");
        /*
        jTextField1DBName.addActionListener(controllo.getAzioneSwing(AzioneOKParametriDB.class.getName()));
        jTextField2Porta.addActionListener(controllo.getAzioneSwing(AzioneOKParametriDB.class.getName()));
        jTextField3User.addActionListener(controllo.getAzioneSwing(AzioneOKParametriDB.class.getName()));
        jPasswordField1.addActionListener(controllo.getAzioneSwing(AzioneOKParametriDB.class.getName()));
        */
    }
    
    public String getDBNAme()   {
        return jTextField1DBName.getText();
    }
    
    public String getPortaDB()   {
        return jTextField2Porta.getText();
    }
    
    public String getUser()   {
        return jTextField3User.getText();
    }
    
    @SuppressWarnings("unchecked")
    public String getPassw()   {
        return jPasswordField1.getText();
    }
    
    public String getSelectedDB()   {
        return (String)jComboBox1.getSelectedItem();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1DBName = new javax.swing.JLabel();
        jLabel2Port = new javax.swing.JLabel();
        jLabel3User = new javax.swing.JLabel();
        jLabel4PassW = new javax.swing.JLabel();
        jTextField1DBName = new javax.swing.JTextField();
        jTextField2Porta = new javax.swing.JTextField();
        jTextField3User = new javax.swing.JTextField();
        jButton1OK = new javax.swing.JButton();
        jButton2Cancel = new javax.swing.JButton();
        jLabel5Type = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configurazione DB", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1DBName.setText("DB Name :");

        jLabel2Port.setText("Porta :");

        jLabel3User.setText("User :");

        jLabel4PassW.setText("Password :");

        jButton1OK.setText("OK");

        jButton2Cancel.setText("Cancel");

        jLabel5Type.setText("Tipo DataBase :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "postgresql", "sqlserver" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jPasswordField1.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1OK)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(jLabel3User))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5Type)
                                            .addComponent(jLabel1DBName, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addComponent(jLabel2Port, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton2Cancel)
                                    .addComponent(jLabel4PassW))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1DBName)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2Porta)
                            .addComponent(jTextField3User)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5Type)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1DBName)
                    .addComponent(jTextField1DBName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2Port)
                    .addComponent(jTextField2Porta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3User)
                    .addComponent(jTextField3User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4PassW)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1OK)
                    .addComponent(jButton2Cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1OK;
    private javax.swing.JButton jButton2Cancel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1DBName;
    private javax.swing.JLabel jLabel2Port;
    private javax.swing.JLabel jLabel3User;
    private javax.swing.JLabel jLabel4PassW;
    private javax.swing.JLabel jLabel5Type;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1DBName;
    private javax.swing.JTextField jTextField2Porta;
    private javax.swing.JTextField jTextField3User;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jLabel1DBName
     */
    public javax.swing.JLabel getjLabel1DBName() {
        return jLabel1DBName;
    }

    /**
     * @return the jLabel2Port
     */
    public javax.swing.JLabel getjLabel2Port() {
        return jLabel2Port;
    }

    /**
     * @return the jLabel3User
     */
    public javax.swing.JLabel getjLabel3User() {
        return jLabel3User;
    }


    /**
     * @return the jLabel5Type
     */
    public javax.swing.JLabel getjLabel5Type() {
        return jLabel5Type;
    }
    
    public void setTitleBorder(String title)   {
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                                                                       title, 
                                                                       javax.swing.border.TitledBorder.CENTER, 
                                                                       javax.swing.border.TitledBorder.TOP, 
                                                                       null, 
                                                                       new java.awt.Color(51, 51, 255)));
    }
}
