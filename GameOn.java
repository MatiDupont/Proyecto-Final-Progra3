import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOn extends JPanel implements ActionListener {
    private JLabel label_titulo, label_footer;
    private JButton button_start, button_loadData;
    private BufferedImage backgroundImage;
    private LogIn panel_LogIn;
    private JScrollPane scrollPane;
    public GameOn(){
        setLayout(null);
        cargarImagenDeFondo();
        iniciarComponentes();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button_start.doClick();
                }
            }
        });

        setFocusable(true);
        requestFocus();
    }
    private void cargarImagenDeFondo() {
        try {
            backgroundImage = ImageIO.read(new File("images\\wallpaper7.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 1280, 700, this);
        }
    }

    private void iniciarComponentes(){
        label_titulo = new JLabel("BATTLE ROYALE");
        label_titulo.setBounds(520,70,300,30);
        label_titulo.setForeground(Color.white);
        label_titulo.setFont(new Font("Tahoma",1,35));
        add(label_titulo);

        button_start = new JButton("Comenzar");
        button_start.setBounds(1080,560,150,30);
        button_start.setBackground(Color.white);
        button_start.setForeground(new Color(0,0,0));
        button_start.setFont(new Font("Calibri",1,18));
        button_start.addActionListener(this);
        button_start.setEnabled(false);
        add(button_start);

        button_loadData = new JButton("Cargar datos");
        button_loadData.setBounds(900,560,150,30);
        button_loadData.setBackground(Color.white);
        button_loadData.setForeground(Color.BLACK);
        button_loadData.setFont(new Font("Calibri",1,18));
        button_loadData.addActionListener(this);
        add(button_loadData);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(580,610,210,20);
        label_footer.setForeground(Color.white);
        add(label_footer);

        panel_LogIn = new LogIn();
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0,0,1400,1400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VentanaEmergente ventanaEmergente = new VentanaEmergente(this);
        if (e.getSource() == button_start){
            add(scrollPane);
            label_titulo.setVisible(false);
            button_loadData.setVisible(false);
            button_start.setVisible(false);
            label_footer.setVisible(false);
            definirPanel(panel_LogIn);
            panel_LogIn.getTextField_usuario().requestFocus();
        }
        if (e.getSource() == button_loadData) {
            ventanaEmergente.setVisible(true);
        }
    }

    private void definirPanel(LogIn panel){
        scrollPane.setViewportView(panel);
    }

    public JButton getButton_start() {
        return button_start;
    }

    public JButton getButton_loadData() {
        return button_loadData;
    }
}
