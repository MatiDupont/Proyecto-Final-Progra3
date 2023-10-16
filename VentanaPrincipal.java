import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class VentanaPrincipal extends JFrame {
    private GameOn panel_OnGame;
    private JScrollPane scrollPane;
    public VentanaPrincipal(){
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null,"Seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                if (confirm == JOptionPane.YES_OPTION){
                    dispose();
                }
            }
        });

        setTitle("BATTLE ROYALE");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\wallpaper7.jpg"))).getImage());

        setSize(1400,1400);
        setResizable(false);
        //pack();
        setLocationRelativeTo(null);

        iniciarComponentes();
    }

    private void iniciarComponentes(){
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
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                ventanaPrincipal.setVisible(true);
            }
        });
    }
}

