import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import com.mysql.jdbc.log.Log;
import conexionBBDD.Conexion;

public class LogIn extends JPanel implements ActionListener {
    private JLabel label_usuario, label_password ,label_footer;
    private JTextField textField_usuario;
    private Inicio panel_Inicio;
    private JScrollPane scrollPane;
    private JPasswordField passwordField;
    private JButton button_acceder, button_registrarse;
    private BufferedImage backgroundImage;
    public static String user = "";
    String pass = "";

    public JTextField getTextField_usuario() {
        return textField_usuario;
    }

    public LogIn(){
        setLayout(null);
        cargarImagenDeFondo();
        iniciarComponentes();

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button_acceder.doClick();
                }
            }
        });

        setFocusable(true);
        requestFocus();
    }

    private void cargarImagenDeFondo() {
        try {
            backgroundImage = ImageIO.read(new File("images\\wallpaper3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 1400, 700, this);
        }
    }


    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button_acceder){
            user = textField_usuario.getText().trim();
            pass = passwordField.getText().trim();

            if ((!user.equals("")) && (!pass.equals(""))){
                try {
                    Connection cn = Conexion.conectar();
                    PreparedStatement pst = cn.prepareStatement("select username, password from usuarios where username = '" + user + "' and password = '" + pass + "'");

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()){
                        panel_Inicio = new Inicio();
                        add(scrollPane);
                        label_usuario.setVisible(false);
                        textField_usuario.setVisible(false);
                        label_password.setVisible(false);
                        passwordField.setVisible(false);
                        button_acceder.setVisible(false);
                        button_registrarse.setVisible(false);
                        label_footer.setVisible(false);
                        definirPanel(panel_Inicio);
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
                textField_usuario.setText("");
                passwordField.setText("");
            }
        }
        if (e.getSource() == button_registrarse){
            new RegistrarUsuario().setVisible(true);
        }
    }
    private void iniciarComponentes() {
        label_usuario = new JLabel("Nombre de usuario");
        label_usuario.setBounds(560, 310, 250, 20);
        label_usuario.setForeground(Color.white);
        label_usuario.setFont(new Font("Arial", 1, 18));
        add(label_usuario);

        textField_usuario = new JTextField();
        textField_usuario.setBounds(560, 340, 210, 30);
        textField_usuario.setBackground(new Color(130, 15, 15));
        textField_usuario.setForeground(Color.BLACK);
        textField_usuario.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        textField_usuario.setFont(new Font("Arial", 1, 18));
        textField_usuario.setHorizontalAlignment(SwingConstants.CENTER);
        add(textField_usuario);

        label_password = new JLabel("Contraseña");
        label_password.setBounds(560, 380, 250, 20);
        label_password.setForeground(Color.white);
        label_password.setFont(new Font("Arial", 1, 18));
        add(label_password);

        passwordField = new JPasswordField();
        passwordField.setBounds(560, 410, 210, 30);
        passwordField.setBackground(new Color(130, 15, 15));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        passwordField.setFont(new Font("Arial", 1, 18));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        add(passwordField);

        button_acceder = new JButton("Acceder");
        button_acceder.setBounds(610, 480, 110, 30);
        button_acceder.setBackground(Color.white);
        button_acceder.setForeground(new Color(130, 15, 15));
        button_acceder.setFont(new Font("Calibri", 1, 18));
        add(button_acceder);
        button_acceder.addActionListener(this);

        button_registrarse = new JButton("Registrate ahora");
        button_registrarse.setBounds(575, 540, 180, 30);
        button_registrarse.setBackground(Color.white);
        button_registrarse.setForeground(new Color(130, 15, 15));
        button_registrarse.setFont(new Font("Calibri", 1, 18));
        add(button_registrarse);
        button_registrarse.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(575, 610, 210, 20);
        label_footer.setForeground(Color.white);
        add(label_footer);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(0,0,1400,1400);
    }
    private void definirPanel(Inicio panel){
        scrollPane.setViewportView(panel);
    }
}