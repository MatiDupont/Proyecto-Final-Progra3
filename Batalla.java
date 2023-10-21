import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import conexionBBDD.Conexion;

public class Batalla extends JPanel implements ActionListener {
    private Elfo elf_m, elf_f;
    private Humano human_m, human_f;
    private Orco orc_m, orc_f;
    private BufferedImage backgroundImage;
    private JScrollPane scrollPane;
    private JTextArea textArea_command;
    private Perder panel_Perder;
    private Ganar panel_Ganar;
    private JLabel label_title, label_player1, label_player2, label_round, label_vs, label_footer, label_arrC1, label_arrC2;
    private JButton button_chracter1, button_chracter2, button_attack, button_attack2, button_V1, button_D1, button_F1, button_N1, button_A1, button_V2, button_D2, button_F2, button_N2, button_A2;
    public static int round = 1, turno, newWidth = 270, newHeight = 370,  indicePersonaje1 = 0, indicePersonaje2 = 0;
    public static int[] arrCards1, arrCards2;
    public static String[] image, image2;
    private int total_ataques1 = 0, total_ataques2 = 0;
    private String habilidadSeleccionada = "";
    String user;
    StringBuilder gameplay;
    public Batalla(Elfo elfo1, Elfo elfo2, Humano humano1, Humano humano2, Orco orco1, Orco orco2){
        gameplay = new StringBuilder();
        user = LogIn.user;
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

        impresionPersonajes1(arrCards1);
        impresionPersonajes2(arrCards2);

        addKeyBindings();
    }

