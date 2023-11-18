package creacionBBDD;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class EjecucionScript {

    public static boolean CrearTablas(String directionIP, String routeMYSQL, String user_ddbb, String pass_ddbb) {

        String scriptPath = "..\\Proyecto Final\\script.sql";

        StringBuilder query = new StringBuilder();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + directionIP + ":" + routeMYSQL, user_ddbb, pass_ddbb);
            Statement statement = connection.createStatement();

            BufferedReader br = new BufferedReader(new FileReader(scriptPath));
            String line;
            while ((line = br.readLine()) != null) {
                query.append(line);
                if (line.endsWith(";")) {
                    statement.executeUpdate(query.toString());
                    query.setLength(0);
                }
            }

            JOptionPane.showMessageDialog(null, "Tablas creadas exitosamente.");

            br.close();
            statement.close();
            connection.close();

            return true;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar las instrucciones del script: " + exception.getMessage());
            exception.printStackTrace();
            return false;
        }
    }
}