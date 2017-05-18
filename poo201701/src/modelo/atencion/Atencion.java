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
 *
 * @author 20111532577
 */
public class Atencion implements SuperTabla{
    private long id;
    private Casa casa;
    private Estado estado;
    private Date fecha;
    private Date fecha_solucion;
    private Motivo motivos;
    private Usuario usuario;

    public Atencion() {
    }
    
    public Atencion(long id, Casa casa, Estado estado, Date fecha, 
            Date fecha_solucion, Motivo motivos, Usuario usuario) {
        this.id = id;
        this.casa = casa;
        this.estado = estado;
        this.fecha = fecha;
        this.fecha_solucion = fecha_solucion;
        this.motivos = motivos;
        this.usuario = usuario;
    }

    public Atencion(Casa casa, Estado estado, Date fecha, 
            Date fecha_solucion, Motivo motivos, Usuario usuario) {
        this.casa = casa;
        this.estado = estado;
        this.fecha = fecha;
        this.fecha_solucion = fecha_solucion;
        this.motivos = motivos;
        this.usuario = usuario;
    }
      
  
    public String obtenerNombreSecuencia() {
        return "ATENCIONES_SEQ";
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
        sql = "INSERT INTO ATENCIONES "
                + "(ID, CASA_ID, ESTADOS_ID, FECHA, FECHA_SOLUCION,"
                + "MOTIVOS_ID, USUARIOS_ID )"
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

    @Override
    public int actualizar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE ATENCIONES SET "
                + "CASA_ID = ?, "
                + "ESTADO_ID = ? "
                + "FECHA = ?, "
                + "FECHA_SOLUCION = ? "
                + "MOTIVOS_ID = ? "
                + "USUARIOS_ID = ? "
                + "WHERE ID = ?";
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
        sql = "SELECT ID, CASA_ID, ESTADOS_ID, FECHA, FECHA_SOLUCION,"
                + "MOTIVOS_ID, USUARIOS_ID "
                + "FROM ATENCIONES "
                + "IINER"
                + "WHERE ID = ?";
        
        /*
        SELECT ATN.ID, ATN.CASA_ID, ATN.ESTADOS_ID,ET.ESTADO, ATN.FECHA, ATN.FECHA_SOLUCION,
                ATN.MOTIVOS_ID,MT.MOTIVO, ATN.USUARIOS_ID,US.NOMBRE 
                FROM ATENCIONES ATN
                INNER JOIN CASA CS ON CS.ID = ATN.CASA_ID
                INNER JOIN ESTADOS ET ON ET.ID = ATN.ESTADOS_ID
                INNER JOIN MOTIVOS MT ON MT.ID = ATN.MOTIVOS_ID
                INNER JOIN USUARIOS US ON US.ID = ATN.USUARIOS_ID
                WHERE ATN.ID = 1
        */
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
            motivo = new Motivo();
            motivo.setId(cursor.getInt("MOTIVOS_ID"));
            usuario = new Usuario();
            usuario.setId(cursor.getInt("USUARIOS_ID"));
            
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
         sql = "SELECT ID, CASA_ID, ESTADOS_ID, FECHA, FECHA_SOLUCION,"
                + "MOTIVOS_ID, USUARIOS_ID "
                + "FROM ATENCIONES "
                + "WHERE ID = ? "
                + "ORDER BY ID";
        
        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaAtencion = new ArrayList<>();
        
        while (cursor.next()) {
            casa = new Casa();
            casa.setId(cursor.getInt("CASA_ID"));
            estado = new Estado();
            estado.setId(cursor.getInt("ESTADOS_ID"));
            motivo = new Motivo();
            motivo.setId(cursor.getInt("MOTIVOS_ID"));
            usuario = new Usuario();
            usuario.setId(cursor.getInt("USUARIOS_ID"));
            
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

    @Override
    public String obtenerNombreReporte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha_solucion() {
        return fecha_solucion;
    }

    public void setFecha_solucion(Date fecha_solucion) {
        this.fecha_solucion = fecha_solucion;
    }

    public Motivo getMotivos() {
        return motivos;
    }

    public void setMotivos(Motivo motivos) {
        this.motivos = motivos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
