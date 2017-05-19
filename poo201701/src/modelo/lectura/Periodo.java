package modelo.lectura;
// Generated May 12, 2017 7:15:35 PM by Hibernate Tools 4.3.1


import basededatos.BaseDatosOracle;
import static basededatos.Secuencia.nextVal;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Luis Rueda
 */
public class Periodo {


     private long id;
     private long periodo;
     private long lectura;
     private long facturado;

    public Periodo() {
    }

	
    public Periodo(long id, long periodo, long lectura, long facturado) {
        this.id = id;
        this.periodo = periodo;
        this.lectura = lectura;
        this.facturado = facturado;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public long getPeriodo() {
        return this.periodo;
    }
    
    public void setPeriodo(long periodo) {
        this.periodo = periodo;
    }
    public long getLectura() {
        return this.lectura;
    }
    
    public void setLectura(long lectura) {
        this.lectura = lectura;
    }
    public long getFacturado() {
        return this.facturado;
    }
    
    public void setFacturado(long facturado) {
        this.facturado = facturado;
    }
    
    public int insertar() throws SQLException{
        long secuencia = nextVal("PERIODOS_SEQ");
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql = "INSERT INTO PERIODOS (ID,PERIODO,LECTURA,FACTURADO) "
                + "VALUES (?, ?, ?, ?)";
        int ejecucion;
        db.conectar();
        db.prepararSql(sql);
        db.asignarParametro(1, secuencia);
        db.asignarParametro(2, getPeriodo());
        db.asignarParametro(3, getLectura());
        db.asignarParametro(4, getFacturado());
        ejecucion = db.ejecutar();
        db.cerrarSentencia();
        return ejecucion;
    }
    
    public int actualizar() throws SQLException{
       BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE PERIODOS "
                + "SET PERIODO = ?, "
                + "LECTURA = ? ,"
                + "FACTURADO = ? "
                + "WHERE ID = ?";
        db.conectar();
        db.prepararSql(sql);
        db.asignarParametro(1, getPeriodo());
        db.asignarParametro(2, getLectura());
        db.asignarParametro(3, getFacturado());
        db.asignarParametro(4, getId());
        ejecucion = db.ejecutar();
        db.cerrarSentencia();
        return ejecucion;
    }
    
    public int eliminar() throws SQLException{
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql = "DELETE FROM PERIODOS WHERE ID = ?";
        int ejecucion;
        db.conectar();
        db.prepararSql(sql);
        db.asignarParametro(1, getId());
        ejecucion = db.ejecutar();
        db.cerrarSentencia();
        return ejecucion;
    }
    
    public static ArrayList<Periodo> listar() throws SQLException {
        ArrayList<Periodo> datos = new ArrayList<>();
        BaseDatosOracle db  = BaseDatosOracle.getInstance();
        String sql = "SELECT ID, PERIODO,LECTURA,FACTURADO FROM PERIODOS";
        db.conectar();
        db.prepararSql(sql);
        ResultSet reg = db.ejecutarQuery();
        datos.clear();
        while (reg.next()) {
            datos.add(new Periodo(reg.getLong("ID"),reg.getLong("PERIODO"),
                    reg.getLong("LECTURA"),
                    reg.getLong("FACTURADO")
            ));
        }
        return datos;
    }
    
    public static Periodo listar(long id) throws SQLException {
        Periodo dato = null;
        BaseDatosOracle db  = BaseDatosOracle.getInstance();
        String sql = "SELECT PERIODO,LECTURA,FACTURADO FROM PERIODOS";
        db.conectar();
        db.prepararSql(sql);
        db.asignarParametro(1, id);
        ResultSet reg = db.ejecutarQuery();
        long periodo = reg.getLong("PERIODO");
        long lectura = reg.getLong("LECTURA");
        long facturado = reg.getLong("FACTURADO");
        while (reg.next()) {
            dato = new Periodo(id,periodo,lectura,facturado);
        }
        return dato;
    }
}


