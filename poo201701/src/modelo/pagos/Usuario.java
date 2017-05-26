/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pagos;

import basededatos.Secuencia;
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
public class Usuario implements SuperTabla{

    private String id;
    private String nombre;
    private String password;
    private Rol rol;
    private Sector sector;

    public Usuario() {
    }

    
     public Rol getRol() {
        return rol;
    }
     
     public Usuario(String id, Rol rol, String nombre, Sector sector, String password) {
        this.id = id;
        this.rol = rol;
        this.nombre = nombre;
        this.sector = sector;
        this.password = password;
    }
     
     public Usuario(String id) {
        this.id = id;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
    

public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

     public String obtenerNombreSecuencia() {
        return "USUARIOS_SEQ";
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
        sql="INSERT INTO USUARIOS "
//                + "VALUES (2,1,'PRUEBA',1,1)";
                + "VALUES (?,?,?,?,?)";
//        sql = "INSERT INTO USUARIOS "
//                + "(ID, ROL)"
//                + " VALUES "
//                + "(?, ?)";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, secuencia);
        basededatos.asignarParametro(2, getRol().getId());
        basededatos.asignarParametro(3, getNombre());
        basededatos.asignarParametro(4, getSector().getId());
        basededatos.asignarParametro(5, getPassword());
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
        sql = "DELETE USUARIOS WHERE ID = ?";
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
        sql="update usuarios "
                + "set roles_id=?,"
                + "nombre=?,"
                + "sector_id=?,"
                + "password=?"
                + " where id=?";
//        sql = "UPDATE ROLES SET "
//                + "ROL = ? "
//                + "WHERE ID = ?";
        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, getRol().getId());
        basededatos.asignarParametro(2, getNombre());
        basededatos.asignarParametro(3, getSector().getId());
        basededatos.asignarParametro(4, getPassword());
        basededatos.asignarParametro(5, getId());
        ejecucion = basededatos.ejecutar();
        basededatos.cerrarSentencia();
        return ejecucion;
    }

    public static Usuario buscar(long codigo) throws SQLException,
            Exception {
        Usuario usuario=new Usuario();
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = "SELECT U.*,ROL,DET " +
                "FROM USUARIOS U" +
                "    INNER JOIN ROLES R" +
                "        ON U.ROLES_ID=R.ID" +
                "    INNER JOIN SECTORES S" +
                "        ON U.SECTOR_ID=S.ID"+
                " WHERE U.ID=?";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        basededatos.asignarParametro(1, codigo);
        cursor = basededatos.ejecutarQuery();
        if (cursor.next()) {
            usuario.setId(cursor.getString("ID"));
            usuario.setNombre(cursor.getString("NOMBRE"));
            usuario.setPassword(cursor.getString("PASSWORD"));
            Rol rol=new Rol();
            rol.setId(cursor.getInt("ROLES_ID"));
            rol.setRol(cursor.getString("ROL"));
            usuario.setRol(rol);
            Sector sec=new Sector();
            sec.setId(cursor.getInt("SECTOR_ID"));
            sec.setDet(cursor.getString("DET"));
            usuario.setSector(sec);
        }
        return usuario;
    }

    public static ArrayList<Usuario> buscar() throws SQLException,
            Exception {
        ArrayList<Usuario> listaUsuarios;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
       sql = "SELECT U.*,ROL,DET " +
                "FROM USUARIOS U" +
                "    INNER JOIN ROLES R" +
                "        ON U.ROLES_ID=R.ID" +
                "    INNER JOIN SECTORES S" +
                "        ON U.SECTOR_ID=S.ID";
                

        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaUsuarios = new ArrayList<>();
        Usuario usuario;        
        Rol rol;
        Sector sec;
        while (cursor.next()) {
            usuario=new Usuario();
            usuario.setId(cursor.getString("ID"));
            usuario.setNombre(cursor.getString("NOMBRE"));
            usuario.setPassword(cursor.getString("PASSWORD"));
            rol=new Rol();
            rol.setId(cursor.getInt("ROLES_ID"));
            rol.setRol(cursor.getString("ROL"));
            usuario.setRol(rol);
            sec=new Sector();
            sec.setId(cursor.getInt("SECTOR_ID"));
            sec.setDet(cursor.getString("DET"));
            usuario.setSector(sec);
            listaUsuarios.add(usuario);
        }
        return listaUsuarios;
    }
    
    @Override
    public String obtenerNombreReporte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
