import creacionBBDD.EjecucionScript;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class VentanaEmergente extends JFrame implements ActionListener {
    private JLabel label_user, label_password, label_IPDirection, label_MYSQLroute, label_obligatoryField, label_defaultField;
    private JTextField textField_user, textField_IPDirection, textField_MYSQLroute;
    private JPasswordField passwordField;
    private JButton button_send;
    public static String user_ddbb, pass_ddbb, directionIP_ddbb, routeMYSQL_ddbb;
    GameOn gameOn;
    public VentanaEmergente(GameOn gameOn){

        this.gameOn = gameOn;
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ingreso de datos");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper7.jpg"))).getImage());

        iniciarComponentes();

        this.setBounds(500,300,400,330);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
    }

    private void iniciarComponentes() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 330));

        label_user = new JLabel("Usuario");
        label_user.setBounds(10, 30, 120, 20);
        label_user.setForeground(Color.BLACK);
        label_user.setFont(new Font("Cascadia Code", 1, 15));
        layeredPane.add(label_user, Integer.valueOf(1));

        textField_user = new JTextField();
        textField_user.setBounds(160,30,200,27);
        textField_user.setBackground(Color.BLACK);
        textField_user.setForeground(Color.white);
        textField_user.setFont(new Font("Arial",1,18));
        textField_user.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(textField_user, Integer.valueOf(1));

        label_defaultField = new JLabel("Este campo es obligatorio.");
        label_defaultField.setBounds(10,60,200,20);
        label_defaultField.setForeground(Color.red);
        label_defaultField.setFont(new Font("Ebrima",1,15));
        layeredPane.add(label_defaultField, Integer.valueOf(1));

        label_password = new JLabel("Contraseña");
        label_password.setBounds(10, 90, 120, 20);
        label_password.setForeground(Color.BLACK);
        label_password.setFont(new Font("Cascadia Code", 1,15));
        layeredPane.add(label_password, Integer.valueOf(1));

        passwordField = new JPasswordField();
        passwordField.setBounds(160,90,200,27);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.white);
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(passwordField, Integer.valueOf(1));

        label_defaultField = new JLabel("Este campo es obligatorio.");
        label_defaultField.setBounds(10,120,200,20);
        label_defaultField.setForeground(Color.red);
        label_defaultField.setFont(new Font("Ebrima",1,15));
        layeredPane.add(label_defaultField, Integer.valueOf(1));

        label_IPDirection = new JLabel("Direccion IP");
        label_IPDirection.setBounds(10,150,120,20);
        label_IPDirection.setForeground(Color.BLACK);
        label_IPDirection.setFont(new Font("Cascadia Code",1,15));
        layeredPane.add(label_IPDirection, Integer.valueOf(1));

        textField_IPDirection = new JTextField();
        textField_IPDirection.setBounds(160,150,200,27);
        textField_IPDirection.setBackground(Color.BLACK);
        textField_IPDirection.setForeground(Color.white);
        textField_IPDirection.setFont(new Font("Arial",1,18));
        textField_IPDirection.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(textField_IPDirection, Integer.valueOf(1));

        label_obligatoryField = new JLabel("Este campo es obligatorio.");
        label_obligatoryField.setBounds(10,180,200,20);
        label_obligatoryField.setForeground(Color.RED);
        label_obligatoryField.setFont(new Font("Ebrima",1,15));
        layeredPane.add(label_obligatoryField, Integer.valueOf(1));

        label_MYSQLroute = new JLabel("Puerto de MYSQL");
        label_MYSQLroute.setBounds(10,210,140,20);
        label_MYSQLroute.setForeground(Color.BLACK);
        label_MYSQLroute.setFont(new Font("Cascadia Code",1,15));
        layeredPane.add(label_MYSQLroute, Integer.valueOf(1));

        textField_MYSQLroute = new JTextField();
        textField_MYSQLroute.setBounds(160,210,200,27);
        textField_MYSQLroute.setBackground(Color.BLACK);
        textField_MYSQLroute.setForeground(Color.white);
        textField_MYSQLroute.setFont(new Font("Arial",1,18));
        textField_MYSQLroute.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(textField_MYSQLroute, Integer.valueOf(1));

        label_obligatoryField = new JLabel("Este campo es obligatorio.");
        label_obligatoryField.setBounds(10,240,200,20);
        label_obligatoryField.setForeground(Color.RED);
        label_obligatoryField.setFont(new Font("Ebrima",1,15));
        layeredPane.add(label_obligatoryField, Integer.valueOf(1));

        button_send = new JButton("ENVIAR");
        button_send.setBounds(130,280,150,30);
        button_send.setBackground(Color.CYAN);
        button_send.setForeground(Color.BLACK);
        button_send.setFont(new Font("Bookman Old Style",1,18));
        button_send.addActionListener(this);
        layeredPane.add(button_send);

        setContentPane(layeredPane);
    }

    public void actionPerformed(ActionEvent e) {
        // 10.20.3.230
        if (e.getSource() == button_send) {
            user_ddbb = textField_user.getText();
            pass_ddbb = passwordField.getText();
            directionIP_ddbb = textField_IPDirection.getText();
            routeMYSQL_ddbb = textField_MYSQLroute.getText();
            while (true) {
                if ((directionIP_ddbb.equals("")) || (routeMYSQL_ddbb.equals("")) || (user_ddbb.equals("")) || (pass_ddbb.equals(""))) {
                    JOptionPane.showMessageDialog(null, "Debes completar todos los campos obligatorios.");
                    setCamposEnVacio();
                    break;
                }
                else {
                    if (EjecucionScript.CrearTablas(VentanaEmergente.directionIP_ddbb, VentanaEmergente.routeMYSQL_ddbb, VentanaEmergente.user_ddbb, VentanaEmergente.pass_ddbb)){
                        gameOn.getButton_start().setEnabled(true);
                        gameOn.getButton_loadData().setEnabled(false);
                        this.dispose();
                    }
                    setCamposEnVacio();
                    break;
                }
            }
        }
    }

    public void setCamposEnVacio() {
        textField_user.setText("");
        passwordField.setText("");
        textField_IPDirection.setText("");
        textField_MYSQLroute.setText("");
    }
}

