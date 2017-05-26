/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.lectura;

import basededatos.BaseDatosOracle;
import java.sql.ResultSet;

/**
 *
 * @author 20102122476
 */
public class Login {
    private String usuario;
    private String clave;

    public Login(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
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
    
    public void autenticar(){
        BaseDatosOracle bd = BaseDatosOracle.getInstance();
        String sql = "";
        bd.conectar();
        bd.prepararSql(sql);
        bd.asignarParametro(1, "%" + det + "%");
        ResultSet reg = bd.ejecutarQuery();
        datos.clear();
        while (reg.next()) {
            datos.add(new Sector(reg.getLong("ID"), reg.getString("DET")));
        }
    }
}
