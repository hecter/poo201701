/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pagos;

import basededatos.BaseDatosOracle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.SuperTabla;
import modelo.atencion.Atencion;
import modelo.atencion.Casa;
import modelo.atencion.Estado;
import modelo.atencion.Motivo;

/**
 *
 * @author Guillermo Daza <gdazaf@gmail.com>
 */
public class Usuario implements SuperTabla {

    private long id;
    private String nombre;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(long id, String nombre, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Usuario(String nombre, Rol rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int insertar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerNombreReporte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<Usuario> buscar() throws SQLException,
            Exception {
        ArrayList<Usuario> listaUsuario;
        Usuario usu;
        Rol rol;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = " SELECT US.ID, US.NOMBRE, US.ROLES_ID, ROL.ROL "
                + " FROM USUARIOS US "
                + " INNER JOIN ROLES ROL ON ROL.ID=US.ROLES_ID "
                + " ORDER BY ID ";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaUsuario = new ArrayList<>();

        while (cursor.next()) {
            rol = new Rol();
            rol.setId(cursor.getInt("ROLES_ID"));
            rol.setRol(cursor.getString("ROL"));

            listaUsuario.add(new Usuario(
                    cursor.getLong("ID"),
                    cursor.getString("NOMBRE"),
                    rol
            ));
        }
        return listaUsuario;
    }
}
