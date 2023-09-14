import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Perder extends JFrame implements ActionListener {
    private JLabel label_wallpaper, label_footer;
    private JButton button_menu;
    public Perder(){
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Perdiste");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper.jpg"))).getImage());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800,500));

        ImageIcon wallpaper_logo = new ImageIcon("images\\wallpaper5.jpg");
        Icon icono = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(800,500,Image.SCALE_DEFAULT));
        label_wallpaper = new JLabel(icono);
        label_wallpaper.setBounds(0,0,800,500);
        this.repaint();
        layeredPane.add(label_wallpaper, Integer.valueOf(0));

        button_menu = new JButton("Volver al menu");
        button_menu.setBounds(310,410,200,30);
        button_menu.setBackground(Color.white);
        button_menu.setForeground(new Color(130,15,15));
        button_menu.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_menu, Integer.valueOf(1));
        button_menu.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(320,460,210,20);
        label_footer.setForeground(Color.white);
        layeredPane.add(label_footer, Integer.valueOf(1));

        setContentPane(layeredPane);

        this.setBounds(0,0,800,500);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button_menu){
            new Inicio().setVisible(true);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Perder().setVisible(true);
            }
        });
    }
}
