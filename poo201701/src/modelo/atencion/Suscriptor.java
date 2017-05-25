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
import java.util.ArrayList;

/**
 *
 * @author Atención
 */
public class Suscriptor implements SuperTabla{
    
    private long id;
    private String nombre;
    private String apellidos;
    private String tipo_documento;
    private String documento;
    private Date  f_expedicion;
    private String ciudad_expedicion;

    /**
     * Constructor vacio
     */
    public Suscriptor() {
    }

    /**
     * Constructor de suscriptor
     * @param id codigo del suscriptor
     * @param nombre nombre del suscriptor
     * @param apellidos apellidos del suscriptor
     * @param tipo_documento tipo_documento del suscriptor
     * @param documento  documento del suscriptor
     * @param f_expedicion  f_expedicion del suscriptor
     * @param ciudad_expedicion  ciudad_expedicion del suscriptor
     * @throws SQLException Exceptiones sql
     * @throws Exception  Excepciones
     */
    public Suscriptor(long id, String nombre, String apellidos, String tipo_documento, 
            String documento, Date f_expedicion, String ciudad_expedicion) throws SQLException, Exception {
        setId(id);
        setNombre(nombre);
        setApellidos(apellidos);
        setTipo_documento(tipo_documento);
        setDocumento(documento);
        setF_expedicion(f_expedicion);
        setCiudad_expedicion(ciudad_expedicion);
    }

   
     /**
     * Constructor de suscriptor
     * @param nombre nombre del suscriptor
     * @param apellidos apellidos del suscriptor
     * @param tipo_documento tipo_documento del suscriptor
     * @param documento  documento del suscriptor
     * @param f_expedicion  f_expedicion del suscriptor
     * @param ciudad_expedicion  ciudad_expedicion del suscriptor
     * @throws SQLException Exceptiones sql
     * @throws Exception  Excepciones
     */
    public Suscriptor(String nombre, String apellidos, String tipo_documento,
            String documento, Date f_expedicion, String ciudad_expedicion) throws SQLException, Exception {
        setId(getconsecutivo());
        setNombre(nombre);
        setApellidos(apellidos);
        setTipo_documento(tipo_documento);
        setDocumento(documento);
        setF_expedicion(f_expedicion);
        setCiudad_expedicion(ciudad_expedicion);
    }

    /**
     * Nombre de secuencia
     * @return el nombre de la secuencia
     */
    public String obtenerNombreSecuencia() {
        return "SUSCRIPTORES_SEQ";
    }
    
