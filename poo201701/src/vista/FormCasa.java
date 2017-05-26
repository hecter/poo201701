/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import basededatos.BaseDatosOracle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import modelo.SuperTabla;
import modelo.atencion.Atencion;
import modelo.atencion.Casa;
import modelo.atencion.Suscriptor;
import modelo.lectura.Sector;

/**
 *
 * @author 201315441932
 */
public class FormCasa extends FormTemplate {

    /**
     * Creates new form FormCasa
     */
    public FormCasa() {
          initComponents();
          mostrarRegistro(0);
          txt_codigo.setEditable(false);
          
          try {
           suscriptor.removeAllItems();
            for (Suscriptor listaDato : Suscriptor.buscar()) {
              suscriptor.addItem(listaDato.getId()+" - "+listaDato.getNombre());
            }
            
            sector.removeAllItems();
            for (Sector listaDato : Sector.listar()) {
              sector.addItem(listaDato.getId()+" - "+listaDato.getDet());
            }
           
        } catch (Exception e) {
        }
        
        
    }
    @Override
    public void limpiarFormulario() {
            txt_direccion.setText(null);
            txt_ciudad.setText(null);
            txt_estrato.setText(null);
            txt_telefono.setText(null);    }

    @Override
    public void mostrarRegistro(int indice) {
          txt_codigo.requestFocus();
         if (indice < 0 || listaDatos.isEmpty()) {
            limpiarFormulario();
            return;
        }
            Casa casa;
        casa = (Casa) listaDatos.get(indice);
       
        if (casa != null) {
            System.out.println(casa);
            txt_codigo.setValue(casa.getId());
            txt_direccion.setText(casa.getDireccion());
            txt_telefono.setText(casa.getTelefono());
            txt_estrato.setText(casa.getEstrato()+"");
            txt_ciudad.setText(casa.getCiudad());
            suscriptor.setSelectedItem(casa.getSuscriptor());
            sector.setSelectedItem(casa.getSector());
            txt_codigo.requestFocus();
        }    }

    @Override
    public void inicializar() {
        try {
            listaDatos = new ArrayList<>();
            listaDatos.addAll(Casa.buscar());
            indiceActual = 0;
            habilitarNavegacion();
        } catch (Exception ex) {
            Logger.getLogger(FormPais.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public SuperTabla getNuevoRegistro() throws Exception {   
         Casa casa;
        
       
        String suscript = String.valueOf(suscriptor.getSelectedItem());
        String sect = String.valueOf(sector.getSelectedItem());
        String direcc = String.valueOf(txt_direccion.getText());
        String tel = String.valueOf(txt_telefono.getText());
        long estra = Long.parseLong(txt_estrato.getText());
        String ciudad = String.valueOf(txt_ciudad.getText());
        
        
        Suscriptor sus = new Suscriptor();
        sus.setId(Long.parseLong(suscript.substring(0,1)));
        Sector sec = new Sector();
        sec.setId(Long.parseLong(sect.substring(0,1)));
        
        casa = new Casa( direcc, tel,estra,ciudad,sus,sec);             
        return casa;
        
    }

    @Override
    public void setRegistroActual(SuperTabla registro) throws Exception {
        String suscript = String.valueOf(suscriptor.getSelectedItem());
        String sect = String.valueOf(sector.getSelectedItem());
        Casa casa;
        casa = (Casa) registro;
        casa.setDireccion(String.valueOf(txt_direccion.getText()));
        casa.setTelefono(String.valueOf(txt_telefono.getText()));
        casa.setEstrato(Long.parseLong(txt_estrato.getText()));
         casa.setCiudad(String.valueOf(txt_ciudad.getText()));
        casa.getSuscriptor().setId(Long.parseLong(suscript.substring(0,1)));
        casa.getSector().setId(Long.parseLong(sect.substring(0,1)));
        
        casa.setId(Long.parseLong(txt_codigo.getText()));
           }

    @Override
    public void ejecutarBusqueda() throws Exception {
          long codigo = 0;
        codigo = (txt_codigo.getText().isEmpty()) 
                ? -1 
                : Long.parseLong(txt_codigo.getText());
        listaDatos = new ArrayList<>();
        if(codigo == -1){
           listaDatos.addAll(Casa.buscar());
        }else{
          listaDatos.add(Casa.buscar(codigo));  
        }
        txt_codigo.setEditable(false);    }

    @Override
    public void habilitarBusqueda() {
     txt_codigo.setEditable(true);
     txt_codigo.requestFocus();    }

    @Override
    public void imprimirJasper() {
            try {
            listaDatos.get(indiceActual)
                    .abrirReporte(BaseDatosOracle.getInstance().getConexion());

        } catch (Exception ex) {
            mostrarException(ex);
        }    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_direccion = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_estrato = new javax.swing.JTextField();
        suscriptor = new javax.swing.JComboBox<>();
        sector = new javax.swing.JComboBox<>();
        txt_codigo = new javax.swing.JFormattedTextField();
        txt_ciudad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CODIGO:");

        jLabel2.setText("DIRECCION:");

        jLabel3.setText("TELEFONO:");

        jLabel4.setText("ESTRATO:");

        jLabel5.setText("CIUDAD:");

        jLabel6.setText("SUSCRIPTOR:");

        jLabel7.setText("SECTOR:");

        txt_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_direccionActionPerformed(evt);
            }
        });

        txt_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telefonoActionPerformed(evt);
            }
        });

        suscriptor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        sector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txt_codigo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_direccion)
                    .addComponent(txt_telefono)
                    .addComponent(txt_estrato)
                    .addComponent(suscriptor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_codigo)
                    .addComponent(txt_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(483, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(txt_estrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(suscriptor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(sector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(149, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_direccionActionPerformed

    private void txt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefonoActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormCasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCasa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> sector;
    private javax.swing.JComboBox<String> suscriptor;
    private javax.swing.JTextField txt_ciudad;
    private javax.swing.JFormattedTextField txt_codigo;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_estrato;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables

    
}
