package conexionBBDD;

import javax.swing.*;
import java.sql.*;

public class Conexion {
    //Conexion Local
    public static Connection conectar(){
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_gameplay", "root", "");
            return cn;
        }
        catch (SQLException exception){
            JOptionPane.showMessageDialog(null, "Error en la conexion local " + exception);
        }
        return null;
    }
}
