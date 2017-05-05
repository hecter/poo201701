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

import basededatos.Secuencia;
import java.sql.SQLException;
import modelo.SuperTabla;

/**
 *
 * @author hteran
 */
public abstract class Lugar implements SuperTabla {

    private long codigo;
    private String nombre;

    public Lugar(long codigo, String nombre) throws Exception {
        setCodigo(codigo);
        setNombre(nombre);
    }

    public abstract String getNombreSecuencia();

    public final long getConsecutivo() throws SQLException {
        long consecutivo;
        consecutivo = Secuencia.nextVal(getNombreSecuencia());
        return consecutivo;
    }

    public Lugar(String nombre) throws Exception {
        setCodigo(getConsecutivo());
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre de " + getClass().getSimpleName()
                    + " no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public long getCodigo() {
        return codigo;
    }

    public final void setCodigo(long codigo) throws Exception {
        if (codigo < 0) {
            throw new Exception("El código no puede ser menor que cero");
        }
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCodigo() != 0) ? getCodigo() : 0;
        hash += (getNombre() != null ? getNombre().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return getCodigo() + "-" + getNombre();
    }

}
