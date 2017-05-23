package modelo.lectura;
// Generated May 12, 2017 7:15:35 PM by Hibernate Tools 4.3.1

import basededatos.BaseDatosOracle;
import static basededatos.Secuencia.nextVal;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import modelo.SuperTabla;

/**
 *
 * Formulario de los periodos,
 *
 * @author Ricardo Aragon, email: <ricardoaragon12@hotmail.com>
 * @version 1.0
 * @since 2017
 */
public class Periodo implements SuperTabla {

    private long id;
    private long periodo;
    private long lectura;
    private long facturado;

    /**
     * constructor vacio
     */
    public Periodo() {
    }

    /**
     * constructor con todoss los parametros de la clase
     *
     * @param id
     * @param periodo
     * @param lectura
     * @param facturado
     */
    public Periodo(long id, long periodo, long lectura, long facturado) {
        this.id = id;
        this.periodo = periodo;
        this.lectura = lectura;
        this.facturado = facturado;
    }

    public Periodo(long periodo, long lectura, long facturado) {
        this.periodo = periodo;
        this.lectura = lectura;
        this.facturado = facturado;
    }

    /**
     * retorna id del regstro
     *
     * @return la id del registro
     */
    public long getId() {
        return this.id;
    }

    /**
     * asigna un valor a el atributo Id
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * retorna el periodo del regstro
     *
     * @return el periodo del registro
     */
    public long getPeriodo() {
        return this.periodo;
    }

    /**
     * asigna un valor a periodo
     *
     * @param periodo
     */
    public void setPeriodo(long periodo) {
        this.periodo = periodo;
    }

    /**
     * retorna la lectura del regstro
     *
     * @return la lectura del registro
     */
    public long getLectura() {
        return this.lectura;
    }

    /**
     * asigna un valor a lectura
     *
     * @param lectura
     */
    public void setLectura(long lectura) {
        this.lectura = lectura;
    }

    /**
     * retorna el facturado del regstro
     *
     * @return el facturado del registro
     */
    public long getFacturado() {
        return this.facturado;
    }

    /**
     * asigna un valor a facturado
     *
     * @param facturado
     */
    public void setFacturado(long facturado) {
        this.facturado = facturado;
    }

    @Override
    /**
     * metodo para lograr INSERT los registros en la base de datos
     */
    public int insertar() throws SQLException {
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

    @Override
    /**
     * metodo para lograr un UPDATE de registros en la base de datos
     */
    public int actualizar() throws SQLException {
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

    @Override
    /**
     * metodo para lograr un DELETE de registros en la base de datos
     */
    public int eliminar() throws SQLException {
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

    /**
     * metodo para lograr listar o Buscar registros en la base de datos
     */
    public static ArrayList<Periodo> listar() throws SQLException {
        ArrayList<Periodo> datos = new ArrayList<>();
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql = "SELECT ID, PERIODO,LECTURA,FACTURADO FROM PERIODOS";
        db.conectar();
        db.prepararSql(sql);
        ResultSet reg = db.ejecutarQuery();
        datos.clear();
        while (reg.next()) {
            datos.add(new Periodo(reg.getLong("ID"), reg.getLong("PERIODO"),
                    reg.getLong("LECTURA"),
                    reg.getLong("FACTURADO")
            ));
        }
        return datos;
    }

    /**
     * metodo para lograr listar o Buscar registros en la base de datos pero con
     * un filtro, en este caso por la Id del registro
     */
    public static Periodo listar(long id) throws SQLException {
        Periodo dato = null;
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql = "SELECT PERIODO,LECTURA,FACTURADO FROM PERIODOS";
        db.conectar();
        db.prepararSql(sql);
        db.asignarParametro(1, id);
        ResultSet reg = db.ejecutarQuery();
        long periodo = reg.getLong("PERIODO");
        long lectura = reg.getLong("LECTURA");
        long facturado = reg.getLong("FACTURADO");
        while (reg.next()) {
            dato = new Periodo(id, periodo, lectura, facturado);
        }
        return dato;
    }

    @Override

    /**
     * metodo para obtener el nombre del reporte en este caso, los reporte de
     * los periodos
     */
    public String obtenerNombreReporte() {
        return "report/Lectura/ReportPeriodos.jrxml";

    }

    @Override
    /**
     * metodo para obtener parametros
     */
    public Map<String, Object> obtenerParametros() {
        Map parametros = new HashMap<String, Object>();
        // parametros.put("nombre", "valor");
        return parametros;
    }

}
