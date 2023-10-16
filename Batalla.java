import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import conexionBBDD.Conexion;

public class Batalla extends JPanel implements ActionListener {
    private Elfo elf_m, elf_f;
    private Humano human_m, human_f;
    private Orco orc_m, orc_f;
    private BufferedImage backgroundImage;
    private JScrollPane scrollPane;
    private Perder panel_Perder;
    private Ganar panel_Ganar;
    private JLabel label_title, label_player1, label_player2, label_round, label_vs, label_footer, label_arrC1, label_arrC2;
    private JButton button_chracter1, button_chracter2, button_attack, button_attack2, button_V1, button_D1, button_F1, button_N1, button_A1, button_V2, button_D2, button_F2, button_N2, button_A2;
    public static int round = 1, turno, newWidth = 270, newHeight = 370,  indicePersonaje1 = 0, indicePersonaje2 = 0;
    public static int[] arrCards1, arrCards2;
    public static String[] image, image2;
    private int total_ataques1 = 0, total_ataques2 = 0;
    private String habilidadSeleccionada = "";
    public Batalla(Elfo elfo1, Elfo elfo2, Humano humano1, Humano humano2, Orco orco1, Orco orco2){
        this.elf_m = elfo1;
        this.elf_f = elfo2;
        this.human_m = humano1;
        this.human_f = humano2;
        this.orc_m = orco1;
        this.orc_f = orco2;

        setLayout(null);
        cargarImagenDeFondo();
        iniciarComponentes();

        if (turno == 1){
            button_attack2.setEnabled(false);
            button_attack.setEnabled(true);
        }
        else {
            button_attack.setEnabled(false);
            button_attack2.setEnabled(true);
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

    private void iniciarComponentes() {
        label_title = new JLabel("BATALLA");
        label_title.setBounds(570,30,300,30);
        label_title.setForeground(new Color(130,15,15));
        label_title.setFont(new Font("Tahoma",1,35));
        add(label_title);

        label_player1 = new JLabel("PLAYER 1");
        label_player1.setBounds(210,100,200,40);
        label_player1.setForeground(Color.white);
        label_player1.setFont(new Font("Arial",1,30));
        add(label_player1);

        label_player2 = new JLabel("PLAYER 2");
        label_player2.setBounds(910,100,200,40);
        label_player2.setForeground(Color.white);
        label_player2.setFont(new Font("Arial",1,30));
        add(label_player2);

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

        ImageIcon originalIcon = new ImageIcon(image[0]);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        button_chracter1 = new JButton(resizedIcon);
        button_chracter1.setBounds(150,150,270,370);
        button_chracter1.setBackground(Color.BLACK);
        add(button_chracter1);
        button_chracter1.addActionListener(this);

        button_V1 = new JButton("V (+1)");
        button_V1.setBounds(40,150,100,50);
        button_V1.setBackground(new Color(0,0,0));
        button_V1.setForeground(new Color(130,15,15));
        button_V1.setFont(new Font("Calibri",1,18));
        button_V1.setEnabled(false);
        add(button_V1);
        button_V1.addActionListener(this);

        button_D1 = new JButton("D (+1)");
        button_D1.setBounds(40,230,100,50);
        button_D1.setBackground(new Color(0,0,0));
        button_D1.setForeground(new Color(130,15,15));
        button_D1.setFont(new Font("Calibri",1,18));
        button_D1.setEnabled(false);
        add(button_D1);
        button_D1.addActionListener(this);

        button_F1 = new JButton("F (+1)");
        button_F1.setBounds(40,310,100,50);
        button_F1.setBackground(new Color(0,0,0));
        button_F1.setForeground(new Color(130,15,15));
        button_F1.setFont(new Font("Calibri",1,18));
        button_F1.setEnabled(false);
        add(button_F1);
        button_F1.addActionListener(this);

        button_N1 = new JButton("N (+1)");
        button_N1.setBounds(40,390,100,50);
        button_N1.setBackground(new Color(0,0,0));
        button_N1.setForeground(new Color(130,15,15));
        button_N1.setFont(new Font("Calibri",1,18));
        button_N1.setEnabled(false);
        add(button_N1);
        button_N1.addActionListener(this);

        button_A1 = new JButton("A (+1)");
        button_A1.setBounds(40,470,100,50);
        button_A1.setBackground(new Color(0,0,0));
        button_A1.setForeground(new Color(130,15,15));
        button_A1.setFont(new Font("Calibri",1,18));
        button_A1.setEnabled(false);
        add(button_A1);
        button_A1.addActionListener(this);

        Random random1 = new Random();
        turno = random1.nextInt(2) + 1;

        button_attack = new JButton("Attack");
        button_attack.setBounds(225,550,120,40);
        button_attack.setBackground(Color.white);
        button_attack.setForeground(new Color(135,15,15));
        button_attack.setFont(new Font("Calibri",1,18));
        add(button_attack);
        button_attack.addActionListener(this);

        label_round = new JLabel("ROUND - " + round + " -");
        label_round.setBounds(505,150,270,40);
        label_round.setForeground(Color.white);
        label_round.setFont(new Font("Times New Roman",2,50));
        add(label_round);

        label_vs = new JLabel("VS");
        label_vs.setBounds(600,300,100,70);
        label_vs.setForeground(Color.white);
        label_vs.setFont(new Font("Lucida Console",1,70));
        add(label_vs);

        image2 = new String[3];

        for (int i = 0; i < arrChracter2.length; i++){
            if (arrCards2[i] == 0){
                image2[i] = elf_f.getImage();
            } else if (arrCards2[i] == 1) {
                image2[i] = elf_m.getImage();
            } else if (arrCards2[i] == 2) {
                image2[i] = human_m.getImage();
            } else if (arrCards2[i] == 3) {
                image2[i] = human_f.getImage();
            } else if (arrCards2[i] == 4) {
                image2[i] = orc_f.getImage();
            } else if (arrCards2[i] == 5) {
                image2[i] = orc_m.getImage();
            }
        }

        ImageIcon originalIcon2 = new ImageIcon(image2[0]);
        Image originalImage2 = originalIcon2.getImage();
        Image resizedImage2 = originalImage2.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        button_chracter2 = new JButton(resizedIcon2);
        button_chracter2.setBounds(850,150,270,370);
        button_chracter2.setBackground(Color.BLACK);
        add(button_chracter2);
        button_chracter2.addActionListener(this);

        button_V2 = new JButton("V (+1)");
        button_V2.setBounds(1130,150,100,50);
        button_V2.setBackground(new Color(0,0,0));
        button_V2.setForeground(new Color(130,15,15));
        button_V2.setFont(new Font("Calibri",1,18));
        button_V2.setEnabled(false);
        add(button_V2);
        button_V2.addActionListener(this);

        button_D2 = new JButton("D (+1)");
        button_D2.setBounds(1130,230,100,50);
        button_D2.setBackground(new Color(0,0,0));
        button_D2.setForeground(new Color(130,15,15));
        button_D2.setFont(new Font("Calibri",1,18));
        button_D2.setEnabled(false);
        add(button_D2);
        button_D2.addActionListener(this);

        button_F2 = new JButton("F (+1)");
        button_F2.setBounds(1130,310,100,50);
        button_F2.setBackground(new Color(0,0,0));
        button_F2.setForeground(new Color(130,15,15));
        button_F2.setFont(new Font("Calibri",1,18));
        button_F2.setEnabled(false);
        add(button_F2);
        button_F2.addActionListener(this);

        button_N2 = new JButton("N (+1)");
        button_N2.setBounds(1130,390,100,50);
        button_N2.setBackground(new Color(0,0,0));
        button_N2.setForeground(new Color(130,15,15));
        button_N2.setFont(new Font("Calibri",1,18));
        button_N2.setEnabled(false);
        add(button_N2);
        button_N2.addActionListener(this);

        button_A2 = new JButton("A (+1)");
        button_A2.setBounds(1130,470,100,50);
        button_A2.setBackground(new Color(0,0,0));
        button_A2.setForeground(new Color(130,15,15));
        button_A2.setFont(new Font("Calibri",1,18));
        button_A2.setEnabled(false);
        add(button_A2);
        button_A2.addActionListener(this);

        button_attack2 = new JButton("Attack");
        button_attack2.setBounds(930,550,120,40);
        button_attack2.setBackground(Color.white);
        button_attack2.setForeground(new Color(135,15,15));
        button_attack2.setFont(new Font("Calibri",1,18));
        //button_attack2.setEnabled(false);
        add(button_attack2);
        button_attack2.addActionListener(this);

        label_arrC1 = new JLabel();
        label_arrC1.setBounds(575,500,200,30);
        label_arrC1.setForeground(Color.white);
        label_arrC1.setFont(new Font("Arial",1,18));
        //add(label_arrC1);

        label_arrC2 = new JLabel();
        label_arrC2.setBounds(575,550,200,30);
        label_arrC2.setForeground(Color.white);
        label_arrC2.setFont(new Font("Arial",1,18));
        //add(label_arrC2);

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
        add(label_footer);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(0,0,1400,1400);
    }

    private void ataqueJugador1(Personaje[] atacante, Personaje[] defensor){
        total_ataques1 ++;
        int attack = 0;
        Random random = new Random();

        if (atacante[indicePersonaje1] instanceof Elfo){
            attack =  Math.abs((int) (((((((Elfo)atacante[indicePersonaje1]).valorAtaque() * ((Elfo)atacante[indicePersonaje1]).getEfectividadDisparo()) - defensor[indicePersonaje2].poderDefensa()) / 500) * 100) * 1.05));
        } else if (atacante[indicePersonaje1] instanceof Humano){
            attack = Math.abs((int) ((((((Humano)atacante[indicePersonaje1]).valorAtaque() * ((Humano)atacante[indicePersonaje1]).getEfectividadDisparo()) - defensor[indicePersonaje2].poderDefensa()) / 500) * 100));
        } else if (atacante[indicePersonaje1] instanceof Orco) {
            attack = Math.abs((int) (((((((Orco)atacante[indicePersonaje1]).valorAtaque() * ((Orco)atacante[indicePersonaje1]).getEfectividadDisparo()) - defensor[indicePersonaje2].poderDefensa()) / 500) * 100) * 1.1));
        }

        atacante[indicePersonaje1].setEfectividadDisparo((random.nextInt(100) + 1) / 100.0);

        JOptionPane.showMessageDialog(null,"Ataque N° " + total_ataques1 + " del jugador 1.\n" + atacante[indicePersonaje1].getNombre() + " le quito " + attack + " de salud a " + defensor[indicePersonaje2].getNombre());
        defensor[indicePersonaje2].setSalud(defensor[indicePersonaje2].getSalud() - attack);
    }

    private void ataqueJugador2(Personaje[] atacante, Personaje[] defensor){
        total_ataques2 ++;
        int attack = 0;
        Random random = new Random();

        if (atacante[indicePersonaje2] instanceof Elfo){
            attack =  Math.abs((int) (((((((Elfo)atacante[indicePersonaje2]).valorAtaque() * ((Elfo)atacante[indicePersonaje2]).getEfectividadDisparo()) - defensor[indicePersonaje1].poderDefensa()) / 500) * 100) * 1.05));
        } else if (atacante[indicePersonaje2] instanceof Humano){
            attack = Math.abs((int) ((((((Humano)atacante[indicePersonaje2]).valorAtaque() * ((Humano)atacante[indicePersonaje2]).getEfectividadDisparo()) - defensor[indicePersonaje1].poderDefensa()) / 500) * 100));
        } else if (atacante[indicePersonaje2] instanceof Orco) {
            attack = Math.abs((int) (((((((Orco)atacante[indicePersonaje2]).valorAtaque() * ((Orco)atacante[indicePersonaje2]).getEfectividadDisparo()) - defensor[indicePersonaje1].poderDefensa()) / 500) * 100) * 1.1));
        }

        atacante[indicePersonaje2].setEfectividadDisparo((random.nextInt(100) + 1) / 100.0);

        JOptionPane.showMessageDialog(null, "Ataque N° " + total_ataques2 + " del jugador 2.\n" + atacante[indicePersonaje2].getNombre() + " le quito " + attack + " de salud a " + defensor[indicePersonaje1].getNombre());
        defensor[indicePersonaje1].setSalud(defensor[indicePersonaje1].getSalud() - attack);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_chracter1) {
            infoChracter1(indicePersonaje1);
        }
        if (e.getSource() == button_chracter2) {
            infoChracter2(indicePersonaje2);
        }
        if (e.getSource() == button_V2 || e.getSource() == button_D2 || e.getSource() == button_F2 || e.getSource() == button_N2 || e.getSource() == button_A2) {
            habilidadSeleccionada = getHabilidadDesdeBoton(e.getSource());
            JOptionPane.showMessageDialog(null, habilidadSeleccionada);
            modificarHabilidad(personajes2(arrCards2), indicePersonaje2, habilidadSeleccionada);
            button_V2.setEnabled(false);
            button_D2.setEnabled(false);
            button_F2.setEnabled(false);
            button_N2.setEnabled(false);
            button_A2.setEnabled(false);

            //personajeVivosJ1(personajes1(arrCards1));
        }
        if (e.getSource() == button_V1 || e.getSource() == button_D1 || e.getSource() == button_F1 || e.getSource() == button_N1 || e.getSource() == button_A1) {
            habilidadSeleccionada = getHabilidadDesdeBoton(e.getSource());
            JOptionPane.showMessageDialog(null, habilidadSeleccionada);
            modificarHabilidad(personajes1(arrCards1), indicePersonaje1, habilidadSeleccionada);
            button_V1.setEnabled(false);
            button_D1.setEnabled(false);
            button_F1.setEnabled(false);
            button_N1.setEnabled(false);
            button_A1.setEnabled(false);

            //personajeVivosJ2(personajes2(arrCards2));
        }

        if (e.getSource() == button_attack){
            ataqueJugador1(personajes1(arrCards1),personajes2(arrCards2));

            boolean estado = estadoCarta(personajes2(arrCards2),indicePersonaje2);

            if (!estado){
                button_attack.setEnabled(false);
                button_attack2.setEnabled(false);
            }
            else {
                turno = 2;
                button_attack.setEnabled(false);
                button_attack2.setEnabled(true);
            }
        }
        if (e.getSource() == button_attack2){
            ataqueJugador2(personajes2(arrCards2),personajes1(arrCards1));

            boolean estado = estadoCarta(personajes1(arrCards1),indicePersonaje1);

            if (!estado){
                button_attack2.setEnabled(false);
                button_attack.setEnabled(false);
            }
            else {
                turno = 1;
                button_attack2.setEnabled(false);
                button_attack.setEnabled(true);
            }
        }
    }

    private boolean estadoCarta(Personaje[] personajes, int indicePersonaje){
        boolean vivo = true;
        if (personajes[indicePersonaje].getSalud() <= 0){
            vivo = false;
            JOptionPane.showMessageDialog(null, personajes[indicePersonaje].getNombre() + " murio.");

            //Personaje siguientePersonaje = null;
            if (elementoEnArreglo(personajes[indicePersonaje], personajes1(arrCards1))){
                button_chracter2.setBackground(new Color(0,255,0));
                button_chracter1.setBackground(new Color(255,0,0));
                button_V2.setEnabled(true);
                button_D2.setEnabled(true);
                button_F2.setEnabled(true);
                button_N2.setEnabled(true);
                button_A2.setEnabled(true);

                //siguientePersonaje = actualizarSiguientePersonaje(personajes1(arrCards1));
            }
            else if (elementoEnArreglo(personajes[indicePersonaje], personajes2(arrCards2))){
                button_chracter1.setBackground(new Color(0,255,0));
                button_chracter2.setBackground(new Color(255,0,0));
                button_V1.setEnabled(true);
                button_D1.setEnabled(true);
                button_F1.setEnabled(true);
                button_N1.setEnabled(true);
                button_A1.setEnabled(true);

                //siguientePersonaje = actualizarSiguientePersonaje(personajes2(arrCards2));
            }

            //personajes[0] = siguientePersonaje;
           /* if (siguientePersonaje != null){
                personajes[0] = siguientePersonaje;
                JOptionPane.showMessageDialog(null, "Cambio de personaje " + personajes[0].getNombre());
            }
            else {
                JOptionPane.showMessageDialog(null, "El jugador ha perdido." );
            }*/
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

    private void modificarHabilidad(Personaje[] personajes, int indice, String habilidad){
        if (habilidad.equals("Velocidad")) {
            if (personajes[indice].getVelocidad() == 10){
                boolean flag = true;
                while (flag){
                    int opcion = JOptionPane.showConfirmDialog(null, "Velocidad ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Destreza": {
                                personajes[indice].setDestreza(personajes[indice].getDestreza() + 1);
                                flag = false;
                                break;
                            }
                            case "Fuerza": {
                                personajes[indice].setFuerza(personajes[indice].getFuerza() + 1);
                                flag = false;
                                break;
                            }
                            case "Nivel": {
                                personajes[indice].setNivel(personajes[indice].getNivel() + 1);
                                flag = false;
                                break;
                            }
                            case "Armadura": {
                                personajes[indice].setArmadura(personajes[indice].getArmadura() + 1);
                                flag = false;
                                break;
                            }
                            default:{
                                flag = true;
                            }
                        }
                    }
                }
            }
            else {
                personajes[indice].setVelocidad(personajes[indice].getVelocidad() + 1);
                JOptionPane.showMessageDialog(null, "Velocidad aumentada.");
            }
        } else if (habilidad.equals("Destreza")) {
            if (personajes[indice].getDestreza() == 5){
                boolean flag = true;
                while (flag){
                    int opcion = JOptionPane.showConfirmDialog(null, "Destreza ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Velocidad": {
                                personajes[indice].setVelocidad(personajes[indice].getVelocidad() + 1);
                                flag = false;
                                break;
                            }
                            case "Fuerza": {
                                personajes[indice].setFuerza(personajes[indice].getFuerza() + 1);
                                flag = false;
                                break;
                            }
                            case "Nivel": {
                                personajes[indice].setNivel(personajes[indice].getNivel() + 1);
                                flag = false;
                                break;
                            }
                            case "Armadura": {
                                personajes[indice].setArmadura(personajes[indice].getArmadura() + 1);
                                flag = false;
                                break;
                            }
                            default:{
                                flag = true;
                            }
                        }
                    }
                }
            }
            else {
                personajes[indice].setDestreza(personajes[indice].getDestreza() + 1);
                JOptionPane.showMessageDialog(null, "Destreza aumentada.");
            }
        } else if (habilidad.equals("Fuerza")) {
            if (personajes[indice].getFuerza() == 10){
                boolean flag = true;
                while (flag){
                    int opcion = JOptionPane.showConfirmDialog(null, "Fuerza ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Velocidad": {
                                personajes[indice].setVelocidad(personajes[indice].getVelocidad() + 1);
                                flag = false;
                                break;
                            }
                            case "Destreza": {
                                personajes[indice].setDestreza(personajes[indice].getDestreza() + 1);
                                flag = false;
                                break;
                            }
                            case "Nivel": {
                                personajes[indice].setNivel(personajes[indice].getNivel() + 1);
                                flag = false;
                                break;
                            }
                            case "Armadura": {
                                personajes[indice].setArmadura(personajes[indice].getArmadura() + 1);
                                flag = false;
                                break;
                            }
                            default:{
                                flag = true;
                            }
                        }
                    }
                }
            }
            else {
                personajes[indice].setFuerza(personajes[indice].getFuerza() + 1);
                JOptionPane.showMessageDialog(null, "Fuerza aumentada.");
            }
        } else if (habilidad.equals("Nivel")) {
            if (personajes[indice].getNivel() == 10){
                boolean flag = true;
                while (flag){
                    int opcion = JOptionPane.showConfirmDialog(null, "Nivel ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Velocidad": {
                                personajes[indice].setVelocidad(personajes[indice].getVelocidad() + 1);
                                flag = false;
                                break;
                            }
                            case "Destreza": {
                                personajes[indice].setDestreza(personajes[indice].getDestreza() + 1);
                                flag = false;
                                break;
                            }
                            case "Fuerza": {
                                personajes[indice].setFuerza(personajes[indice].getFuerza() + 1);
                                flag = false;
                                break;
                            }
                            case "Armadura": {
                                personajes[indice].setArmadura(personajes[indice].getArmadura() + 1);
                                flag = false;
                                break;
                            }
                            default:{
                                flag = true;
                            }
                        }
                    }
                }
            }
            else {
                personajes[indice].setNivel(personajes[indice].getNivel() + 1);
                JOptionPane.showMessageDialog(null, "Nivel aumentado.");
            }
        } else if (habilidad.equals("Armadura")) {
            if (personajes[indice].getArmadura() == 10){
                boolean flag = true;
                while (flag){
                    int opcion = JOptionPane.showConfirmDialog(null, "Nivel ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Velocidad": {
                                personajes[indice].setVelocidad(personajes[indice].getVelocidad() + 1);
                                flag = false;
                                break;
                            }
                            case "Destreza": {
                                personajes[indice].setDestreza(personajes[indice].getDestreza() + 1);
                                flag = false;
                                break;
                            }
                            case "Fuerza": {
                                personajes[indice].setFuerza(personajes[indice].getFuerza() + 1);
                                flag = false;
                                break;
                            }
                            case "Nivel": {
                                personajes[indice].setNivel(personajes[indice].getNivel() + 1);
                                flag = false;
                                break;
                            }
                            default:{
                                flag = true;
                            }
                        }
                    }
                }
            }
            else {
                personajes[indice].setArmadura(personajes[indice].getArmadura() + 1);
                JOptionPane.showMessageDialog(null, "Armadura aumentada.");
            }
        }

        boolean obj_in_arr = elementoEnArreglo(personajes[indice], personajes1(arrCards1));

        if (obj_in_arr){
            Personaje siguiente = siguientePersonaje(personajes2(arrCards2), personajes2(arrCards2)[indicePersonaje2]);
            if (siguiente == null){
                panel_Ganar = new Ganar(elf_m, elf_f, human_m, human_f, orc_m, orc_f);
                add(scrollPane);
                label_title.setVisible(false);
                label_player1.setVisible(false);
                label_player2.setVisible(false);
                button_chracter1.setVisible(false);
                button_V1.setVisible(false);
                button_D1.setVisible(false);
                button_F1.setVisible(false);
                button_N1.setVisible(false);
                button_A1.setVisible(false);
                button_attack.setVisible(false);
                label_round.setVisible(false);
                label_vs.setVisible(false);
                button_chracter2.setVisible(false);
                button_V2.setVisible(false);
                button_D2.setVisible(false);
                button_F2.setVisible(false);
                button_N2.setVisible(false);
                button_A2.setVisible(false);
                button_attack2.setVisible(false);
                definirPanel(panel_Ganar);
            }
            else {
                ImageIcon originalIcon2 = new ImageIcon(siguiente.getImage());
                Image originalImage2 = originalIcon2.getImage();
                Image resizedImage2 = originalImage2.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
                button_chracter2.setIcon(resizedIcon2);
                button_chracter2.setBackground(new Color(0,0,0));
                button_chracter1.setBackground(new Color(0,0,0));
                button_attack2.setEnabled(true);
                total_ataques1 = 0;
                total_ataques2 = 0;
                button_attack.setText("Attack");
                button_attack2.setText("Attack");
                indicePersonaje2 += 1;
            }
        }
        else {
            Personaje siguiente = siguientePersonaje(personajes1(arrCards1), personajes1(arrCards1)[indicePersonaje1]);
            if (siguiente == null){
                panel_Perder = new Perder();
                add(scrollPane);
                label_title.setVisible(false);
                label_player1.setVisible(false);
                label_player2.setVisible(false);
                button_chracter1.setVisible(false);
                button_V1.setVisible(false);
                button_D1.setVisible(false);
                button_F1.setVisible(false);
                button_N1.setVisible(false);
                button_A1.setVisible(false);
                button_attack.setVisible(false);
                label_round.setVisible(false);
                label_vs.setVisible(false);
                button_chracter2.setVisible(false);
                button_V2.setVisible(false);
                button_D2.setVisible(false);
                button_F2.setVisible(false);
                button_N2.setVisible(false);
                button_A2.setVisible(false);
                button_attack2.setVisible(false);
                definirPanel(panel_Perder);
            }
            else {
                ImageIcon originalIcon = new ImageIcon(siguiente.getImage());
                Image originalImage = originalIcon.getImage();
                Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                button_chracter1.setIcon(resizedIcon);
                button_chracter1.setBackground(new Color(0,0,0));
                button_chracter2.setBackground(new Color(0,0,0));
                button_attack.setEnabled(true);
                total_ataques1 = 0;
                total_ataques2 = 0;
                button_attack.setText("Attack");
                button_attack2.setText("Attack");
                indicePersonaje1 += 1;
            }
        }
        label_round.setText("ROUND - " + (round += 1) + " -");
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

    private String elegirOtraHabilidad(){
        String[] opciones = {"Velocidad", "Destreza", "Fuerza", "Nivel", "Armadura"};

        return (String) JOptionPane.showInputDialog(
                null,
                "Elija una habilidad para mejorar",
                "Habilidad en su maximo nivel",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
    }

    private Personaje siguientePersonaje(Personaje[] personajes, Personaje personajeMuerto){
        for (int i = 0; i < personajes.length; i++){
            if (personajes[i] == personajeMuerto){
                if (i < personajes.length - 1){
                    return personajes[i + 1];
                }
            }
        }
        return null;
    }
    private void infoChracter1(int indice){
        Personaje[] p1 = personajes1(arrCards1);

        String chracter1Info = "Nombre: " + p1[indice].getNombre() + "\n\n";
        chracter1Info += "           Caracteristicas" + "\n\n";
        chracter1Info += "Apodo: " + p1[indice].getApodo() + "\n";
        chracter1Info += "Edad: " + p1[indice].getEdad() + "\n";
        chracter1Info += "Raza: " + p1[indice].getRaza() + "\n";
        chracter1Info += "Salud: " + p1[indice].getSalud() + "\n";
        chracter1Info += "Velocidad: " + p1[indice].getVelocidad() + "\n";
        chracter1Info += "Destreza: " + p1[indice].getDestreza() + "\n";
        chracter1Info += "Fuerza: " + p1[indice].getFuerza() + "\n";
        chracter1Info += "Nivel: " + p1[indice].getNivel() + "\n";
        chracter1Info += "Armadura: " + p1[indice].getArmadura() + "\n";
        chracter1Info += "Efectividad de Disparo: " + p1[indice].getEfectividadDisparo();

        JOptionPane.showMessageDialog(this, chracter1Info, "Información del Personaje", JOptionPane.INFORMATION_MESSAGE);
    }

    private void infoChracter2(int indice){
        Personaje[] p2 = personajes2(arrCards2);

        String chracter2Info = "Nombre: " + p2[indice].getNombre() + "\n\n";
        chracter2Info += "           Caracteristicas" + "\n\n";
        chracter2Info += "Apodo: " + p2[indice].getApodo() + "\n";
        chracter2Info += "Edad: " + p2[indice].getEdad() + "\n";
        chracter2Info += "Raza: " + p2[indice].getRaza() + "\n";
        chracter2Info += "Salud: " + p2[indice].getSalud() + "\n";
        chracter2Info += "Velocidad: " + p2[indice].getVelocidad() + "\n";
        chracter2Info += "Destreza: " + p2[indice].getDestreza() + "\n";
        chracter2Info += "Fuerza: " + p2[indice].getFuerza() + "\n";
        chracter2Info += "Nivel: " + p2[indice].getNivel() + "\n";
        chracter2Info += "Armadura: " + p2[indice].getArmadura() + "\n";
        chracter2Info += "Efectividad de Disparo: " + p2[indice].getEfectividadDisparo();

        JOptionPane.showMessageDialog(this, chracter2Info, "Información del Personaje", JOptionPane.INFORMATION_MESSAGE);
    }

    String cartasJ1 = "";
    private Personaje[] personajes1(int[] arr1){
        Personaje[] personajes1 = new Personaje[3];
        for (int i = 0; i < arr1.length; i++) {
            cartasJ1.concat("-------- Personaje " + (i + 1) + " Jugador 1 --------\n");
            if (arr1[i] == 0) {
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

            cartasJ1.concat("Nombre: " + personajes1[i].getNombre() + "\n\n" +
                    "           Caracteristicas" + "\n\n" +
                    "Apodo: " + personajes1[i].getApodo() + "\n" +
                    "Edad: " + personajes1[i].getEdad() + "\n" +
                    "Raza: " + personajes1[i].getRaza() + "\n" +
                    "Salud: " + personajes1[i].getSalud() + "\n" +
                    "Velocidad: " + personajes1[i].getVelocidad() + "\n" +
                    "Destreza: " + personajes1[i].getDestreza() + "\n" +
                    "Fuerza: " + personajes1[i].getFuerza() + "\n" +
                    "Nivel: " + personajes1[i].getNivel() + "\n" +
                    "Armadura: " + personajes1[i].getArmadura() + "\n" +
                    "Efectividad de Disparo: " + personajes1[i].getEfectividadDisparo() + "\n\n");
        }
        return personajes1;
    }
    String cartasJ2 = "";
    private Personaje[] personajes2(int[] arr2){
        Personaje[] personajes2 = new Personaje[3];
        for (int i = 0; i < arr2.length; i++) {
            cartasJ2.concat("-------- Personaje " + (i + 1) + " Jugador 2 --------\n");
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

            cartasJ2.concat("Nombre: " + personajes2[i].getNombre() + "\n\n" +
            "           Caracteristicas" + "\n\n" +
            "Apodo: " + personajes2[i].getApodo() + "\n" +
            "Edad: " + personajes2[i].getEdad() + "\n" +
            "Raza: " + personajes2[i].getRaza() + "\n" +
            "Salud: " + personajes2[i].getSalud() + "\n" +
            "Velocidad: " + personajes2[i].getVelocidad() + "\n" +
            "Destreza: " + personajes2[i].getDestreza() + "\n" +
            "Fuerza: " + personajes2[i].getFuerza() + "\n" +
            "Nivel: " + personajes2[i].getNivel() + "\n" +
            "Armadura: " + personajes2[i].getArmadura() + "\n" +
            "Efectividad de Disparo: " + personajes2[i].getEfectividadDisparo() + "\n\n");
        }
        insertarDatosTablaCartas();
        return personajes2;
    }
    private void insertarDatosTablaCartas(){
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into cartas values (?,?,?,?)");
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String fechaHoraFormateada = ahora.format(formato);
            JOptionPane.showMessageDialog(null, "La fecha y la hora actual es: \n" + fechaHoraFormateada);

            pst.setInt(1,0);
            pst.setString(2, cartasJ1);
            pst.setString(3, cartasJ2);
            pst.setString(4, fechaHoraFormateada);

            pst.executeUpdate();
            cn.close();

            JOptionPane.showMessageDialog(null, "Datos registrados exitosamente.");

        }
        catch (SQLException exception){
            JOptionPane.showMessageDialog(null, "Error al insertar los datos en la tabla cartas " + exception.getMessage());
        }
    }

    private void definirPanel(Perder panel) {
        scrollPane.setViewportView(panel);
    }

    private void definirPanel(Ganar panel) {
        scrollPane.setViewportView(panel);
    }
}
