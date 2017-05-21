/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import basededatos.BaseDatosOracle;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import modelo.SuperTabla;
import modelo.atencion.Suscriptor;

/**
 *
 * @author leydi
 */
public class FormSuscriptor extends FormTemplate {
Calendar calendar = Calendar.getInstance();//Variable de Objeto Calendar
    /**
     * Creates new form FormSuscriptor
     */
    public FormSuscriptor() {
        initComponents();
        mostrarRegistro(0);
        txt_codigo.setEditable(false);
        //inicio();
    }

    
    @Override
    public void limpiarFormulario() {
            txt_codigo.setText(null);
            txt_nombres.setText(null);
            txt_apellidos.setText(null);
            txt_tipodocumento.setSelectedIndex(0);
            txt_documento.setText(null);
            txt_ciudad_ex.setText(null);
    }

    @Override
    public void mostrarRegistro(int indice) {
        txt_codigo.requestFocus();
         if (indice < 0 || listaDatos.isEmpty()) {
            limpiarFormulario();
            return;
        }
        Suscriptor susc;
        susc = (Suscriptor) listaDatos.get(indice);
       
        if (susc != null) {
            System.out.println(susc);
            txt_codigo.setText(susc.getId()+"");
            txt_nombres.setText(susc.getNombre());
            txt_apellidos.setText(susc.getApellidos());
            txt_tipodocumento.setSelectedItem(susc.getTipo_documento());
            txt_documento.setText(susc.getDocumento());
            System.err.println(susc.getF_expedicion()+"");
            String fecha = susc.getF_expedicion().toString();
            String[] parts = fecha.split("-");
                int anio = Integer.parseInt(parts[0]); // 2017
                int mes = Integer.parseInt(parts[1]); // 05
                int dia = Integer.parseInt(parts[2]); // 06
            jc_dia.setSelectedItem(dia+"");
            jc_anio.setSelectedItem(anio+"");
            jc_meses.setSelectedIndex(mes-1);           
            //txt_fechaexp.setDate(susc.getF_expedicion());
            txt_ciudad_ex.setText(susc.getCiudad_expedicion());
            txt_codigo.requestFocus();
        }
    }

