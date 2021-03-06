/*
 * Copyright (C) 2016 hteran.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package vista;

import java.awt.Frame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.SuperTabla;

/**
 *
 * @author hteran
 */
public abstract class FormTemplate extends javax.swing.JFrame {

    public ArrayList<SuperTabla> listaDatos;
    public int indiceActual;

    public abstract void limpiarFormulario();

    public abstract void mostrarRegistro(int indice);

    public abstract void inicializar();

    public abstract SuperTabla getNuevoRegistro() throws Exception;

    public abstract void setRegistroActual(SuperTabla registro)
            throws Exception;

    public abstract void ejecutarBusqueda() throws Exception;

    public abstract void habilitarBusqueda();

    public abstract void imprimirJasper();

    /**
     * Creates new form SuperForm
     */
    public FormTemplate() {
        initComponents();
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle(getClass().getSimpleName());
        inicializar();
        textNumeroRegistro.setMaximumSize(textNumeroRegistro.getPreferredSize());
        textNumeroRegistro.setText((indiceActual + 1) + "");
        labelTotal.setText("de " + listaDatos.size());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        buttonEjecutar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        buttonReload = new javax.swing.JToggleButton();
        buttonNuevo = new javax.swing.JToggleButton();
        buttonGuardar = new javax.swing.JButton();
        buttonBuscar = new javax.swing.JToggleButton();
        buttonEliminar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        textNumeroRegistro = new javax.swing.JTextField();
        labelTotal = new java.awt.Label();
        buttonPrimero = new javax.swing.JButton();
        buttonAnterior = new javax.swing.JButton();
        buttonSiguiente = new javax.swing.JButton();
        buttonUltimo = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        buttonImprimir = new javax.swing.JButton();
        buttonVolver = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        buttonAyuda = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        buttonEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ejecutar_48x48.png"))); // NOI18N
        buttonEjecutar.setFocusable(false);
        buttonEjecutar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonEjecutar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEjecutarActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonEjecutar);

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setToolTipText("");
        jToolBar1.add(jSeparator1);

        buttonReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reload.png"))); // NOI18N
        buttonReload.setFocusable(false);
        buttonReload.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonReload.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReloadActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonReload);

        buttonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/nuevo1.png"))); // NOI18N
        buttonNuevo.setFocusable(false);
        buttonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonNuevo);

        buttonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/guardar2.png"))); // NOI18N
        buttonGuardar.setEnabled(false);
        buttonGuardar.setFocusable(false);
        buttonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonGuardar);

        buttonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/buscar_1.png"))); // NOI18N
        buttonBuscar.setFocusable(false);
        buttonBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonBuscar);

        buttonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/eliminar48x48.png"))); // NOI18N
        buttonEliminar.setFocusable(false);
        buttonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonEliminar);
        jToolBar1.add(jSeparator2);

        textNumeroRegistro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textNumeroRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textNumeroRegistro.setMaximumSize(new java.awt.Dimension(50, 50));
        textNumeroRegistro.setPreferredSize(new java.awt.Dimension(70, 50));
        textNumeroRegistro.setVerifyInputWhenFocusTarget(false);
        textNumeroRegistro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNumeroRegistroFocusLost(evt);
            }
        });
        textNumeroRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNumeroRegistroActionPerformed(evt);
            }
        });
        textNumeroRegistro.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                textNumeroRegistroPropertyChange(evt);
            }
        });
        jToolBar1.add(textNumeroRegistro);

        labelTotal.setPreferredSize(new java.awt.Dimension(50, 50));
        labelTotal.setText("de x");
        jToolBar1.add(labelTotal);
        labelTotal.getAccessibleContext().setAccessibleName("de");

        buttonPrimero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/first_record.png"))); // NOI18N
        buttonPrimero.setFocusable(false);
        buttonPrimero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonPrimero.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrimeroActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonPrimero);

        buttonAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/previous_record_48.PNG"))); // NOI18N
        buttonAnterior.setFocusable(false);
        buttonAnterior.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAnterior.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnteriorActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonAnterior);

        buttonSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/next_record_48.png"))); // NOI18N
        buttonSiguiente.setFocusable(false);
        buttonSiguiente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSiguiente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSiguienteActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonSiguiente);

        buttonUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/last_record.png"))); // NOI18N
        buttonUltimo.setFocusable(false);
        buttonUltimo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonUltimo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUltimoActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonUltimo);
        jToolBar1.add(jSeparator4);

        buttonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/print.png"))); // NOI18N
        buttonImprimir.setFocusable(false);
        buttonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonImprimir);

        buttonVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/volver.png"))); // NOI18N
        buttonVolver.setFocusable(false);
        buttonVolver.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonVolver.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVolverActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonVolver);
        jToolBar1.add(jSeparator5);

        buttonAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ayuda.png"))); // NOI18N
        buttonAyuda.setFocusable(false);
        buttonAyuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAyuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(buttonAyuda);

        jPanel1.setForeground(new java.awt.Color(255, 0, 51));
        jPanel1.setLayout(new java.awt.CardLayout());

        jMenu1.setText("Archivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 466, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        // TODO add your handling code here:
        crearRegistro();
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrimeroActionPerformed
        // TODO add your handling code here:
        irAlPrimerRegistro();
    }//GEN-LAST:event_buttonPrimeroActionPerformed

    private void buttonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnteriorActionPerformed
        // TODO add your handling code here:
        irAlAnteriorRegistro();
    }//GEN-LAST:event_buttonAnteriorActionPerformed

    private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSiguienteActionPerformed
        // TODO add your handling code here:
        irAlSiguienteRegistro();
    }//GEN-LAST:event_buttonSiguienteActionPerformed

    private void buttonUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUltimoActionPerformed
        // TODO add your handling code here:
        irAlUltimoRegistro();
    }//GEN-LAST:event_buttonUltimoActionPerformed

    private void buttonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVolverActionPerformed
        // TODO add your handling code here:
        dispose();
        //setVisible(false);
    }//GEN-LAST:event_buttonVolverActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        // TODO add your handling code here:
       
        buscar();
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirActionPerformed
        // TODO add your handling code here:
        imprimirJasper();
    }//GEN-LAST:event_buttonImprimirActionPerformed

    private void textNumeroRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNumeroRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNumeroRegistroActionPerformed

    private void textNumeroRegistroPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_textNumeroRegistroPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_textNumeroRegistroPropertyChange

    private void textNumeroRegistroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNumeroRegistroFocusLost
        // TODO add your handling code here:

        try {
            int indice;
            indice = Integer.parseInt(textNumeroRegistro.getText());
            if (indice < 1 || indice > listaDatos.size()) {
                throw new Exception("Fuera del rango");
            }
            mostrarRegistro(indice - 1);
        } catch (Exception e) {
            mostrarException(e);
        }

    }//GEN-LAST:event_textNumeroRegistroFocusLost

    private void buttonReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReloadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonReloadActionPerformed

    private void buttonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEjecutarActionPerformed
        try {
            // TODO add your handling code here:
            actualizar();
        } catch (Exception ex) {
            Logger.getLogger(FormTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonEjecutarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton buttonAnterior;
    protected javax.swing.JButton buttonAyuda;
    private javax.swing.JToggleButton buttonBuscar;
    protected javax.swing.JButton buttonEjecutar;
    protected javax.swing.JButton buttonEliminar;
    protected javax.swing.JButton buttonGuardar;
    protected javax.swing.JButton buttonImprimir;
    protected javax.swing.JToggleButton buttonNuevo;
    protected javax.swing.JButton buttonPrimero;
    protected javax.swing.JToggleButton buttonReload;
    protected javax.swing.JButton buttonSiguiente;
    protected javax.swing.JButton buttonUltimo;
    protected javax.swing.JButton buttonVolver;
    protected javax.swing.JMenu jMenu1;
    protected javax.swing.JMenu jMenu2;
    protected javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private java.awt.Label labelTotal;
    private javax.swing.JTextField textNumeroRegistro;
    // End of variables declaration//GEN-END:variables

    public void mostrarException(Exception e) {
        JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "[" + getTitle() + "]", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(System.out);
    }

    public void mostrarMensaje(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "[" + getTitle() + "]" + titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        mostrarMensaje(mensaje, null);
    }

    public void irAlPrimerRegistro() {
        if (listaDatos.size() > 0) {
            indiceActual = 0;
            textNumeroRegistro.setText((indiceActual + 1) + "");
            labelTotal.setText("de " + listaDatos.size());
            mostrarRegistro(indiceActual);
            habilitarNavegacion();
        }
    }

    public void irAlUltimoRegistro() {
        if (listaDatos.size() > 0) {
            indiceActual = listaDatos.size() - 1;
            textNumeroRegistro.setText((indiceActual + 1) + "");
            labelTotal.setText("de " + listaDatos.size());
            mostrarRegistro(indiceActual);
            habilitarNavegacion();
        }
    }

    public void irAlSiguienteRegistro() {

        if (listaDatos.size() == 0) {
            return;
        }
        /**
         * Incrementamos en indice Actual
         */
        indiceActual++;
        /**
         * Verificamos que si alcanza el ùltimo registro comienze desde el
         * primer
         */
        indiceActual = (indiceActual > listaDatos.size() - 1)
                ? listaDatos.size() - 1 : indiceActual;
        /**
         * Mostrar los datos en el formulario
         */
        textNumeroRegistro.setText((indiceActual + 1) + "");
        labelTotal.setText("de " + listaDatos.size());
        mostrarRegistro(indiceActual);
        habilitarNavegacion();
    }

    /**
     * Ubica el cursor en el Anterior Registro
     */
    public void irAlAnteriorRegistro() {
        if (listaDatos.isEmpty()) {
            return;
        }
        indiceActual--;
        indiceActual = (indiceActual < 0) ? 0 : indiceActual;
        textNumeroRegistro.setText((indiceActual + 1) + "");
        labelTotal.setText("de " + listaDatos.size());
        mostrarRegistro(indiceActual);
        habilitarNavegacion();
    }

    public void habilitarNavegacion() {

        buttonPrimero.setEnabled(
                indiceActual != 0
                && !buttonNuevo.isSelected());

        buttonUltimo.setEnabled(indiceActual
                != listaDatos.size() - 1
                && (listaDatos.size() > 0)
                && !buttonNuevo.isSelected());

        buttonAnterior.setEnabled(
                indiceActual > 0
                && listaDatos.size() > 1
                && !buttonNuevo.isSelected());

        buttonSiguiente.setEnabled(
                indiceActual != listaDatos.size() - 1
                && listaDatos.size() > 1
                && !buttonNuevo.isSelected());

        buttonEliminar.setEnabled(listaDatos.size() > 0
                && !buttonNuevo.isSelected());
        buttonImprimir.setEnabled(listaDatos.size() > 0
                && !buttonNuevo.isSelected());
        if (listaDatos.size() == 0) {
            limpiarFormulario();
        }

    }

    public void eliminar() {
        try {
            if (listaDatos.size() == 0) {
                mostrarMensaje("No hay datos para eliminar.");
                return;
            }
            if (JOptionPane.showConfirmDialog(this,
                    "¿Desea eliminar este registro",
                    getTitle(), JOptionPane.OK_CANCEL_OPTION)
                    == JOptionPane.CANCEL_OPTION) {
                return;
            }
            listaDatos.get(indiceActual).eliminar();
            listaDatos.remove(indiceActual);
            indiceActual--;
            indiceActual = (indiceActual < 0) ? 0 : indiceActual;
            textNumeroRegistro.setText((indiceActual + 1) + "");
            labelTotal.setText("de " + listaDatos.size());
            mostrarMensaje("Dato eliminado exitosamente.",
                    "Eliminación");
            mostrarRegistro(indiceActual);
        } catch (Exception ex) {
            mostrarException(ex);
        }
    }

    public void guardar() {
        try {

            if (buttonNuevo.isSelected()) {
                insertar();
                buttonNuevo.setSelected(false);

                crearRegistro();
            } else {
                actualizar();
            }
            habilitarNavegacion();
        } catch (Exception e) {
            mostrarException(e);
        }

    }

    public void insertar() throws SQLException, Exception {
        SuperTabla registro = getNuevoRegistro();
        registro.insertar();
        listaDatos.add(registro);
        indiceActual = listaDatos.size() - 1;
        indiceActual = (indiceActual < 0) ? 0 : indiceActual;
        textNumeroRegistro.setText((indiceActual + 1) + "");
        labelTotal.setText("de " + listaDatos.size());
        mostrarRegistro(indiceActual);
        mostrarMensaje("Dato insertado exitosamente.",
                "Inserción de datos");

    }

    public void actualizar() throws SQLException, Exception {
        setRegistroActual(listaDatos.get(indiceActual));
        listaDatos.get(indiceActual).actualizar();
        mostrarMensaje("Datos actualizados exitosamente.",
                "Actualización de datos");
    }

    public void crearRegistro() {
        ImageIcon icon;
        if (buttonNuevo.isSelected()) {
            buttonGuardar.setEnabled(true);
            icon = new ImageIcon(getClass()
                    .getResource("/image/delete_1.png"));
            limpiarFormulario();
        } else {
            buttonGuardar.setEnabled(false);
            icon = new ImageIcon(getClass()
                    .getResource("/image/nuevo1.png"));
            mostrarRegistro(indiceActual);
        }
        buttonNuevo.setIcon(icon);
    }

    public void buscar() {
        try {
            ImageIcon icon;
            if (buttonBuscar.isSelected()) {
                icon = new ImageIcon(getClass()
                        .getResource("/image/buscar_2.png"));
                limpiarFormulario();
                habilitarBusqueda();
            } else {
                ejecutarBusqueda();
                mostrarRegistro(indiceActual);
                icon = new ImageIcon(getClass()
                        .getResource("/image/buscar_1.png"));
                habilitarNavegacion();
            }
            buttonBuscar.setIcon(icon);
        } catch (Exception ex) {
            mostrarException(ex);
        }
    }

}
