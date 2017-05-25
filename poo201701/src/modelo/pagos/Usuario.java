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
import modelo.lectura.Sector;

/**
 *
 * @author Guillermo Daza <gdazaf@gmail.com>
 */
public class Usuario implements SuperTabla {

    private String id;
    private String nombre;
    private Rol rol;

    public Usuario() {
    }
    
    public Usuario(String id) {
        this.id = id;
    }

    public Usuario(String id, String nombre, Rol rol) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                    cursor.getString("ID"),
                    cursor.getString("NOMBRE"),
                    rol
            ));
        }
        return listaUsuario;
    }
    
    public static Usuario listar(Sector sector) throws SQLException {
        Usuario usuario =null;
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        bd.conectar();
        bd.prepararSql("SELECT USUARIOS.ID FROM USUARIOS WHERE SECTOR_ID = ?");
        bd.asignarParametro(1, sector.getId());
        ResultSet reg = bd.ejecutarQuery();
        if (reg.next()) {
            usuario = new Usuario(reg.getString("ID"));
        }
        return usuario;
    }
    
    public static ArrayList<String> listar() throws SQLException {
        ArrayList<String> listado =new ArrayList<>();
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        bd.conectar();
        bd.prepararSql("SELECT "
                            + "USUARIOS.ID, "
                            + "ROLES.ROL, "
                            + "USUARIOS.NOMBRE, "
                            + "USUARIOS.SECTOR_ID||':'||SECTORES.DET as SECTOR "
                        + "FROM "
                            + "USUARIOS "
                            + "LEFT JOIN SECTORES "
                                + "ON SECTORES.ID = USUARIOS.SECTOR_ID "
                            + "INNER JOIN ROLES "
                                + "ON ROLES.ID = USUARIOS.ROLES_ID");
        ResultSet reg = bd.ejecutarQuery();
        listado.clear();
        while (reg.next()) {
            if(reg.getString("SECTOR").equals(":")) listado.add(reg.getString("ID")+";"+reg.getString("ROL")+";"+reg.getString("NOMBRE")+";"+"POR ASIGNAR");
            else listado.add(reg.getString("ID")+";"+reg.getString("ROL")+";"+reg.getString("NOMBRE")+";"+reg.getString("SECTOR"));
        }
        return listado;
    }
    /**
     * utilizado para revisar cuando se asigne el sector al usuario
     * @return 
     * @throws java.sql.SQLException 
     */
    public boolean verificarLector() throws SQLException{
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        bd.conectar();
        bd.prepararSql("SELECT USUARIOS.SECTOR_ID||':' AS SECTOR_ID FROM USUARIOS WHERE ID = ?");
        bd.asignarParametro(1, getId());
        ResultSet reg = bd.ejecutarQuery();
        if(reg.next()){
            if(reg.getString("SECTOR_ID").equals(":")){
                return true;
            }
        }
        return false;
    }
    
    public int asignarRol(long sector) throws SQLException{
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        bd.conectar();
        bd.prepararSql("UPDATE USUARIOS SET SECTOR_ID = ? WHERE ID = ?");
        bd.asignarParametro(1, sector);
        bd.asignarParametro(2, getId());
        int ejecucion = bd.ejecutar();
        bd.cerrarSentencia();
        return ejecucion;
    }
}
