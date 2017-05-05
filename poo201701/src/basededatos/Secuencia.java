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
import java.sql.ResultSet;

/**
 *
 * @author hteran
 */
public class Secuencia {

    /**
     * Devuelve el póximo consecutivo de la secuencia enviada como parámetro
     *
     * @param secuencia el nombre de la secuencia en Oracle
     * @return El Próximo consecutivo de la secuencia
     * @throws SQLException Excepciones con la base de datos
     */
    public static long nextVal(String secuencia) throws SQLException {
        BaseDatosOracle basedatos;
        String sql;
        ResultSet cursor;
        basedatos = BaseDatosOracle.getInstance();
        long consecutivo;
        sql = "select " + secuencia + ".NEXTVAL as siguiente from dual";
        basedatos.conectar();
        basedatos.prepararSql(sql);
        cursor = basedatos.ejecutarQuery(sql);
        consecutivo = 0;
        if (cursor.next()) {
            consecutivo = cursor.getLong("siguiente");
        }
        BaseDatosOracle.cerrarCursor(cursor);
        return consecutivo;
    }
}
