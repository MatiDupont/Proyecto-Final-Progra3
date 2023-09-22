import com.sun.jdi.ObjectReference;

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
    private JButton button_chracter1, button_chracter2, button_attack, button_attack2, button_V1, button_D1, button_F1, button_N1, button_A1, button_V2, button_D2, button_F2, button_N2, button_A2;
    public static int round = 1, turno, indice = 0, indice2 = 0, newWidth = 270, newHeight = 370;
    public static int[] arrCards1, arrCards2;
    public static String[] image;
    private int total_ataques1 = 7, total_ataques2 = 7;
    private String habilidadSeleccionada = "";
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

        image = new String[3];

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

        ImageIcon originalIcon = new ImageIcon(image[indice]);
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

        ImageIcon originalIcon2 = new ImageIcon(image[indice2]);
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

    private void ataqueJugador1(Personaje[] atacante, Personaje[] defensor){
        int attack = 0;
        Random random = new Random();

        if (atacante[0] instanceof Elfo){
            attack =  Math.abs((int) (((((((Elfo)atacante[0]).valorAtaque() * ((Elfo)atacante[0]).getEfectividadDisparo()) - defensor[0].poderDefensa()) / 500) * 100) * 1.05));
        } else if (atacante[0] instanceof Humano){
            attack = Math.abs((int) ((((((Humano)atacante[0]).valorAtaque() * ((Humano)atacante[0]).getEfectividadDisparo()) - defensor[0].poderDefensa()) / 500) * 100));
        } else if (atacante[0] instanceof Orco) {
            attack = Math.abs((int) (((((((Orco)atacante[0]).valorAtaque() * ((Orco)atacante[0]).getEfectividadDisparo()) - defensor[0].poderDefensa()) / 500) * 100) * 1.1));
        }

        atacante[0].setEfectividadDisparo((random.nextInt(100) + 1) / 100.0);
        // personajeDefensor.setEfectividadDisparo((random.nextInt(100) + 1) / 100.0);

        //JOptionPane.showMessageDialog(null, attack);
        defensor[0].setSalud(defensor[0].getSalud() - attack);
    }

    private void ataqueJugador2(Personaje[] atacante, Personaje[] defensor){
        int attack = 0;
        Random random = new Random();

        if (atacante[0] instanceof Elfo){
            attack =  Math.abs((int) (((((((Elfo)atacante[0]).valorAtaque() * ((Elfo)atacante[0]).getEfectividadDisparo()) - defensor[0].poderDefensa()) / 500) * 100) * 1.05));
        } else if (atacante[0] instanceof Humano){
            attack = Math.abs((int) ((((((Humano)atacante[0]).valorAtaque() * ((Humano)atacante[0]).getEfectividadDisparo()) - defensor[0].poderDefensa()) / 500) * 100));
        } else if (atacante[0] instanceof Orco) {
            attack = Math.abs((int) (((((((Orco)atacante[0]).valorAtaque() * ((Orco)atacante[0]).getEfectividadDisparo()) - defensor[0].poderDefensa()) / 500) * 100) * 1.1));
        }

        atacante[0].setEfectividadDisparo((random.nextInt(100) + 1) / 100.0);
        // personajeDefensor.setEfectividadDisparo((random.nextInt(100) + 1) / 100.0);

        //JOptionPane.showMessageDialog(null, attack);
        defensor[0].setSalud(defensor[0].getSalud() - attack);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button_chracter1){
            Personaje[] p1 = personajes1(arrCards1);

            String chracter1Info = "Nombre: " + p1[0].getNombre() + "\n\n";
            chracter1Info += "           Caracteristicas" + "\n\n";
            chracter1Info += "Apodo: " + p1[0].getApodo() + "\n";
            chracter1Info += "Edad: " + p1[0].getEdad() + "\n";
            chracter1Info += "Raza:" + p1[0].getRaza() + "\n";
            chracter1Info += "Puntos de Vida: " + p1[0].getSalud() + "\n";
            chracter1Info += "Velocidad: " + p1[0].getVelocidad() + "\n";
            chracter1Info += "Destreza: " + p1[0].getDestreza() + "\n";
            chracter1Info += "Fuerza: " + p1[0].getFuerza() + "\n";
            chracter1Info += "Nivel: " + p1[0].getNivel() + "\n";
            chracter1Info += "Armadura: " + p1[0].getArmadura() + "\n";
            chracter1Info += "Efectividad de Disparo: " + p1[0].getEfectividadDisparo();

            JOptionPane.showMessageDialog(this, chracter1Info, "Información del Personaje", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == button_chracter2) {
            Personaje[] p2 = personajes2(arrCards2);
            String chracter2Info = "Nombre: " + p2[0].getNombre() + "\n\n";
            chracter2Info += "           Caracteristicas" + "\n\n";
            chracter2Info += "Apodo: " + p2[0].getApodo() + "\n";
            chracter2Info += "Edad: " + p2[0].getEdad() + "\n";
            chracter2Info += "Raza:" + p2[0].getRaza() + "\n";
            chracter2Info += "Puntos de Vida: " + p2[0].getSalud() + "\n";
            chracter2Info += "Velocidad: " + p2[0].getVelocidad() + "\n";
            chracter2Info += "Destreza: " + p2[0].getDestreza() + "\n";
            chracter2Info += "Fuerza: " + p2[0].getFuerza() + "\n";
            chracter2Info += "Nivel: " + p2[0].getNivel() + "\n";
            chracter2Info += "Armadura: " + p2[0].getArmadura() + "\n";
            chracter2Info += "Efectividad de Disparo: " + p2[0].getEfectividadDisparo();

            JOptionPane.showMessageDialog(this, chracter2Info, "Información del Personaje", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == button_V2 || e.getSource() == button_D2 || e.getSource() == button_F2 || e.getSource() == button_N2 || e.getSource() == button_A2) {
            habilidadSeleccionada = getHabilidadDesdeBoton(e.getSource());
            JOptionPane.showMessageDialog(null, habilidadSeleccionada);
            modificarHabilidad(personajes2(arrCards2), habilidadSeleccionada);
            button_V2.setEnabled(false);
            button_D2.setEnabled(false);
            button_F2.setEnabled(false);
            button_N2.setEnabled(false);
            button_A2.setEnabled(false);
        }
        if (e.getSource() == button_D1 || e.getSource() == button_V1 || e.getSource() == button_F1 || e.getSource() == button_N1 || e.getSource() == button_A1) {
            habilidadSeleccionada = getHabilidadDesdeBoton(e.getSource());
            JOptionPane.showMessageDialog(null, habilidadSeleccionada);
            modificarHabilidad(personajes1(arrCards1), habilidadSeleccionada);
            button_V1.setEnabled(false);
            button_D1.setEnabled(false);
            button_F1.setEnabled(false);
            button_N1.setEnabled(false);
            button_A1.setEnabled(false);
        }

        if (e.getSource() == button_attack){
            ataqueJugador1(personajes1(arrCards1),personajes2(arrCards2));
            estadoCarta(personajes2(arrCards2));

            total_ataques1 --;
            button_attack.setText("Attack (" + total_ataques1 + ")");
            turno = 2;
            button_attack.setEnabled(false);
            button_attack2.setEnabled(true);
        }
        if (e.getSource() == button_attack2){
            ataqueJugador2(personajes2(arrCards2),personajes1(arrCards1));
            estadoCarta(personajes1(arrCards1));

            total_ataques2 --;
            button_attack2.setText("Attack (" + total_ataques2 + ")");
            turno = 1;
            button_attack2.setEnabled(false);
            button_attack.setEnabled(true);
        }
            //Ganar ganar = new Ganar(elf_m, elf_f, human_m, human_f, orc_m, orc_f);
            //ganar.setVisible(true);
    }

    private Personaje[] personajes1(int[] arr1){
        Personaje[] personajes1 = new Personaje[3];
        for (int i = 0; i < arr1.length; i++){
            if (arr1[i] == 0){
                personajes1[i] = elf_f;
            } else if (arr1[i] == 1) {
                personajes1[i] = elf_m;
            } else if (arr1[i] == 2) {
                personajes1[i] = human_m;
            } else if (arr1[i] == 3) {
                personajes1[i] = human_f;
            } else if (arr1[i] == 4) {
                personajes1[i] = orc_f;
            } else if (arr1[i] == 5) {
                personajes1[i] = orc_m;
            }
        }
        return personajes1;
    }
    private Personaje[] personajes2(int[] arr2){
        Personaje[] personajes2 = new Personaje[3];
        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] == 0) {
                personajes2[i] = elf_f;
            } else if (arr2[i] == 1) {
                personajes2[i] = elf_m;
            } else if (arr2[i] == 2) {
                personajes2[i] = human_m;
            } else if (arr2[i] == 3) {
                personajes2[i] = human_f;
            } else if (arr2[i] == 4) {
                personajes2[i] = orc_f;
            } else if (arr2[i] == 5) {
                personajes2[i] = orc_m;
            }
        }
        return personajes2;
    }

    private boolean estadoCarta(Personaje[] personajes){
        boolean vivo = true;
        if (personajes[0].getSalud() <= 0){
            vivo = false;
            JOptionPane.showMessageDialog(null, "El personaje murio.");

            if (elementoEnArreglo(personajes[0], personajes1(arrCards1))){
                button_V2.setEnabled(true);
                button_D2.setEnabled(true);
                button_F2.setEnabled(true);
                button_N2.setEnabled(true);
                button_A2.setEnabled(true);
            }
            else if (elementoEnArreglo(personajes[0], personajes2(arrCards2))){
                button_V1.setEnabled(true);
                button_D1.setEnabled(true);
                button_F1.setEnabled(true);
                button_N1.setEnabled(true);
                button_A1.setEnabled(true);
            }
        }
        return vivo;
    }

    private boolean elementoEnArreglo(Personaje personaje, Personaje[] arreglo){
        for (Personaje p : arreglo){
            if (p == personaje){
                return true;
            }
        }
        return false;
    }

    private void modificarHabilidad(Personaje[] personajes, String habilidad){
        if (habilidad.equals("Velocidad")) {
            personajes[0].setVelocidad(personajes[0].getVelocidad() + 1);
        } else if (habilidad.equals("Destreza")) {
            personajes[0].setDestreza(personajes[0].getDestreza() + 1);
        } else if (habilidad.equals("Fuerza")) {
            personajes[0].setFuerza(personajes[0].getFuerza() + 1);
        } else if (habilidad.equals("Nivel")) {
            personajes[0].setNivel(personajes[0].getNivel() + 1);
        } else if (habilidad.equals("Armadura")) {
            personajes[0].setArmadura(personajes[0].getArmadura() + 1);
        }

        JOptionPane.showMessageDialog(null, "Habilidad modificada");
    }

    private String getHabilidadDesdeBoton(Object botonPresionado){
        if (botonPresionado == button_V1 || botonPresionado == button_V2) {
            return "Velocidad";
        } else if (botonPresionado == button_D1 || botonPresionado == button_D2) {
            return "Destreza";
        } else if (botonPresionado == button_F1 || botonPresionado == button_F2) {
            return "Fuerza";
        } else if (botonPresionado == button_N1 || botonPresionado == button_N2) {
            return "Nivel";
        } else if (botonPresionado == button_A1 || botonPresionado == button_A2) {
            return "Armadura";
        } else {
            return "";
        }
    }
}

