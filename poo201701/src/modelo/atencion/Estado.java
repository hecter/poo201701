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
import report.facturacion.Concepto;

/**
 *
 * @author 20111532577
 */
public class Estado {
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
    
}
