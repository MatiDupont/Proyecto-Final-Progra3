import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Inicio extends JFrame implements ActionListener {
    private JLabel label_wallpaper, label_menu, label_footer;
    private JButton button_inicio, button_leer_log, button_borrar_log, button_salir;
    String user;
    public Inicio() {
        user = LogIn.user;
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inicio del jugador - " + user);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper.jpg"))).getImage());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1400, 1400));

        ImageIcon wallpaper_logo = new ImageIcon("images\\wallpaper3.jpg");
        Icon icono = new ImageIcon((wallpaper_logo.getImage().getScaledInstance(1400, 700, Image.SCALE_DEFAULT)));
        label_wallpaper = new JLabel(icono);
        label_wallpaper.setBounds(0, 0, 1400, 700);
        this.repaint();
        layeredPane.add(label_wallpaper, Integer.valueOf(0));

        label_menu = new JLabel("MENU DE OPCIONES");
        label_menu.setBounds(505, 140, 370, 30);
        label_menu.setForeground(new Color(130, 15, 15));
        label_menu.setFont(new Font("Tahoma", 1, 35));
        layeredPane.add(label_menu, Integer.valueOf(1));

        button_inicio = new JButton("Iniciar partida");
        button_inicio.setBounds(555, 280, 270, 35);
        button_inicio.setBackground(Color.white);
        button_inicio.setForeground(new Color(130, 15, 15));
        button_inicio.setFont(new Font("Calibri", 1, 25));
        layeredPane.add(button_inicio, Integer.valueOf(1));
        button_inicio.addActionListener(this);

        button_leer_log = new JButton("Historial");
        button_leer_log.setBounds(555, 350, 270, 35);
        button_leer_log.setBackground(Color.white);
        button_leer_log.setForeground(new Color(130, 15, 15));
        button_leer_log.setFont(new Font("Calibri", 1, 25));
        layeredPane.add(button_leer_log, Integer.valueOf(1));
        button_leer_log.addActionListener(this);

        button_borrar_log = new JButton("Borrar historial");
        button_borrar_log.setBounds(555, 420, 270, 35);
        button_borrar_log.setBackground(Color.white);
        button_borrar_log.setForeground(new Color(130, 15, 15));
        button_borrar_log.setFont(new Font("Calibri", 1, 25));
        layeredPane.add(button_borrar_log, Integer.valueOf(1));

        button_salir = new JButton("Salir");
        button_salir.setBounds(555, 490, 270, 35);
        button_salir.setBackground(Color.white);
        button_salir.setForeground(new Color(130, 15, 15));
        button_salir.setFont(new Font("Calibri", 1, 25));
        layeredPane.add(button_salir, Integer.valueOf(1));
        button_salir.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(600,600,210,20);
        label_footer.setForeground(Color.white);
        layeredPane.add(label_footer, Integer.valueOf(1));

        setContentPane(layeredPane);

        this.setBounds(0,0,1400,1400);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button_inicio){
            new EleccionCartas().setVisible(true);
        }
        if (e.getSource() == button_leer_log){
            new Historial().setVisible(true);
        }
        if (e.getSource() == button_salir){
            new LogIn().setVisible(true);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }
}
