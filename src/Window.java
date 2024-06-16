import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;

public class Window extends JFrame implements ActionListener {
    JLabel textCup;
    JLabel textCash;
    JLabel textCopies;

    JTextField inputCup;
    JTextField inputCash;
    JTextField inputCopies;

    JButton GenerateButton;
    JButton OptionsButton;

    String txtName = "test";
    String txtPathName = System.getProperty("user.home") + "/Desktop/" + txtName + ".txt";

    JCheckBox checkBoxRandomCash;

    Window(){
        this.setSize(265, 250);
        this.setTitle("Generator random winnings");
        this.setLayout(null);

        try {
            Image icon = ImageIO.read(Main.class.getClassLoader().getResource("RTGicon.png"));
            setIconImage(icon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setLocationRelativeTo(null);
        //                  X   X    Y   Y   Y   Y    Y
        int[] parameters = {40, 85, 20, 50, 80, 120, 175};

        textCup = new JLabel("Cups: ");
        textCup.setBounds(parameters[0], parameters[2], 50, 20);
        add(textCup);
        textCash = new JLabel("Cash: ");
        textCash.setBounds(parameters[0], parameters[3], 50, 20);
        add(textCash);
        textCopies = new JLabel("Copies: ");
        textCopies.setBounds(parameters[0], parameters[4], 50, 20);
        add(textCopies);

        inputCup = new JTextField("2");
        inputCup.setBounds(parameters[1], parameters[2], 125, 25);
        add(inputCup);

        inputCash = new JTextField();
        inputCash.setBounds(parameters[1], parameters[3], 125, 25);
        add(inputCash);

        inputCopies = new JTextField();
        inputCopies.setBounds(parameters[1], parameters[4], 125, 25);
        add(inputCopies);


        checkBoxRandomCash = new JCheckBox("Random Cash");
        checkBoxRandomCash.setBounds(130, parameters[6] + 2, 120, 20);
        checkBoxRandomCash.setSelected(true);
        add(checkBoxRandomCash);


        GenerateButton = new JButton("Generate");
        GenerateButton.setBounds(25, parameters[5], 200, 50);
        add(GenerateButton);

        OptionsButton = new JButton("Options");
        OptionsButton.setBounds(20, parameters[6], 100, 25);
        add(OptionsButton);

        GenerateButton.addActionListener(this);
        OptionsButton.addActionListener(this);

        checkBoxRandomCash.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == GenerateButton){
            try {
                BufferedWriter ticketGenerated = new BufferedWriter(new FileWriter(txtPathName));

                ticketGenerated.write("=====================================================================\n");

                float cash = (Math.round(Float.parseFloat(inputCash.getText()))*100)/100;
                int cup = Integer.parseInt(inputCup.getText());
                int copies = Integer.parseInt(inputCopies.getText());

                for(int i=0; i<copies; i++){
                    RandomConverterGenerator randomConv = new RandomConverterGenerator(cup);

                    ticketGenerated.write("Congratulation " + randomConv.getRandomName() + " !" + "\n");

                    if(checkBoxRandomCash.isSelected()){
                        cash = Math.round(randomConv.getRandomCash(cash, 50)); //In setting value space will be able to change
                    }

                    CoreWinnings cw = new CoreWinnings(randomConv.getWinMultiplier(), cash);

                    ticketGenerated.write(cup + " Match cups: " + "[ " + randomConv.getWinMultiplier() + " ]" + "\n");

                    ticketGenerated.write("Cash: " + cash + "\n");

                    ticketGenerated.write("Cash win - VAT: " + cw.getWinningVAT() + "\n");
                    ticketGenerated.write("Cash win: " + cw.getWinning() + "\n");

                    ticketGenerated.write("Time: " + LocalDate.now() + "  " + RandomConverterGenerator.getRandomTime(1, 24) + "\n");

                    ticketGenerated.write("=====================================================================\n");
                }

                ticketGenerated.close();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == OptionsButton){
            OptionsWindow optionsWindow = new OptionsWindow();
        }
    }
}