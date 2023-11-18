import conexionBBDD.Conexion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Historial extends JFrame implements ActionListener {
    private JLabel label_wallpaper, label_titulo, label_footer;
    private JTextPane textPane_resume;
    private JScrollPane scrollPane;
    private JButton button_borrar_log;
    private boolean historialVacio = true;
    private static int ultimoGame = 1;
    public Historial(){
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Historial del jugador - " + LogIn.user + " -");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper7.jpg"))).getImage());

        iniciarComponentes();

        this.setBounds(0,0,800,500);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
    }

    public String imprimirDatosTabla() {
        StringBuilder gameplay = new StringBuilder();
        try {
            Connection cn = Conexion.conectar(VentanaEmergente.directionIP_ddbb, VentanaEmergente.routeMYSQL_ddbb, VentanaEmergente.user_ddbb, VentanaEmergente.pass_ddbb);
            PreparedStatement pst = cn.prepareStatement("select * from cartas where usuario = '" + LogIn.user + "' and id_juego >= '" + ultimoGame + "' and mostrar = '" + 1 + "'");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id_juego = rs.getInt("id_juego");
                String cartasJ1 = rs.getString("cartasJ1");
                String cartasJ2 = rs.getString("cartasJ2");
                String fecha_hora = rs.getString("fecha_hora");
                String usuario = rs.getString("usuario");

                gameplay.append("------------------------------------------------------------------------------------------------\nEl dia ").append(fecha_hora).append(" ").append(usuario).append(" desarrolló el juego ").append(id_juego).append(".\n------------------------------------------------------------------------------------------------\n");
                String infoJuego = "\n                 Se eligieron los siguientes personajes: \n" +
                            cartasJ1 + cartasJ2;

                gameplay.append(infoJuego);

                Connection cn2 = Conexion.conectar(VentanaEmergente.directionIP_ddbb, VentanaEmergente.routeMYSQL_ddbb, VentanaEmergente.user_ddbb, VentanaEmergente.pass_ddbb);
                PreparedStatement pst2 = cn2.prepareStatement("select datos_historial from batalla where id_juego = '" + id_juego + "'");

                ResultSet rs2 = pst2.executeQuery();

                if (rs2.next()) {
                   String registroBatalla = rs2.getString("datos_historial");

                   gameplay.append("~~~~~~~~~~~DESAROLLO DE LA BATALLA~~~~~~~~~~~").append(registroBatalla);
                }
                cn2.close();

                historialVacio = false;
            }

            cn.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos de la tabla cartas " + exception.getMessage());
        }

        return gameplay.toString();
    }

    private void incrementarContadorJuego() {
        try {
            Connection cn2 = Conexion.conectar(VentanaEmergente.directionIP_ddbb, VentanaEmergente.routeMYSQL_ddbb, VentanaEmergente.user_ddbb, VentanaEmergente.pass_ddbb);
            PreparedStatement pst2 = cn2.prepareStatement("select max(id_juego) from cartas where usuario = '" + LogIn.user + "'");

            ResultSet rs2 = pst2.executeQuery();

            if (rs2.next()) {
                ultimoGame = rs2.getInt(1);
                //JOptionPane.showMessageDialog(null, ultimoGame);
            }

            ultimoGame += 1;

            cn2.close();
        }
        catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Error al mostrar el maximo del id_juego " + exception.getMessage());
        }
    }
    private void iniciarComponentes() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800,500));

        ImageIcon wallpaper_logo = new ImageIcon("images\\wallpaper2.jpg");
        Icon icono = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(800,500,Image.SCALE_DEFAULT));
        label_wallpaper = new JLabel(icono);
        label_wallpaper.setBounds(0,0,800,500);
        this.repaint();
        layeredPane.add(label_wallpaper, Integer.valueOf(0));

        label_titulo = new JLabel("HISTORIAL");
        label_titulo.setBounds(300,30,300,30);
        label_titulo.setForeground(new Color(130,15,15));
        label_titulo.setFont(new Font("Tahoma",1,35));
        layeredPane.add(label_titulo, Integer.valueOf(1));

        textPane_resume = new JTextPane();
        textPane_resume.setText(imprimirDatosTabla());
        textPane_resume.setFont(new Font("Arial",1,20));
        textPane_resume.setForeground(Color.RED);
        textPane_resume.setBackground(Color.BLACK);
        textPane_resume.setEditable(false);
        scrollPane = new JScrollPane(textPane_resume);
        scrollPane.setBounds(105,100,570,270);
        layeredPane.add(scrollPane, Integer.valueOf(1));

        button_borrar_log = new JButton("Borrar historial");
        button_borrar_log.setBounds(300, 400, 220, 35);
        button_borrar_log.setBackground(Color.white);
        button_borrar_log.setForeground(new Color(130, 15, 15));
        button_borrar_log.setFont(new Font("Calibri", 1, 25));
        layeredPane.add(button_borrar_log, Integer.valueOf(1));
        button_borrar_log.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(320,440,210,20);
        label_footer.setForeground(Color.white);
        layeredPane.add(label_footer, Integer.valueOf(1));

        setContentPane(layeredPane);
    }

    public JTextPane getTextPane_resume() {
        return textPane_resume;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_borrar_log){
            incrementarContadorJuego();
            getTextPane_resume().setText("");
            historialVacio = true;
            try {
                Connection cn = Conexion.conectar(VentanaEmergente.directionIP_ddbb, VentanaEmergente.routeMYSQL_ddbb, VentanaEmergente.user_ddbb, VentanaEmergente.pass_ddbb);
                PreparedStatement pst = cn.prepareStatement("update cartas set mostrar = '" + 0 + "' where usuario = '" + LogIn.user + "'");

                pst.executeUpdate();

                cn.close();
                JOptionPane.showMessageDialog(null, "Actualizacion del campo mostrar exitosamente.");
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(null, "Error al actualizar los datos del campo 'mostrar'" + exception.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Historial borrado exitosamente.");
        }
    }

    public boolean isHistorialVacio() {
        return historialVacio;
    }

}