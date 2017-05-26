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
import modelo.atencion.Casa;

/**
 *
 * @author 20102122476
 */
public class Medidor {

    private long id;
    private Casa casas;
    private String serial;
    private double costo;

    /**
     * constructor de la clase Medidor
     */
    public Medidor() {
    }

    /**
     * constructor de la clase Medidor
     *
     * @param id id del medidor
     */
    public Medidor(long id) {
        this.id = id;
    }

    /**
     * constructor de la clase Medidor
     *
     * @param id id del medidor
     * @param casas casa al que pertenece
     * @param serial serial del medidor
     * @param costo costo del medidor
     */
    public Medidor(long id, Casa casas, String serial, double costo) {
        this.id = id;
        this.casas = casas;
        this.serial = serial;
        this.costo = costo;
    }

    /**
     * constructor de la clase Medidor
     * @param id id del medidor
     * @param serial serial del medidor 
     * @param costo costo del medidor
     */
    public Medidor(long id, String serial, double costo) {
        this.id = id;
        this.serial = serial;
        this.costo = costo;
    }

    /**
     * constructor de la clase Medidor
     * @param serial serial del medidor 
     * @param costo costo del medidor
     */
    public Medidor(String serial, double costo) {
        this.serial = serial;
        this.costo = costo;
    }

    /**
     * función que retorna id del medidor
     * @return id del medidor
     */
    public long getId() {
        return this.id;
    }

    /**
     * Método que recibe el id del medidor
     * @param id id del medidor
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Función que retorna la casa donde fue instalado el medidor
     * @return la casa donde fue instalado el medidor
     */
    public Casa getCasas() {
        return this.casas;
    }

    /**
     * Método que recibe la casa donde fue instalado el medidor
     * @param casas la casa donde fue instalado el medidor
     */
    public void setCasas(Casa casas) {
        this.casas = casas;
    }

    /**
     * Función que retorna el serial del medidor
     * @return el serial del medidor
     */
    public String getSerial() {
        return this.serial;
    }

    /**
     * Método que recibe el serial del medidor
     * @param serial el serial del medidor
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * Función que devuelve el costo del medidor
     * @return el costo del medidor
     */
    public double getCosto() {
        return this.costo;
    }

    /**
     * Método que recibe el costo del medidor
     * @param costo el costo del medidor
     */
    public void setCosto(long costo) {
        this.costo = costo;
    }

    /**
     * Función que inserta el medidor en la base de datos, 
     * retorna 0 en caso de no registrar y 1 en caso contrario
     * @return 0 o 1
     * @throws SQLException en caso de excepcion
     */
    public int insertar() throws SQLException {
        long secuencia = nextVal("SECTORES_SEQ");
        BaseDatosOracle basededatos = BaseDatosOracle.getInstance();
        String sql = "INSERT INTO MEDIDORES (ID,SERIAL,COSTO) "
                + "VALUES(?,?,?)";
        int ejecucion;
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, secuencia);
        basededatos.asignarParametro(2, getSerial());
        basededatos.asignarParametro(3, getCosto());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * Función que actualiza el serial y costo del medidor en la base de datos
     * @return 0 en caso de no actualizar y 1 en caso contrario
     * @throws SQLException en caso de alguna excepcion
     */
    public int actualizar() throws SQLException {
        BaseDatosOracle basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE MEDIDORES SET SERIAL = ?,COSTO = ? WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getSerial());
        basededatos.asignarParametro(2, getCosto());
        basededatos.asignarParametro(3, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * Función que actualiza el costo del medidor
     * @return 0 en caso de no actualizar y 1 en caso contrario
     * @throws SQLException 
     */
    public int actualizarCosto() throws SQLException {
        BaseDatosOracle basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE MEDIDORES SET COSTO = ? WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getCosto());
        basededatos.asignarParametro(2, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * Función que elimina un medidor de la base de datos
     * @return 0 en caso de no eliminar y 1 en caso contrario
     * @throws SQLException en caso de alguna excepcion
     */
    public int eliminar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "DELETE MEDIDORES WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * Método que lista los medidores en un ArrayList
     * @return ArrayList
     * @throws SQLException en caso de alguna excepcion
     */
    public ArrayList<Medidor> listar() throws SQLException {
        ArrayList<Medidor> datos = new ArrayList<>();
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        String sql = "SELECT ID, SERIAL, COSTO FROM MEDIDORES";
        bd.conectar();
        bd.prepararSql(sql);
        ResultSet cursor = bd.ejecutarQuery();
        datos.clear();
        while (cursor.next()) {
            datos.add(
                    new Medidor(
                            cursor.getLong("ID"),
                            cursor.getString("SERIAL"),
                            cursor.getDouble("COSTO")
                    )
            );
        }
        return datos;
    }

    /**
     * Función booleana que me valida si un medidor existe o no
     * @param serial serial del medidor
     * @return true o false
     * @throws SQLException en caso de excepcion
     */
    public static boolean existe(String serial) throws SQLException {
        ArrayList<Sector> datos = new ArrayList<>();
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        bd.conectar();
        bd.prepararSql("SELECT MEDIDORES.ID FROM MEDIDORES WHERE SERIAL= ?");
        bd.asignarParametro(1, serial);
        ResultSet reg = bd.ejecutarQuery();
        datos.clear();
        return reg.next();
    }
    
    /**
     * Método que lista en un ArrayList los medidores instalados 
     * @return ArrayList
     * @throws SQLException en caso de excepcion 
     */
    public static ArrayList<String> listarMedidoreInstalados() 
            throws SQLException {
        ArrayList<String> datos = new ArrayList<>();
        BaseDatosOracle db = BaseDatosOracle.getInstance();
        String sql = "SELECT MEDIDORES.ID,CASAS.SECTOR_ID FROM CASAS "
                + "LEFT JOIN MEDIDORES ON CASAS.ID = MEDIDORES.CASA_ID";
        db.conectar();
        db.prepararSql(sql);
        ResultSet reg = db.ejecutarQuery();
        datos.clear();
        while (reg.next()) {
            datos.add(reg.getString("ID") + ";" + reg.getString("SECTOR_ID"));
        }
        return datos;
    }
}
