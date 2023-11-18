import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import creacionBBDD.EjecucionScript;

public class VentanaPrincipal extends JFrame implements ActionListener {
    private GameOn panel_OnGame;
    private JButton button_sound, button_sound_off;
    private JScrollPane scrollPane;
    Clip clip;
    public VentanaPrincipal() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null,"Seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                if (confirm == JOptionPane.YES_OPTION){
                    clip.close();
                    dispose();
                }
                else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });

        setTitle("BATTLE ROYALE");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper7.jpg"))).getImage());

        setSize(1400,1400);
        setResizable(false);
        //pack();
        setLocationRelativeTo(null);

        iniciarMusica();

        iniciarComponentes();
    }

    private void iniciarMusica() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("cancion.wav"));

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciarComponentes() {
        ImageIcon sound = new ImageIcon("images\\sound.png");
        Icon icono = new ImageIcon(sound.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
        button_sound = new JButton(icono);
        button_sound.setBounds(20,20,40,40);
        button_sound.setBackground(Color.green);
        button_sound.addActionListener(this);
        this.repaint();
        add(button_sound);

        ImageIcon sound_off = new ImageIcon("images\\sound_off.png");
        Icon icono_off = new ImageIcon(sound_off.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH));
        button_sound_off = new JButton(icono_off);
        button_sound_off.setBounds(90,20,40,40);
        button_sound_off.setBackground(Color.red);
        this.repaint();
        button_sound_off.addActionListener(this);
        add(button_sound_off);
        panel_OnGame = new GameOn();
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0,0,1400,1400);

        definirPanel(panel_OnGame);

        add(scrollPane);
    }



    private void definirPanel(GameOn panel){
        scrollPane.setViewportView(panel);
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaPrincipal ventanaPrincipal = null;
                try {
                    ventanaPrincipal = new VentanaPrincipal();
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
                ventanaPrincipal.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_sound_off) {
            clip.stop();
            button_sound.setBackground(Color.red);
            button_sound_off.setBackground(Color.green);
        }
        if (e.getSource() == button_sound) {
            clip.start();
            button_sound.setBackground(Color.green);
            button_sound_off.setBackground(Color.red);
        }
    }
}

