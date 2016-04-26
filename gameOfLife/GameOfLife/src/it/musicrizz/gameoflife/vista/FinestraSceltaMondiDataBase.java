/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.gameoflife.vista;

import it.unibas.ping.framework.FinestraDiDialogoPing;
import it.musicrizz.gameoflife.Costanti;
import it.musicrizz.gameoflife.Language;
import it.musicrizz.gameoflife.controllo.AzioneOKSceltaMondoDB;
import it.musicrizz.gameoflife.persistenza.Descrizione;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Grandinetti Giovanni <musicrizz@hotmail.it>
 */
public class FinestraSceltaMondiDataBase extends FinestraDiDialogoPing   {
    
    private Log log = LogFactory.getLog(FinestraSceltaMondiDataBase.class);
    private JList listaMondi;
    private Image img;
    
    static {
        setDefaultLookAndFeelDecorated(true);
    }
    
    public void inizializza()   {
        this.setTitle(Language.FINES_MONDI_DB_TITLE_IT);
        try{
            img = ImageIO.read(FramePrincipale.class.getResource(Costanti.ICONA_FRAME));
            if(img != null) this.setIconImage(img);
        }catch(Exception e)   {
            log.error("Errore nel caricamento dell' immagine del frame -> "+e);
        }
        this.setModal(true);
        this.initComponents();
        initButton();
    }
    
    private void initButton()   {
        jButton2Cancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nascondi();
            }
        });
        
        jButton1OK.setAction(controllo.getAzioneSwing(AzioneOKSceltaMondoDB.class.getName()));
    }
    
    @SuppressWarnings("unchecked")
    public void initJList(List lista)   {
        listaMondi = new JList();
        listaMondi.setCellRenderer(new CellRenderMondi());
        listaMondi.setDragEnabled(false);
        listaMondi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMondi.setModel(new modelloListaMondi(lista));
        jScrollPane1.setViewportView(listaMondi);
        pack();
    }
    
    public Object getSelectedValue()   {
        return listaMondi.getSelectedValue();
    }
    
    
    private class modelloListaMondi extends DefaultListModel  {
        
        private List lista;
        
        public modelloListaMondi(List l) {
            lista = l;
        }

        @Override
        public Object getElementAt(int index) {
                return (lista.get(index));
        }

        @Override
        public int getSize() {
            return lista.size();
        }
        
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1OK = new javax.swing.JButton();
        jButton2Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 1, true));

        jButton1OK.setText("OK");

        jButton2Cancel.setText("Cancel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2Cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(jButton1OK)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1OK)
                    .addComponent(jButton2Cancel))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1OK;
    private javax.swing.JButton jButton2Cancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
