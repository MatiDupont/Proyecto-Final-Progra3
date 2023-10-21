import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Inicio extends JPanel implements ActionListener {
    private JLabel label_menu, label_footer;
    private JButton button_inicio, button_leer_log, button_salir;
    private BufferedImage backgroundImage;
    private JScrollPane scrollPane;
    private Historial historial;
    private LogIn panel_LogIn;
    private EleccionCartas panel_EleccionCartas;
    String user;
    public Inicio() {
        user = LogIn.user;
        setLayout(null);
        cargarImagenDeFondo();
        iniciarComponentes();
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button_inicio){
            panel_EleccionCartas = new EleccionCartas();
            add(scrollPane);
            label_menu.setVisible(false);
            button_inicio.setVisible(false);
            button_leer_log.setVisible(false);
            button_salir.setVisible(false);
            label_footer.setVisible(false);
            definirPanel(panel_EleccionCartas);
        }
        if (e.getSource() == button_leer_log){
            if (historial.isHistorialVacio()){
                historial.getTextPane_resume().setText("Aun no hay registros de batallas.");
            }
            else {
                historial.getTextPane_resume().setText(historial.imprimirDatosTabla());
            }
            historial.setVisible(true);

        }
        if (e.getSource() == button_salir) {
            panel_LogIn = new LogIn();
            add(scrollPane);
            label_menu.setVisible(false);
            button_inicio.setVisible(false);
            button_leer_log.setVisible(false);
            button_salir.setVisible(false);
            label_footer.setVisible(false);
            definirPanel(panel_LogIn);
        }
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

    private void iniciarComponentes() {
        label_menu = new JLabel("MENU DE OPCIONES");
        label_menu.setBounds(505, 140, 370, 30);
        label_menu.setForeground(new Color(130, 15, 15));
        label_menu.setFont(new Font("Tahoma", 1, 35));
        add(label_menu);

        button_inicio = new JButton("Iniciar partida");
        button_inicio.setBounds(555, 280, 270, 35);
        button_inicio.setBackground(Color.white);
        button_inicio.setForeground(new Color(130, 15, 15));
        button_inicio.setFont(new Font("Calibri", 1, 25));
        add(button_inicio);
        button_inicio.addActionListener(this);

        button_leer_log = new JButton("Historial");
        button_leer_log.setBounds(555, 350, 270, 35);
        button_leer_log.setBackground(Color.white);
        button_leer_log.setForeground(new Color(130, 15, 15));
        button_leer_log.setFont(new Font("Calibri", 1, 25));
        add(button_leer_log);
        button_leer_log.addActionListener(this);

        button_salir = new JButton("Salir");
        button_salir.setBounds(555, 420, 270, 35);
        button_salir.setBackground(Color.white);
        button_salir.setForeground(new Color(130, 15, 15));
        button_salir.setFont(new Font("Calibri", 1, 25));
        add(button_salir);
        button_salir.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(600,600,210,20);
        label_footer.setForeground(Color.white);
        add(label_footer);

        historial = new Historial();
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0,0,1400,1400);
    }

    private void definirPanel(LogIn panel) {
        scrollPane.setViewportView(panel);
    }

    private void definirPanel(EleccionCartas panel) {
        scrollPane.setViewportView(panel);
    }
}