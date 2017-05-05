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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hteran
 */
public class Pais extends Lugar {

    private String iso2;
    private String iso3;
    private ArrayList<Departamento> listaDepartamento;

    public Pais(int codigo, String nombre) throws Exception {
        super(codigo, nombre);
    }

    public Pais(long codigo, String nombre, String iso2, String iso3)
            throws Exception {
        super(codigo, nombre);
        setIso2(iso2);
        setIso3(iso3);

    }

    public Pais(String nombre, String iso2, String iso3) throws Exception {
        super(nombre);
        setIso2(iso2);
        setIso3(iso3);
    }

    public String getIso2() {
        return iso2;
    }

    public final void setIso2(String iso2) throws Exception {

        if (iso2 == null) {
            throw new Exception("El codigo Iso 2 no puede ser null");
        }
        if (iso2.length() != 2) {
            throw new Exception("El codigo Iso 2 debe tener exactamente "
                    + "2 caracteres");
        }
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public final void setIso3(String iso3) throws Exception {
        if (iso3 == null) {
            throw new Exception("El codigo Iso 3 no puede ser null");
        }
        if (iso3.length() != 3) {
            throw new Exception("El codigo Iso 3 debe tener exactamente "
                    + "3 caracteres");
        }
        this.iso3 = iso3;
    }

    @Override
    public String getNombreSecuencia() {
        return "PAISES_SEQ";
    }

    /**
     *
     * @return
     */
    public ArrayList<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    /**
     *
     * @param listaDepartamento
     */
    public final void setListaDepartamento(
            ArrayList<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public final void setListaDepartamento() throws Exception {
        setListaDepartamento(Departamento.buscar(this));
    }

    /**
     *
     * @param codigo
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static Pais buscar(long codigo) throws SQLException,
            Exception {
        Pais pais;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT CODIGO, NOMBRE, ISO2, ISO3 "
                + "FROM PAISES "
                + "WHERE CODIGO = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, codigo);
        cursor = basededatos.ejecutarQuery();
        pais = null;
        if (cursor.next()) {
            pais = new Pais(cursor.getLong("CODIGO"),
                    cursor.getString("NOMBRE"),
                    cursor.getString("ISO2"),
                    cursor.getString("ISO3"));
        }
        return pais;
    }

    /**
     *
     * @return @throws SQLException
     * @throws Exception
     */
    public static ArrayList<Pais> buscar() throws SQLException,
            Exception {
        ArrayList<Pais> listaPais;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT CODIGO, NOMBRE, ISO2, ISO3 FROM PAISES ORDER BY CODIGO";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaPais = new ArrayList<>();
        while (cursor.next()) {
            listaPais.add(new Pais(cursor.getLong("CODIGO"),
                    cursor.getString("NOMBRE"),
                    cursor.getString("ISO2"),
                    cursor.getString("ISO3")
            ));
        }
        return listaPais;
    }

    @Override
    public int insertar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "INSERT INTO PAISES "
                + "(CODIGO, NOMBRE, ISO2, ISO3 )"
                + " VALUES "
                + "(?, ?, ?, ? )";
        basededatos.conectar();
        basededatos.prepararSql(sql);

        System.out.println(this);
        basededatos.asignarParametro(1, getCodigo());
        basededatos.asignarParametro(2, getNombre());
        basededatos.asignarParametro(3, getIso2());
        basededatos.asignarParametro(4, getIso3());
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
        sql = "DELETE PAISES WHERE CODIGO = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getCodigo());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    public int actualizar() throws SQLException {
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "UPDATE PAISES "
                + "SET NOMBRE = ?, "
                + "ISO2 = ?, "
                + "ISO3 = ? "
                + "WHERE CODIGO = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getNombre());
        basededatos.asignarParametro(2, getIso2());
        basededatos.asignarParametro(3, getIso3());
        basededatos.asignarParametro(4, getCodigo());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    @Override
    public String obtenerNombreReporte() {
        return "/report/ReportPais.jrxml";
    }

    @Override
    public Map<String, Object> obtenerParametros() {
        Map parametros = new HashMap<String, Object>();
        // parametros.put("nombre", "valor");
        return parametros;
    }

}
