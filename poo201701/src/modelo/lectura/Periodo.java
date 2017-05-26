/*
 * Copyright (C) 2017 20102122476.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package modelo.lectura;

import basededatos.BaseDatosOracle;
import static basededatos.Secuencia.nextVal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.SuperTabla;

/**
 *
 * @author 20102122476
 */
public class Periodo implements SuperTabla {

    private long id;
    private long periodo;
    private int lectura;
    private int facturado;

    /**
     * Constructor de la clase Periodo
     */
    public Periodo() {
    }

    /**
     * Constructor de la clase Periodo
     * @param id id del periodo
     * @param periodo periodo
     * @param lectura si fue leido o no
     * @param facturado si dur facturado o no
     */
    public Periodo(long id, long periodo, int lectura, int facturado) {
        this.id = id;
        this.periodo = periodo;
        this.lectura = lectura;
        this.facturado = facturado;
    }

    /**
     * Constructor de la clase Periodo
     * @param id id del periodo
     * @param periodo periodo
     */
    public Periodo(long id, long periodo) {
        this.id = id;
        this.periodo = periodo;
    }

    /**
     * Constructor de la clase Periodo
     * @param periodo periodo
     * @param lectura si fue leido o no
     * @param facturado si fue facturado o no
     */
    public Periodo(long periodo, int lectura, int facturado) {
        this.periodo = periodo;
        this.lectura = lectura;
        this.facturado = facturado;
    }

    /**
     * Función que devuelve el id del periodo
     * @return id del periodo
     */
    public long getId() {
        return this.id;
    }

    /**
     * Método que recibe el id del periodo
     * @param id id del periodo
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Función que devuelve el Periodo
     * @return Periodo
     */
    public long getPeriodo() {
        return this.periodo;
    }

    /**
     * Método que recibe el Periodo
     * @param periodo periodo
     */
    public void setPeriodo(long periodo) {
        this.periodo = periodo;
    }

    /**
     * Función que devuelve la lectura
     * @return  lectura
     */
    public long getLectura() {
        return this.lectura;
    }

    /**
     * Método que 0 o 1
     * @param lectura 0= no facturado, 1 = facturado
     */
    public void setLectura(int lectura) {
        this.lectura = lectura;
    }

    /**
     * Función que retorna 0 o 1
     * @return 0= no facturado, 1 = facturado
     */
    public long getFacturado() {
        return this.facturado;
    }

    /**
     * Método que recibe si fue Facturado o no
     * @param facturado 0= no facturado, 1 = facturado 
     */
    public void setFacturado(int facturado) {
        this.facturado = facturado;
    }

    /**
     * Función utilizada para listar los periodos existentes
     * @return ArrayList
     * @throws SQLException en caso de excepcion
     */
    public static ArrayList<Periodo> listar() throws SQLException {
        ArrayList<Periodo> datos = new ArrayList<>();
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql = "SELECT ID, PERIODO, LECTURA, FACTURADO FROM PERIODOS";
        db.conectar();
        db.prepararSql(sql);
        ResultSet reg = db.ejecutarQuery();
        datos.clear();
        while (reg.next()) {
            datos.add(
                    new Periodo(reg.getLong("ID"), 
                            reg.getLong("PERIODO"), 
                            reg.getInt("LECTURA"), 
                            reg.getInt("FACTURADO")
            ));
        }
        return datos;
    }

    /**
     * Función utilizada para listar los periodos existentes ciertos campos
     * @param id id del periodo
     * @return objeto Periodo
     * @throws SQLException en caso de excepcion
     */
    public static Periodo listar(long id) throws SQLException {
        Periodo dato = null;
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql = "SELECT PERIODO,LECTURA,FACTURADO "
                + "FROM PERIODOS WHERE ID = ?";
        db.conectar();
        db.prepararSql(sql);
        db.asignarParametro(1, id);
        ResultSet reg = db.ejecutarQuery();
        long periodo = reg.getLong("PERIODO");
        int lectura = reg.getInt("LECTURA");
        int facturado = reg.getInt("FACTURADO");
        while (reg.next()) {
            dato = new Periodo(id, periodo, lectura, facturado);
        }
        return dato;
    }

    /**
     * Función que inserta un Periodo en la base de datos
     * @return 0=no insertado, 1=insertado
     * @throws SQLException en caso de alguno excepcion
     */
    @Override
    public int insertar() throws SQLException {
        long secuencia = nextVal("PERIODOS_SEQ");
        setId(secuencia);
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql = "INSERT INTO PERIODOS(ID, PERIODO, LECTURA, FACTURADO) "
                + "VALUES (?,?, ?, ?)";
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

    /**
     * Función que actualiza un Periodo en la base de datos
     * @return 0=no actualizado, 1=actualizado
     * @throws SQLException en caso de excepcion
     */
    @Override
    public int actualizar() throws SQLException {
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE PERIODOS SET PERIODO = ?, LECTURA = ?, "
                + "FACTURADO = ? WHERE ID = ?";
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

    /**
     * Función que actualiza LECTURA, FACTURADO de un Periodo 
     * @return 0=no actualizado, 1=actualizado
     * @throws SQLException en caso de excepcion
     */
    public int actualizarLecturaFacturado() throws SQLException {
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

    /**
     * Función que elimina un Periodo en la base de datos
     * @return 0=no eliminado, 1=eliminado
     * @throws SQLException en caso de excepcion
     */
    @Override
    public int eliminar() throws SQLException {
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

    /**
     * Función Booleana que valida si un periodo existe o no
     * @param periodo periodo de consulta
     * @return true o false segun la condicion
     * @throws SQLException en caso de excepcion
     */
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

    /**
     * nombre del reporte
     * @return nombre del reporte
     */
    @Override
    public String obtenerNombreReporte() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}