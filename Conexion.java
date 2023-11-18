package conexionBBDD;

import javax.swing.*;
import java.sql.*;
public class Conexion {
    //Conexion Local
    public static Connection conectar(String directionIP, String routeMYSQL, String user_ddbb, String pass_ddbb){

        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://" + directionIP + ":" + routeMYSQL + "/bd_gameplay", user_ddbb, pass_ddbb);
            return cn;
        }
        catch (SQLException exception){
            JOptionPane.showMessageDialog(null, "Error en la conexion local " + exception);
        }
        return null;
    }
}
