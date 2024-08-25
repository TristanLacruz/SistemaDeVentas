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

    /**
     * Método para registrar una nueva categoría
     * @param objeto
     * @return 
     */
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

    /**
     * Método para consultar si ya existe una categoría
     * @param categoria
     * @return 
     */
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
    
    /**
     * Método para actualizar una nueva categoría
     * @param objeto
     * @return 
     */
    public boolean actualizar(Categoria objeto, int idCategoria) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_categoria set descripcion=? where idCategoria = '" + idCategoria + "'");
            consulta.setString(1, objeto.getDescripcion());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar la categoría: " + e);
        }
        return respuesta;
    }
    
    /**
     * Método para eliminar una categoría
     * @param idCategoria
     * @return 
     */
    public boolean eliminar(int idCategoria) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement(
                    "delete from tb_categoria where idCategoria = '" + idCategoria + "'");
            consulta.executeUpdate();
            
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar la categoría: " + e);
        }
        return respuesta;
    }
}
