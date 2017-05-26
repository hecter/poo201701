/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        try {
             txt_casa.removeAllItems();
            for (Casa listaDato : Casa.buscar()) {
              txt_casa.addItem(listaDato.getId()+" - "+listaDato.getDireccion());
            }
            
            txt_usuario.removeAllItems();
            for (Usuario listaDato : Usuario.buscar()) {
              txt_usuario.addItem(listaDato.getId()+" - "+listaDato.getNombre());
            }
            
            txt_motivos.removeAllItems();
            for (Motivo listaDato : Motivo.buscar()) {
              txt_motivos.addItem(listaDato.getId()+" - "+listaDato.getMotivos());
            }
            
            txt_estado.removeAllItems();
            for (Estado listaDato : Estado.buscar()) {
              txt_estado.addItem(listaDato.getId()+" - "+listaDato.getNombre());
            }
           
        } catch (Exception ex) {
            mostrarException(ex);
        }
    }
    
    
      @Override
    public void limpiarFormulario() {
         txt_codigo.setText("");
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
            txt_codigo.setText(aten.getId()+"");
            txt_casa.setSelectedItem((aten.getCasa().getId())+" - "+aten.getCasa().getDireccion());
            txt_estado.setSelectedItem(aten.getEstado().getId()+" - "+String.valueOf(aten.getEstado().getNombre()));
            //Fecha 1 
            String fecha = aten.getFecha().toString();
            String[] parts = fecha.split("-");
                int anio = Integer.parseInt(parts[0]); // 2017
                int mes = Integer.parseInt(parts[1]); // 05
                int dia = Integer.parseInt(parts[2]); // 06
            jc_dia.setSelectedItem(dia+"");
            jc_anio.setSelectedItem(anio+"");
            jc_meses.setSelectedIndex(mes-1);   
            
            String fecha2 = aten.getFecha_solucion().toString();
            String[] parts2 = fecha2.split("-");
                int anio1 = Integer.parseInt(parts2[0]); // 2017
                int mes1 = Integer.parseInt(parts2[1]); // 05
                int dia1 = Integer.parseInt(parts2[2]); // 06
            jc_dia1.setSelectedItem(dia1+"");
            jc_anio1.setSelectedItem(anio1+"");
            jc_meses1.setSelectedIndex(mes1-1);
            //txt_fechas.setDate(aten.getFecha_solucion());
            txt_motivos.setSelectedItem(String.valueOf(aten.getMotivos().getId()+" - "+aten.getMotivos().getMotivos()));
            txt_usuario.setSelectedItem(aten.getUsuario().getId()+" - "+aten.getUsuario().getNombre());
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
        Atencion aten;
        String casa = String.valueOf(txt_casa.getSelectedItem());
        String estado = String.valueOf(txt_estado.getSelectedItem());
        String motivos = String.valueOf(txt_motivos.getSelectedItem());
        String usuario = String.valueOf(txt_usuario.getSelectedItem());
        //Fecha
        String fecha_combo = jc_dia.getSelectedItem()+"-"+(jc_meses.getSelectedIndex()+1)+"-"+jc_anio.getSelectedItem();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date parsed = format.parse(fecha_combo);
        java.sql.Date fecha = new java.sql.Date(parsed.getTime());
         //Fecha_solucion
        String fecha2_combo = jc_dia1.getSelectedItem()+"-"+(jc_meses1.getSelectedIndex()+1)+"-"+jc_anio1.getSelectedItem();
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        Date parsed2 = format2.parse(fecha2_combo);
        java.sql.Date fecha_solucion = new java.sql.Date(parsed2.getTime());
        
        Casa cs = new Casa();
        cs.setId(Long.parseLong(casa.substring(0,1)));
        Estado es = new Estado();
        es.setId(Long.parseLong(estado.substring(0,1)));
        Motivo mt = new Motivo();
        mt.setId(Long.parseLong(motivos.substring(0,1)));
        Usuario us = new Usuario();
        us.setId(usuario.substring(0,1));
        
        
      // aten.getCasa().setId(Long.parseLong(casa.substring(0,1)));
      // aten.getEstado().setId(Long.parseLong(estado.substring(0,1)));
      // aten.getMotivos().setId(Long.parseLong(motivos.substring(0,1)));
      // aten.getUsuario().setId(Integer.parseInt(usuario.substring(0,1)));
      // aten.setFecha(fecha);
      // aten.setFecha_solucion(fecha_solucion);
       
       aten = new Atencion(cs, es, fecha,fecha_solucion,mt,us);             
        return aten;
    }

    @Override
    public void setRegistroActual(SuperTabla registro) throws Exception {
         String casa = String.valueOf(txt_casa.getSelectedItem());
        String estado = String.valueOf(txt_estado.getSelectedItem());
        String motivos = String.valueOf(txt_motivos.getSelectedItem());
        String usuario = String.valueOf(txt_usuario.getSelectedItem());
        
        //Fecha
        String fecha_combo = jc_dia.getSelectedItem()+"-"+(jc_meses.getSelectedIndex()+1)+"-"+jc_anio.getSelectedItem();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date parsed = format.parse(fecha_combo);
        java.sql.Date fecha = new java.sql.Date(parsed.getTime());
         //Fecha_solucion
        String fecha2_combo = jc_dia1.getSelectedItem()+"-"+(jc_meses1.getSelectedIndex()+1)+"-"+jc_anio1.getSelectedItem();
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        Date parsed2 = format2.parse(fecha2_combo);
        java.sql.Date fecha_solucion = new java.sql.Date(parsed2.getTime());
        
        Atencion ate;
        ate = (Atencion) registro;
        ate.getCasa().setId(Long.parseLong(casa.substring(0,1)));
        ate.getEstado().setId(Long.parseLong(estado.substring(0,1)));
        ate.getMotivos().setId(Long.parseLong(motivos.substring(0,1)));
        ate.getUsuario().setId(usuario.substring(0,1));
        ate.setId(Long.parseLong(txt_codigo.getText()));
        ate.setFecha(fecha);
        ate.setFecha_solucion(fecha_solucion);
     
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_motivos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jc_dia = new javax.swing.JComboBox<>();
        jc_meses = new javax.swing.JComboBox<>();
        jc_anio = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jc_dia1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jc_meses1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jc_anio1 = new javax.swing.JComboBox<>();
        txt_codigo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Codigo:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Casa:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Estado:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Fecha:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Fecha Solucion:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Motivos:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Usuario:");

        jLabel8.setText("Dia:");

        jc_dia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jc_meses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Enero", "2 - Febrero", "3 - Marzo", "4 - Abril", "5 - Mayo", "6 - Junio", "7- Julio", "8 - Agosto", "9 - Septiembre", "10 - Octubre", "11 - Noviembre", "12 - Diciembre" }));

        jc_anio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017" }));

        jLabel10.setText("Año:");

        jLabel9.setText("Mes:");

        jLabel11.setText("Dia:");

        jc_dia1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel12.setText("Mes:");

        jc_meses1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Enero", "2 - Febrero", "3 - Marzo", "4 - Abril", "5 - Mayo", "6 - Junio", "7- Julio", "8 - Agosto", "9 - Septiembre", "10 - Octubre", "11 - Noviembre", "12 - Diciembre" }));

        jLabel13.setText("Año:");

        jc_anio1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jc_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jc_meses, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jc_anio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jc_dia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jc_meses1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jc_anio1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_motivos, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_estado, javax.swing.GroupLayout.Alignment.LEADING, 0, 234, Short.MAX_VALUE)
                        .addComponent(txt_casa, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(159, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jc_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jc_meses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jc_anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jc_dia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jc_meses1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jc_anio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_motivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> jc_anio;
    private javax.swing.JComboBox<String> jc_anio1;
    private javax.swing.JComboBox<String> jc_dia;
    private javax.swing.JComboBox<String> jc_dia1;
    private javax.swing.JComboBox<String> jc_meses;
    private javax.swing.JComboBox<String> jc_meses1;
    private javax.swing.JComboBox<String> txt_casa;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JComboBox<String> txt_estado;
    private javax.swing.JComboBox<String> txt_motivos;
    private javax.swing.JComboBox<String> txt_usuario;
    // End of variables declaration//GEN-END:variables

}