    @Override
    public void inicializar() {
         try {
            listaDatos = new ArrayList<>();
            listaDatos.addAll(Suscriptor.buscar());
            indiceActual = 0;
            habilitarNavegacion();
        } catch (Exception ex) {
            Logger.getLogger(FormPais.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public SuperTabla getNuevoRegistro() throws Exception {
        Suscriptor sub;
        String fecha = jc_dia.getSelectedItem()+"-"+(jc_meses.getSelectedIndex()+1)+"-"+jc_anio.getSelectedItem();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date parsed = format.parse(fecha);
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        sub = new Suscriptor(txt_nombres.getText(), txt_apellidos.getText(),
                txt_tipodocumento.getSelectedItem().toString(),txt_documento.getText(), sqlDate, txt_ciudad_ex.getText());
        return sub;
    }

    @Override
    public void setRegistroActual(SuperTabla registro) throws Exception {
         String fecha = jc_dia.getSelectedItem()+"-"+jc_meses.getSelectedIndex()+"-"+jc_anio.getSelectedItem();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date parsed = format.parse(fecha);
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        
        Suscriptor sub;
        sub = (Suscriptor) registro;
        sub.setNombre(txt_nombres.getText());
        sub.setApellidos(txt_apellidos.getText());
        sub.setNombre(txt_nombres.getText());
        sub.setTipo_documento(txt_tipodocumento.getSelectedItem()+"");
        sub.setDocumento(txt_documento.getText());
        sub.setCiudad_expedicion(txt_ciudad_ex.getText());
        sub.setF_expedicion(sqlDate);
    }

    @Override
    public void ejecutarBusqueda() throws Exception {
        txt_codigo.setEditable(true); 
          long codigo = 0;
        codigo = (txt_codigo.getText().isEmpty()) 
                ? -1 
                : Long.parseLong(txt_codigo.getText());
        listaDatos = new ArrayList<>();
        if(codigo == -1){
           listaDatos.addAll(Suscriptor.buscar());
        }else{
          listaDatos.add(Suscriptor.buscar(codigo));  
        }
        
    }

    @Override
    public void habilitarBusqueda() {
     txt_codigo.setEditable(true);
     txt_codigo.requestFocus(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imprimirJasper() {
        try {
            listaDatos.get(indiceActual)
                    .abrirReporte(BaseDatosOracle.getInstance().getConexion());

        } catch (Exception ex) {
            mostrarException(ex);
        }
    }
       
    /* private void inicio(){
        //El array mes puede contener un registro vacío, se puede corregir con un pequeño ajuste...
        //String[] mes = new java.text.DateFormatSymbols().getMonths();
        //Tipo entero para año
        int an = calendar.get(Calendar.YEAR);
        int ms = calendar.get(Calendar.MONTH);
        int di = calendar.get(Calendar.DATE);
        
        //Especificando modelos para cada Spinner
        SpinnerModel modelDias = new SpinnerNumberModel(di,1,31,1);
        SpinnerModel modelMes = new SpinnerNumberModel(ms,1,12,1);
        //Parámetro numéricos
        //public SpinnerNumberModel(int value, int minimum, int maximum, int stepSize)
        //Valor inicial - valor mínimo - valor máximo - incremento a 1
         SpinnerModel modelAnyo = new SpinnerNumberModel(an, an - 100, an + 100, 1);
        //Agregamos el modelo a cada Spinner
        dia.setModel(modelDias);
        meses.setModel(modelMes);
        anio.setModel(modelAnyo);
    }
    */
    
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
        txt_nombres = new javax.swing.JTextField();
        txt_apellidos = new javax.swing.JTextField();
        txt_ciudad_ex = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_documento = new javax.swing.JTextField();
        txt_codigo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jc_dia = new javax.swing.JComboBox<>();
        jc_meses = new javax.swing.JComboBox<>();
        jc_anio = new javax.swing.JComboBox<>();
        txt_tipodocumento = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nombres:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Apellidos:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tipo Documento:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Fecha de expedicion:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Ciudad de expedicion:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Codigo:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Documento:");

        txt_codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_codigoKeyReleased(evt);
            }
        });

        jLabel8.setText("Dia:");

        jLabel9.setText("Mes:");

        jLabel10.setText("Año:");

        jc_dia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jc_meses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Enero", "2 - Febrero", "3 - Marzo", "4 - Abril", "5 - Mayo", "6 - Junio", "7- Julio", "8 - Agosto", "9 - Septiembre", "10 - Octubre", "11 - Noviembre", "12 - Diciembre" }));

        jc_anio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017" }));

        txt_tipodocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CC", "TI", "CE", "NIT" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ciudad_ex))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_apellidos)
                            .addComponent(txt_nombres)
                            .addComponent(txt_documento)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jc_anio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jc_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jc_meses, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txt_tipodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(316, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_tipodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jc_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jc_meses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jc_anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_ciudad_ex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_codigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigoKeyReleased
         char caracter = evt.getKeyChar();
          if(((caracter < '0') || (caracter > '9'))){
              JOptionPane.showMessageDialog(null, "Digite solo numero");
              txt_codigo.setText("");
          evt.consume();
       }
    }//GEN-LAST:event_txt_codigoKeyReleased

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
            java.util.logging.Logger.getLogger(FormSuscriptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSuscriptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSuscriptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSuscriptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSuscriptor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> jc_anio;
    private javax.swing.JComboBox<String> jc_dia;
    private javax.swing.JComboBox<String> jc_meses;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_ciudad_ex;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_documento;
    private javax.swing.JTextField txt_nombres;
    private javax.swing.JComboBox<String> txt_tipodocumento;
    // End of variables declaration//GEN-END:variables

    
}
