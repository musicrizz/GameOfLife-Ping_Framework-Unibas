/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.vista;

import it.unibas.ping.framework.FinestraDiDialogoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.Language;
import it.musicrizz.gameoflife.controllo.AzioneOKParametriChat;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import javax.imageio.ImageIO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class FinestraConfChat extends FinestraDiDialogoPing   {

    private Image img;
    private Log log = LogFactory.getLog(FinestraConfChat.class);
    
    static {
        setDefaultLookAndFeelDecorated(true);
    }
    
    @Override
    public void inizializza() {
        this.setTitle(Language.FINES_CONF_CHAT_TITLE_IT);
        
        try{
            img = ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_FRAME));
            if(img != null) this.setIconImage(img);
        }catch(Exception e)   {
            System.out.println("Errore nel caricamento dell' immagine del frame -> "+e);
        }
                
        initComponents();
        initButtonTextFIeld();
        this.setModal(true);
    }

    
    private void initButtonTextFIeld()   {
        jButton1Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nascondi();
            }
        });
        jButton2OK.setAction(controllo.getAzioneSwing(AzioneOKParametriChat.class.getName()));
        
        jTextField1HostName.addActionListener(controllo.getAzioneSwing(AzioneOKParametriChat.class.getName()));
        try{
            jTextField1HostName.setText(InetAddress.getLocalHost().getHostName());
        }catch(Exception e)   {
            log.error("Errore hostName");
        }
        jTextField2PortaRicevente.addActionListener(controllo.getAzioneSwing(AzioneOKParametriChat.class.getName()));
        jTextField3MiaPorta.addActionListener(controllo.getAzioneSwing(AzioneOKParametriChat.class.getName()));
        jTextField1Nick.addActionListener(controllo.getAzioneSwing(AzioneOKParametriChat.class.getName()));
        
    }
    
    
    public String getPortaRicevente()   {
        return jTextField2PortaRicevente.getText();
    }
    
    public String getPortaServer()   {
        return jTextField3MiaPorta.getText();
    }
    
    public String getHostName()   {
        return jTextField1HostName.getText();
    }
    
    public String getNickName()   {
        return this.jTextField1Nick.getText();
    }
    
    public void setTitlePanel(String title)   {
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                          title,
                          javax.swing.border.TitledBorder.CENTER,
                          javax.swing.border.TitledBorder.TOP,
                          null, new java.awt.Color(51, 51, 255)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2HostPort = new javax.swing.JLabel();
        jTextField2PortaRicevente = new javax.swing.JTextField();
        jLabel5HostName = new javax.swing.JLabel();
        jTextField1HostName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3ServerPort = new javax.swing.JLabel();
        jTextField3MiaPorta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton1Cancel = new javax.swing.JButton();
        jButton2OK = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1NickName = new javax.swing.JLabel();
        jTextField1Nick = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ricevente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel2HostPort.setText("Porta : ");

        jTextField2PortaRicevente.setText("64444");
        jTextField2PortaRicevente.setToolTipText("");

        jLabel5HostName.setText("HostName : ");

        jTextField1HostName.setText("computer Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2HostPort)
                    .addComponent(jLabel5HostName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2PortaRicevente, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jTextField1HostName))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5HostName)
                    .addComponent(jTextField1HostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2HostPort)
                    .addComponent(jTextField2PortaRicevente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel3ServerPort.setText("Porta :");

        jTextField3MiaPorta.setText("64444");
        jTextField3MiaPorta.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3ServerPort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jTextField3MiaPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3ServerPort)
                    .addComponent(jTextField3MiaPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1Cancel.setText("Cancel");

        jButton2OK.setText("OK");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1Cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2OK)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1Cancel)
                    .addComponent(jButton2OK))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chat", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1NickName.setText("Nickname :");

        jTextField1Nick.setText("NickName");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1NickName)
                .addGap(33, 33, 33)
                .addComponent(jTextField1Nick)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1NickName)
                    .addComponent(jTextField1Nick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1Cancel;
    private javax.swing.JButton jButton2OK;
    private javax.swing.JLabel jLabel1NickName;
    private javax.swing.JLabel jLabel2HostPort;
    private javax.swing.JLabel jLabel3ServerPort;
    private javax.swing.JLabel jLabel5HostName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1HostName;
    private javax.swing.JTextField jTextField1Nick;
    private javax.swing.JTextField jTextField2PortaRicevente;
    private javax.swing.JTextField jTextField3MiaPorta;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jLabel1NickName
     */
    public javax.swing.JLabel getjLabel1NickName() {
        return jLabel1NickName;
    }

    /**
     * @return the jLabel2HostPort
     */
    public javax.swing.JLabel getjLabel2HostPort() {
        return jLabel2HostPort;
    }

    /**
     * @return the jLabel3ServerPort
     */
    public javax.swing.JLabel getjLabel3ServerPort() {
        return jLabel3ServerPort;
    }

    /**
     * @return the jLabel5HostName
     */
    public javax.swing.JLabel getjLabel5HostName() {
        return jLabel5HostName;
    }
    
}
