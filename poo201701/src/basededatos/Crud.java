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
package basededatos;

import java.sql.SQLException;

/**
 *
 * @author Héctor Teran <hteran@unisimonbolivar.educ.co>
 */
public interface Crud {

    /**
     *
     * @return @throws SQLException
     */
    public int insertar() throws SQLException;

    /**
     *
     * @return @throws SQLException
     */
    public int eliminar() throws SQLException;

    /**
     *
     * @return @throws SQLException
     */
    public int actualizar() throws SQLException;

}
