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
import modelo.lectura.Sector;

/**
 *
 * @author 201315441932
 */
 public class Casa implements SuperTabla{
    private long id;
    private String direccion;
    private String telefono;
    private long estrato;
    private String ciudad;
    private Suscriptor  suscriptor;
    private Sector sector;
 /**
  *    constructor
  * @param id
  * @param direccion
  * @param telefono
  * @param estrato
  * @param ciudad
  * @param suscriptor
  * @param sector 
  */
    public Casa(long id, String direccion, String telefono, long estrato, 
            String ciudad, Suscriptor suscriptor, Sector sector) {
        setId(id);
        setDireccion(direccion);
        setTelefono(telefono);
        setEstrato(estrato);
        setCiudad(ciudad);
        setSuscriptor(suscriptor);
        setSector(sector);
    }
 /**
  * constructor vacio
  */
    public Casa() {
    }
 /**
  * constructor para guardar
  * @param direccion
  * @param telefono
  * @param estrato
  * @param ciudad
  * @param suscriptor
  * @param sector
  * @throws SQLException 
  */
    public Casa(String direccion, String telefono, long estrato, 
            String ciudad, Suscriptor suscriptor, Sector sector) throws SQLException {
          setId(getconsecutivo());
         setDireccion(direccion);
        setTelefono(telefono);
        setEstrato(estrato);
        setCiudad(ciudad);
        setSuscriptor(suscriptor);
        setSector(sector);
    }
  /**
   * 
   * @return id
   */
    public long getId() {
        return id;
    }
 /** 
  * 
  * @param id 
  */
    public void setId(long id) {
        this.id = id;
    }
/**
 * 
 * @return direccion
 */
    public String getDireccion() {
        return direccion;
    }
/**
 * 
 * @param direccion 
 */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
/**
 * 
 * @return telefono
 */
    public String getTelefono() {
        return telefono;
    }
/**
 * 
 * @param telefono 
 */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
/**
 * 
 * @return estrato
 */
    public long getEstrato() {
        return estrato;
    }
/**
 * 
 * @param estrato 
 */
    public void setEstrato(long estrato) {
        this.estrato = estrato;
    }
/**
 * 
 * @return ciudad
 */
    public String getCiudad() {
        return ciudad;
    }
/**
 * 
 * @param ciudad 
 */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
/**
 * 
 * @return suscriptor
 */
    public Suscriptor getSuscriptor() {
        return suscriptor;
    }
/**
 * 
 * @param suscriptor 
 */
    public void setSuscriptor(Suscriptor suscriptor) {
        this.suscriptor = suscriptor;
    }
/**
 * 
 * @return sector
 */
    public Sector getSector() {
        return sector;
    }
/**
 * 
 * @param sector 
 */
    public void setSector(Sector sector) {
        this.sector = sector;
    }
    /**
     * 
     * @return secuencia
     */
      public String obtenerNombreSecuencia() {
        return "CASA_SEQ";
    }
      /**
       * 
       * @return
       * @throws SQLException 
       */
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
        sql = "INSERT INTO CASAS "
                + "(ID, DIRECCION, TELEFONO, ESTRATO, CIUDAD, SUSCRIPTOR_ID, SECTOR_ID)"
                + " VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        
        System.out.println(this);
        basededatos.asignarParametro(1, getId());
        basededatos.asignarParametro(2, getDireccion());
        basededatos.asignarParametro(3, getTelefono());
        basededatos.asignarParametro(4, getEstrato());
        basededatos.asignarParametro(5, getCiudad());
        basededatos.asignarParametro(6, getSuscriptor().getId());
        basededatos.asignarParametro(7, getSector().getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;    }
 /**
  * 
  * @return
  * @throws SQLException 
  */
    @Override
    public int eliminar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "DELETE CASAS WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;    
    }
/**
 * 
 * @return
 * @throws SQLException 
 */
    @Override
    public int actualizar() throws SQLException {
      BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE CASAS "
                + "SET DIRECCION = ?, "
                + "TELEFONO = ?, "
                + "ESTRATO = ?, "
                + "CIUDAD = ?, "
                + "SUSCRIPTOR_ID = ?, "
                + "SECTOR_ID = ? "
                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getDireccion());
        basededatos.asignarParametro(2, getTelefono());
        basededatos.asignarParametro(3, getEstrato());
        basededatos.asignarParametro(4, getCiudad());
        basededatos.asignarParametro(5, getSuscriptor().getId());
        basededatos.asignarParametro(6, getSector().getId());
        basededatos.asignarParametro(7, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;    }

    /**
     * 
     * @param codigo
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public static Casa buscar(long codigo) throws SQLException,
            Exception {
        Casa cas;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ID, DIRECCION, TELEFONO, ESTRATO,CIUDAD,"
                + "SUSCRIPTOR_ID, SECTOR_ID "
                + "FROM CASAS "
                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, codigo);
        cursor = basededatos.ejecutarQuery();
        Suscriptor suscrip= new Suscriptor();
        Sector sect= new Sector();
        cas = null;
        if (cursor.next()) {
            suscrip.setId(cursor.getInt("SUSCRIPTOR_ID"));
            sect.setId(cursor.getInt("SECTOR_ID"));
            cas = new Casa(
                    
                    cursor.getLong("ID"),
                    cursor.getString("DIRECCION"),
                    cursor.getString("TELEFONO"),
                    cursor.getLong("ESTRATO"),
                    cursor.getString("CIUDAD"),
                    suscrip,
                    sect
            );
                   
        }
        return cas;
    }
     /**
      * 
      * @return
      * @throws SQLException
      * @throws Exception 
      */
     public static ArrayList<Casa> buscar() throws SQLException,
            Exception {
        ArrayList<Casa> listaCas;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = " SELECT CS.ID, CS.DIRECCION, CS.TELEFONO, CS.ESTRATO,CS.CIUDAD, " +
            "   CS.SUSCRIPTOR_ID, CS.SECTOR_ID, SEC.DET, SUP.NOMBRES " +
            "   FROM CASAS CS " +
            "   INNER JOIN SECTORES SEC ON SEC.ID = CS.SECTOR_ID " +
            "   INNER JOIN SUSCRIPTORES SUP ON CS.SUSCRIPTOR_ID = SUP.ID "
                + " ORDER BY CS.ID ";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaCas = new ArrayList<>();
        Suscriptor suscrip;
        Sector sect;
        while (cursor.next()) {
            suscrip= new Suscriptor();
            sect= new Sector();
            sect.setId(cursor.getInt("SECTOR_ID"));
            sect.setDet(cursor.getString("DET"));
            suscrip.setId(cursor.getInt("SUSCRIPTOR_ID"));
            suscrip.setNombre(cursor.getString("NOMBRES"));

            listaCas.add(new Casa(
                    cursor.getLong("ID"),
                    cursor.getString("DIRECCION"),
                    cursor.getString("TELEFONO"),
                    cursor.getLong("ESTRATO"),
                    cursor.getString("CIUDAD"),
                    suscrip,
                    sect
            )); 
        }
        return listaCas;
    }
     /**
      * 
      * @return 
      */
    @Override
    public String obtenerNombreReporte() {
        return "/report/atencion/ReportCasa.jrxml";    
    }
    
    
    
    
    
    
}
