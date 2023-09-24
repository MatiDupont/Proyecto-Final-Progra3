import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Ganar extends JFrame implements ActionListener {
    private Elfo elf_m, elf_f;
    private Humano human_m, human_f;
    private Orco orc_m, orc_f;
    private JLabel label_wallpaper, label_footer;
    private JTextArea textArea_title;
    private JButton button_card1, button_card2, button_card3, button_menu;
    public static int[] arrChracter1;
    public Ganar(Elfo elfo1, Elfo elfo2, Humano humano1, Humano humano2, Orco orco1, Orco orco2){
        this.elf_m = elfo1;
        this.elf_f = elfo2;
        this.human_m = humano1;
        this.human_f = humano2;
        this.orc_m = orco1;
        this.orc_f = orco2;

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

        arrChracter1 = EleccionCartas.arr_chracter1;
        String[] image = new String[3];

        for (int i = 0; i < arrChracter1.length; i++){
            if (arrChracter1[i] == 0){
                image[i] = elf_f.getImage();
            } else if (arrChracter1[i] == 1) {
                image[i] = elf_m.getImage();
            } else if (arrChracter1[i] == 2) {
                image[i] = human_m.getImage();
            } else if (arrChracter1[i] == 3) {
                image[i] = human_f.getImage();
            } else if (arrChracter1[i] == 4) {
                image[i] = orc_f.getImage();
            } else if (arrChracter1[i] == 5) {
                image[i] = orc_m.getImage();
            }
        }
        int newWidth = 270;
        int newHeight = 370;

        Personaje personaje = null;

        personaje = devolverPersonaje(0);
        ImageIcon originalIcon = new ImageIcon(image[0]);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        button_card1 = new JButton(resizedIcon);
        button_card1.setBounds(35,150,270,370);
        button_card1.setBackground(Color.BLACK);
        layeredPane.add(button_card1, Integer.valueOf(1));
        button_card1.addActionListener(this);
        if (personaje.getSalud() > 0){
            button_card1.setBackground(new Color(0,255,0));
        }
        else {
            button_card1.setBackground(new Color(255,0,0));
        }

        personaje = devolverPersonaje(1);
        ImageIcon originalIcon2 = new ImageIcon(image[1]);
        Image originalImage2 = originalIcon2.getImage();
        Image resizedImage2 = originalImage2.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        button_card2 = new JButton(resizedIcon2);
        button_card2.setBounds(500,150,270,370);
        button_card2.setBackground(Color.BLACK);
        layeredPane.add(button_card2, Integer.valueOf(1));
        button_card2.addActionListener(this);
        if (personaje.getSalud() > 0){
            button_card2.setBackground(new Color(0,255,0));
        }
        else {
            button_card2.setBackground(new Color(255,0,0));
        }

        personaje = devolverPersonaje(2);
        ImageIcon originalIcon3 = new ImageIcon(image[2]);
        Image originalImage3 = originalIcon3.getImage();
        Image resizedImage3 = originalImage3.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        button_card3 = new JButton(resizedIcon3);
        button_card3.setBounds(980,150,270,370);
        button_card3.setBackground(Color.BLACK);
        layeredPane.add(button_card3, Integer.valueOf(1));
        button_card3.addActionListener(this);
        if (personaje.getSalud() > 0){
            button_card3.setBackground(new Color(0,255,0));
        }
        else {
            button_card3.setBackground(new Color(255,0,0));
        }

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
        if (e.getSource() == button_card1) {
            infoMessage(0);
        }
        if (e.getSource() == button_card2) {
            infoMessage(1);
        }
        if (e.getSource() == button_card3) {
            infoMessage(2);
        }
        if (e.getSource() == button_menu){
            new Inicio().setVisible(true);
        }
    }
    private void infoMessage(int param) {
        Personaje personaje = null;

        personaje = devolverPersonaje(param);

        String chracter1Info = "Nombre: " + personaje.getNombre() + "\n\n";
        chracter1Info += "           Caracteristicas" + "\n\n";
        chracter1Info += "Apodo: " + personaje.getApodo() + "\n";
        chracter1Info += "Edad: " + personaje.getEdad() + "\n";
        chracter1Info += "Raza: " + personaje.getRaza() + "\n";
        chracter1Info += "Salud: " + personaje.getSalud() + "\n";
        chracter1Info += "Velocidad: " + personaje.getVelocidad() + "\n";
        chracter1Info += "Destreza: " + personaje.getDestreza() + "\n";
        chracter1Info += "Fuerza: " + personaje.getFuerza() + "\n";
        chracter1Info += "Nivel: " + personaje.getNivel() + "\n";
        chracter1Info += "Armadura: " + personaje.getArmadura() + "\n";
        chracter1Info += "Efectividad de Disparo: " + personaje.getEfectividadDisparo();

        JOptionPane.showMessageDialog(this, chracter1Info, "Información del Personaje", JOptionPane.INFORMATION_MESSAGE);
    }

    private Personaje devolverPersonaje (int param){
        Personaje personaje = null;
        if (arrChracter1[param] == 0) {
            personaje = elf_f;
        } else if (arrChracter1[param] == 1) {
            personaje = elf_m;
        } else if (arrChracter1[param] == 2) {
            personaje = human_m;
        } else if (arrChracter1[param] == 3) {
            personaje = human_f;
        } else if (arrChracter1[param] == 4) {
            personaje = orc_f;
        } else if (arrChracter1[param] == 5) {
            personaje = orc_m;
        }

        return personaje;
    }
}
