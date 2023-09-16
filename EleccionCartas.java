import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class EleccionCartas extends JFrame implements ActionListener {
    private JLabel label_wallpaper, label_title, label_footer;
    private JTextArea textArea_loading;
    private JButton button_elfF, button_elfM, button_humanF, button_humanM, button_orcF, button_orcM, button_start, button_enter;
    private JButton[] buttons = new JButton[6];
    public static int arr_chracter1[], arr_chracter2[];
    private int turno = 1;
    int validacion = 6;
    public EleccionCartas(){
        arr_chracter1 = new int[3];
        arr_chracter2 = new int[3];

        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Eleccion de cartas");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper.jpg"))).getImage());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1400,1400));

        ImageIcon wallpaper_logo = new ImageIcon("images\\wallpaper.jpg");
        Icon icono = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(1400,700,Image.SCALE_DEFAULT));
        label_wallpaper = new JLabel(icono);
        label_wallpaper.setBounds(0,0,1400,700);
        this.repaint();
        layeredPane.add(label_wallpaper, Integer.valueOf(0));

        label_title = new JLabel("ELECCION");
        label_title.setBounds(550,30,300,30);
        label_title.setForeground(new Color(130, 15, 15));
        label_title.setFont(new Font("Tahoma",1,35));
        layeredPane.add(label_title, Integer.valueOf(1));

        textArea_loading = new JTextArea();
        textArea_loading.setText(LogIn.user + " ELIJA UNA CARTA...");
        textArea_loading.setEditable(false);
        textArea_loading.setBounds(40,90,900,30);
        textArea_loading.setForeground(new Color(130,15,78));
        textArea_loading.setFont(new Font("Britannic Bold",4,27));
        layeredPane.add(textArea_loading, Integer.valueOf(1));

        button_enter = new JButton("ENTER");
        button_enter.setBounds(1050,90,100,30);
        button_enter.setBackground(Color.white);
        button_enter.setForeground(new Color(135,15,15));
        button_enter.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_enter, Integer.valueOf(1));
        button_enter.addActionListener(this);

        button_elfF = new JButton(new ImageIcon("images\\icon_elf_f.png"));
        button_elfF.setBounds(150,150,170,155);
        button_elfF.setBackground(Color.BLACK);
        this.repaint();
        layeredPane.add(button_elfF, Integer.valueOf(1));
        buttons[0] = button_elfF;
        button_elfF.addActionListener(this);

        button_elfM = new JButton(new ImageIcon("images\\icon_elf_m.png"));
        button_elfM.setBounds(150,370,170,155);
        button_elfM.setBackground(Color.BLACK);
        this.repaint();
        layeredPane.add(button_elfM, Integer.valueOf(1));
        buttons[1] = button_elfM;
        button_elfM.addActionListener(this);

        button_humanM = new JButton(new ImageIcon("images\\icon_human_m.png"));
        button_humanM.setBounds(550,150,170,155);
        button_humanM.setBackground(Color.BLACK);
        this.repaint();
        layeredPane.add(button_humanM, Integer.valueOf(1));
        buttons[2] = button_humanM;
        button_humanM.addActionListener(this);

        button_humanF = new JButton(new ImageIcon("images\\icon_human_f.png"));
        button_humanF.setBounds(550,370,170,155);
        button_humanF.setBackground(Color.BLACK);
        this.repaint();
        layeredPane.add(button_humanF, Integer.valueOf(1));
        buttons[3] = button_humanF;
        button_humanF.addActionListener(this);

        button_orcF = new JButton(new ImageIcon("images\\icon_orc_f.png"));
        button_orcF.setBounds(950,150,170,155);
        button_orcF.setBackground(Color.BLACK);
        this.repaint();
        layeredPane.add(button_orcF, Integer.valueOf(1));
        buttons[4] = button_orcF;
        button_orcF.addActionListener(this);

        button_orcM = new JButton(new ImageIcon("images\\icon_orc_m.png"));
        button_orcM.setBounds(950,370,170,155);
        button_orcM.setBackground(Color.BLACK);
        this.repaint();
        layeredPane.add(button_orcM, Integer.valueOf(1));
        buttons[5] = button_orcM;
        button_orcM.addActionListener(this);

        button_start = new JButton("Comenzar");
        button_start.setBounds(950,590,270,35);
        button_start.setBackground(Color.white);
        button_start.setForeground(new Color(130,15,15));
        button_start.setFont(new Font("Calibri",1,25));
        layeredPane.add(button_start, Integer.valueOf(1));
        button_start.setEnabled(false);
        button_start.addActionListener(this);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(575,610,210,20);
        label_footer.setForeground(Color.white);
        layeredPane.add(label_footer, Integer.valueOf(1));

        setContentPane(layeredPane);

        this.setBounds(0,0,1400,1400);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
    }

    int i = 0, j = 0;

    public void actionPerformed(ActionEvent e){
        JButton source = (JButton) e.getSource();
        if (source == button_start){
            Elfo elf_m = new Elfo("Elfo","Thandor","Thor","152",100,7,3,8,10,6);
            Elfo elf_f = new Elfo("Elfo", "Elionor", "Lion","275",100,9,4,3,7,10);
            Humano human_m = new Humano("Humano","Kellan", "Storm", "39",100,10,2,6,9,4);
            Humano human_f = new Humano("Humano","Astrid", "Dri", "22",100,5,5,4,2,8);
            Orco orc_m = new Orco("Orco", "Grommash", "Hacha de guerra", "280",100,1,2,10,8,9);
            Orco orc_f = new Orco("Orco", "Maela", "Garra sangrienta","164",100,2,3,7,6,8);

            Batalla batalla = new Batalla(elf_m, elf_f, human_m, human_f, orc_m, orc_f);
            batalla.setVisible(true);
        }

        if (turno == 1 && source.isEnabled()){
            if (source == button_elfF || source == button_elfM || source == button_humanM || source == button_humanF || source == button_orcF || source == button_orcM){
                source.setEnabled(false);
                arr_chracter1[i] = Arrays.asList(buttons).indexOf(source);
                i ++;
                turno = 2;
                validacion --;
                textArea_loading.setText("PRESIONE 'ENTER' PARA SIMULAR EL TURNO DEL JUGADOR 2...");
            }
        }
        else if (turno == 2 && (source == button_enter)){
            List<Integer> cartasDisponibles = new ArrayList<>();
            for (int k = 0; k < buttons.length; k++){
                if (buttons[k].isEnabled()){
                    cartasDisponibles.add(k);
                }
            }
            if (!cartasDisponibles.isEmpty()){
                Random random = new Random();
                int cartaElegida = cartasDisponibles.get(random.nextInt(cartasDisponibles.size()));

                buttons[cartaElegida].setEnabled(false);
                arr_chracter2[j] = cartaElegida;
                j++;
                turno = 1;
                validacion --;
                if (validacion != 0){
                    textArea_loading.setText(LogIn.user + " ELIJA UNA CARTA...");
                }
                else {
                    textArea_loading.setText("PRESIONE COMENZAR");
                }
            }
            /*if (source == button_elfF || source == button_elfM || source == button_humanM || source == button_humanF || source == button_orcF || source == button_orcM){
                source.setEnabled(false);
                arr_chracter2[j] = Arrays.asList(buttons).indexOf(source);
                j ++;
                turno = 1;
                validacion --;
            }*/
        }

        if (validacion == 0) {
            button_start.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EleccionCartas().setVisible(true);
            }
        });
    }
}
