package controlador;

import conexion.Conexion;
import modelo.Categoria;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author trist
 */
public class Ctrl_Categoria {

    //Método para registrar categoría
    public boolean guardar(Categoria objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {

            //Para realizar una inserción en la base de datos
            PreparedStatement consulta = cn.prepareStatement("insert into tb_categoria values(?,?,?)");
            //Para cada '?', insertamos un campo:
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getDescripcion());
            consulta.setInt(3, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            //Cerrar conexión
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar categoría: " + e);
        }
        return respuesta;
    }

    //metodo para consultar si existe la categoría
    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        String sql = "select descripcion from tb_categoria where descripcion = '" + categoria + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar categoria: " + e);
        }
        return respuesta;
    }
}
