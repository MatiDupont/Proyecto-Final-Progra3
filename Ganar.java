import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Ganar extends JFrame implements ActionListener {
    private JLabel label_wallpaper, label_footer;
    private JTextArea textArea_title;
    private JButton button_card1, button_card2, button_card3, button_menu;
    public Ganar(){
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ganaste");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper.jpg"))).getImage());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1400,1400));

        ImageIcon wallpaper_logo = new ImageIcon("images\\wallpaper6.png");
        Icon icono = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(1400,700,Image.SCALE_DEFAULT));
        label_wallpaper = new JLabel(icono);
        label_wallpaper.setBounds(0,0,1400,700);
        this.repaint();
        layeredPane.add(label_wallpaper, Integer.valueOf(0));

        textArea_title = new JTextArea();
        textArea_title.setText("FELICIDADES, ERES MERECEDOR DEL TRONO DE HIERRO");
        textArea_title.setEditable(false);
        textArea_title.setBounds(300,30,700,30);
        textArea_title.setForeground(new Color(130,15,15));
        textArea_title.setFont(new Font("Britannic Bold",4,27));
        layeredPane.add(textArea_title, Integer.valueOf(1));

        /*int[] arrChracter1 = EleccionCartas.arr_chracter1;
        String[] image = new String[3];

        for (int i = 0; i < arrChracter1.length; i++){
            if (arrChracter1[i] == 0){
                image[i] = "images\\icon_elf_f.png";
            } else if (arrChracter1[i] == 1) {
                image[i] = "images\\icon_elf_m.png";
            } else if (arrChracter1[i] == 2) {
                image[i] = "images\\icon_human_m.png";
            } else if (arrChracter1[i] == 3) {
                image[i] = "images\\icon_human_f.png";
            } else if (arrChracter1[i] == 4) {
                image[i] = "images\\icon_orc_f.png";
            } else if (arrChracter1[i] == 5) {
                image[i] = "images\\icon_orc_m.png";
            }
            i++;
        }*/
        button_card1 = new JButton();//new ImageIcon(image[0]));
        button_card1.setBounds(35,150,270,370);
        button_card1.setBackground(Color.BLACK);
        layeredPane.add(button_card1, Integer.valueOf(1));

        button_card2 = new JButton();//new ImageIcon(image[0]));
        button_card2.setBounds(500,150,270,370);
        button_card2.setBackground(Color.BLACK);
        layeredPane.add(button_card2, Integer.valueOf(1));

        button_card3 = new JButton();//new ImageIcon(image[0]));
        button_card3.setBounds(980,150,270,370);
        button_card3.setBackground(Color.BLACK);
        layeredPane.add(button_card3, Integer.valueOf(1));

        button_menu = new JButton("Volver al menu");
        button_menu.setBounds(580,600,200,30);
        button_menu.setBackground(Color.white);
        button_menu.setForeground(new Color(130,15,15));
        button_menu.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_menu, Integer.valueOf(1));
        button_menu.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(600,650,210,20);
        label_footer.setForeground(Color.white);
        layeredPane.add(label_footer, Integer.valueOf(1));

        setContentPane(layeredPane);

        this.setBounds(0,0,1400,1400);
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
                new Ganar().setVisible(true);
            }
        });
    }
}
