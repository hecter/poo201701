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
package model.geografia;

import basededatos.BaseDatosOracle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hteran
 */
public class Departamento extends Lugar {

    private Pais pais;

    public Departamento(long codigo, String nombre, Pais pais) throws Exception {
        super(codigo, nombre);
        setPais(pais);
    }

    public Departamento(long codigo, String nombre, long pais) throws Exception {
        super(codigo, nombre);
        setPais(Pais.buscar(pais));
    }

    public Departamento(String nombre, Pais pais) throws Exception {
        super(nombre);
        setPais(pais);
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public static Departamento buscar(long codigo) throws SQLException,
            Exception {
        Departamento departamento;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT CODIGO, NOMBRE, PAIS "
                + "FROM DEPARTAMENTOS "
                + "WHERE "
                + "CODIGO = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, codigo);
        cursor = basededatos.ejecutarQuery();
        departamento = null;
        if (cursor.next()) {
            departamento = new Departamento(cursor.getLong("CODIGO"),
                    cursor.getString("NOMBRE"), null);
        }
        return departamento;
    }

    public static ArrayList<Departamento> buscar() throws SQLException,
            Exception {
        ArrayList<Departamento> listaDepartamento;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();

        sql = "SELECT CODIGO, NOMBRE, PAIS "
                + "FROM DEPARTAMENTOS ";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery();
        listaDepartamento = new ArrayList<>();
        while (cursor.next()) {
            listaDepartamento.add(new Departamento(cursor.getLong("CODIGO"),
                    cursor.getString("NOMBRE"), null));
        }
        return listaDepartamento;
    }

    public static ArrayList<Departamento> buscar(Pais pais) throws SQLException,
            Exception {
        ArrayList<Departamento> listaDepartamento;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT CODIGO, NOMBRE, PAIS "
                + "FROM DEPARTAMENTOS "
                + "WHERE "
                + "PAIS = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, pais.getCodigo());
        cursor = basededatos.ejecutarQuery();
        listaDepartamento = new ArrayList<>();
        while (cursor.next()) {
            listaDepartamento.add(new Departamento(cursor.getLong("CODIGO"),
                    cursor.getString("NOMBRE"), pais));
        }
        return listaDepartamento;
    }

    @Override
    public String getNombreSecuencia() {
        return "DEPARTAMENTOS_SEQ";
    }

    @Override
    public int insertar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "INSERT INTO DEPARTAMENTOS (CODIGO, NOMBRE, PAIS )"
                + " VALUES"
                + " (?, ?, ? )";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getCodigo());
        basededatos.asignarParametro(2, getNombre());
        basededatos.asignarParametro(3, getPais().getCodigo());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    @Override
    public int eliminar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "DELETE DEPARTAMENTOS WHERE CODIGO = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getCodigo());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    @Override
    public int actualizar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE DEPARTMAMENTOS "
                + "SET NOMBRE = ?, "
                + "PAIS = ? "
                + "WHERE CODIGO = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getNombre());
        basededatos.asignarParametro(2, getPais().getCodigo());
        basededatos.asignarParametro(3, getCodigo());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    @Override
    public String obtenerNombreReporte() {
        return "ReportDepartamento";
    }

}
