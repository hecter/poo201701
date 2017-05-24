/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.atencion;

import basededatos.BaseDatosOracle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.SuperTabla;
import report.facturacion.Concepto;

/**
 *
 * @author 20111532577
 */
public class Estado implements SuperTabla {
    private long id;
    private String nombre;

    public Estado(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    public Estado() {
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public static Estado buscar(long id) throws SQLException,
            Exception {
        Estado estado;
         BaseDatosOracle basededatos;
         ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ID, ESTADO "
                + "FROM ESTADOS "
                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, id);
        cursor = basededatos.ejecutarQuery();
        estado = null;
        if (cursor.next()) {
            estado = new Estado(cursor.getLong("ID"),
                    cursor.getString("ESTADO"));
        }
        return estado;
    }
    
    public static ArrayList<Estado> buscar() throws SQLException,
            Exception {
        ArrayList<Estado> listaEstado;
        Estado est;
     
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = " select ES.ID, ES.Estado "
                + "from ESTADOS ES"
                + " ORDER BY ID ";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaEstado = new ArrayList<>();

        while (cursor.next()) {
           listaEstado.add(new Estado(cursor.getLong("ID"), 
                   cursor.getString("Estado")));
        }
        return listaEstado;
    }
    @Override
    public int insertar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "INSERT INTO ESTADOS "
                + "(ID, ESTADO)"
                + " VALUES "
                + "(?, ?, ?, ? )";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        
        System.out.println(this);
        basededatos.asignarParametro(1, getId());
        basededatos.asignarParametro(2, getNombre());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    @Override
    public int eliminar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "DELETE ESTADOS WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    @Override
    public int actualizar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE ESTADOS "
                + "SET NOMBRE = ?"
                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        basededatos.asignarParametro(2, getNombre());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    @Override
    public String obtenerNombreReporte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
