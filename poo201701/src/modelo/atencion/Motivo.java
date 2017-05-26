/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.atencion;

import basededatos.BaseDatosOracle;
import basededatos.Secuencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.SuperTabla;
import modelo.pagos.Rol;
import modelo.pagos.Usuario;
import report.facturacion.Concepto;

/**
 *
 * @author 20111532577
 */
public class Motivo implements SuperTabla{
    private long id;
    private String motivos;
    private Concepto concepto;

    public Motivo(long id, String motivos, Concepto concepto) {
        this.id = id;
        this.motivos = motivos;
        this.concepto = concepto;
    }

    public Motivo(String motivos, Concepto concepto) {
        this.motivos = motivos;
        this.concepto = concepto;
    }

    public Motivo() {
    }
    
    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }
    
    public String getMotivos() {
        return motivos;
    }

    public void setMotivos(String motivos) {
        this.motivos = motivos;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
     public String obtenerNombreSecuencia() {
        return "MOTIVOS_SEQ";
    }
      public long getconsecutivo() throws SQLException{
        return Secuencia.nextVal(obtenerNombreSecuencia());
    }
    
    @Override
    public int insertar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "INSERT INTO MOTIVOS "
                + "(ID, MOTIVO, CONCEPTOS_ID)"
                + " VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        
        System.out.println(this);
        basededatos.asignarParametro(1, getId());
        basededatos.asignarParametro(2, getMotivos());
        basededatos.asignarParametro(3, getConcepto.getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;      }

    @Override
    public int eliminar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "DELETE MOTIVOS WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;    }

    @Override
    public int actualizar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE MOTIVOS "
                + "SET MOTIVO = ?, "
                + "CONCEPTOS_ID = ?, "
                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getMotivos());
        basededatos.asignarParametro(2, getConcepto.getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;     
    }

    public static ArrayList<Motivo> buscar() throws SQLException,
            Exception {
        ArrayList<Motivo> listaMotivo;
        Motivo mot;
        Concepto concep;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = " SELECT MT.ID,MT.MOTIVO,MT.CONCEPTOS_ID,CON.DESCRIPCION " +
                " FROM MOTIVOS MT " +
                " INNER JOIN CONCEPTOS CON ON CON.ID=MT.CONCEPTOS_ID "
                + " ORDER BY ID ";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaMotivo = new ArrayList<>();

        while (cursor.next()) {
            concep = new Concepto();
            concep.setId(cursor.getInt("CONCEPTOS_ID"));

            listaMotivo.add(new Motivo(
                    cursor.getLong("ID"),
                    cursor.getString("MOTIVO"),
                    concep
            ));
        }
        return listaMotivo;
    }
    
    @Override
    public String obtenerNombreReporte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
