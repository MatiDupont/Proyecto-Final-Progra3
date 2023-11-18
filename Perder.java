import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Perder extends JPanel implements ActionListener {
    private JLabel label_footer;
    private JButton button_menu;
    private JScrollPane scrollPane;
    private Inicio panel_Inicio;
    private BufferedImage backgroundImage;

    public Perder(){
        setLayout(null);
        cargarImagenDeFondo();
        iniciarComponentes();
    }

    private void cargarImagenDeFondo() {
        try {
            backgroundImage = ImageIO.read(new File("images\\wallpaper5.jpg"));
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
        button_menu = new JButton("Volver al menu");
        button_menu.setBounds(560,550,200,30);
        button_menu.setForeground(new Color(130,15,15));
        button_menu.setFont(new Font("Calibri",1,18));
        add(button_menu);
        button_menu.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(590,650,210,20);
        label_footer.setForeground(Color.white);
        add(label_footer);

        panel_Inicio = new Inicio();
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0,0,1400,1400);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button_menu){
            add(scrollPane);
            button_menu.setVisible(false);
            label_footer.setVisible(false);
            definirPanel(panel_Inicio);
        }
    }

    private void definirPanel(Inicio panel) {
        scrollPane.setViewportView(panel);
    }
}