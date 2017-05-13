/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.atencion;

import basededatos.BaseDatosOracle;
import java.sql.Date;
import java.sql.SQLException;
import modelo.SuperTabla;

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
    private long usuario;

    public Atencion() {
    }

    public Atencion(long codigo, Casa casa, Estado estado, Date fecha, Date fecha_solucion, Motivo motivos, long usuario) {
        this.id = codigo;
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
        
        System.out.println(this);
        basededatos.asignarParametro(1, getId());
        //basededatos.asignarParametro(2, getCasa());
        //basededatos.asignarParametro(3, getEstado());
        basededatos.asignarParametro(4, getFecha());
        basededatos.asignarParametro(4, getFecha_solucion());
       // basededatos.asignarParametro(4, getMotivos());
        basededatos.asignarParametro(4, getUsuario());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    @Override
    public int eliminar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }
    

  
    
    
}
