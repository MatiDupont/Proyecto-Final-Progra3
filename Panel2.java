import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel2 extends JPanel implements ActionListener {
    JTextField campo;
    JButton buttonPanelInt;
    public Panel2(){
        setLayout(null);
        setBackground(Color.RED);
        iniciarComponentes();
    }

    private void iniciarComponentes(){
        buttonPanelInt = new JButton("ENVIAR");
        buttonPanelInt.setBounds(200,10,90,25);
        buttonPanelInt.addActionListener(this);

        campo = new JTextField();
        campo.setBounds(10,10,180,25);

        add(buttonPanelInt);
        add(campo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonPanelInt){
            JOptionPane.showMessageDialog(null, "MENSAJE" + campo.getText());
        }
    }
}
