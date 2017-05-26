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
import modelo.lectura.Lectura;
import modelo.lectura.Medidor;
import modelo.lectura.Periodo;
import modelo.lectura.Sector;
import modelo.pagos.Usuario;

/**
 *
 * @author 20102122476
 */
public class periodo extends javax.swing.JFrame/*FormTemplate*/ {
    /**
     * Creates new form sector
     */
    public periodo() {
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
        limpiar();
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
    
    public void limpiar(){
        txid.setText("");
        txperiodo.setText("");
        txlno.setSelected(true);
        txfno.setSelected(true);
        txid.requestFocus();
    }
    
    public void loadTable(){
        limpiarTabla(tablaDatos);
        try {
            Periodo periodo = new Periodo();
            ArrayList<Periodo> listado;
                listado = periodo.listar();
                if (!listado.isEmpty()) {
                    listado.stream().forEach((dato) -> {
                        DefaultTableModel tb = (DefaultTableModel) tablaDatos.getModel();
                        Vector datos = new Vector();
                        datos.add(dato.getId());
                        datos.add(dato.getPeriodo());
                        datos.add(dato.getLectura());
                        datos.add(dato.getFacturado());
                        tb.addRow(datos);
                });
                }        
        } catch (SQLException ex) {
            msn(this, ex.getMessage(), "ERROR", 0);
        }
    }
    
    public void insertar(){
        if (txperiodo.getText().length() != 0 ) {
            try {
                if(!Periodo.existe(Long.parseLong(txperiodo.getText()))){
                    int lectura=0, facturado = 0;
                    if(txlsi.isSelected()) lectura = 1;
                    if(txfsi.isSelected()) facturado = 1;                    
                    Periodo periodo = new Periodo(Long.parseLong(txperiodo.getText()), lectura, facturado);
                    int insertados = periodo.insertar();
                    limpiar();
                    if (insertados > 0) {
                        loadTable();
                        //System.err.println("Secuencia: "+periodo.getId());
                        try {
                            ArrayList<String> medidores = Medidor.listarMedidoreInstalados();
                            medidores.stream().forEach((registros) -> {
                                try {
                                    String[] array = registros.split("\\;");
                                    Medidor medidor = new Medidor(Long.parseLong(array[0]));
                                    Sector sector = new Sector(Long.parseLong(array[1]));
                                    boolean temp = true;
                                    Lectura l = new Lectura(periodo, medidor);
                                    Usuario usuario = Usuario.listar(sector);
                                    while (temp) {
                                        if (l.insertarLecturasVacias(usuario) > 0) {
                                            temp = false;
                                        }
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(periodo.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            msn(this, "SE INSERTÓ "+(1+medidores.size())+" REGISTROS", "MENSAJE", 1);
                        } catch (SQLException ex) {
                            Logger.getLogger(periodo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        /*try {
                            ArrayList<Medidor> medidores = Medidor.listarMedidoreInstalados();
                            medidores.stream().forEach((medidor) -> {
                                boolean temp = true;
                                Lectura l = new Lectura(periodo, medidor);
                                while (temp) {
                                    try {
                                        if (l.insertarLecturasVacias() > 0) {
                                            temp = false;
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(periodo.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            msn(this, "SE INSERTÓ "+(1+medidores.size())+" REGISTROS", "MENSAJE", 1);
                        } catch (SQLException ex) {
                            Logger.getLogger(periodo.class.getName()).log(Level.SEVERE, null, ex);
                        /*try {
                            ArrayList<Medidor> medidores = Medidor.listarMedidoreInstalados();
                            medidores.stream().forEach((medidor) -> {
                                boolean temp = true;
                                Lectura l = new Lectura(periodo, medidor);
                                while (temp) {
                                    try {
                                        if (l.insertarLecturasVacias() > 0) {
                                            temp = false;
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(periodo.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            msn(this, "SE INSERTÓ "+(1+medidores.size())+" REGISTROS", "MENSAJE", 1);
                        } catch (SQLException ex) {
                            Logger.getLogger(periodo.class.getName()).log(Level.SEVERE, null, ex);
                        }*/
                    }
                }else{
                    msn(this, "REGISTRO EXISTENTE", "ERROR", 0);
                }
            } catch (SQLException ex) {
                msn(this, ex.getMessage(), "ERROR", 0);
            }
        }else{
            msn(this, "DEBES DIGITAR UN SERIAL Y UN COSTO", "ERROR", 0);
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

        Leido = new javax.swing.ButtonGroup();
        Facturado = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txid = new javax.swing.JTextField();
        txperiodo = new javax.swing.JTextField();
        btinsertar = new javax.swing.JButton();
        btactualizar = new javax.swing.JButton();
        bteliminar = new javax.swing.JButton();
        btlimpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txlno = new javax.swing.JCheckBox();
        txlsi = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        txfno = new javax.swing.JCheckBox();
        txfsi = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PERIODO", "LEIDO", "FACTURADO"
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
        jLabel1.setText("Formulario Periodos");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Id:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Periodo: ");

        txid.setEditable(false);
        txid.setBackground(new java.awt.Color(204, 255, 204));

        btinsertar.setText("INSERTAR");
        btinsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btinsertarActionPerformed(evt);
            }
        });

        btactualizar.setText("ACTUALIZAR");
        btactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btactualizarActionPerformed(evt);
            }
        });

        bteliminar.setText("ELIMINAR");
        bteliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteliminarActionPerformed(evt);
            }
        });

        btlimpiar.setText("Limpiar");
        btlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlimpiarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Leido: ");

        Leido.add(txlno);
        txlno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txlno.setSelected(true);
        txlno.setText("NO");
        txlno.setOpaque(false);

        Leido.add(txlsi);
        txlsi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txlsi.setText("SI");
        txlsi.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Facturado: ");

        Facturado.add(txfno);
        txfno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txfno.setSelected(true);
        txfno.setText("NO");
        txfno.setOpaque(false);

        Facturado.add(txfsi);
        txfsi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txfsi.setText("SI");
        txfsi.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btlimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bteliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btactualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btinsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txfsi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txfno))
                            .addComponent(txid)
                            .addComponent(txperiodo)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txlsi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txlno)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txid, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txperiodo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txlno)
                    .addComponent(txlsi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txfno)
                    .addComponent(txfsi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(btinsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bteliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btinsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btinsertarActionPerformed
        insertar();
    }//GEN-LAST:event_btinsertarActionPerformed

    private void tablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosMouseClicked
        // TODO add your handling code here:
        try{
            int fila = tablaDatos.getSelectedRow();
            txid.setText(tablaDatos.getValueAt(fila, 0).toString());
            txperiodo.setText(tablaDatos.getValueAt(fila, 1).toString());
            if(tablaDatos.getValueAt(fila, 2).toString().equals("1")){
                txlsi.setSelected(true);
            }
            else{
                txlno.setSelected(true);
            }
            if(tablaDatos.getValueAt(fila, 3).toString().equals("1")){
                txfsi.setSelected(true);
            }
            else{
                txfno.setSelected(true);
            }
        }catch(Exception ex){}
        
    }//GEN-LAST:event_tablaDatosMouseClicked

    private void btactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btactualizarActionPerformed
        // TODO add your handling code here:
        if (txid.getText().length() != 0 & txperiodo.getText().length() != 0) {
                try {
                    if (!Periodo.existe(Long.parseLong(txperiodo.getText()))) {
                        long id = Long.parseLong(txid.getText());
                        long per = Long.parseLong(txperiodo.getText());
                        Periodo periodo = new Periodo(id, per);
                        int actualizados = periodo.actualizar();
                        msn(this, "SE ACTUALIZO " + actualizados + " REGISTRO(S)", "MENSAJE", 1);
                        if (actualizados > 0) {
                            loadTable();
                        }
                    } else {
                        long id = Long.parseLong(txid.getText());
                        long per = Long.parseLong(txperiodo.getText());
                        int lectura=0, facturado = 0;
                        if(txlsi.isSelected()) lectura = 1;
                        if(txfsi.isSelected()) facturado = 1;
                        Periodo periodo = new Periodo(id, per, lectura, facturado);
                        int actualizados = periodo.actualizarLecturaFacturado();
                        msn(this, "SE ACTUALIZO " + actualizados + " REGISTRO(S)", "MENSAJE", 1);
                        if (actualizados > 0) {
                            loadTable();
                        }
                    }
                    limpiar();
                } catch (SQLException ex) {
                    msn(this, ex.getMessage(), "ERROR", 0);
                }            
        }else{
            msn(this, "NO SE ADMITEN CAMPOS VACIOS", "ERROR", 0);
        }
    }//GEN-LAST:event_btactualizarActionPerformed

    private void bteliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteliminarActionPerformed
        // TODO add your handling code here:
        if (txid.getText().length() != 0) {
            try {
                Periodo periodo = new Periodo(Long.parseLong(txid.getText()),0);
                int eliminados = periodo.eliminar();
                msn(this, "SE ELIMINO " + eliminados + " REGISTRO(S)", "MENSAJE", 1);
                limpiar();
                if (eliminados > 0) {
                    loadTable();
                }
            } catch (SQLException ex) {
                msn(this, ex.getMessage(), "ERROR", 0);
            }
        }else{
            msn(this, "DEBES DIGITAR UN ID", "ERROR", 0);
        }
    }//GEN-LAST:event_bteliminarActionPerformed

    private void btlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btlimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(periodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            new periodo().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Facturado;
    private javax.swing.ButtonGroup Leido;
    private javax.swing.JButton btactualizar;
    private javax.swing.JButton bteliminar;
    private javax.swing.JButton btinsertar;
    private javax.swing.JButton btlimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JCheckBox txfno;
    private javax.swing.JCheckBox txfsi;
    private javax.swing.JTextField txid;
    private javax.swing.JCheckBox txlno;
    private javax.swing.JCheckBox txlsi;
    private javax.swing.JTextField txperiodo;
    // End of variables declaration//GEN-END:variables
}
