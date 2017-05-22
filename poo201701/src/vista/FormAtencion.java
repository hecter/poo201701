/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.SuperTabla;
import modelo.atencion.Atencion;
import modelo.atencion.Casa;
import modelo.atencion.Estado;
import modelo.atencion.Motivo;
import modelo.pagos.Usuario;

/**
 *
 * @author LEo
 */
public class FormAtencion extends FormTemplate {
private  Atencion atencion = new  Atencion();
    /**
     * Creates new form FormAtencion
     */
    public FormAtencion() {
        initComponents();
         mostrarRegistro(0);
        txt_codigo.setEditable(false);
    }
    
    
      @Override
    public void limpiarFormulario() {
       //  txt_codigo.setValue("");
         txt_casa.setSelectedIndex(0);
         txt_motivos.setSelectedIndex(0);
         txt_usuario.setSelectedIndex(0);
         txt_estado.setSelectedIndex(0);
    }

    @Override
    public void mostrarRegistro(int indice) {
         txt_codigo.requestFocus();
         if (indice < 0 || listaDatos.isEmpty()) {
            limpiarFormulario();
            return;
        }
        Atencion aten;
        aten = (Atencion) listaDatos.get(indice);
       
        if (aten != null) {
        
            System.out.println(aten);
            txt_codigo.setValue(aten.getId());
            txt_casa.addItem(String.valueOf(aten.getCasa().getId())+" "+aten.getCasa().getDireccion());
            txt_estado.addItem(aten.getEstado().getId()+" "+String.valueOf(aten.getEstado().getNombre()));
            txt_fecha.setDate(aten.getFecha());
            txt_fechas.setDate(aten.getFecha_solucion());
            txt_motivos.addItem(String.valueOf(aten.getMotivos().getId()+" "+aten.getMotivos().getMotivos()));
            txt_usuario.addItem(aten.getUsuario().getId()+" "+aten.getUsuario().getNombre());
            txt_codigo.requestFocus();
        }
    }

    @Override
    public void inicializar() {
        try {
            listaDatos = new ArrayList<>();
            listaDatos.addAll(Atencion.buscar());
            indiceActual = 0;
            habilitarNavegacion();
        } catch (Exception ex) {
            Logger.getLogger(FormPais.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public SuperTabla getNuevoRegistro() throws Exception {
        Atencion aten = new Atencion();
        String casa = String.valueOf(txt_casa.getSelectedItem());
        String estado = String.valueOf(txt_estado.getSelectedItem());
        String motivos = String.valueOf(txt_motivos.getSelectedItem());
        String usuario = String.valueOf(txt_usuario.getSelectedItem());

        java.sql.Date fecha1 = new java.sql.Date(txt_fecha.getDate().getTime());
        java.sql.Date fecha2 = new java.sql.Date(txt_fechas.getDate().getTime());
       aten.getCasa().setId(Long.parseLong(casa.substring(0,1)));
       aten.getEstado().setId(Long.parseLong(estado.substring(0,1)));
       aten.getMotivos().setId(Long.parseLong(motivos.substring(0,1)));
       aten.getUsuario().setId(Integer.parseInt(usuario.substring(0,1)));
       aten.setId(Long.parseLong(txt_codigo.getText()));
       aten.setFecha(fecha1);
       aten.setFecha_solucion(fecha2);
        return aten;
    }

    @Override
    public void setRegistroActual(SuperTabla registro) throws Exception {
         String casa = String.valueOf(txt_casa.getSelectedItem());
        String estado = String.valueOf(txt_estado.getSelectedItem());
        String motivos = String.valueOf(txt_motivos.getSelectedItem());
        String usuario = String.valueOf(txt_usuario.getSelectedItem());
           java.sql.Date fecha1 = new java.sql.Date(txt_fecha.getDate().getTime());
        java.sql.Date fecha2 = new java.sql.Date(txt_fechas.getDate().getTime());
        Atencion ate;
        ate = (Atencion) registro;
        ate.getCasa().setId(Long.parseLong(casa.substring(0,1)));
        ate.getEstado().setId(Long.parseLong(estado.substring(0,1)));
        ate.getMotivos().setId(Long.parseLong(motivos.substring(0,1)));
        ate.getUsuario().setId(Integer.parseInt(usuario.substring(0,1)));
        ate.setId(Long.parseLong(txt_codigo.getText()));
        ate.setFecha(fecha1);
        ate.setFecha_solucion(fecha2);
     
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ejecutarBusqueda() throws Exception {
        
            long codigo = 0;
        codigo = (txt_codigo.getText().isEmpty()) 
                ? -1 
                : Long.parseLong(txt_codigo.getText());
        listaDatos = new ArrayList<>();
        if(codigo == -1){
           listaDatos.addAll(Atencion.buscar());
        }else{
          listaDatos.add(Atencion.buscar(codigo));  
        }
        txt_codigo.setEditable(false);
    //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void habilitarBusqueda() {
         txt_codigo.setEditable(true);
     txt_codigo.requestFocus();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imprimirJasper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
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
        txt_casa = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txt_estado = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txt_fecha = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txt_fechas = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txt_motivos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JComboBox<>();
        txt_codigo = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Codigo:");

        jLabel2.setText("Casa:");

        jLabel3.setText("Estado:");

        jLabel4.setText("Fecha:");

        jLabel5.setText("Fecha Solucion:");

        jLabel6.setText("Motivos:");

        jLabel7.setText("Usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txt_fechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_estado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_casa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_codigo))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txt_motivos, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(62, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_casa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_fechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_motivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FormAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAtencion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAtencion().setVisible(true);
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
    private javax.swing.JComboBox<String> txt_casa;
    private javax.swing.JFormattedTextField txt_codigo;
    private javax.swing.JComboBox<String> txt_estado;
    private com.toedter.calendar.JDateChooser txt_fecha;
    private com.toedter.calendar.JDateChooser txt_fechas;
    private javax.swing.JComboBox<String> txt_motivos;
    private javax.swing.JComboBox<String> txt_usuario;
    // End of variables declaration//GEN-END:variables

}
