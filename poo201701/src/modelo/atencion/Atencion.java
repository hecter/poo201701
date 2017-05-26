/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.atencion;

import basededatos.BaseDatosOracle;
import basededatos.Secuencia;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.SuperTabla;
import modelo.pagos.Usuario;

/**
 * Clase Atención
 * @author Leonardo
 */
public class Atencion implements SuperTabla {

    private long id;
    private Casa casa;
    private Estado estado;
    private Date fecha;
    private Date fecha_solucion;
    private Motivo motivos;
    private Usuario usuario;

    /**
     * Constructor vacio de la clase Atencion 
     */
    public Atencion() {
      
    }
    /**
     * Constructor de la clase Atencion
     * @param id codigo de la atencion
     * @param casa casa a la que se le hace la atencion
     * @param estado estado de la atencion
     * @param fecha fecha en la que se hace la atencion
     * @param fecha_solucion fecha en la que se le da solucion a la atencion
     * @param motivos motivos de la atencion prestada
     * @param usuario usuario que atendio la atencion
     */
    public Atencion(long id, Casa casa, Estado estado, Date fecha,
            Date fecha_solucion, Motivo motivos, Usuario usuario) {
        setId(id);
        setCasa(casa);
        setEstado(estado);
        setFecha(fecha);
        setFecha_solucion(fecha_solucion);
        setMotivos(motivos);
        setUsuario(usuario);
       
    }
    /**
     * Constructor de la clase Atencion
     * @param casa casa a la que se le hace la atencion
     * @param estado estado de la atencion
     * @param fecha fecha en la que se hace la atencion
     * @param fecha_solucion fecha en la que se le da solucion a la atencion
     * @param motivos motivos de la atencion prestada
     * @param usuario usuario que atendio la atencion
     * @throws SQLException 
     */
    public Atencion(Casa casa, Estado estado, Date fecha,
            Date fecha_solucion, Motivo motivos, Usuario usuario) throws SQLException {
        setId(getconsecutivo());
        setCasa(casa);
        setEstado(estado);
        setFecha(fecha);
        setFecha_solucion(fecha_solucion);
        setMotivos(motivos);
        setUsuario(usuario);
    }
    /**
     * Nombre de la secuencia
     * @return el nombre de la secuencia
     */
    public String obtenerNombreSecuencia() {
        return "ATENCIONES_SEQ";
    }
    /**
     * Obtiene el consecutivo de la secuencia
     * @return secuencia
     * @throws SQLException 
     */
    public long getconsecutivo() throws SQLException {
        return Secuencia.nextVal(obtenerNombreSecuencia());
    }
    
    /**
     * Insertar los datos de la atencion
     * @return un entero
     * @throws SQLException 
     */
    @Override
    public int insertar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "INSERT INTO ATENCIONES "
                + "(ID, CASA_ID, ESTADOS_ID, FECHA, FECHA_SOLUCION,"
                + " MOTIVOS_ID, USUARIOS_ID )"
                + " VALUES "
                + "(?, ?, ?, ?, ?, ?, ? )";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        basededatos.asignarParametro(2, getCasa().getId());
        basededatos.asignarParametro(3, getEstado().getId());
        basededatos.asignarParametro(4, getFecha());
        basededatos.asignarParametro(5, getFecha_solucion());
        basededatos.asignarParametro(6, getMotivos().getId());
        basededatos.asignarParametro(7, getUsuario().getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }
    
    /**
     * metodo eliminar atencion por codigo
     * @return entero
     * @throws SQLException 
     */
    @Override
    public int eliminar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "DELETE ATENCIONES WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }
    
