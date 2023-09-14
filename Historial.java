import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Historial extends JFrame {
    private JLabel label_wallpaper, label_titulo, label_footer;
    private JTextPane textPane_resume;
    private JScrollPane scrollPane;
    String user;
    public Historial(){
        user = LogIn.user;
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Historial del jugador - " + user);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper.jpg"))).getImage());

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
        textPane_resume.setText("                                      LOG");
        textPane_resume.setFont(new Font("Arial",1,25));
        textPane_resume.setEnabled(false);
        scrollPane = new JScrollPane(textPane_resume);
        scrollPane.setBounds(100,120,600,270);
        layeredPane.add(scrollPane, Integer.valueOf(1));

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(320,440,210,20);
        label_footer.setForeground(Color.white);
        layeredPane.add(label_footer, Integer.valueOf(1));

        setContentPane(layeredPane);

        this.setBounds(0,0,800,500);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Historial().setVisible(true);
            }
        });
    }
}
