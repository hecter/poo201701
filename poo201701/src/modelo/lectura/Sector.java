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

/**
 *
 * @author 20102122476
 */
public class Sector {

    private long id;
    private String det;

    /**
     * Constructor de la clase Sector
     */
    public Sector() {
    }

    /**
     * Constructor de la clase Sector
     * @param id id del sector
     */
    public Sector(long id) {
        this.id = id;
    }

    /**
     * Constructor de la clase Sector
     * @param id id del sector
     * @param det nombre del sector
     */
    public Sector(String det) {
        this.det = det;
    }

    /**
     * Constructor de la clase Sector
     * @param id id del sector
     * @param det nombre del sector
     */
    public Sector(long id, String det) {
        this.id = id;
        this.det = det;
    }

    /**
     * Funcion que retorna el id del Sector
     * @return id del sector
     */
    public long getId() {
        return this.id;
    }

    /**
     * Métopdo que recibe el id del Sector
     * @param id id del sector
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Funcion que retorna el nombre del Sector
     * @return nombre del sector
     */
    public String getDet() {
        return this.det;
    }

    /**
     * Métopdo que recibe el nombre del Sector
     * @param det nombre del sector
     */
    public void setDet(String det) {
        this.det = det;
    }

    /**
     * Función utilizada para insetar los sectores
     * @return 0 en caso de no insertado y otro numero en caso contrario
     * @throws SQLException en caso de excepcion
     */
    public int insertar() throws SQLException {
        long secuencia = nextVal("SECTORES_SEQ");
        System.err.println("intente con: " + secuencia);
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "INSERT INTO SECTORES (ID,DET) VALUES (?,?)";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, secuencia);
        basededatos.asignarParametro(2, getDet());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * Función utilizada para actualizar los sectores
     * @return 0 en caso de no actualizar y otro numero en caso contrario
     * @throws SQLException en caso de excepcion
     */
    public int actualizar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE SECTORES SET DET = ? WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getDet());
        basededatos.asignarParametro(2, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * Función utilizada para eliminar los sectores
     * @return 0 en caso de no eliminar y otro numero en caso contrario
     * @throws SQLException en caso de excepcion
     */
    public int eliminar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "DELETE FROM SECTORES WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * Función utilizada para listar los sectores
     * @return ArrayList
     * @throws SQLException en caso de excepcion
     */
    public static ArrayList<Sector> listar() throws SQLException {
        ArrayList<Sector> datos = new ArrayList<>();
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT ID, DET FROM SECTORES";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery();
        datos.clear();
        while (cursor.next()) {
            datos.add(new Sector(
                    cursor.getLong("ID"),
                    cursor.getString("DET")
            )
            );
        }
        return datos;
    }

    /**
     * Función buscar un Sector dependiendo
     * @param id id del sector
     * @return objeto Sector
     * @throws SQLException en caso de excepcion
     */
    public static Sector listar(long id) throws SQLException {
        Sector dato = null;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT DET FROM SECTORES WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, id);
        cursor = basededatos.ejecutarQuery();
        if (cursor.next()) {
            dato = new Sector(id, cursor.getString("DET"));
        }
        return dato;
    }

    /**
     * Función buscar un Sector dependiendo
     * @param det nombre del sector
     * @return objeto Sector
     * @throws SQLException en caso de excepcion
     */
    public static ArrayList<Sector> listar(String det) throws SQLException {
        ArrayList<Sector> datos = new ArrayList<>();
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        String sql = "SELECT ID, DET FROM SECTORES WHERE DET LIKE ?";
        bd.conectar();
        bd.prepararSql(sql);
        bd.asignarParametro(1, "%" + det + "%");
        ResultSet reg = bd.ejecutarQuery();
        datos.clear();
        while (reg.next()) {
            datos.add(new Sector(reg.getLong("ID"), reg.getString("DET")));
        }
        return datos;
    }

    /**
     * Función utilizada para saber si un sector existe o no
     * @param det nombre del sector
     * @return true o false segun la condicion
     * @throws SQLException en caso de excepcion
     */
    public static boolean existe(String det) throws SQLException {
        ArrayList<Sector> datos = new ArrayList<>();
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        bd.conectar();
        bd.prepararSql("SELECT ID, DET FROM SECTORES WHERE DET LIKE ?");
        bd.asignarParametro(1, det);
        ResultSet reg = bd.ejecutarQuery();
        datos.clear();
        return reg.next();
    }

    /**
     * UTILIZADO PARA EL RE-ASIGNAR SECTOR EN EL JFRAME LECTORES
     * ME VALIDA SI EL USUARIO YA FUE ASIGNADO O NO
     * @param sector id del sector
     * @return true o false segun la condicion
     * @throws SQLException en caso de excepcion
     */
    public static boolean estaAsignado(long sector) throws SQLException {
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        bd.conectar();
        bd.prepararSql("SELECT USUARIOS.ID FROM USUARIOS WHERE SECTOR_ID = ?");
        bd.asignarParametro(1, sector);
        ResultSet reg = bd.ejecutarQuery();
        return reg.next();
    }
}
