/*
 * Copyright (C) 2017 hteran.
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
package basededatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.LocalDate;
import java.util.Calendar;
import oracle.jdbc.pool.OracleDataSource;

/**
 * 
 * @author hteran
 */
public class BaseDatosOracle {

    private String url;
    private String usuario;
    private String password;
    private Connection conexion;
    private PreparedStatement sentencia;
    private static BaseDatosOracle instance;

    /**
     *
     */
    public BaseDatosOracle() {
        setPassword(Usuario.getInstance().getPassword());
        setUsuario(Usuario.getInstance().getUsuario());
        setUrl("jdbc:oracle:thin:@10.0.33.163:1521:usboracle");  
    }

    /**
     *
     * @return        
     */
    public static BaseDatosOracle getInstance() {
        if (instance == null) {
            instance = new BaseDatosOracle();
        }
        return instance;
    }

    /**
     *
     * @param aInstance
     */
    public static void setInstance(BaseDatosOracle aInstance) {
        instance = aInstance;
    }

    /**
     *
     * @throws SQLException
     */
    public void conectar() throws SQLException {
        OracleDataSource dataSource;
        if (conexion == null || !conexion.isClosed()) {
            dataSource = new OracleDataSource();
            dataSource.setURL(getUrl());
            conexion = dataSource.getConnection(getUsuario(), getPassword());
        }
    }

    public void desconectar() {
        try {
            if (conexion == null) {
                return;
            }
            cerrarSentencia();
            if (!conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException ex) {
            System.err.println("desconectar()" + ex.getMessage());
        }
    }

    public static void cerrarCursor(ResultSet cursor) throws SQLException {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public void crearTransaccion() throws SQLException {
        setAutoCommit(false);
    }

    public void cerrarSentencia() {
        try {
            if (sentencia != null) {
                return;
            }
            if (!sentencia.isClosed()) {
                sentencia.close();
            }
        } catch (SQLException ex) {
            System.err.println("cerrarSentencia()" + ex.getMessage());
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public Savepoint crearSavePoint() throws SQLException {
        return conexion.setSavepoint();
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        conexion.setAutoCommit(autoCommit);
    }

    public void commit() throws SQLException {
        conexion.commit();
    }

    public void rollback() throws SQLException {
        conexion.rollback();
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        conexion.rollback(savepoint);
    }

    public void asignarParametro(int indice, float valor) throws SQLException {
        sentencia.setFloat(indice, valor);
    }

    public void asignarParametro(int indice, int valor) throws SQLException {
        sentencia.setInt(indice, valor);
    }

    public void asignarParametro(int indice, String valor) throws SQLException {
        sentencia.setString(indice, valor);
    }

    public void asignarParametro(int indice, Date valor) throws SQLException {
        sentencia.setDate(indice, valor);
    }

    public void asignarParametro(int indice, Calendar valor)
            throws SQLException {
        sentencia.setDate(indice, new java.sql.Date(valor.getTimeInMillis()));
    }

    public void asignarParametro(int indice, LocalDate valor)
            throws SQLException {
        sentencia.setDate(indice, Date.valueOf(valor));
    }

    /*public void asignarParametro(int indice, java.util.Date valor)
     throws SQLException {
     sentencia.setDate(indice, new java.sql.Date());
     }*/
    public void prepararSql(String sql) throws SQLException {
        int ejecucion;
        sentencia = conexion.prepareStatement(sql);
    }

    public ResultSet ejecutarQuery(String sql) throws SQLException {
        ResultSet cursor;
        //sentencia =  conexion.prepareStatement(sql, resultSetType, resultSetConcurrency)
        try {
            sentencia = conexion.prepareStatement(sql);
            cursor = sentencia.executeQuery(sql);
        } finally {
            cerrarSentencia();
        }
        return cursor;
    }

    public ResultSet ejecutarQuery() throws SQLException {
        ResultSet cursor;
        try {
            cursor = sentencia.executeQuery();
        } finally {
            cerrarSentencia();
        }
        return cursor;
    }

    public int ejecutar() throws SQLException {
        return sentencia.executeUpdate();
    }

    public int ejecutar(String sql) throws SQLException {
        int ejecucion;
        ejecucion = sentencia.executeUpdate();

        return ejecucion;
    }

    public String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public final void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public String toString() {
        return "BaseDatosOracle{" + "url=" + url + ", usuario=" + usuario
                + ", password=" + password + '}';
    }

}
