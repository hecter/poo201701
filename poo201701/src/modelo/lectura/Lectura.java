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
import java.util.Calendar;
import java.util.Date;
import modelo.pagos.Usuario;

/**
 *
 * @author 20102122476
 */
public class Lectura {

    private long id;
    private Usuario usuarios;
    private Periodo periodos;
    private Medidor medidores;
    private int lectura;
    private Date fecha;
    private String causal;
    private long estado;

    /**
     * constructor de la clase
     */
    public Lectura() {
    }

    /**
     * constructor de la clase que recibe periodo y medidor
     *
     * @param periodos periodo el cual se leera
     * @param medidores medidor alq ue se le realizara la lectura
     */
    public Lectura(Periodo periodos, Medidor medidores) {
        this.periodos = periodos;
        this.medidores = medidores;
    }

    /**
     * constructor de la clase que recibe el id del registro y la lectura
     *
     * @param id id del registro
     * @param lectura lectura leida
     */
    public Lectura(long id, int lectura) {
        this.id = id;
        this.lectura = lectura;
    }

    /**
     * constructor de la clase que recibe el id de registro y la causal
     *
     * @param id id de registro
     * @param causal causal de no lectura
     */
    public Lectura(long id, String causal) {
        this.id = id;
        this.causal = causal;
    }

    /**
     * constructor de la clase que recibe todos los parametros
     *
     * @param id id con el que se registrara en la base de datos
     * @param usuarios usuario el cual realiza la lectura
     * @param periodos periodo al cual pertenece esa lectura
     * @param medidores medidor al cual pertenece esa lectura
     * @param lectura total lectura
     * @param fecha fecha en que se realizo esa lectura
     * @param causal causa de no lectura
     * @param estado estado de la lectura, 0= no leido, 1 = leido
     */
    public Lectura(long id, Usuario usuarios, Periodo periodos,
            Medidor medidores, int lectura, Date fecha,
            String causal, long estado) {
        this.id = id;
        this.usuarios = usuarios;
        this.periodos = periodos;
        this.medidores = medidores;
        this.lectura = lectura;
        this.fecha = fecha;
        this.causal = causal;
        this.estado = estado;
    }

    /**
     * funcion que retorna el id de la lectura
     *
     * @return
     */
    public long getId() {
        return this.id;
    }

    /**
     * metodo set que recibe el id de la lectura
     *
     * @param id id de la lectura
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * funcion que retorna el usuario que realiza la lectura
     *
     * @return usuario que realiza la lectura
     */
    public Usuario getUsuarios() {
        return this.usuarios;
    }

    /**
     * metodo que recibe el usuario que realiza la lectura
     *
     * @param usuarios usuario que realiza la lectura
     */
    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * funcion que retorna el periodo de lectura
     *
     * @return periodo de lectura
     */
    public Periodo getPeriodos() {
        return this.periodos;
    }

    /**
     * metodo que recibe el periodo de elctura
     *
     * @param periodos periodo de lectura
     */
    public void setPeriodos(Periodo periodos) {
        this.periodos = periodos;
    }

    /**
     * funcion que retorna el medidor al que se le realiza la lectura
     *
     * @return medidor al que se le realiza la lectura
     */
    public Medidor getMedidores() {
        return this.medidores;
    }

    /**
     * metodo que recibe el medidor al que se le realiza la lectura
     *
     * @param medidores medidor al que se le realiza la lectura
     */
    public void setMedidores(Medidor medidores) {
        this.medidores = medidores;
    }

    /**
     * funcion que retorna la lectura realizada por el lector encargado
     *
     * @return lectura
     */
    public int getLectura() {
        return this.lectura;
    }

    /**
     * metodo que recibe la lectura realizada por el lector encargado
     *
     * @param lectura la lectura realizada por el lector encargado
     */
    public void setLectura(int lectura) {
        this.lectura = lectura;
    }

    /**
     * funcion que devuelve la fecha en que se realiza la lectura
     *
     * @return la fecha en que se realiza la lectura
     */
    public Date getFecha() {
        return this.fecha;
    }

    /**
     * metodo que recibe la fecha en que se realiza la lectura
     *
     * @param fecha la fecha en que se realiza la lectura
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * funcion que retorna la causal de no lectura
     *
     * @return la causal de no lectura
     */
    public String getCausal() {
        return this.causal;
    }

    /**
     * metodo que recibe la causal de no lectura
     *
     * @param causal la causal de no lectura
     */
    public void setCausal(String causal) {
        this.causal = causal;
    }

    /**
     * funcion que retorna el estado de lectura
     *
     * @return estado de lectura 0=no leido, 1 = leido
     */
    public long getEstado() {
        return this.estado;
    }

