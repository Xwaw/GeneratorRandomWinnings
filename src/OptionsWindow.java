import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OptionsWindow extends JFrame implements ActionListener {
    OptionsWindow(){
        this.setSize(265, 250);
        this.setTitle("Options");
        this.setLayout(null);

        try {
            Image icon = ImageIO.read(Main.class.getClassLoader().getResource("settingsIcon.png"));
            setIconImage(icon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
