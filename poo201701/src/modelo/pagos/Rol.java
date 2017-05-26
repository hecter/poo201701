/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pagos;

import basededatos.BaseDatosOracle;
import basededatos.Secuencia;
import basededatos.BaseDatosOracle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.SuperTabla;

/**
 *
 * @author Guillermo Daza <gdazaf@gmail.com>
 */
public class Rol implements SuperTabla {

    private int id;
    private String rol;

    public Rol() {
    }

    public Rol(int id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String obtenerNombreSecuencia() {
        return "ROLES_SEQ";
    }

    public long getconsecutivo() throws SQLException {
        return Secuencia.nextVal(obtenerNombreSecuencia());
    }

    @Override
    public int insertar() throws SQLException {
        long secuencia = Secuencia.nextVal(obtenerNombreSecuencia());
        BaseDatosOracle basededatos;
        basededatos = BaseDatosOracle.getInstance();
        String sql;
        int ejecucion;
        sql = "INSERT INTO ROLES "
                + "(ID, ROL)"
                + " VALUES "
                + "(?, ?)";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, secuencia);
        basededatos.asignarParametro(2, getRol());
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
        sql = "DELETE ROLES WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getId());
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
        sql = "UPDATE ROLES SET "
                + "ROL = ? "
                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getRol());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    public static Rol buscar(long codigo) throws SQLException,
            Exception {
        Rol rol;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT * FROM ROLES  "
                + "  WHERE ID = ?";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, codigo);
        cursor = basededatos.ejecutarQuery();
        rol = null;
        if (cursor.next()) {
            rol = new Rol();
            rol.setId(cursor.getInt("ID"));
        }
        return rol;
    }

    public static ArrayList<Rol> buscar() throws SQLException,
            Exception {
        ArrayList<Rol> listaRoles;
        Rol rol;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT * FROM ROLES  "
                + "  WHERE ID = ?";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaRoles = new ArrayList<>();

        while (cursor.next()) {
            rol = new Rol();
            rol.setId(cursor.getInt("ID"));
            rol.setRol(cursor.getString("ROL"));
        }
        return listaRoles;
    }

    @Override
    public String obtenerNombreReporte() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
