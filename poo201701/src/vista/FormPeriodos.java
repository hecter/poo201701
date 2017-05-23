/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import modelo.SuperTabla;
import modelo.lectura.Periodo;

/**
 *
 * Formulario de los periodos,
 *
 * @author Ricardo Aragon, email: <ricardoaragon12@hotmail.com>
 * @version 1.0
 * @since 2017
 */
public class FormPeriodos extends FormTemplate {

    /**
     * Creates new form FormPeriodo
     */
    public FormPeriodos() {
        initComponents();
        inicializar();
        mostrarRegistro(0);
        setLocationRelativeTo(null);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_periodo = new javax.swing.JTextField();
        checkBoxLectura = new javax.swing.JCheckBox();
        checkBoxFacturado = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Id: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Periodo:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Lectura:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Facturado:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxLectura)
                    .addComponent(checkBoxFacturado))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(checkBoxLectura))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(checkBoxFacturado))
                .addContainerGap(111, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(FormPeriodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPeriodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPeriodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPeriodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPeriodos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxFacturado;
    private javax.swing.JCheckBox checkBoxLectura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_periodo;
    // End of variables declaration//GEN-END:variables
  
    
    
    @Override
    /**
     * funcion de inicializar con el formulario periodos
     */
    public void inicializar() {
        try {
            listaDatos = new ArrayList<>();
            listaDatos.addAll(Periodo.listar());
            indiceActual = 0;

            habilitarNavegacion();
        } catch (Exception ex) {
            mostrarException(ex);
        }
    }

    @Override
    /**
     * funcion para limpiar el formulario de el form. Periodos
     */
    public void limpiarFormulario() {
        txt_id.setText("");
        txt_periodo.setText("");
        checkBoxFacturado.setSelected(false);
        checkBoxLectura.setSelected(false);
      
    }

    @Override
    /**
     * funcion para mostrar registros en el form. Periodos
     */
    public void mostrarRegistro(int indice) {
        Periodo periodos;
        periodos = (Periodo) listaDatos.get(indice);
        if (periodos != null) {
            txt_id.setText(periodos.getId() + "");
            txt_periodo.setText(periodos.getPeriodo() + "");
            checkBoxLectura.setSelected(Boolean.parseBoolean(periodos.getLectura()+""));
            checkBoxFacturado.setSelected(Boolean.parseBoolean(periodos.getFacturado()+""));
        }
    }

    @Override
      /**
     * funcion para  obtener los nuevos registros en el form. Periodos
     */
    public SuperTabla getNuevoRegistro() throws Exception {
        Periodo periodo;
        periodo = new Periodo(Long.parseLong(txt_id.getText()),
                Long.parseLong(txt_periodo.getText()),
                Boolean.compare(checkBoxLectura.isSelected(), false),
                Boolean.compare(checkBoxFacturado.isSelected(), false));
        
 
        return (SuperTabla) periodo;
    }

    @Override
      /**
     * funcion para llenar los campos con registros en el form. Periodos
     */
    public void setRegistroActual(SuperTabla registro) throws Exception {
        Periodo periodos;
        periodos = (Periodo) registro;
        periodos.setId(Long.parseLong(txt_id.getText()));
        periodos.setPeriodo(Long.parseLong(txt_periodo.getText()));
        periodos.setLectura(Boolean.compare(checkBoxLectura.isSelected(), false));
        periodos.setFacturado(Boolean.compare(checkBoxFacturado.isSelected(), false));
    }

    @Override
      /**
     * funcion para ejecutar una busqueda de registros en el form. Periodos
     */
    public void ejecutarBusqueda() throws Exception {
        long id = 0;

        id = (txt_id.getText().isEmpty())
                ? 0 : Long.parseLong(txt_id.getText());
        listaDatos = new ArrayList<>();
        if (id == 0) {
            listaDatos.addAll(Periodo.listar());
        } else {
            listaDatos.add((SuperTabla) Periodo.listar(id));
        }
        txt_id.setEditable(false);
    }

    @Override
      /**
     * funcion para habilitar la busqueda por el (id) del  registros en el form. Periodos
     */
    public void habilitarBusqueda() {
        txt_id.setEditable(true);
        txt_id.requestFocus();
    }

    @Override
      /**
     * funcion para imprimir reporte de registros en el form. Periodos
     */
    public void imprimirJasper() {
        try {
            listaDatos.get(indiceActual)
                    .abrirReporte(basededatos.BaseDatosOracle
                            .getInstance().getConexion());
        } catch (Exception ex) {
            mostrarException(ex);
        }
    }
}
