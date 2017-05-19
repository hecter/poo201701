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
        BaseDatosOracle basededatos = BaseDatosOracle.getInstance();
        String sql = "INSERT INTO PERIODOS (ID,PERIODO,LECTURA,FACTURADO) VALUES (?, ?, ?, ?)";
        int ejecucion;
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, secuencia);
        basededatos.asignarParametro(2, getPeriodo());
        basededatos.asignarParametro(3, getLectura());
        basededatos.asignarParametro(4, getFacturado());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }
    
    public int actualizar() throws SQLException{
       BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE PERIODOS SET PERIODO = ?, LECTURA = ? ,FACTURADO = ? WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getPeriodo());
        basededatos.asignarParametro(2, getLectura());
        basededatos.asignarParametro(3, getFacturado());
        basededatos.asignarParametro(4, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }
    
    public int eliminar() throws SQLException{
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "DELETE FROM PERIODOS WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }
    
    public static ArrayList<Periodo> listar() throws SQLException {
        ArrayList<Periodo> datos = new ArrayList<>();
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ID, PERIODO,LECTURA,FACTURADO FROM PERIODOS";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery();
        datos.clear();
        while (cursor.next()) {
            datos.add(
                    new Periodo(
                            cursor.getLong("ID"),
                            cursor.getLong("PERIODO"),
                            cursor.getLong("LECTURA"),
                            cursor.getLong("FACTURADO")
                    )
            );
        }
        return datos;
    }
}