    private void addKeyBindings() {
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.ALT_DOWN_MASK), "atacar");
        actionMap.put("atacar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turno == 1 && button_attack.isEnabled()) {
                    button_attack.doClick();
                }
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.ALT_DOWN_MASK), "defender");
        actionMap.put("defender", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turno == 2 && button_attack2.isEnabled()) {
                    button_attack2.doClick();
                }
            }
        });
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
        gameplay.append("\n\n##################################################");
        gameplay.append("ROUND - ").append(round).append(" -");
        gameplay.append("##################################################");
        if (turno == 1){
            gameplay.append("\nEl sistema eligio al personaje ").append(personajes1(arrCards1)[indicePersonaje1]).append(" del jugador 1 y al personaje ")
                    .append(personajes2(arrCards2)[indicePersonaje2]).append(" del jugador 2 para que se enfrenten en esta ronda.\n");
        }
        else {
            gameplay.append("\nEl sistema eligio al personaje ").append(personajes2(arrCards2)[indicePersonaje2]).append(" del jugador 2 y al personaje ")
                    .append(personajes1(arrCards1)[indicePersonaje1]).append(" del jugador 1 para que se enfrenten en esta ronda.\n");
        }
        gameplay.append("Empieza atacando el jugador ").append(turno).append("\n");
        label_round.setBounds(505,150,270,40);
        label_round.setForeground(Color.white);
        label_round.setFont(new Font("Times New Roman",2,50));
        add(label_round);

        label_vs = new JLabel("VS");
        label_vs.setBounds(600,300,100,70);
        label_vs.setForeground(Color.white);
        label_vs.setFont(new Font("Lucida Console",1,70));
        add(label_vs);

        textArea_command = new JTextArea();
        if (turno == 1) {
            textArea_command.setText("Presione Alt + A para atacar");
        }
        else {
            textArea_command.setText("Presione Alt + D para defender");
        }
        textArea_command.setEditable(false);
        textArea_command.setForeground(new Color(130,15,78));
        textArea_command.setBounds(450,485,370,30);
        textArea_command.setFont(new Font("Britannic Bold",4,27));
        add(textArea_command);

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
        add(button_attack2);
        button_attack2.addActionListener(this);

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

        gameplay.append("--> Ataque N° ").append(total_ataques1).append(" del jugador 1.\n").append(atacante[indicePersonaje1].getNombre()).append(" le quito ").append(attack).append(" de salud a ").append(defensor[indicePersonaje2].getNombre()).append(" dejandole ").append(defensor[indicePersonaje2].getSalud()).append(" de vida <--\n");
        //JOptionPane.showMessageDialog(null,"Ataque N° " + total_ataques1 + " del jugador 1.\n" + atacante[indicePersonaje1].getNombre() + " le quito " + attack + " de salud a " + defensor[indicePersonaje2].getNombre());
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

        gameplay.append("--> Ataque N° ").append(total_ataques2).append(" del jugador 2.\n").append(atacante[indicePersonaje2].getNombre()).append(" le quito ").append(attack).append(" de salud a ").append(defensor[indicePersonaje1].getNombre()).append(" dejandole ").append(defensor[indicePersonaje1].getSalud()).append(" de vida <--\n");
        //JOptionPane.showMessageDialog(null, "Ataque N° " + total_ataques2 + " del jugador 2.\n" + atacante[indicePersonaje2].getNombre() + " le quito " + attack + " de salud a " + defensor[indicePersonaje1].getNombre());
        defensor[indicePersonaje1].setSalud(defensor[indicePersonaje1].getSalud() - attack);
    }

    private void guardarRegistroAtaque() {
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into batalla values (?,?,?,?,?)");

            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());

            pst.setInt(1,0);
            pst.setInt(2,obtenerIdJuego());
            pst.setString(3, String.valueOf(gameplay));
            pst.setTimestamp(4,timestamp);
            pst.setString(5,user);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos de la batalla registrados exitosamente.");

            cn.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Error al intentar guardar los registros del ataque 1. " + exception.getMessage());
        }
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
            //JOptionPane.showMessageDialog(null, habilidadSeleccionada);
            modificarHabilidad(personajes2(arrCards2), indicePersonaje2, habilidadSeleccionada);
            button_V2.setEnabled(false);
            button_D2.setEnabled(false);
            button_F2.setEnabled(false);
            button_N2.setEnabled(false);
            button_A2.setEnabled(false);
        }
        if (e.getSource() == button_V1 || e.getSource() == button_D1 || e.getSource() == button_F1 || e.getSource() == button_N1 || e.getSource() == button_A1) {
            habilidadSeleccionada = getHabilidadDesdeBoton(e.getSource());
            //JOptionPane.showMessageDialog(null, habilidadSeleccionada);
            modificarHabilidad(personajes1(arrCards1), indicePersonaje1, habilidadSeleccionada);
            button_V1.setEnabled(false);
            button_D1.setEnabled(false);
            button_F1.setEnabled(false);
            button_N1.setEnabled(false);
            button_A1.setEnabled(false);
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
                textArea_command.setText("Presione Alt + D para defender");
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
                textArea_command.setText("Presione Alt + A para atacar");
            }
            else {
                turno = 1;
                textArea_command.setText("Presione Alt + A para atacar");
                button_attack2.setEnabled(false);
                button_attack.setEnabled(true);
            }
        }
    }

    private boolean estadoCarta(Personaje[] personajes, int indicePersonaje){
        boolean vivo = true;
        if (personajes[indicePersonaje].getSalud() <= 0){
            vivo = false;
            gameplay.append("\n=======================================================");
            gameplay.append(personajes[indicePersonaje].getNombre() + " murio.\n");
            gameplay.append("=======================================================");
            //JOptionPane.showMessageDialog(null, personajes[indicePersonaje].getNombre() + " murio.");

            //Personaje siguientePersonaje = null;
            if (elementoEnArreglo(personajes[indicePersonaje], personajes1(arrCards1))){
                button_chracter2.setBackground(new Color(0,255,0));
                button_chracter1.setBackground(new Color(255,0,0));
                button_V2.setEnabled(true);
                button_D2.setEnabled(true);
                button_F2.setEnabled(true);
                button_N2.setEnabled(true);
                button_A2.setEnabled(true);
            }
            else if (elementoEnArreglo(personajes[indicePersonaje], personajes2(arrCards2))){
                button_chracter1.setBackground(new Color(0,255,0));
                button_chracter2.setBackground(new Color(255,0,0));
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

    private void modificarHabilidad(Personaje[] personajes, int indice, String habilidad){
        gameplay.append(personajes[indice]).append(" obtiene la posibilidad de poder mejorar una de sus habilidades.\n");

        if (habilidad.equals("Velocidad")) {
            if (personajes[indice].getVelocidad() == 10){
                boolean flag = true;
                while (flag){
                    gameplay.append("Velocidad ya está en su nivel máximo. ¿Desea elegir otra habilidad?\n\n");
                    int opcion = JOptionPane.showConfirmDialog(null, "Velocidad ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Destreza": {
                                personajes[indice].setDestreza(personajes[indice].getDestreza() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Destreza aumentada (+1). --> ").append(personajes[indice].getDestreza()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Fuerza": {
                                personajes[indice].setFuerza(personajes[indice].getFuerza() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Fuerza aumentada (+1). --> ").append(personajes[indice].getFuerza()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Nivel": {
                                personajes[indice].setNivel(personajes[indice].getNivel() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Nivel aumentado (+1). --> ").append(personajes[indice].getNivel()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Armadura": {
                                personajes[indice].setArmadura(personajes[indice].getArmadura() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Armadura aumentada (+1). --> ").append(personajes[indice].getArmadura()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
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
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                gameplay.append("Velocidad aumentada (+1). --> ").append(personajes[indice].getVelocidad()).append(" <--");
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                //JOptionPane.showMessageDialog(null, "Velocidad aumentada.");
            }
        } else if (habilidad.equals("Destreza")) {
            if (personajes[indice].getDestreza() == 5){
                boolean flag = true;
                while (flag){
                    gameplay.append("Destreza ya está en su nivel máximo. ¿Desea elegir otra habilidad?\n\n");
                    int opcion = JOptionPane.showConfirmDialog(null, "Destreza ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Velocidad": {
                                personajes[indice].setVelocidad(personajes[indice].getVelocidad() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Velocidad aumentada (+1). --> ").append(personajes[indice].getVelocidad()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Fuerza": {
                                personajes[indice].setFuerza(personajes[indice].getFuerza() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Fuerza aumentada (+1). --> ").append(personajes[indice].getFuerza()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Nivel": {
                                personajes[indice].setNivel(personajes[indice].getNivel() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Nivel aumentado (+1). --> ").append(personajes[indice].getNivel()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Armadura": {
                                personajes[indice].setArmadura(personajes[indice].getArmadura() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Armadura aumentada (+1). --> ").append(personajes[indice].getArmadura()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
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
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                gameplay.append("Destreza aumentada (+1). --> ").append(personajes[indice].getDestreza()).append(" <--");
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                //JOptionPane.showMessageDialog(null, "Destreza aumentada.");
            }
        } else if (habilidad.equals("Fuerza")) {
            if (personajes[indice].getFuerza() == 10){
                boolean flag = true;
                while (flag){
                    gameplay.append("Fuerza ya está en su nivel máximo. ¿Desea elegir otra habilidad?\n\n");
                    int opcion = JOptionPane.showConfirmDialog(null, "Fuerza ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Velocidad": {
                                personajes[indice].setVelocidad(personajes[indice].getVelocidad() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Velocidad aumentada (+1). --> ").append(personajes[indice].getVelocidad()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Destreza": {
                                personajes[indice].setDestreza(personajes[indice].getDestreza() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Destreza aumentada (+1). --> ").append(personajes[indice].getDestreza()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Nivel": {
                                personajes[indice].setNivel(personajes[indice].getNivel() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Nivel aumentado (+1). --> ").append(personajes[indice].getNivel()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Armadura": {
                                personajes[indice].setArmadura(personajes[indice].getArmadura() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Armadura aumentada (+1). --> ").append(personajes[indice].getArmadura()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
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
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                gameplay.append("Fuerza aumentada (+1). --> ").append(personajes[indice].getFuerza()).append(" <--");
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                //JOptionPane.showMessageDialog(null, "Fuerza aumentada.");
            }
        } else if (habilidad.equals("Nivel")) {
            if (personajes[indice].getNivel() == 10){
                boolean flag = true;
                while (flag){
                    gameplay.append("Nivel ya está en su nivel máximo. ¿Desea elegir otra habilidad?\n\n");
                    int opcion = JOptionPane.showConfirmDialog(null, "Nivel ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Velocidad": {
                                personajes[indice].setVelocidad(personajes[indice].getVelocidad() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Velocidad aumentada (+1). --> ").append(personajes[indice].getVelocidad()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Destreza": {
                                personajes[indice].setDestreza(personajes[indice].getDestreza() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Destreza aumentada (+1). --> ").append(personajes[indice].getDestreza()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Fuerza": {
                                personajes[indice].setFuerza(personajes[indice].getFuerza() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Fuerza aumentada (+1). --> ").append(personajes[indice].getFuerza()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Armadura": {
                                personajes[indice].setArmadura(personajes[indice].getArmadura() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Armadura aumentada (+1). --> ").append(personajes[indice].getArmadura()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
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
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                gameplay.append("Nivel aumentado (+1). --> ").append(personajes[indice].getNivel()).append(" <--");
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                //JOptionPane.showMessageDialog(null, "Nivel aumentado.");
            }
        } else if (habilidad.equals("Armadura")) {
            if (personajes[indice].getArmadura() == 10){
                boolean flag = true;
                while (flag){
                    gameplay.append("Armadura ya está en su nivel máximo. ¿Desea elegir otra habilidad?\n\n");
                    int opcion = JOptionPane.showConfirmDialog(null, "Armadura ya está en su nivel máximo. ¿Desea elegir otra habilidad?", "Habilidad en su Nivel Máximo", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION){
                        String nuevaHabilidad = elegirOtraHabilidad();
                        switch (nuevaHabilidad){
                            case "Velocidad": {
                                personajes[indice].setVelocidad(personajes[indice].getVelocidad() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Velocidad aumentada (+1). --> ").append(personajes[indice].getVelocidad()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Destreza": {
                                personajes[indice].setDestreza(personajes[indice].getDestreza() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Destreza aumentada (+1). --> ").append(personajes[indice].getDestreza()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Fuerza": {
                                personajes[indice].setFuerza(personajes[indice].getFuerza() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Fuerza aumentada (+1). --> ").append(personajes[indice].getFuerza()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                                break;
                            }
                            case "Nivel": {
                                personajes[indice].setNivel(personajes[indice].getNivel() + 1);
                                flag = false;
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                                gameplay.append("Nivel aumentado (+1). --> ").append(personajes[indice].getNivel()).append(" <--");
                                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
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
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                gameplay.append("Armadura aumentada (+1). --> ").append(personajes[indice].getArmadura()).append(" <--");
                gameplay.append("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
                //JOptionPane.showMessageDialog(null, "Armadura aumentada.");
            }
        }

        boolean obj_in_arr = elementoEnArreglo(personajes[indice], personajes1(arrCards1));

        if (obj_in_arr){
            Personaje siguiente = siguientePersonaje(personajes2(arrCards2), personajes2(arrCards2)[indicePersonaje2]);
            if (siguiente == null){
                gameplay.append("Gana Jugador 1, le quedo/aron vivo/s los siguientes personajes: \n");
                for (int i = 0; i < arrCards1.length; i++){
                    if (estadoCarta(personajes1(arrCards1),i)) {
                        gameplay.append("--> ").append(personajes1(arrCards1)[i]).append(" <-- \n");
                    }
                }
                gameplay.append("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                gameplay.append("Felicitaciones Jugador 1, las fuerzas mágicas del universo luz te abrazan!\n");
                gameplay.append("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                gameplay.append("FIN");
                insertarDatosTablaCartas();
                guardarRegistroAtaque();
                panel_Ganar = new Ganar(elf_m, elf_f, human_m, human_f, orc_m, orc_f);
                add(scrollPane);
                deshabilitarComponentes();
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
                indicePersonaje2 += 1;
                turno = 2;
                textArea_command.setText("Presione Alt + D para defender");
                gameplay.append("\nEmpieza atacando Jugador 2 por perder la ronda ").append(round).append("\n");
                gameplay.append("El sistema elegio al personaje ").append(personajes2(arrCards2)[indicePersonaje2]).append(" del jugador 2 y al personaje ")
                        .append(personajes1(arrCards1)[indicePersonaje1]).append(" del jugador 1 para que se enfrenten en esta ronda.\n");
            }
        }
        else {
            Personaje siguiente = siguientePersonaje(personajes1(arrCards1), personajes1(arrCards1)[indicePersonaje1]);
            if (siguiente == null){
                gameplay.append("Gana Jugador 2, le quedo/aron vivo/s los siguientes personajes: \n");
                for (int i = 0; i < arrCards2.length; i++) {
                    if (estadoCarta(personajes2(arrCards2),i)){
                        gameplay.append("--> ").append(personajes2(arrCards2)[i]).append(" <-- \n");
                    }
                }
                gameplay.append("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                gameplay.append("Felicitaciones Jugador 2, las fuerzas mágicas del universo luz te abrazan!\n");
                gameplay.append("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                gameplay.append("FIN");
                insertarDatosTablaCartas();
                guardarRegistroAtaque();
                panel_Perder = new Perder();
                add(scrollPane);
                deshabilitarComponentes();
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
                indicePersonaje1 += 1;
                turno = 1;
                textArea_command.setText("Presione Alt + A para atacar");
                gameplay.append("\nEmpieza atacando Jugador 1 por perder la ronda ").append(round).append("\n");
                gameplay.append("El sistema eligio al personaje ").append(personajes1(arrCards1)[indicePersonaje1]).append(" del jugador 1 y al personaje ")
                        .append(personajes2(arrCards2)[indicePersonaje2]).append(" del jugador 2 para que se enfrenten en esta ronda.\n");
            }
        }
        label_round.setText("ROUND - " + (round += 1) + " -");
        gameplay.append("\n\n##################################################");
        gameplay.append("ROUND - ").append(round += 1).append(" -");
        gameplay.append("##################################################");
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

    private Personaje[] personajes1(int[] arr1){
        Personaje[] personajes1 = new Personaje[3];
        for (int i = 0; i < arr1.length; i++) {
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

    StringBuilder cartasJ1 = new StringBuilder();
    private void impresionPersonajes1(int[] arr1) {
        Personaje[] personajes1 = new Personaje[3];
        for (int i = 0; i < arr1.length; i++) {
            cartasJ1.append("-------- Personaje ").append(i + 1).append(" Jugador 1 --------\n");
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

            cartasJ1.append("Nombre: ").append(personajes1[i].getNombre()).append("\n\n")
                    .append("           Caracteristicas").append("\n\n")
                    .append("Apodo: ").append(personajes1[i].getApodo()).append("\n")
                    .append("Edad: ").append(personajes1[i].getEdad()).append("\n")
                    .append("Raza: ").append(personajes1[i].getRaza()).append("\n")
                    .append("Salud: ").append(personajes1[i].getSalud()).append("\n")
                    .append("Velocidad: ").append(personajes1[i].getVelocidad()).append("\n")
                    .append("Destreza: ").append(personajes1[i].getDestreza()).append("\n")
                    .append("Fuerza: ").append(personajes1[i].getFuerza()).append("\n")
                    .append("Nivel: ").append(personajes1[i].getNivel()).append("\n")
                    .append("Armadura: ").append(personajes1[i].getArmadura()).append("\n")
                    .append("Efectividad de Disparo: ").append(personajes1[i].getEfectividadDisparo()).append("\n\n");
        }
    }
    StringBuilder cartasJ2 = new StringBuilder();
    public void impresionPersonajes2(int[] arr2) {
        Personaje[] personajes2 = new Personaje[3];
        for (int i = 0; i < arr2.length; i++) {
            cartasJ2.append("-------- Personaje ").append(i + 1).append(" Jugador 2 --------\n");
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

            cartasJ2.append("Nombre: ").append(personajes2[i].getNombre()).append("\n\n")
                    .append("           Caracteristicas").append("\n\n")
                    .append("Apodo: ").append(personajes2[i].getApodo()).append("\n")
                    .append("Edad: ").append(personajes2[i].getEdad()).append("\n")
                    .append("Raza: ").append(personajes2[i].getRaza()).append("\n")
                    .append("Salud: ").append(personajes2[i].getSalud()).append("\n")
                    .append("Velocidad: ").append(personajes2[i].getVelocidad()).append("\n")
                    .append("Destreza: ").append(personajes2[i].getDestreza()).append("\n")
                    .append("Fuerza: ").append(personajes2[i].getFuerza()).append("\n")
                    .append("Nivel: ").append(personajes2[i].getNivel()).append("\n")
                    .append("Armadura: ").append(personajes2[i].getArmadura()).append("\n")
                    .append("Efectividad de Disparo: ").append(personajes2[i].getEfectividadDisparo()).append("\n\n");
        }
    }

    private int obtenerIdJuego() {
        int idJuegoUsuario = 1;
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select id_juego from usuarios where username = '" + LogIn.user + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idJuegoUsuario = rs.getInt("id_juego");
            }

            cn.close();

            int nuevoIdJuego = idJuegoUsuario + 1;

            Connection cn2 = Conexion.conectar();
            PreparedStatement pst2 = cn2.prepareStatement("update usuarios set id_juego = '" + nuevoIdJuego + "' where username = '" + LogIn.user + "'");

            pst2.executeUpdate();

            cn2.close();

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Error al buscar el id_juego en la tabla usuarios " + exception.getMessage());
        }

        return idJuegoUsuario;
    }
    private void insertarDatosTablaCartas(){
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into cartas values (?,?,?,?,?,?,true)");
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            //JOptionPane.showMessageDialog(null, "La fecha y la hora actual es: \n" + fechaHoraFormateada);

            pst.setInt(1,0);
            pst.setInt(2,obtenerIdJuego());
            pst.setString(3, String.valueOf(cartasJ1));
            pst.setString(4, String.valueOf(cartasJ2));
            pst.setTimestamp(5, timestamp);
            pst.setString(6, user);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos de cartas registrados exitosamente.");

            cn.close();
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

    private void deshabilitarComponentes() {
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
        label_footer.setVisible(false);
    }
}