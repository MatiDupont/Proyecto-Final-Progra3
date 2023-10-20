import javax.swing.*;
import java.awt.*;

public class Panel1 extends JPanel{
    private JTextArea textArea;
    public Panel1(){
        setLayout(null);
        setBackground(Color.BLUE);
        iniciarComponentes();
   }

   private void iniciarComponentes(){
        textArea = new JTextArea();
        textArea.setBounds(10,10,350,181);
        textArea.setText("PENSAS FUNCIONAR?");

        add(textArea);
   }

}
