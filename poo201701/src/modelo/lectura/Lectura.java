package modelo.lectura;
// Generated May 12, 2017 7:15:35 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import modelo.atencion.Usuario;

/**
 * Lectura generated by hbm2java
 */
public class Lectura {


     private BigDecimal id;
     private Usuario usuarios;
     private Periodo periodos;
     private Medidor medidores;
     private BigDecimal lectura;
     private Date fecha;
     private String causal;
     private BigDecimal estado;

    public Lectura() {
    }

    public Lectura(BigDecimal id, Usuario usuarios, Periodo periodos, Medidor medidores, BigDecimal lectura, Date fecha, String causal, BigDecimal estado) {
       this.id = id;
       this.usuarios = usuarios;
       this.periodos = periodos;
       this.medidores = medidores;
       this.lectura = lectura;
       this.fecha = fecha;
       this.causal = causal;
       this.estado = estado;
    }
   
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public Usuario getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }
    public Periodo getPeriodos() {
        return this.periodos;
    }
    
    public void setPeriodos(Periodo periodos) {
        this.periodos = periodos;
    }
    public Medidor getMedidores() {
        return this.medidores;
    }
    
    public void setMedidores(Medidor medidores) {
        this.medidores = medidores;
    }
    public BigDecimal getLectura() {
        return this.lectura;
    }
    
    public void setLectura(BigDecimal lectura) {
        this.lectura = lectura;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getCausal() {
        return this.causal;
    }
    
    public void setCausal(String causal) {
        this.causal = causal;
    }
    public BigDecimal getEstado() {
        return this.estado;
    }
    
    public void setEstado(BigDecimal estado) {
        this.estado = estado;
    }




}


