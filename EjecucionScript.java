package creacionBBDD;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class EjecucionScript {
    public static void CrearTablas() {
        String scriptPath = "C:\\Users\\Usuario\\Documents\\Matias\\PROGRA3 2023\\Proyecto Final\\script.sql";

        StringBuilder query = new StringBuilder();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", "root", "");
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
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar las instrucciones del script: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}