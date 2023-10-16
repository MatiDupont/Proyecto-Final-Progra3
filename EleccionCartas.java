import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

public class EleccionCartas extends JPanel implements ActionListener {
    private JLabel label_title, label_footer;
    private BufferedImage backgroundImage;
    private JTextArea textArea_loading;
    private JButton button_elfF, button_elfM, button_humanF, button_humanM, button_orcF, button_orcM, button_start, button_enter;
    private JButton[] buttons = new JButton[6];
    private JScrollPane scrollPane;
    private Batalla panel_Batalla;
    public static int arr_chracter1[], arr_chracter2[];
    private int turno = 1;
    int validacion = 6;
    public EleccionCartas(){
        arr_chracter1 = new int[3];
        arr_chracter2 = new int[3];

        setLayout(null);
        cargarImagenDeFondo();
        iniciarComponentes();
    }

    int i = 0, j = 0;

    public void actionPerformed(ActionEvent e){
        JButton source = (JButton) e.getSource();
        if (source == button_start){
            Elfo elf_m = new Elfo("Elfo","Thandor","Thor","152",100,7,3,8,10,6, "images\\elf_male.png");
            Elfo elf_f = new Elfo("Elfo", "Elionor", "Lion","275",100,9,4,3,7,10, "images\\elf_female.png");
            Humano human_m = new Humano("Humano","Kellan", "Storm", "39",100,10,2,6,9,4, "images\\human_male.png");
            Humano human_f = new Humano("Humano","Astrid", "Dri", "22",100,5,5,4,2,8, "images\\human_female.png");
            Orco orc_m = new Orco("Orco", "Grommash", "Hacha de guerra", "280",100,1,2,10,8,9, "images\\orc_male.png");
            Orco orc_f = new Orco("Orco", "Maela", "Garra sangrienta","164",100,2,3,7,6,8, "images\\orc_female.png");

            panel_Batalla = new Batalla(elf_m, elf_f, human_m, human_f, orc_m, orc_f);
            add(scrollPane);
            label_title.setVisible(false);
            textArea_loading.setVisible(false);
            button_enter.setVisible(false);
            button_elfF.setVisible(false);
            button_elfM.setVisible(false);
            button_humanM.setVisible(false);
            button_humanF.setVisible(false);
            button_orcF.setVisible(false);
            button_orcM.setVisible(false);
            button_start.setVisible(false);
            label_footer.setVisible(false);
            definirPanel(panel_Batalla);
        }

        if (turno == 1 && source.isEnabled()){
            if (source == button_elfF || source == button_elfM || source == button_humanM || source == button_humanF || source == button_orcF || source == button_orcM){
                source.setEnabled(false);
                arr_chracter1[i] = Arrays.asList(buttons).indexOf(source);
                i ++;
                turno = 2;
                validacion --;
                textArea_loading.setText("PRESIONE 'ENTER' PARA SIMULAR EL TURNO DEL JUGADOR 2...");
                button_enter.setEnabled(true);
                //getRootPane().setDefaultButton(button_enter);
                addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            button_enter.doClick();
                        }
                    }
                });

                setFocusable(true);
                requestFocus();
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
            button_enter.setEnabled(false);
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
            //getRootPane().setDefaultButton(button_start);
        }
    }

    private void cargarImagenDeFondo() {
        try {
            backgroundImage = ImageIO.read(new File("images\\wallpaper.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage,0,0,1400,700,this);
        }
    }

    private void iniciarComponentes(){
        label_title = new JLabel("ELECCION");
        label_title.setBounds(550,30,300,30);
        label_title.setForeground(new Color(130, 15, 15));
        label_title.setFont(new Font("Tahoma",1,35));
        add(label_title);

        textArea_loading = new JTextArea();
        textArea_loading.setText(LogIn.user + " ELIJA UNA CARTA...");
        textArea_loading.setEditable(false);
        textArea_loading.setBounds(40,90,900,30);
        textArea_loading.setForeground(new Color(130,15,78));
        textArea_loading.setFont(new Font("Britannic Bold",4,27));
        add(textArea_loading);

        button_enter = new JButton("ENTER");
        button_enter.setBounds(1050,90,100,30);
        button_enter.setBackground(Color.white);
        button_enter.setForeground(new Color(135,15,15));
        button_enter.setFont(new Font("Calibri",1,18));
        button_enter.setEnabled(false);
        button_enter.addActionListener(this);
        add(button_enter);

        button_elfF = new JButton(new ImageIcon("images\\icon_elf_f.png"));
        button_elfF.setBounds(150,150,170,155);
        button_elfF.setBackground(Color.BLACK);
        this.repaint();
        add(button_elfF);
        buttons[0] = button_elfF;
        button_elfF.addActionListener(this);

        button_elfM = new JButton(new ImageIcon("images\\icon_elf_m.png"));
        button_elfM.setBounds(150,370,170,155);
        button_elfM.setBackground(Color.BLACK);
        this.repaint();
        add(button_elfM);
        buttons[1] = button_elfM;
        button_elfM.addActionListener(this);

        button_humanM = new JButton(new ImageIcon("images\\icon_human_m.png"));
        button_humanM.setBounds(550,150,170,155);
        button_humanM.setBackground(Color.BLACK);
        this.repaint();
        add(button_humanM);
        buttons[2] = button_humanM;
        button_humanM.addActionListener(this);

        button_humanF = new JButton(new ImageIcon("images\\icon_human_f.png"));
        button_humanF.setBounds(550,370,170,155);
        button_humanF.setBackground(Color.BLACK);
        this.repaint();
        add(button_humanF);
        buttons[3] = button_humanF;
        button_humanF.addActionListener(this);

        button_orcF = new JButton(new ImageIcon("images\\icon_orc_f.png"));
        button_orcF.setBounds(950,150,170,155);
        button_orcF.setBackground(Color.BLACK);
        this.repaint();
        add(button_orcF);
        buttons[4] = button_orcF;
        button_orcF.addActionListener(this);

        button_orcM = new JButton(new ImageIcon("images\\icon_orc_m.png"));
        button_orcM.setBounds(950,370,170,155);
        button_orcM.setBackground(Color.BLACK);
        this.repaint();
        add(button_orcM);
        buttons[5] = button_orcM;
        button_orcM.addActionListener(this);

        button_start = new JButton("Comenzar");
        button_start.setBounds(950,590,270,35);
        button_start.setBackground(Color.white);
        button_start.setForeground(new Color(130,15,15));
        button_start.setFont(new Font("Calibri",1,25));
        button_start.setEnabled(false);
        button_start.addActionListener(this);
        add(button_start);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(575,610,210,20);
        label_footer.setForeground(Color.white);
        add(label_footer);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(0,0,1400,1400);
    }

    private void definirPanel(Batalla panel) {
        scrollPane.setViewportView(panel);
    }
}