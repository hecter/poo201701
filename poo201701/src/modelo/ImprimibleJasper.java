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
package modelo;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author hteran
 */
public interface ImprimibleJasper {

    /**
     *
     * @return
     */
    String obtenerNombreReporte();

    /**
     *
     * @return
     */
    default Map<String, Object> obtenerParametros() {
        return new HashMap<String, Object>();
    }

    /**
     * Abre el Reporte Jasper indicado en el Paramtero
     *
     * @param archivo Nombre del Archivo a ejecutar incluida la ruta
     * @param parametros Parametros del Reporte
     * @param connection Conexion a la base de Datos
     * @throws Exception No hay conexion con la base de datos
     */
    default void abrirReporte(String archivo, Map parametros,
            Connection connection) throws Exception {
        JasperDesign jasperDesign = null;
        jasperDesign = JRXmlLoader.load(archivo);
        JasperReport jasper = (JasperReport) JasperCompileManager
                .compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper,
                parametros, connection);
        JasperViewer jviewer = new JasperViewer(jasperPrint, false);
        jviewer.setTitle(getClass().getSimpleName());
        jviewer.setVisible(true);
    }

    /**
     * Abre el Reporte Jasper indicado en el Paramtero
     *
     * @param connection Conexion a la base de Datos
     * @throws Exception No hay conexion con la base de datos
     */
    default void abrirReporte(Connection connection) throws Exception {
        abrirReporte(
                getClass().getResource(obtenerNombreReporte()).getFile(),
                obtenerParametros(),
                connection);
    }

    /**
     * Abre el Reporte Jasper indicado en el Paramtero
     *
     * @param connection Conexion a la base de Datos
     * @param parametros Parametros del reporte
     * @throws Exception No hay conexion con la base de datos
     */
    default void abrirReporte(Connection connection, Map parametros)
            throws Exception {
        abrirReporte(
                getClass().getResource(obtenerNombreReporte()).getFile(),
                parametros,
                connection);
    }
    
    

}
