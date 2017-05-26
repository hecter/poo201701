/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.lectura;

import Messages.Mensajes;
import static Messages.Mensajes.msn;
import basededatos.BaseDatosOracle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import modelo.pagos.Rol;
import vista.FormLogin;
import vista.lectura.index;
import vista.lectura.lectura;

/**
 *
 * @author 20102122476
 */
public class Login {
    public static ArrayList<Login> login;
    private String usuario;
    private String clave;
    private String nombre;
    private Sector sector;
    private Rol rol;

    public Login(String usuario, String clave) {
        login = new ArrayList<>();
        this.usuario = usuario;
        this.clave = clave;
    }

    public Login(String usuario, String nombre, Sector sector, Rol rol) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.sector = sector;
        this.rol = rol;
    }
    
    public Login(String usuario, String nombre, Rol rol) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.rol = rol;
    }
    

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public void autenticar(JFrame ventana) throws SQLException{
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        String sql = "SELECT "
                        + "USUARIOS.ID, "
                        + "USUARIOS.ROLES_ID, "
                        + "ROLES.ID AS ROLES_DET, "
                        + "USUARIOS.NOMBRE, "
                        + "USUARIOS.SECTOR_ID, "
                        + "SECTORES.DET AS SECTOR_DET "
                    + "FROM "
                        + "USUARIOS "
                    + "LEFT JOIN  SECTORES "
                        + "ON SECTORES.ID = USUARIOS.SECTOR_ID "
                    + "INNER JOIN ROLES "
                        + "ON ROLES.ID = USUARIOS.ROLES_ID "
                    + "WHERE "
                        + "USUARIOS.ID = ? "
                    + "AND "
                        + "USUARIOS.PASSWORD = ?";
        bd.conectar();
        bd.prepararSql(sql);
        bd.asignarParametro(1, getUsuario());
        bd.asignarParametro(2, getClave());
        ResultSet reg = bd.ejecutarQuery();
        bd.cerrarSentencia();
        if (reg.next()) {
            ventana.setVisible(false);
            if(reg.getLong("ROLES_ID")>=5 & reg.getLong("ROLES_ID") <=7){
                int rol_id = reg.getInt("ROLES_ID");
                String rol_det = reg.getString("ROLES_DET");
                Rol r = new Rol(rol_id, rol_det);
                if(reg.getString("SECTOR_ID")!=null){
                    int sector_id = reg.getInt("SECTOR_ID");
                    String sector_det = reg.getString("SECTOR_DET");
                    Sector s = new Sector(sector_id, sector_det);
                    Login l = new Login(reg.getString("ID"), reg.getString("NOMBRE"), s, r);
                    login.clear();
                    login.add(l);
                }else{
                    Login l = new Login(reg.getString("ID"), reg.getString("NOMBRE"), r);
                    login.clear();
                    login.add(l);
                }
                if(rol_id==5){//LECTURA -
                    
                }else{
                    if(rol_id==6){//TECNICO_LECTURA
                        if(reg.getString("SECTOR_DET")!=null){
                            lectura l = new lectura();
                            l.txusuario.setText(login.get(0).getUsuario());
                            l.txsector.setText(String.valueOf(login.get(0).getSector().getId()));
                            FormLogin.limpiar();
                            l.setVisible(true);
                        }else{
                            msn(ventana, "NO PUEDES INGRESAR AL "
                                    + "SISTEMA\n ACERCATE AL AREA ENCARGADA "
                                    + "\nPARA QUE TE ASIGNE UN SECTOR",
                                    "ERROR", 0);
                            FormLogin.limpiar();
                            ventana.setVisible(true);
                        }
                    }else{//ADMIN_LECTURA
                        FormLogin.limpiar();
                        new index().setVisible(true);
                    }
                }
            }
        }else{
            FormLogin.limpiar();
            ventana.setVisible(true);
            msn(ventana, "USAURIO O CLAVE ERRADOS", "ERROR", 0);
        }
    }
}
