/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.atencion;

import basededatos.BaseDatosOracle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.pagos.Rol;
import modelo.pagos.Usuario;
import report.facturacion.Concepto;

/**
 *
 * @author 20111532577
 */
public class Motivo {
    private long id;
    private String motivos;
    private Concepto concepto;

    public Motivo(long id, String motivos, Concepto concepto) {
        this.id = id;
        this.motivos = motivos;
        this.concepto = concepto;
    }

    public Motivo(String motivos, Concepto concepto) {
        this.motivos = motivos;
        this.concepto = concepto;
    }

    public Motivo() {
    }
    
    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }
    
    public String getMotivos() {
        return motivos;
    }

    public void setMotivos(String motivos) {
        this.motivos = motivos;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    public static ArrayList<Motivo> buscar() throws SQLException,
            Exception {
        ArrayList<Motivo> listaMotivo;
        Motivo mot;
        Concepto concep;
        BaseDatosOracle basededatos;
        ResultSet cursor;
        String sql;
        basededatos = BaseDatosOracle.getInstance();
        sql = " SELECT MT.ID,MT.MOTIVO,MT.CONCEPTOS_ID,CON.DESCRIPCION " +
                " FROM MOTIVOS MT " +
                " INNER JOIN CONCEPTOS CON ON CON.ID=MT.CONCEPTOS_ID "
                + " ORDER BY ID ";

        basededatos.conectar();
        basededatos.prepararSql(sql);
        cursor = basededatos.ejecutarQuery(sql);
        listaMotivo = new ArrayList<>();

        while (cursor.next()) {
            concep = new Concepto();
            concep.setId(cursor.getInt("CONCEPTOS_ID"));

            listaMotivo.add(new Motivo(
                    cursor.getLong("ID"),
                    cursor.getString("MOTIVO"),
                    concep
            ));
        }
        return listaMotivo;
    }
    
}