    /**
     * Metodo que obtiene el consecutivo 
     * de la secuencia
     * @return consecutivo
     * @throws SQLException  Excepciones
     */
    public long getconsecutivo() throws SQLException{
        return Secuencia.nextVal(obtenerNombreSecuencia());
    }
    /**
     * Método insertar
     * @return entero ejecucion 
     * @throws SQLException 
     */
    @Override
    public int insertar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "INSERT INTO SUSCRIPTORES "
                + "(ID, NOMBRES, APELLIDOS, TIPO_DOCUMENTO, DOCUMENTO, "
                + " F_EXPEDICION,CIUDAD_EXPEDICION)"
                + " VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        
        basededatos.asignarParametro(1, getId());
        basededatos.asignarParametro(2, getNombre());
        basededatos.asignarParametro(3, getApellidos());
        basededatos.asignarParametro(4, getTipo_documento());
        basededatos.asignarParametro(5, getDocumento());
        basededatos.asignarParametro(6, getF_expedicion());
        basededatos.asignarParametro(7, getCiudad_expedicion());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }
    /**
     * Método eliminar
     * @return entero ejecucion 
     * @throws SQLException 
     */
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
    /**
     * Método actualizar
     * @return entero ejecucion 
     * @throws SQLException 
     */
    @Override
    public int actualizar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE SUSCRIPTORES SET "
                + "NOMBRES = ?, APELLIDOS = ?, TIPO_DOCUMENTO = ?, "
                + "DOCUMENTO = ?, F_EXPEDICION = ?, CIUDAD_EXPEDICION = ? "
                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getNombre());
        basededatos.asignarParametro(2, getApellidos());
        basededatos.asignarParametro(3, getTipo_documento());
        basededatos.asignarParametro(4, getDocumento());
        basededatos.asignarParametro(5, getF_expedicion());
        basededatos.asignarParametro(6, getCiudad_expedicion());
        basededatos.asignarParametro(7, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }
    
    /**
     *  Método buscar
     * @param codigo del suscriptor 
     * a buscar
     * @return el suscriptor
     * @throws SQLException
     * @throws Exception 
     */
    public static Suscriptor buscar(long codigo) throws SQLException,
            Exception {
        Suscriptor susc;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ID, NOMBRES, APELLIDOS,DOCUMENTO, TIPO_DOCUMENTO,F_EXPEDICION,"
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
                    cursor.getString("DOCUMENTO"),
                    cursor.getDate("F_EXPEDICION"),
                    cursor.getString("CIUDAD_EXPEDICION")
            );
        }
        return susc;
    }
     /**
      * Método buscar suscriptor
      * @return arraylist con los suscriptores
      * @throws SQLException
      * @throws Exception 
      */
     public static ArrayList<Suscriptor> buscar() throws SQLException,
            Exception {
        ArrayList<Suscriptor> listaSuscp;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ID, NOMBRES, APELLIDOS,DOCUMENTO, TIPO_DOCUMENTO,F_EXPEDICION,"
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
                    cursor.getString("DOCUMENTO"),
                    cursor.getDate("F_EXPEDICION"),
                    cursor.getString("CIUDAD_EXPEDICION")
            ));
        }
        return listaSuscp;
    }

    /**
     * Nombre del reporte
     * @return nombre
     */
    @Override
    public String obtenerNombreReporte() {
       return "/report/atencion/ReportSuscriptor.jrxml";
    }
    
    /**
     * Método obtener id
     * @return id
     */
    public long getId() {
        return id;
    }
    /**
     * Método set id
     * @param id del suscriptor
     * @throws Exception excepciones
     */
    public void setId(long id) throws Exception{
        if(id<0){
            throw new Exception("El id no puede ser negativo");
        }
         this.id = id;
    }
    
    /**
     * Método obtener nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método set nombre
     * @param nombre del suscriptor
     * @throws Exception excepciones
     */
    public void setNombre(String nombre) throws Exception {
        if(nombre==null){
            throw new Exception("El nombre es nulo");
        }
        if(nombre.equals("")){
            throw new Exception("El nombre es vacio");
        }
        this.nombre = nombre;
    }

    /**
     * Método obtener apellido
     * @return apellido
     */
    public String getApellidos() {
        return apellidos;
    }
    
    /**
     * Método set apellidos
     * @param apellidos del suscriptor
     * @throws Exception excepciones
     */
    public void setApellidos(String apellidos) throws Exception {
        if(apellidos==null){
            throw new Exception("Los apellidos es nulo");
        }
        if(apellidos.equals("")){
            throw new Exception("Los apellidos es vacio");
        }
        this.apellidos = apellidos;
    }
    
    /**
     * Método obtener tipo documento
     * @return tipo documento
     */
    public String getTipo_documento() {
        return tipo_documento;
    }
    
     /**
     * Método set tipo_documento
     * @param tipo_documento del suscriptor
     * @throws Exception excepciones
     */
    public void setTipo_documento(String tipo_documento) throws Exception {
        if(tipo_documento==null){
            throw new Exception("El tipo documento no puede ser nulo");
        }
        if(tipo_documento.equals("")){
            throw new Exception("El tipo documento no puede ser vacio");
        }
        this.tipo_documento = tipo_documento;
    }
    
    /**
     * Método obtener fecha expedición
     * @return fecha expedición
     */
    public Date  getF_expedicion() {
        return f_expedicion;
    }
    
    /**
     * Método set f_expedicion
     * @param f_expedicion del suscriptor
     */
    public void setF_expedicion(Date  f_expedicion) {
        this.f_expedicion = f_expedicion;
    }
    
    /**
     * Método obtener ciudad de expedicion
     * @return ciudad de expedicion
     */
    public String getCiudad_expedicion() {
        return ciudad_expedicion;
    }
    
    /**
     * Método set ciudad_expedicion
     * @param ciudad_expedicion del suscriptor
     * @throws Exception excepciones
     */
    public void setCiudad_expedicion(String ciudad_expedicion) throws Exception {
        if(ciudad_expedicion==null){
            throw new Exception("La ciudad de expedicion es nulo");
        }
        if(ciudad_expedicion.equals("")){
            throw new Exception("La ciudad de expedicion es vacio");
        }
        this.ciudad_expedicion = ciudad_expedicion;
    }
    
    /**
     * Método obtener documento
     * @return obtener documento
     */
    public String getDocumento() {
        return documento;
    }
    
     /**
     * Método set documento
     * @param documento del suscriptor
     * @throws Exception excepciones
     */
    public void setDocumento(String documento) throws Exception {
        if(documento==null){
            throw new Exception("La ciudad de expedicion es nulo");
        }
        if(documento.equals("")){
            throw new Exception("La ciudad de expedicion es vacio");
        }
        this.documento = documento;
    }
}
