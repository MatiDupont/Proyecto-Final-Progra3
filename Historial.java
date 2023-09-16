import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Historial extends JFrame {
    private Elfo elf_m, elf_f;
    private Humano human_m, human_f;
    private Orco orc_m, orc_f;
    private JLabel label_wallpaper, label_titulo, label_footer;
    private JTextPane textPane_resume;
    private JScrollPane scrollPane;
    String user;
    public static String gameplay = "Se generaron 6 personajes:\n" +
            "--------Personaje 1 Jugador 1-------\n" +
            "nombre: Uhov, Tipo : Troll, Apodo:Chanerium , Velocidad: 8, …etc\n" +
            "…..\n" +
            "--------Personaje 2 Jugador 1 -------\n" +
            "nombre: Rand, Tipo : Wizard, Apodo:Trumer , Velocidad: 1, …etc\n" +
            "…\n" +
            "\n" +
            "Chanerium ataca a XXXX y le quita 20 de salud. XXXX queda con 80 de salud.\n" +
            "XXXX ataca a Chanerium y le quita 12 de saluds. Chanerium queda con 88 de salud.\n" +
            "Chanerium ataca a XXXX y le quita ….\n" +
            "….\n" +
            "Muere XXXX.\n" +
            "Chanerium gana 10 de salud como premio, quedando con 90 de salud.\n" +
            "Ronda 2\n" +
            "Empieza atacando Jugador 2 por perder la ronda 1.\n" +
            "El sistema eligió al personaje Trumer del jugador 1 y al personaje YYYY del Jugador 2 para\n" +
            "que se enfrenten en esta ronda.\n" +
            "YYYY ataca a Trumer y le quita 2 de salud. Trumer queda con 98 de salud\n" +
            "….\n" +
            "Ronda X\n" +
            "…..\n" +
            "Gana Jugador 2, le quedo/aron vivos los sgtes. personajes:\n" +
            "…………\n" +
            "Felicitaciones Jugador 2 , las fuerzas mágicas del universo luz te abrazan!\n" +
            "Fin.\n";
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
        textPane_resume.setText(gameplay);
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
