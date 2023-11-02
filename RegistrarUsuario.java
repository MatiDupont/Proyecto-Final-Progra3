import conexionBBDD.Conexion;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class RegistrarUsuario extends JFrame implements ActionListener {
    private JLabel label_wallpaper, label_title, label_username, label_password, label_footer;
    private JTextField textField_user;
    private JPasswordField passwordField;
    private JButton button_registrar;
    public RegistrarUsuario(){
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar usuario");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper7.jpg"))).getImage());

        iniciarComponentes();

        this.setBounds(0,0,700,400);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button_registrar){
            String user = "", pass = "";

            user = textField_user.getText().trim();
            pass = passwordField.getText().trim();

            if (!user.equals("") && (!pass.equals(""))){
                try{
                    Connection cn = Conexion.conectar();
                    PreparedStatement pst = cn.prepareStatement("select usuario from usuarios where usuario = '" + user + "'");

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()){
                        JOptionPane.showMessageDialog(null, "Usuario con ese nombre ya registrado en la base de datos.");
                    }
                    else {
                        PreparedStatement pst2 = cn.prepareStatement("insert into usuarios values (?,?,?,1)");

                        pst2.setInt(1,0);
                        pst2.setString(2,user);
                        pst2.setString(3,pass);

                        pst2.executeUpdate();

                        cn.close();

                        JOptionPane.showMessageDialog(null, "Registro exitoso.");
                        this.dispose();
                    }


                }
                catch (SQLException exception){
                    JOptionPane.showMessageDialog(null, "Error al registrar usuario! " + exception.getMessage());
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
            }
        }
    }

    private void iniciarComponentes() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700,400));

        ImageIcon wallpaper_logo = new ImageIcon("images\\wallpaper4.jpg");
        Icon icono = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(700,400,Image.SCALE_DEFAULT));
        label_wallpaper = new JLabel(icono);
        label_wallpaper.setBounds(0,0,700,400);
        this.repaint();
        layeredPane.add(label_wallpaper, Integer.valueOf(0));

        label_title = new JLabel("REGISTRO");
        label_title.setBounds(265,20,300,30);
        label_title.setForeground(new Color(130,15,15));
        label_title.setFont(new Font("Tahoma",1,35));
        layeredPane.add(label_title, Integer.valueOf(1));

        label_username = new JLabel("Nombre de usuario");
        label_username.setBounds(255,120,250,20);
        label_username.setForeground(Color.BLACK);
        label_username.setFont(new Font("Arial",1,18));
        layeredPane.add(label_username, Integer.valueOf(1));

        textField_user = new JTextField();
        textField_user.setBounds(255,150,210,30);
        textField_user.setBackground(new Color(130,15,15));
        textField_user.setForeground(Color.BLACK);
        textField_user.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        textField_user.setFont(new Font("Arial",1,18));
        textField_user.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(textField_user, Integer.valueOf(1));

        label_password = new JLabel("Contraseña");
        label_password.setBounds(255,190,210,30);
        label_password.setForeground(Color.BLACK);
        label_password.setFont(new Font("Arial",1,18));
        layeredPane.add(label_password, Integer.valueOf(1));

        passwordField = new JPasswordField();
        passwordField.setBounds(255,220,210,30);
        passwordField.setBackground(new Color(130,15,15));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        passwordField.setFont(new Font("Arial",1,18));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(passwordField, Integer.valueOf(1));

        button_registrar = new JButton("Registrarse");
        button_registrar.setBounds(290,280,150,30);
        button_registrar.setBackground(Color.white);
        button_registrar.setForeground(new Color(130,15,15));
        button_registrar.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_registrar, Integer.valueOf(1));
        button_registrar.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(275,350,210,20);
        label_footer.setForeground(Color.white);
        layeredPane.add(label_footer, Integer.valueOf(1));

        setContentPane(layeredPane);
    }
}