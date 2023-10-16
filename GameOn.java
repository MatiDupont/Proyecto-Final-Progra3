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
    private JButton button_start;
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
        button_start.setBounds(1080,560,110,30);
        button_start.setBackground(Color.white);
        button_start.setForeground(new Color(0,0,0));
        button_start.setFont(new Font("Calibri",1,18));
        button_start.addActionListener(this);
        add(button_start);

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
        if (e.getSource() == button_start){
            add(scrollPane);
            label_titulo.setVisible(false);
            button_start.setVisible(false);
            label_footer.setVisible(false);
            definirPanel(panel_LogIn);
            panel_LogIn.getTextField_usuario().requestFocus();
        }
    }

    private void definirPanel(LogIn panel){
        scrollPane.setViewportView(panel);
    }
}