    /**
     * metodo que recibe el estado de lectura
     *
     * @param estado estado de lectura 0=no leido, 1 = leido
     */
    public void setEstado(long estado) {
        this.estado = estado;
    }

    /**
     * realizada para insertar lecturas vacias cuando se insertan los periodos
     *
     * @param usuario usuario que realiza la accion
     * @return 0= no insertado y diferente de ese valor lo contrario
     * @throws SQLException en caso de excepcion
     */
    public int insertarLecturasVacias(Usuario usuario) throws SQLException {
        long secuencia = nextVal("LECTURA_SEQ");
        setId(secuencia);
        BaseDatosOracle basededatos = BaseDatosOracle.getInstance();
        String sql = "INSERT INTO LECTURA(ID, MEDIDORES_ID,PERIODOS_ID, "
                + "USUARIOS_ID) VALUES(?,?,?,?)";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        basededatos.asignarParametro(2, getMedidores().getId());
        basededatos.asignarParametro(3, getPeriodos().getId());
        basededatos.asignarParametro(4, usuario.getId());
        int ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * utilizado en lectura, aqui se listan las casas que debe leer el usuario
     * de acuerdo al sector asignado
     *
     * @param usuario usuario
     * @param sector sector
     * @param periodo periodo
     * @return coleccion de datos
     * @throws SQLException
     */
    public static ArrayList<String> ListarPorLeer(String usuario, 
            String sector, String periodo) throws SQLException {
        ArrayList<String> listado = new ArrayList<>();
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        String sql = "SELECT "
                + "LECTURA.ID, "
                + "MEDIDORES.SERIAL,"
                + "CASAS.DIRECCION,"
                + "SECTORES.DET AS SECTOR_NOMBRE "
                + "FROM LECTURA "
                + "INNER JOIN PERIODOS "
                + "ON PERIODOS.ID = LECTURA.PERIODOS_ID "
                + "INNER JOIN MEDIDORES "
                + "ON MEDIDORES.ID = LECTURA.MEDIDORES_ID "
                + "INNER JOIN CASAS "
                + "ON CASAS.ID = MEDIDORES.CASA_ID "
                + "INNER JOIN SECTORES "
                + "ON SECTORES.ID = CASAS.SECTOR_ID "
                + "WHERE "
                + "PERIODOS.PERIODO  = ? "
                + "AND "
                + "LECTURA.USUARIOS_ID = ? "
                + "AND "
                + "LECTURA.ESTADO = 0 "
                + "AND "
                + "PERIODOS.LECTURA = 0 "
                + "AND "
                + "CASAS.SECTOR_ID = ?";
        bd.conectar();
        bd.prepararSql(sql);
        bd.asignarParametro(1, periodo);
        bd.asignarParametro(2, usuario);
        bd.asignarParametro(3, sector);
        ResultSet cursor = bd.ejecutarQuery();
        listado.clear();
        while (cursor.next()) {
            String id = cursor.getString("ID");
            String ser = cursor.getString("SERIAL");
            String dir = cursor.getString("DIRECCION");
            String nombre_sector = cursor.getString("SECTOR_NOMBRE");
            listado.add(id + ";" + ser + ";" + dir + ";" + nombre_sector);
        }
        return listado;
    }

    /**
     * lista todos las lecturas de un periodo especifico
     *
     * @param suscriptor suscriptor que realiza la peticion
     * @param periodo periodo para el que se realiza la peticion
     * @return coleccion de datos
     * @throws SQLException en caso de excepcion
     */
    public static ArrayList<String> ListarPorCorregir(long suscriptor, 
            long periodo) throws SQLException {
        ArrayList<String> listado = new ArrayList<>();
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        String sql = "SELECT "
                + "LECTURA.ID, "
                + "LECTURA.LECTURA, "
                + "LECTURA.FECHA, "
                + "LECTURA.CAUSAL, "
                + "LECTURA.ESTADO AS ESTADO_LECTURA, "
                + "MEDIDORES.SERIAL AS MEDIDOR_SERIAL, "
                + "CASAS.DIRECCION, "
                + "SECTORES.DET AS SECTOR_DET, "
                + "SUSCRIPTORES.NOMBRES||' '||SUSCRIPTORES.APELLIDOS "
                + "AS SUSCRIPTOR "
                + "FROM "
                + "LECTURA "
                + "INNER JOIN MEDIDORES "
                + "ON MEDIDORES.ID = LECTURA.MEDIDORES_ID "
                + "INNER JOIN PERIODOS "
                + "ON PERIODOS.ID = LECTURA.PERIODOS_ID "
                + "INNER JOIN CASAS "
                + "ON CASAS.ID = MEDIDORES.CASA_ID "
                + "INNER JOIN SECTORES "
                + "ON SECTORES.ID = CASAS.SECTOR_ID "
                + "INNER JOIN SUSCRIPTORES "
                + "ON SUSCRIPTORES.ID = CASAS.SUSCRIPTOR_ID "
                + "WHERE "
                + "PERIODOS.PERIODO = ? "
                + "AND "
                + "CASAS.SUSCRIPTOR_ID= ?";
        bd.conectar();
        bd.prepararSql(sql);
        bd.asignarParametro(1, periodo);
        bd.asignarParametro(2, suscriptor);
        ResultSet cursor = bd.ejecutarQuery();
        listado.clear();
        while (cursor.next()) {
            String id = cursor.getString("ID");
            String lectura = cursor.getString("LECTURA");
            if (lectura == null) {
                lectura = "POR ASIGNAR";
            }
            String fecha = cursor.getString("FECHA");
            if (fecha == null) {
                fecha = "POR ASIGNAR";
            }
            String causal = cursor.getString("CAUSAL");
            String estado = cursor.getString("ESTADO_LECTURA");
            String serial = cursor.getString("MEDIDOR_SERIAL");
            String direccion = cursor.getString("DIRECCION");
            String sector = cursor.getString("SECTOR_DET");
            String nsuscriptor = cursor.getString("SUSCRIPTOR");
            listado.add(id + ";" + lectura + ";" + fecha + ";" + 
                    causal + ";" + estado + ";"
                    + serial + ";" + direccion + ";" + sector + ";" 
                    + nsuscriptor);
        }
        return listado;
    }

    /**
     * utilizado en lectura para buscar el id de registro de la lectura
     *
     * @param usuario usuario que realiza la lectura
     * @param serial serial del medidor que le realizo la lectura
     * @return numero positivo en caso de que exista el registro o en caso
     * contratio un numero negativo
     * @throws SQLException en caso de exception
     */
    public static long BuscarIdLectura(String usuario, 
            String serial) throws SQLException {
        ArrayList<String> listado = new ArrayList<>();
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        String sql = "SELECT "
                + "LECTURA.ID "
                + "FROM "
                + "LECTURA "
                + "INNER JOIN MEDIDORES "
                + "ON MEDIDORES.ID = LECTURA.MEDIDORES_ID "
                + "WHERE "
                + "LECTURA.USUARIOS_ID = ? "
                + "AND "
                + "MEDIDORES.SERIAL = ?";
        bd.conectar();
        bd.prepararSql(sql);
        bd.asignarParametro(1, usuario);
        bd.asignarParametro(2, serial);
        ResultSet cursor = bd.ejecutarQuery();
        listado.clear();
        if (cursor.next()) {
            return cursor.getLong("ID");
        }
        return -1;
    }

    /**
     * utilizado en lectura para que el usuario encargado de ese sector lea la
     * lectura del medidor
     *
     * @return valor mayor a cero en caso de que realize la actualizacion y 0 en
     * caso contrario
     * @throws SQLException en caso de exception
     */
    public int leerLectura() throws SQLException {
        BaseDatosOracle basededatos = BaseDatosOracle.getInstance();
        Calendar fregistro = Calendar.getInstance();
        String sql = "UPDATE LECTURA SET LECTURA = ?, FECHA = ?, "
                + "ESTADO = 1 WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getLectura());
        basededatos.asignarParametro(2, fregistro);
        basededatos.asignarParametro(3, getId());
        int ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        System.err.println("LECTURA: " + getLectura());
        System.err.println("registro: " + getId());
        return ejecucion;
    }

    /**
     * utilizado en lectura cuando por alguna causal no se pudo leer
     *
     * @return valor mayor a cero en caso de que realize la actualizacion y 0 en
     * caso contrario
     * @throws SQLException en caso de alguna exception
     */
    public int leerLecturaExcepcion() throws SQLException {
        BaseDatosOracle basededatos = BaseDatosOracle.getInstance();
        Calendar fecha = Calendar.getInstance();
        String sql = "UPDATE LECTURA SET CAUSAL = ?, FECHA = ?, "
                + "ESTADO = 1 WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getCausal());
        basededatos.asignarParametro(2, fecha);
        basededatos.asignarParametro(3, getId());
        int ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    /**
     * utilizado en lectura para verificar si un registro fue leido o no
     *
     * @return true si no fue leido, false en caso contrario
     * @throws SQLException en cado de exception
     */
    public boolean esLeido() throws SQLException {
        BaseDatosOracle basededatos = BaseDatosOracle.getInstance();
        String sql = "SELECT LECTURA.ESTADO FROM LECTURA WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
        ResultSet cursor = basededatos.ejecutarQuery();
        if (cursor.next()) {
            if (cursor.getInt("ESTADO") == 0) {
                return false;
            }
        }
        return true;
    }
}
