/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.lectura;

import static JTable.Tablas.limpiarTabla;
import static Messages.Mensajes.msn;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.lectura.Sector;
import modelo.pagos.Rol;
import modelo.pagos.Usuario;

/**
 *
 * @author 20102122476
 */
public class lectores extends javax.swing.JFrame/*FormTemplate*/ {
    /**
     * Creates new form sector
     */
    public lectores() {
        setDefaultCloseOperation(
                javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE
        );
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        loadTable();
        loadSectores();
    }
    
    private void close(){
        int respuesta =  JOptionPane.showConfirmDialog(
                this, 
                "¿ Realmente Deseas Salir ?", "GAS COLOMBIA", 
                JOptionPane.YES_NO_OPTION
        ); 
        if(respuesta==0){
            System.exit(0);
        }
    }
    
    public void loadTable(){
        limpiarTabla(tablaDatos);
        try {
            ArrayList<String> listado;
                listado = Usuario.listar();
                if (!listado.isEmpty()) {
                    listado.stream().forEach((dato) -> {
                        DefaultTableModel tb = (DefaultTableModel) tablaDatos.getModel();
                        String[] array = dato.split("\\;");
                        Vector datos = new Vector();
                        datos.add(array[0]);
                        datos.add(array[1]);
                        datos.add(array[2]);
                        datos.add(array[3]);
                        tb.addRow(datos);
                });
                }        
        } catch (SQLException ex) {
            Logger.getLogger(lectores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadSectores(){
        txrol.removeAllItems();
        ArrayList<Sector> listado;
        try {
            listado = Sector.listar();
            if (!listado.isEmpty()) {
                    listado.stream().forEach((dato) -> {
                        String temp = dato.getId()+":"+dato.getDet();
                        txrol.addItem(temp);
                });
                }   
        } catch (SQLException ex) {
            Logger.getLogger(lectores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txid = new javax.swing.JTextField();
        btinsertar = new javax.swing.JButton();
        btactualizar = new javax.swing.JButton();
        btlimpiar = new javax.swing.JButton();
        txrol = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btinsertar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ROL", "NOMBRE", "SECTOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDatos);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Formulario Lectores");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jLabel2.setText("Id Usuario: ");

        btinsertar.setText("ASIGNAR");
        btinsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btinsertarActionPerformed(evt);
            }
        });

        btactualizar.setText("RE - ASIGNAR");
        btactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btactualizarActionPerformed(evt);
            }
        });

        btlimpiar.setText("Limpiar");
        btlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlimpiarActionPerformed(evt);
            }
        });

        jLabel4.setText("Sectores: ");

        btinsertar1.setText("Recargar");
        btinsertar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btinsertar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txid)
                            .addComponent(txrol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btinsertar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btlimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btactualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btinsertar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txrol, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btinsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btinsertar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btinsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btinsertarActionPerformed
        Usuario usuario = new Usuario(txid.getText());
        try {
            if(usuario.verificarLector()){
                String[] array = txrol.getSelectedItem().toString().split("\\:");
                long rol = Long.parseLong(array[0]);
                if (!Sector.estaAsignado(rol)) {//PUEDE SER ASIGNADO
                    int updates = usuario.asignarRol(Long.parseLong(array[0]));
                    if(updates > 0){
                        loadTable();
                        msn(this, "REGISTRO ACTUALIZADO", "MENSAJE", 1);
                    }else{
                        msn(this, "NO SE ACTUALIZO EL REGISTRO", "ERROR", 0);
                    }
                }else{
                    msn(this, "NO SE PUEDE ASIGNAR EL SECTOR, "
                            + "\n(ESTA SIENDO UTILIZADO POR OTRO USUARIO "
                            + "O NO EXISTE","ERROR", 0);
                }
            }
            else{
                msn(this, "NO SE PUEDE ASIGNAR ROL, "
                        + "(ROL YA ASIGNADO O USUARIO INEXISTENTE)", 
                        "ERROR", 0);
            }
        } catch (SQLException ex) {
            msn(this, ex.getMessage(), "ERROR", 0);
        }
    }//GEN-LAST:event_btinsertarActionPerformed

    private void tablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosMouseClicked
        // TODO add your handling code here:
        try{
            int fila = tablaDatos.getSelectedRow();
            txid.setText(tablaDatos.getValueAt(fila, 0).toString());
            txrol.setSelectedItem(tablaDatos.getValueAt(fila, 3));
        }catch(Exception ex){}
        
    }//GEN-LAST:event_tablaDatosMouseClicked

    private void btactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btactualizarActionPerformed
        try {
            // TODO add your handling code here:
            String[] array = txrol.getSelectedItem().toString().split("\\:");
            long rol = Long.parseLong(array[0]);
            if (!Sector.estaAsignado(rol)) {//PUEDE SER ASIGNADO
                Usuario usuario = new Usuario(txid.getText());
                int updates = usuario.asignarRol(Long.parseLong(array[0]));
                if (updates > 0) {
                    loadTable();
                    msn(this, "REGISTRO ACTUALIZADO", "MENSAJE", 1);
                } else {
                    msn(this, "NO SE ACTUALIZO EL REGISTRO", "ERROR", 0);
                }
            }else{
                msn(this, "NO SE PUEDE RE-ASIGNAR EL SECTOR, "
                        + "\n(ESTA SIENDO UTILIZADO POR OTRO USUARIO O NO EXISTE","ERROR", 0);
            }
            /*if (txid.getText().length() != 0 & txdet.getText().length() != 0) {
            try {
            if (!existe(txdet.getText())) {
            Sector sector = new Sector(Long.parseLong(txid.getText()), txdet.getText());
            int eliminados = sector.actualizar();
            msn(this, "SE ACTUALIZO " + eliminados + " REGISTRO(S)", "MENSAJE", 1);
            if (eliminados > 0) {
            loadTable();
            }
            } else {
            msn(this, "REGISTRO EXISTENTE", "ERROR", 0);
            }
            } catch (SQLException ex) {
            msn(this, ex.getMessage(), "ERROR", 0);
            }
            }else{
            msn(this, "NO SE ADMITEN CAMPOS VACIOS", "ERROR", 0);
            }*/
        } catch (SQLException ex) {
            msn(this, ex.getMessage(), "ERROR", 0);
        }
    }//GEN-LAST:event_btactualizarActionPerformed

    private void btlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlimpiarActionPerformed
        // TODO add your handling code here:
        txid.setText("");
        txrol.setSelectedIndex(0);
        txid.requestFocus();
    }//GEN-LAST:event_btlimpiarActionPerformed

    private void btinsertar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btinsertar1ActionPerformed
        // TODO add your handling code here:
        loadTable();
        loadSectores();
    }//GEN-LAST:event_btinsertar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lectores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new lectores().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btactualizar;
    private javax.swing.JButton btinsertar;
    private javax.swing.JButton btinsertar1;
    private javax.swing.JButton btlimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JTextField txid;
    private javax.swing.JComboBox<String> txrol;
    // End of variables declaration//GEN-END:variables
}
