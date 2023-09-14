import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Batalla extends JFrame {
    private JLabel label_wallpaper, label_title, label_player1, label_player2, label_round, label_vs, label_footer, label_arrC1, label_arrC2;
    private JButton button_chracter1, button_chracter2, button_attack, button_defense, button_attack2, button_defense2;
    public static int round = 1;
    private JTextArea textArea;
    public Batalla(){
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Batalla");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper.jpg"))).getImage());

        JLayeredPane layeredPane = new JLayeredPane();
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

        Random random = new Random();
        int start = random.nextInt(2) + 1;

        if (start == 1){
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
        }
        else {
            label_player2 = new JLabel("PLAYER 2");
            label_player2.setBounds(210,100,200,40);
            label_player2.setForeground(Color.white);
            label_player2.setFont(new Font("Arial",1,30));
            layeredPane.add(label_player2, Integer.valueOf(1));

            label_player1 = new JLabel("PLAYER 1");
            label_player1.setBounds(910,100,200,40);
            label_player1.setForeground(Color.white);
            label_player1.setFont(new Font("Arial",1,30));
            layeredPane.add(label_player1, Integer.valueOf(1));
        }
        ImageIcon originalIcon = new ImageIcon("images\\elf_male.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        button_chracter1 = new JButton(resizedIcon);
        button_chracter1.setBounds(150,150,270,370);
        button_chracter1.setBackground(Color.BLACK);
        layeredPane.add(button_chracter1, Integer.valueOf(1));

        button_attack = new JButton("Attack");
        button_attack.setBounds(150,550,100,40);
        button_attack.setBackground(Color.white);
        button_attack.setForeground(new Color(135,15,15));
        button_attack.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_attack, Integer.valueOf(1));

        button_defense = new JButton("Defense");
        button_defense.setBounds(320,550,100,40);
        button_defense.setBackground(Color.white);
        button_defense.setForeground(new Color(135,15,15));
        button_defense.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_defense, Integer.valueOf(1));

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

        ImageIcon originalIcon2 = new ImageIcon("images\\elf_female.png");
        Image originalImage2 = originalIcon2.getImage();
        Image resizedImage2 = originalImage2.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        button_chracter2 = new JButton(resizedIcon2);
        button_chracter2.setBounds(850,150,270,370);
        button_chracter2.setBackground(Color.BLACK);
        layeredPane.add(button_chracter2, Integer.valueOf(1));

        button_attack2 = new JButton("Attack");
        button_attack2.setBounds(850,550,100,40);
        button_attack2.setBackground(Color.white);
        button_attack2.setForeground(new Color(135,15,15));
        button_attack2.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_attack2, Integer.valueOf(1));

        button_defense2 = new JButton("Defense");
        button_defense2.setBounds(1020,550,100,40);
        button_defense2.setBackground(Color.white);
        button_defense2.setForeground(new Color(135,15,15));
        button_defense2.setFont(new Font("Calibri",1,18));
        layeredPane.add(button_defense2, Integer.valueOf(1));

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

        int[] arrChracter1 = EleccionCartas.arr_chracter1;
        int[] arrChracter2 = EleccionCartas.arr_chracter2;

        String textArrChracter1 = "Player 1: ";
        for (int value : arrChracter1) {
            textArrChracter1 += value + " ";
        }

        String textArrChracter2 = "Player 2: ";
        for (int value : arrChracter2) {
            textArrChracter2 += value + " ";
        }

        label_arrC1.setText(textArrChracter1);
        label_arrC2.setText(textArrChracter2);

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

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Batalla().setVisible(true);
            }
        });
    }
}
