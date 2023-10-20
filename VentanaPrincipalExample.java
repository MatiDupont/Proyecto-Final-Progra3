import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipalExample extends JFrame implements ActionListener {
    JLabel lblTitulo;
    JButton buttonPanel1, buttonPanel2;

    Panel1 miPanel1;
    Panel2 miPanel2;
    JScrollPane scrollPane;

    public VentanaPrincipalExample(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(387,365);
        setTitle("EJEMPLO PARA QUE ANDE EL HDP");
        setLayout(null);

        setLocationRelativeTo(null);
        iniciarComponentes();
    }

    private void iniciarComponentes(){
        miPanel1 = new Panel1();
        miPanel2 = new Panel2();

        scrollPane = new JScrollPane();
        scrollPane.setBounds(5,112,360,200);

        buttonPanel1 = new JButton("PANEL 1");
        buttonPanel1.setBounds(10,76,89,23);
        buttonPanel1.addActionListener(this);
        definirPanel(miPanel1);

        buttonPanel2 = new JButton("PANEL 2");
        buttonPanel2.setBounds(109,76,89,23);
        buttonPanel2.addActionListener(this);

        lblTitulo = new JLabel("EJEMPLO DE JPANEL ");
        lblTitulo.setBounds(10,14,221,51);

        add(buttonPanel1);
        add(lblTitulo);
        add(buttonPanel2);
        add(scrollPane);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == buttonPanel1){
            definirPanel(miPanel1);
        }
        if (e.getSource() == buttonPanel2){
            definirPanel(miPanel2);
        }
    }

    private void definirPanel(Panel1 miPanel){
        scrollPane.setViewportView(miPanel);
    }

    private void definirPanel(Panel2 miPanel){
        scrollPane.setViewportView(miPanel);
    }

    public static void main(String[] args) {
        VentanaPrincipalExample vpe = new VentanaPrincipalExample();
        vpe.setVisible(true);
    }
}
