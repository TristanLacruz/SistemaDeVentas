package controlador;

import com.mysql.jdbc.ResultSetRow;
import conexion.Conexion;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author trist
 */
public class Ctrl_Usuario {

    //metodo para Iniciar Sesion
    public boolean loginUser(Usuario objeto) {

        boolean respuesta = false;

        Connection cn = Conexion.conectar();
        String sql = "select usuario, password from tb_usuario where usuario = '" + objeto.getUsuario() + "' and password ='" + objeto.getPassword()+ "'";
        Statement st;
        try {

            st = cn.createStatement();
            //Ejecutar la sentencia sql
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión");
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión");
        }

        return respuesta;
    }
}
