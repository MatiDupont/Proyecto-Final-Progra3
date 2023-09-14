import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import conexionBBDD.Conexion;

public class LogIn extends JFrame implements ActionListener {
    private JLabel label_wallpaper, label_titulo, label_usuario, label_password ,label_footer;
    private JTextField textField_usuario;
    private JPasswordField passwordField;
    private JButton button_acceder, button_registrarse;
    public static String user = "";
    String pass = "";
    public LogIn(){
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null,"Seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (confirm == JOptionPane.YES_OPTION){
                    dispose();
                }
            }
        });
        setTitle("Acceso al Juego");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper.jpg"))).getImage());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1400,1400));

        ImageIcon wallpaper_logo = new ImageIcon("images\\wallpaper3.jpg");
        Icon icono = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(1400,700,Image.SCALE_DEFAULT));
        label_wallpaper = new JLabel(icono);
        label_wallpaper.setBounds(0,0,1400,700);
        this.repaint();
        layeredPane.add(label_wallpaper, Integer.valueOf(0));

        label_titulo = new JLabel("BATTLE ROYALE");
        label_titulo.setBounds(520,140,300,30);
        label_titulo.setForeground(new Color(130,15,15));
        label_titulo.setFont(new Font("Tahoma",1,35));
        layeredPane.add(label_titulo, Integer.valueOf(1));

        label_usuario = new JLabel("Nombre de usuario");
        label_usuario.setBounds(560,310,250,20);
        label_usuario.setForeground(Color.white);
        label_usuario.setFont(new Font("Arial", 1,18));
        layeredPane.add(label_usuario, Integer.valueOf(1));

        textField_usuario = new JTextField();
        textField_usuario.setBounds(560,340,210,30);
        textField_usuario.setBackground(new Color(130,15,15));
        textField_usuario.setForeground(Color.BLACK);
        textField_usuario.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        textField_usuario.setFont(new Font("Arial",1,18));
        textField_usuario.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(textField_usuario, Integer.valueOf(1));

        label_password = new JLabel("Contraseña");
        label_password.setBounds(560,380,250,20);
        label_password.setForeground(Color.white);
        label_password.setFont(new Font("Arial", 1,18));
        layeredPane.add(label_password, Integer.valueOf(1));

        passwordField = new JPasswordField();
        passwordField.setBounds(560,410,210,30);
        passwordField.setBackground(new Color(130,15,15));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        passwordField.setFont(new Font("Arial",1,18));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(passwordField, Integer.valueOf(1));

        button_acceder = new JButton("Acceder");
        button_acceder.setBounds(610,480,110,30);
        button_acceder.setBackground(Color.white);
        button_acceder.setForeground(new Color(130, 15, 15));
        button_acceder.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_acceder, Integer.valueOf(1));
        button_acceder.addActionListener(this);

        button_registrarse = new JButton("Registrate ahora");
        button_registrarse.setBounds(575,540,180,30);
        button_registrarse.setBackground(Color.white);
        button_registrarse.setForeground(new Color(130,15,15));
        button_registrarse.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_registrarse, Integer.valueOf(1));
        button_registrarse.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(575,610,210,20);
        label_footer.setForeground(Color.white);
        layeredPane.add(label_footer, Integer.valueOf(1));

        setContentPane(layeredPane);

        this.setBounds(0,0,1400,1400);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button_acceder){
            //new Inicio().setVisible(true);
            user = textField_usuario.getText().trim();
            pass = passwordField.getText().trim();

            if ((!user.equals("")) && (!pass.equals(""))){
                try {
                    Connection cn = Conexion.conectar();
                    PreparedStatement pst = cn.prepareStatement("select username, password from usuarios where username = '" + user + "' and password = '" + pass + "'");

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()){
                        new Inicio().setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Datos de acceso incorrectos.");
                        textField_usuario.setText("");
                        passwordField.setText("");
                    }
                }
                catch (SQLException exception){
                    System.err.println("Error en el boton Acceder. " + exception.getMessage());
                    JOptionPane.showMessageDialog(null, "Error al iniciar sesion!. Contacte con el administrador.");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
            }
        }
        if (e.getSource() == button_registrarse){
            new RegistrarUsuario().setVisible(true);
        }
    };
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }
}
