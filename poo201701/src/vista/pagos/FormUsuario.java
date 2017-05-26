/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.pagos;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.SuperTabla;
import modelo.lectura.Sector;
import modelo.pagos.Rol;
import modelo.pagos.Usuario;
import vista.FormTemplate;

/**
 *
 * @author 201315439871
 */
public class FormUsuario extends FormTemplate {

    /**
     * Creates new form FormRol
     */
    public FormUsuario() {
        initComponents();
        mostrarRegistro(0);
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
        Txt_Nombre = new javax.swing.JTextField();
        Cmb_Roles = new javax.swing.JComboBox();
        Txt_Password = new javax.swing.JTextField();
        Cmb_Sectores = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Rol:");

        jLabel3.setText("Password:");

        jLabel4.setText("Sector:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Cmb_Roles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Txt_Nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(Cmb_Sectores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Txt_Password))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cmb_Roles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cmb_Sectores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(99, Short.MAX_VALUE))
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
            for (javax.swing.UIManager.LookAndFeelInfo info : 
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormUsuario.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUsuario.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUsuario.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUsuario.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Cmb_Roles;
    private javax.swing.JComboBox Cmb_Sectores;
    private javax.swing.JTextField Txt_Nombre;
    private javax.swing.JTextField Txt_Password;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

    @Override
    public void limpiarFormulario() {
        Txt_Nombre.setText("");
        Cmb_Roles.setSelectedIndex(-1);
        Cmb_Sectores.setSelectedIndex(-1);
        Txt_Password.setText("");
    }

    @Override
    public void mostrarRegistro(int indice) {
        Txt_Nombre.requestFocus();
        if (indice < 0 || listaDatos.isEmpty()) {
            limpiarFormulario();
            return;
        }
        Usuario usu;
        usu = (Usuario) listaDatos.get(indice);

        if (usu != null) {
            System.out.println(usu);
            Txt_Nombre.setText(usu.getNombre());
            Txt_Password.setText(usu.getPassword());
            //FALTA EL SECTOR Y EL ROL
        }
    }

    @Override
    public void inicializar() {
        try {
            listaDatos = new ArrayList<>();
            listaDatos.addAll(Usuario.buscar());
            indiceActual = 0;
            habilitarNavegacion();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public SuperTabla getNuevoRegistro() throws Exception {
        Usuario usu = new Usuario();
        usu.setNombre(Txt_Nombre.getText());
        usu.setPassword(Txt_Password.getText());
        Rol r=new Rol();
        r.setId(Cmb_Roles.getSelectedIndex());
        usu.setRol(r);
        Sector sec=new Sector();
        sec.setId(Cmb_Sectores.getSelectedIndex());
        usu.setSector(sec);
        return usu;
    }

    @Override
    public void setRegistroActual(SuperTabla registro) throws Exception {
        Usuario usu;
        usu = (Usuario) registro;
        Cmb_Roles.setSelectedIndex(usu.getRol().getId());
        Txt_Nombre.setText(usu.getNombre());
        Txt_Password.setText(usu.getPassword());
        Cmb_Sectores.setSelectedIndex((int)usu.getSector().getId());
    }

    @Override
    public void ejecutarBusqueda() throws Exception {

    }

    @Override
    public void habilitarBusqueda() {

    }

    @Override
    public void imprimirJasper() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}