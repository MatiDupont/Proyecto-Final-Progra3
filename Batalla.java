import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Batalla extends JFrame implements ActionListener {
    private Elfo elf_m, elf_f;
    private Humano human_m, human_f;
    private Orco orc_m, orc_f;
    private JLabel label_wallpaper, label_title, label_player1, label_player2, label_round, label_vs, label_footer, label_arrC1, label_arrC2;
    private JButton button_chracter1, button_chracter2, button_attack, button_defense, button_attack2, button_defense2, button_V1, button_D1, button_F1, button_N1, button_A1, button_V2, button_D2, button_F2, button_N2, button_A2;
    public static int round = 1, turno;
    public static int[] arrCards1, arrCards2;
    private boolean chracter1WonRound = false, chracter2WonRound = false;
    private int total_ataques1 = 7, total_ataques2 = 7;
    JLayeredPane layeredPane = new JLayeredPane();
    public Batalla(Elfo elfo1, Elfo elfo2, Humano humano1, Humano humano2, Orco orco1, Orco orco2){
        this.elf_m = elfo1;
        this.elf_f = elfo2;
        this.human_m = humano1;
        this.human_f = humano2;
        this.orc_m = orco1;
        this.orc_f = orco2;

        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Batalla");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper.jpg"))).getImage());

        layeredPane.setPreferredSize(new Dimension(1400,1400));

        ImageIcon wallpaper_logo = new ImageIcon("images\\wallpaper.jpg");
        Icon icono = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(1400,700,Image.SCALE_DEFAULT));
        label_wallpaper = new JLabel(icono);
        label_wallpaper.setBounds(0,0,1400,700);
        this.repaint();
        layeredPane.add(label_wallpaper, Integer.valueOf(0));

        label_title = new JLabel("BATALLA");
        label_title.setBounds(570,30,300,30);
        label_title.setForeground(new Color(130,15,15));
        label_title.setFont(new Font("Tahoma",1,35));
        layeredPane.add(label_title, Integer.valueOf(1));

        int newWidth = 270;
        int newHeight = 370;

        label_player1 = new JLabel("PLAYER 1");
        label_player1.setBounds(210,100,200,40);
        label_player1.setForeground(Color.white);
        label_player1.setFont(new Font("Arial",1,30));
        layeredPane.add(label_player1, Integer.valueOf(1));

        label_player2 = new JLabel("PLAYER 2");
        label_player2.setBounds(910,100,200,40);
        label_player2.setForeground(Color.white);
        label_player2.setFont(new Font("Arial",1,30));
        layeredPane.add(label_player2, Integer.valueOf(1));

        Random random = new Random();

        int[] arrChracter1 = EleccionCartas.arr_chracter1;
        int[] arrChracter2 = EleccionCartas.arr_chracter2;

        ArrayList<Integer> values1 = new ArrayList<>();
        for (int i = 0; i < arrChracter1.length; i++){
            values1.add(arrChracter1[i]);
        }
        ArrayList<Integer> values2 = new ArrayList<>();
        for (int t = 0; t < arrChracter2.length; t++){
            values2.add(arrChracter2[t]);
        }

        arrCards1 = new int[3];
        arrCards2 = new int[3];

        for (int i = 0; i < arrChracter1.length; i++){
            int pos = random.nextInt(values1.size());
            arrCards1[i] = values1.get(pos);
            values1.remove(pos);
        }
        for (int t = 0; t < arrChracter2.length; t++){
            int pos = random.nextInt(values2.size());
            arrCards2[t] = values2.get(pos);
            values2.remove(pos);
        }

        String[] image = new String[3];

        for (int i = 0; i < arrChracter1.length; i++){
            if (arrCards1[i] == 0){
                image[i] = "images\\elf_female.png";
            } else if (arrCards1[i] == 1) {
                image[i] = "images\\elf_male.png";
            } else if (arrCards1[i] == 2) {
                image[i] = "images\\human_male.png";
            } else if (arrCards1[i] == 3) {
                image[i] = "images\\human_female.png";
            } else if (arrCards1[i] == 4) {
                image[i] = "images\\orc_female.png";
            } else if (arrCards1[i] == 5) {
                image[i] = "images\\orc_male.png";
            }
        }

        ImageIcon originalIcon = new ImageIcon(image[0]);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        button_chracter1 = new JButton(resizedIcon);
        button_chracter1.setBounds(150,150,270,370);
        button_chracter1.setBackground(Color.BLACK);
        layeredPane.add(button_chracter1, Integer.valueOf(1));
        button_chracter1.addActionListener(this);

        button_V1 = new JButton("V (+1)");
        button_V1.setBounds(40,150,100,50);
        button_V1.setBackground(new Color(0,0,0));
        button_V1.setForeground(new Color(130,15,15));
        button_V1.setFont(new Font("Calibri",1,18));
        button_V1.setEnabled(false);
        layeredPane.add(button_V1, Integer.valueOf(1));
        button_V1.addActionListener(this);

        button_D1 = new JButton("D (+1)");
        button_D1.setBounds(40,230,100,50);
        button_D1.setBackground(new Color(0,0,0));
        button_D1.setForeground(new Color(130,15,15));
        button_D1.setFont(new Font("Calibri",1,18));
        button_D1.setEnabled(false);
        layeredPane.add(button_D1, Integer.valueOf(1));

        button_F1 = new JButton("F (+1)");
        button_F1.setBounds(40,310,100,50);
        button_F1.setBackground(new Color(0,0,0));
        button_F1.setForeground(new Color(130,15,15));
        button_F1.setFont(new Font("Calibri",1,18));
        button_F1.setEnabled(false);
        layeredPane.add(button_F1, Integer.valueOf(1));

        button_N1 = new JButton("N (+1)");
        button_N1.setBounds(40,390,100,50);
        button_N1.setBackground(new Color(0,0,0));
        button_N1.setForeground(new Color(130,15,15));
        button_N1.setFont(new Font("Calibri",1,18));
        button_N1.setEnabled(false);
        layeredPane.add(button_N1, Integer.valueOf(1));

        button_A1 = new JButton("A (+1)");
        button_A1.setBounds(40,470,100,50);
        button_A1.setBackground(new Color(0,0,0));
        button_A1.setForeground(new Color(130,15,15));
        button_A1.setFont(new Font("Calibri",1,18));
        button_A1.setEnabled(false);
        layeredPane.add(button_A1, Integer.valueOf(1));

        Random random1 = new Random();
        turno = random1.nextInt(2) + 1;

        button_attack = new JButton("Attack (7)");
        button_attack.setBounds(225,550,120,40);
        button_attack.setBackground(Color.white);
        button_attack.setForeground(new Color(135,15,15));
        button_attack.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_attack, Integer.valueOf(1));
        button_attack.addActionListener(this);

        /*button_defense = new JButton("Defense");
        button_defense.setBounds(320,550,100,40);
        button_defense.setBackground(Color.white);
        button_defense.setForeground(new Color(135,15,15));
        button_defense.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_defense, Integer.valueOf(1));*/

        label_round = new JLabel("ROUND - " + round + " -");
        label_round.setBounds(505,150,270,40);
        label_round.setForeground(Color.white);
        label_round.setFont(new Font("Times New Roman",2,50));
        layeredPane.add(label_round, Integer.valueOf(1));

        label_vs = new JLabel("VS");
        label_vs.setBounds(600,300,100,70);
        label_vs.setForeground(Color.white);
        label_vs.setFont(new Font("Lucida Console",1,70));
        layeredPane.add(label_vs, Integer.valueOf(1));

        for (int i = 0; i < arrChracter2.length; i++){
            if (arrCards2[i] == 0){
                image[i] = "images\\elf_female.png";
            } else if (arrCards2[i] == 1) {
                image[i] = "images\\elf_male.png";
            } else if (arrCards2[i] == 2) {
                image[i] = "images\\human_male.png";
            } else if (arrCards2[i] == 3) {
                image[i] = "images\\human_female.png";
            } else if (arrCards2[i] == 4) {
                image[i] = "images\\orc_female.png";
            } else if (arrCards2[i] == 5) {
                image[i] = "images\\orc_male.png";
            }
        }

        ImageIcon originalIcon2 = new ImageIcon(image[0]);
        Image originalImage2 = originalIcon2.getImage();
        Image resizedImage2 = originalImage2.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        button_chracter2 = new JButton(resizedIcon2);
        button_chracter2.setBounds(850,150,270,370);
        button_chracter2.setBackground(Color.BLACK);
        layeredPane.add(button_chracter2, Integer.valueOf(1));
        button_chracter2.addActionListener(this);

        button_V2 = new JButton("V (+1)");
        button_V2.setBounds(1130,150,100,50);
        button_V2.setBackground(new Color(0,0,0));
        button_V2.setForeground(new Color(130,15,15));
        button_V2.setFont(new Font("Calibri",1,18));
        button_V2.setEnabled(false);
        layeredPane.add(button_V2, Integer.valueOf(1));

        button_D2 = new JButton("D (+1)");
        button_D2.setBounds(1130,230,100,50);
        button_D2.setBackground(new Color(0,0,0));
        button_D2.setForeground(new Color(130,15,15));
        button_D2.setFont(new Font("Calibri",1,18));
        button_D2.setEnabled(false);
        layeredPane.add(button_D2, Integer.valueOf(1));

        button_F2 = new JButton("F (+1)");
        button_F2.setBounds(1130,310,100,50);
        button_F2.setBackground(new Color(0,0,0));
        button_F2.setForeground(new Color(130,15,15));
        button_F2.setFont(new Font("Calibri",1,18));
        button_F2.setEnabled(false);
        layeredPane.add(button_F2, Integer.valueOf(1));

        button_N2 = new JButton("N (+1)");
        button_N2.setBounds(1130,390,100,50);
        button_N2.setBackground(new Color(0,0,0));
        button_N2.setForeground(new Color(130,15,15));
        button_N2.setFont(new Font("Calibri",1,18));
        button_N2.setEnabled(false);
        layeredPane.add(button_N2, Integer.valueOf(1));

        button_A2 = new JButton("A (+1)");
        button_A2.setBounds(1130,470,100,50);
        button_A2.setBackground(new Color(0,0,0));
        button_A2.setForeground(new Color(130,15,15));
        button_A2.setFont(new Font("Calibri",1,18));
        button_A2.setEnabled(false);
        layeredPane.add(button_A2, Integer.valueOf(1));

        button_attack2 = new JButton("Attack (7)");
        button_attack2.setBounds(930,550,120,40);
        button_attack2.setBackground(Color.white);
        button_attack2.setForeground(new Color(135,15,15));
        button_attack2.setFont(new Font("Calibri",1,18));
        //button_attack2.setEnabled(false);
        layeredPane.add(button_attack2, Integer.valueOf(1));
        button_attack2.addActionListener(this);

        /*button_defense2 = new JButton("Defense");
        button_defense2.setBounds(1020,550,100,40);
        button_defense2.setBackground(Color.white);
        button_defense2.setForeground(new Color(135,15,15));
        button_defense2.setFont(new Font("Calibri",1,18));
        button_defense2.setEnabled(false);
        layeredPane.add(button_defense2, Integer.valueOf(1));*/

        label_arrC1 = new JLabel();
        label_arrC1.setBounds(575,500,200,30);
        label_arrC1.setForeground(Color.white);
        label_arrC1.setFont(new Font("Arial",1,18));
        layeredPane.add(label_arrC1, Integer.valueOf(1));

        label_arrC2 = new JLabel();
        label_arrC2.setBounds(575,550,200,30);
        label_arrC2.setForeground(Color.white);
        label_arrC2.setFont(new Font("Arial",1,18));
        layeredPane.add(label_arrC2, Integer.valueOf(1));

        String textArrChracter1 = "Player 1: ";
        for (int value : arrCards1) {
            textArrChracter1 += value + " ";
        }

        String textArrChracter2 = "Player 2: ";
        for (int value : arrCards2) {
            textArrChracter2 += value + " ";
        }

        label_arrC1.setText(textArrChracter1);
        label_arrC2.setText(textArrChracter2);

        label_footer = new JLabel("Creado por Matias Dupont ©");
        label_footer.setBounds(575,610,210,20);
        label_footer.setForeground(Color.white);
        layeredPane.add(label_footer, Integer.valueOf(1));

        setContentPane(layeredPane);

        if (turno == 1){
            button_attack2.setEnabled(false);
            button_attack.setEnabled(true);
        }
        else {
            button_attack.setEnabled(false);
            button_attack2.setEnabled(true);
        }

        this.setBounds(0,0,1400,1400);
        this.setResizable(false);
        pack();
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button_chracter1){
            int param = 0;
            Personaje personaje = null;
            if (arrCards1[param] == 0){
                personaje = elf_f;
            } else if (arrCards1[param] == 1) {
                personaje = elf_m;
            } else if (arrCards1[param] == 2) {
                personaje = human_m;
            } else if (arrCards1[param] == 3) {
                personaje = human_f;
            } else if (arrCards1[param] == 4) {
                personaje = orc_f;
            } else if (arrCards1[param] == 5) {
                personaje = orc_m;
            }
                String chracter1Info = "Nombre: " + personaje.getNombre() + "\n\n";
                chracter1Info += "           Caracteristicas" + "\n\n";
                chracter1Info += "Apodo: " + personaje.getApodo() + "\n";
                chracter1Info += "Edad: " + personaje.getEdad() + "\n";
                chracter1Info += "Puntos de Vida: " + personaje.getSalud() + "\n";
                chracter1Info += "Velocidad: " + personaje.getVelocidad() + "\n";
                chracter1Info += "Destreza: " + personaje.getDestreza() + "\n";
                chracter1Info += "Fuerza: " + personaje.getFuerza() + "\n";
                chracter1Info += "Nivel: " + personaje.getNivel() + "\n";
                chracter1Info += "Armadura: " + personaje.getArmadura() + "\n";
                chracter1Info += "Efectividad de Disparo: " + personaje.getEfectividadDisparo();

                JOptionPane.showMessageDialog(this, chracter1Info, "Información del Personaje", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == button_chracter2) {
            int param = 0;
            Personaje personaje = null;
            if (arrCards2[param] == 0) {
                personaje = elf_f;
            } else if (arrCards2[param] == 1) {
                personaje = elf_m;
            } else if (arrCards2[param] == 2) {
                personaje = human_m;
            } else if (arrCards2[param] == 3) {
                personaje = human_f;
            } else if (arrCards2[param] == 4) {
                personaje = orc_f;
            } else if (arrCards2[param] == 5) {
                personaje = orc_m;
            }
            String chracter2Info = "Nombre: " + personaje.getNombre() + "\n\n";
            chracter2Info += "           Caracteristicas" + "\n\n";
            chracter2Info += "Apodo: " + personaje.getApodo() + "\n";
            chracter2Info += "Edad: " + personaje.getEdad() + "\n";
            chracter2Info += "Puntos de Vida: " + personaje.getSalud() + "\n";
            chracter2Info += "Velocidad: " + personaje.getVelocidad() + "\n";
            chracter2Info += "Destreza: " + personaje.getDestreza() + "\n";
            chracter2Info += "Fuerza: " + personaje.getFuerza() + "\n";
            chracter2Info += "Nivel: " + personaje.getNivel() + "\n";
            chracter2Info += "Armadura: " + personaje.getArmadura() + "\n";
            chracter2Info += "Efectividad de Disparo: " + personaje.getEfectividadDisparo();

            JOptionPane.showMessageDialog(this, chracter2Info, "Información del Personaje", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == button_attack){
            int ataque = 0;
            if (arrCards1[0] == 0){
                ataque = (int) (((((elf_f.valorAtaque() * elf_f.getEfectividadDisparo()) - elf_f.poderDefensa()) / 500) * 100) * 1.05);
            } else if (arrCards1[0] == 1) {
                ataque = (int) (((((elf_m.valorAtaque() * elf_m.getEfectividadDisparo()) - elf_m.poderDefensa()) / 500) * 100) * 1.05);
            } else if (arrCards1[0] == 2){
                ataque = (int) ((((human_m.valorAtaque() * human_m.getEfectividadDisparo()) - human_m.poderDefensa()) / 500) * 100);
            } else if (arrCards1[0] == 3){
                ataque = (int) ((((human_f.valorAtaque() * human_f.getEfectividadDisparo()) - human_f.poderDefensa()) / 500) * 100);
            } else if (arrCards1[0] == 4){
                ataque = (int) (((((orc_f.valorAtaque() * orc_f.getEfectividadDisparo()) - orc_f.poderDefensa()) / 500) * 100) * 1.1);
            } else if (arrCards1[0] == 5){
                ataque = (int) (((((orc_m.valorAtaque() * orc_m.getEfectividadDisparo()) - orc_m.poderDefensa()) / 500) * 100) * 1.1);
            }

            if (arrCards2[0] == 0) {
                elf_f.setSalud(elf_f.getSalud() - ataque);
            } else if (arrCards2[0] == 1) {
                elf_m.setSalud(elf_m.getSalud() - ataque);
            } else if (arrCards2[0] == 2) {
                human_m.setSalud(human_m.getSalud() - ataque);
            } else if (arrCards2[0] == 3) {
                human_f.setSalud(human_f.getSalud() - ataque);
            } else if (arrCards2[0] == 4) {
                orc_f.setSalud(orc_f.getSalud() - ataque);
            } else if (arrCards2[0] == 5) {
                orc_m.setSalud(orc_m.getSalud() - ataque);
            }

            total_ataques1 --;
            button_attack.setText("Attack (" + total_ataques1 + ")");
            turno = 2;
            button_attack.setEnabled(false);
            button_attack2.setEnabled(true);
        }
        if (e.getSource() == button_attack2){
            button_attack.setEnabled(false);
            int ataque = 0;
            if (arrCards2[0] == 0){
                ataque = (int) (((((elf_f.valorAtaque() * elf_f.getEfectividadDisparo()) - elf_f.poderDefensa()) / 500) * 100) * 1.05);
            } else if (arrCards2[0] == 1) {
                ataque = (int) (((((elf_m.valorAtaque() * elf_m.getEfectividadDisparo()) - elf_m.poderDefensa()) / 500) * 100) * 1.05);
            } else if (arrCards2[0] == 2){
                ataque = (int) ((((human_m.valorAtaque() * human_m.getEfectividadDisparo()) - human_m.poderDefensa()) / 500) * 100);
            } else if (arrCards2[0] == 3){
                ataque = (int) ((((human_f.valorAtaque() * human_f.getEfectividadDisparo()) - human_f.poderDefensa()) / 500) * 100);
            } else if (arrCards2[0] == 4){
                ataque = (int) (((((orc_f.valorAtaque() * orc_f.getEfectividadDisparo()) - orc_f.poderDefensa()) / 500) * 100) * 1.1);
            } else if (arrCards2[0] == 5){
                ataque = (int) (((((orc_m.valorAtaque() * orc_m.getEfectividadDisparo()) - orc_m.poderDefensa()) / 500) * 100) * 1.1);
            }

            if (arrCards1[0] == 0) {
                elf_f.setSalud(elf_f.getSalud() - ataque);
            } else if (arrCards1[0] == 1) {
                elf_m.setSalud(elf_m.getSalud() - ataque);
            } else if (arrCards1[0] == 2) {
                human_m.setSalud(human_m.getSalud() - ataque);
            } else if (arrCards1[0] == 3) {
                human_f.setSalud(human_f.getSalud() - ataque);
            } else if (arrCards1[0] == 4) {
                orc_f.setSalud(orc_f.getSalud() - ataque);
            } else if (arrCards1[0] == 5) {
                orc_m.setSalud(orc_m.getSalud() - ataque);
            }

            total_ataques2 --;
            button_attack2.setText("Attack (" + total_ataques2 + ")");
            turno = 1;
            button_attack2.setEnabled(false);
            button_attack.setEnabled(true);
        }
            //Ganar ganar = new Ganar(elf_m, elf_f, human_m, human_f, orc_m, orc_f);
            //ganar.setVisible(true);
    }
}