    /**
     * metodo actualizar
     * @return entero
     * @throws SQLException 
     */
    @Override
    public int actualizar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE ATENCIONES SET "
                + "CASA_ID = ?, "
                + "ESTADOS_ID = ?, "
                + "FECHA = ?, "
                + "FECHA_SOLUCION = ?, "
                + "MOTIVOS_ID = ?, "
                + "USUARIOS_ID = ? "
                + "WHERE ID = ? ";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getCasa().getId());
        basededatos.asignarParametro(2, getEstado().getId());
        basededatos.asignarParametro(3, getFecha());
        basededatos.asignarParametro(4, getFecha_solucion());
        basededatos.asignarParametro(5, getMotivos().getId());
        basededatos.asignarParametro(6, getUsuario().getId());
        basededatos.asignarParametro(7, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * Metodo buscar atencion
     * @param codigo codigo de la atencion
     * @return clase atencion
     * @throws SQLException
     * @throws Exception 
     */
    public static Atencion buscar(long codigo) throws SQLException,
            Exception {
        Atencion atencion;
        Casa casa;
        Estado estado;
        Motivo motivo;
        Usuario usuario;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ATN.ID, ATN.CASA_ID, ATN.ESTADOS_ID,ET.ESTADO, ATN.FECHA, ATN.FECHA_SOLUCION,"
                + "  ATN.MOTIVOS_ID,MT.MOTIVO, ATN.USUARIOS_ID,US.NOMBRE "
                + "  FROM ATENCIONES ATN "
                + "  INNER JOIN CASAS CS ON CS.ID = ATN.CASA_ID "
                + "  INNER JOIN ESTADOS ET ON ET.ID = ATN.ESTADOS_ID "
                + "  INNER JOIN MOTIVOS MT ON MT.ID = ATN.MOTIVOS_ID "
                + "  INNER JOIN USUARIOS US ON US.ID = ATN.USUARIOS_ID "
                + "  WHERE ATN.ID = ?";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, codigo);
        cursor = basededatos.ejecutarQuery();
        atencion = null;
        if (cursor.next()) {
            casa = new Casa();
            casa.setId(cursor.getInt("CASA_ID"));
            estado = new Estado();
            estado.setId(cursor.getInt("ESTADOS_ID"));
            estado.setNombre(cursor.getString("ESTADO"));
            motivo = new Motivo();
            motivo.setId(cursor.getInt("MOTIVOS_ID"));
            motivo.setMotivos(cursor.getString("MOTIVO"));
            usuario = new Usuario();
            usuario.setId(cursor.getString("USUARIOS_ID"));
            usuario.setNombre(cursor.getString("NOMBRE"));
            atencion = new Atencion(
                    cursor.getLong("ID"),
                    casa,
                    estado,
                    cursor.getDate("FECHA"),
                    cursor.getDate("FECHA_SOLUCION"),
                    motivo,
                    usuario
            );
        }
        return atencion;
    }
    
    /**
     * Metodo buscar sin parametro 
     * @return lista de atencion
     * @throws SQLException
     * @throws Exception 
     */
    public static ArrayList<Atencion> buscar() throws SQLException,
            Exception {
        ArrayList<Atencion> listaAtencion;
        Atencion atencion;
        Casa casa;
        Estado estado;
        Motivo motivo;
        Usuario usuario;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ATN.ID, ATN.CASA_ID, ATN.ESTADOS_ID,ET.ESTADO, ATN.FECHA, ATN.FECHA_SOLUCION,"
                + "  ATN.MOTIVOS_ID,MT.MOTIVO, ATN.USUARIOS_ID,US.NOMBRE,CS.Direccion "
                + "  FROM ATENCIONES ATN "
                + "  INNER JOIN CASAS CS ON CS.ID = ATN.CASA_ID "
                + "  INNER JOIN ESTADOS ET ON ET.ID = ATN.ESTADOS_ID "
                + "  INNER JOIN MOTIVOS MT ON MT.ID = ATN.MOTIVOS_ID "
                + "  INNER JOIN USUARIOS US ON US.ID = ATN.USUARIOS_ID "
                + "ORDER BY ID";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaAtencion = new ArrayList<>();

        while (cursor.next()) {
            casa = new Casa();
            casa.setId(cursor.getInt("CASA_ID"));
            casa.setDireccion(cursor.getString("Direccion"));
            estado = new Estado();
            estado.setId(cursor.getInt("ESTADOS_ID"));
            estado.setNombre(cursor.getString("ESTADO"));
            motivo = new Motivo();
            motivo.setId(cursor.getInt("MOTIVOS_ID"));
            motivo.setMotivos(cursor.getString("MOTIVO"));
            usuario = new Usuario();
            usuario.setId(cursor.getString("USUARIOS_ID"));
            usuario.setNombre(cursor.getString("NOMBRE"));

            listaAtencion.add(new Atencion(
                    cursor.getLong("ID"),
                    casa,
                    estado,
                    cursor.getDate("FECHA"),
                    cursor.getDate("FECHA_SOLUCION"),
                    motivo,
                    usuario
            ));
        }
        return listaAtencion;
    }
    
    /**
     * obtiene el nombre del reporte
     * @return  nombre del reporte
     */
    @Override
    public String obtenerNombreReporte() {
        return "/report/atencion/ReportAtencion.jrxml";   
    }
    /**
     * Metodo que retorna id
     * @return  id 
     */
    public long getId() {
        return id;
    }
    
    /**
     * Metodo que recibe el id 
     * @param id id de la atencion
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Metodo que retorna clase Casa
     * @return clase Casa 
     */
    public Casa getCasa() {
        return casa;
    }
    
    /**
     * Metodo que recibe el casa 
     * @param casa casa de la atencion
     */
    public void setCasa(Casa casa) {
        this.casa = casa;
    }
    
    /**
     * Metodo que retorna clase Estado
     * @return clase  Estado 
     */
    public Estado getEstado() {
        return estado;
    }
    
    /**
     * Metodo que recibe el estado 
     * @param estado  estado de la atencion
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    /**
     * Metodo que retorna la fecha
     * @return  fecha 
     */
    public Date getFecha() {
        return fecha;
    }
    
    /**
     * Metodo que recibe fecha
     * @param fecha  fecha de atencion
     */ 
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Metodo que retorna la fecha de solucion
     * @return  fecha de solucion 
     */
    public Date getFecha_solucion() {
        return fecha_solucion;
    }
    
    
    /**
     * Metodo que recibe la fecha solucion 
     * @param fecha_solucion  fecha solucion
     */
    public void setFecha_solucion(Date fecha_solucion) {
        this.fecha_solucion = fecha_solucion;
    }
    
    /**
     * Metodo que retorna clase  motivo
     * @return  clase motivo 
     */
    public Motivo getMotivos() {
        return motivos;
    }
    
    /**
     * Metodo que recibe el motivo 
     * @param motivos  clase motivos
     */
    public void setMotivos(Motivo motivos) {
        this.motivos = motivos;
    }
    
    /**
     * Metodo que retorna clase usuario
     * @return clase usuario 
     */
    public Usuario getUsuario() {
        return usuario;
    }
    
    /**
     * Metodo que recibe el usuario 
     * @param usuario clase usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
