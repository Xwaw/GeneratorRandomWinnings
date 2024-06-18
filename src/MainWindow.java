import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;

public class MainWindow extends JFrame implements ActionListener {
    JTextField inputCup;
    JTextField inputCash;
    JTextField inputCopies;

    JButton GenerateButton;
    JButton OptionsButton;

    String txtName = "test";
    String txtPathName = System.getProperty("user.home") + "/Desktop/" + txtName + ".txt";

    JCheckBox checkBoxRandomCash;

    MainWindow(){
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
        // cordinates        X,  X,  Y,  Y,  Y,  Y,   Y
        final int[] parameters = {40, 85, 20, 50, 80, 120, 175};


        // Jabels, Texts for mainWindow //
        JLabel textCup = new JLabel("Cups: ");
        textCup.setBounds(parameters[0], parameters[2], 50, 20);
        add(textCup);
        JLabel textCash = new JLabel("Cash: ");
        textCash.setBounds(parameters[0], parameters[3], 50, 20);
        add(textCash);
        JLabel textCopies = new JLabel("Copies: ");
        textCopies.setBounds(parameters[0], parameters[4], 50, 20);
        add(textCopies);
        /////////////////////////////////

        // inputBoxes, input for mainWindow //
        inputCup = new JTextField("2");
        inputCup.setBounds(parameters[1], parameters[2], 125, 25);
        add(inputCup);

        inputCash = new JTextField();
        inputCash.setBounds(parameters[1], parameters[3], 125, 25);
        add(inputCash);

        inputCopies = new JTextField();
        inputCopies.setBounds(parameters[1], parameters[4], 125, 25);
        add(inputCopies);
        /////////////////////////////////////

        // CheckBox, it will be dead in this window //
        checkBoxRandomCash = new JCheckBox("Random Cash");
        checkBoxRandomCash.setBounds(130, parameters[6] + 2, 120, 20);
        checkBoxRandomCash.setSelected(true);
        add(checkBoxRandomCash);

        checkBoxRandomCash.addActionListener(this);
        ///////////////////////////////////////////////

        // Buttons, input for mainWindow //
        GenerateButton = new JButton("Generate");
        GenerateButton.setBounds(25, parameters[5], 200, 50);
        add(GenerateButton);

        OptionsButton = new JButton("Options");
        OptionsButton.setBounds(20, parameters[6], 100, 25);
        add(OptionsButton);

        GenerateButton.addActionListener(this);
        OptionsButton.addActionListener(this);
        //////////////////////////////////

        // Some setting for working window //
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        ////////////////////////////////////
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

                Desktop.getDesktop().open(new File(txtPathName));

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == OptionsButton){
            OptionsWindow optionsWindow = new OptionsWindow();
        }
    }
}