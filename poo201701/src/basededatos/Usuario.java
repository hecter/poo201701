/*
 * Copyright (C) 2017 8649840.
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

import java.sql.SQLException;

/**
 *
 * @author hecter 
 */
public class Usuario {
       
    private String usuario;
    private String password;
    private static Usuario instance;

    /**
     *
     * @param usuario
     * @param password
     */
    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    /**
     *
     */
    public Usuario() {
    }

    /**
     *
     * @return
     */
    public static Usuario getInstance() {
        if (instance == null) {
            instance = new Usuario();
        }
        return instance;
    }

    /**
     *
     * @param usuario
     * @param password
     * @return
     */
    public static Usuario getInstance(String usuario, String password) {
        if (instance == null) {
            instance = new Usuario(usuario, password);
        }
        return instance;
    }

    /**
     *
     * @param aInstance
     */
    public static void setInstance(Usuario aInstance) {
        instance = aInstance;
    }

    /**
     *
     * @return
     */
    public String getUsuario() {

        if (usuario == null) {
            return "oraelectivapoo";
        } // quitar en ambiente productivo
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        if (password == null) {
            return "oraelectivapoo";
        }
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @throws SQLException
     */
    public void iniciarSesion() throws SQLException {
        BaseDatosOracle basedatos;
        basedatos = new BaseDatosOracle();
        basedatos.conectar();
    }

}
