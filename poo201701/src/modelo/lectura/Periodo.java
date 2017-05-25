package modelo.lectura;
// Generated May 12, 2017 7:15:35 PM by Hibernate Tools 4.3.1


import basededatos.BaseDatosOracle;
import static basededatos.Secuencia.nextVal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import modelo.SuperTabla;

/**
 * Luis Rueda
 */
public class Periodo /*implements SuperTabla*/{
     private long id;
     private long periodo;
     private int lectura;
     private int facturado;

    public Periodo() {
    }

	
    public Periodo(long id, long periodo, int lectura, int facturado) {
        this.id = id;
        this.periodo = periodo;
        this.lectura = lectura;
        this.facturado = facturado;
    }
    
    public Periodo(long id, long periodo) {
        this.id = id;
        this.periodo = periodo;
    }
    
    public Periodo(long periodo, int lectura, int facturado) {
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
    
    public void setLectura(int lectura) {
        this.lectura = lectura;
    }
    public long getFacturado() {
        return this.facturado;
    }
    
    public void setFacturado(int facturado) {
        this.facturado = facturado;
    }
    
    public static ArrayList<Periodo> listar() throws SQLException {
        ArrayList<Periodo> datos = new ArrayList<>();
        BaseDatosOracle db  = BaseDatosOracle.getInstance();
        String sql = "SELECT ID, PERIODO, LECTURA, FACTURADO FROM PERIODOS";
        db.conectar();
        db.prepararSql(sql);
        ResultSet reg = db.ejecutarQuery();
        datos.clear();
        while (reg.next()) {
            datos.add(new Periodo(reg.getLong("ID"),reg.getLong("PERIODO"),reg.getInt("LECTURA"),reg.getInt("FACTURADO")
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
        int lectura = reg.getInt("LECTURA");
        int facturado = reg.getInt("FACTURADO");
        while (reg.next()) {
            dato = new Periodo(id,periodo,lectura,facturado);
        }
        return dato;
    }
    
    public int insertar() throws SQLException{
        long secuencia = nextVal("PERIODOS_SEQ");
        setId(secuencia);
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql = "INSERT INTO PERIODOS(ID, PERIODO, LECTURA, FACTURADO) VALUES (?,?, ?, ?)";
        int ejecucion;
        db.conectar();
        db.prepararSql(sql);
        db.asignarParametro(1, getId());
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
        sql = "UPDATE PERIODOS SET PERIODO = ?, LECTURA = ?, FACTURADO = ? WHERE ID = ?";
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
    
    public int actualizarLecturaFacturado() throws SQLException{
       BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE PERIODOS SET LECTURA = ?, FACTURADO = ? WHERE ID = ?";
        db.conectar();
        db.prepararSql(sql);
        db.asignarParametro(1, getLectura());
        db.asignarParametro(2, getFacturado());
        db.asignarParametro(3, getId());
        ejecucion = db.ejecutar();
        db.cerrarSentencia();
        return ejecucion;
    }
    
    public int eliminar() throws SQLException{
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        System.err.println(getId());
        String sql = "DELETE FROM PERIODOS WHERE ID = ?";
        int ejecucion;
        db.conectar();
        db.prepararSql(sql);
        db.asignarParametro(1, getId());
        ejecucion = db.ejecutar();
        db.cerrarSentencia();
        return ejecucion;
    }
    
    public static boolean existe(long periodo) throws SQLException {
        ArrayList<Sector> datos = new ArrayList<>();
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        bd.conectar();
        bd.prepararSql("SELECT PERIODOS.ID FROM PERIODOS WHERE PERIODO = ?");
        bd.asignarParametro(1, periodo);
        ResultSet reg = bd.ejecutarQuery();
        datos.clear();
        return reg.next();
    }

    /*@Override
    public String obtenerNombreReporte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}


