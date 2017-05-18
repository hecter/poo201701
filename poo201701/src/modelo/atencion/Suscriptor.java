/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.atencion;

import java.sql.SQLException;
import modelo.SuperTabla;
import basededatos.*;
import java.sql.Date;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author leydi
 */
public class Suscriptor implements SuperTabla{
    
    private long id;
    private String nombre;
    private String apellidos;
    private String tipo_documento;
    private Date  f_expedicion;
    private String ciudad_expedicion;

    public Suscriptor() {
    }

    public Suscriptor(long id,String nombre, String apellidos, String tipo_documento, 
            Date  f_expedicion, String ciudad_expedicion) throws SQLException, Exception {
        setId(getconsecutivo());
        setNombre(nombre);
        setApellidos(apellidos);
        setTipo_documento(tipo_documento);
        setF_expedicion(f_expedicion);
        setCiudad_expedicion(ciudad_expedicion);
    }

    public Suscriptor(String nombre, String apellidos, String tipo_documento,
            Date f_expedicion, String ciudad_expedicion) throws SQLException, Exception {
        setNombre(nombre);
        setApellidos(apellidos);
        setTipo_documento(tipo_documento);
        setF_expedicion(f_expedicion);
        setCiudad_expedicion(ciudad_expedicion);
    }
    
   
    public String obtenerNombreSecuencia() {
        return "SUSCRIPTORES_SEQ";
    }
    
    public long getconsecutivo() throws SQLException{
        return Secuencia.nextVal(obtenerNombreSecuencia());
    }
    /**
     * 
     * @return
     * @throws SQLException 
     */
    @Override
    public int insertar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "INSERT INTO SUSCRIPTORES "
                + "(ID, NOMBRES, APELLIDOS, TIPO_DOCUMENTO,F_EXPEDICION,CIUDAD_EXPEDICION)"
                + " VALUES "
                + "(?, ?, ?, ?, ?, ?)";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        
        System.out.println(this);
        basededatos.asignarParametro(1, getId());
        basededatos.asignarParametro(2, getNombre());
        basededatos.asignarParametro(3, getApellidos());
        basededatos.asignarParametro(4, getTipo_documento());
        basededatos.asignarParametro(5, getF_expedicion());
        basededatos.asignarParametro(6, getCiudad_expedicion());
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
        sql = "DELETE SUSCRIPTORES WHERE ID = ?";
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
        sql = "UPDATE SUSCRIPTORES "
                + "SET NOMBRES = ?, "
                + "APELLIDOS = ?, "
                + "TIPO_DOCUMENTO = ? "
                + "F_EXPEDICION = ? "
                + "CIUDAD_EXPEDICION = ? "
                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getNombre());
        basededatos.asignarParametro(2, getApellidos());
        basededatos.asignarParametro(3, getTipo_documento());
        basededatos.asignarParametro(4, getF_expedicion());
        basededatos.asignarParametro(5, getCiudad_expedicion());
        basededatos.asignarParametro(6, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }
    
    public static Suscriptor buscar(long codigo) throws SQLException,
            Exception {
        Suscriptor susc;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ID, NOMBRES, APELLIDOS, TIPO_DOCUMENTO,F_EXPEDICION,"
                + "CIUDAD_EXPEDICION "
                + "FROM SUSCRIPTORES "
                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, codigo);
        cursor = basededatos.ejecutarQuery();
        susc = null;
        if (cursor.next()) {
            susc = new Suscriptor(
                    cursor.getLong("ID"),
                    cursor.getString("NOMBRES"),
                    cursor.getString("APELLIDOS"),
                    cursor.getString("TIPO_DOCUMENTO"),
                    cursor.getDate("F_EXPEDICION"),
                    cursor.getString("CIUDAD_EXPEDICION")
            );
        }
        return susc;
    }
     
     public static ArrayList<Suscriptor> buscar() throws SQLException,
            Exception {
        ArrayList<Suscriptor> listaSuscp;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ID, NOMBRES, APELLIDOS, TIPO_DOCUMENTO,F_EXPEDICION,"
                + "CIUDAD_EXPEDICION "
                + "FROM SUSCRIPTORES "
                + "ORDER BY ID";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaSuscp = new ArrayList<>();
        while (cursor.next()) {
            listaSuscp.add(new Suscriptor(
                    cursor.getLong("ID"),
                    cursor.getString("NOMBRES"),
                    cursor.getString("APELLIDOS"),
                    cursor.getString("TIPO_DOCUMENTO"),
                    cursor.getDate("F_EXPEDICION"),
                    cursor.getString("CIUDAD_EXPEDICION")
            ));
        }
        return listaSuscp;
    }

    @Override
    public String obtenerNombreReporte() {
       return "Ni idea";
    }
    
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) throws Exception{
        if(id<0){
            throw new Exception("El id no puede ser negativo");
        }
         this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if(nombre==null){
            throw new Exception("El nombre es nulo");
        }
        if(nombre.equals("")){
            throw new Exception("El nombre es vacio");
        }
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) throws Exception {
        if(apellidos==null){
            throw new Exception("Los apellidos es nulo");
        }
        if(apellidos.equals("")){
            throw new Exception("Los apellidos es vacio");
        }
        this.apellidos = apellidos;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) throws Exception {
        if(tipo_documento==null){
            throw new Exception("El tipo documento no puede ser nulo");
        }
        if(tipo_documento.equals("")){
            throw new Exception("El tipo documento no puede ser vacio");
        }
        this.tipo_documento = tipo_documento;
    }

    public Date  getF_expedicion() {
        return f_expedicion;
    }

    public void setF_expedicion(Date  f_expedicion) {
        this.f_expedicion = f_expedicion;
    }

    public String getCiudad_expedicion() {
        return ciudad_expedicion;
    }

    public void setCiudad_expedicion(String ciudad_expedicion) throws Exception {
        if(ciudad_expedicion==null){
            throw new Exception("La ciudad de expedicion es nulo");
        }
        if(ciudad_expedicion.equals("")){
            throw new Exception("La ciudad de expedicion es vacio");
        }
        this.ciudad_expedicion = ciudad_expedicion;
    }

   
    
}
