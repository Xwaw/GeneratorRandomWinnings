import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    String txtPathName = System.getProperty("user.home") + "\\Desktop\\" + txtName + ".txt";

    JCheckBox checkBoxRandomCash;

    Window(){
        this.setSize(265, 250);
        this.setTitle("Generator random winnings");
        this.setLayout(null);
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
                BufferedWriter writer = new BufferedWriter(new FileWriter(txtPathName));

                writer.write("=====================================================================\n");

                for(int i=0; i<Integer.parseInt(inputCopies.getText()); i++){
                    writer.write("\n");

                    RandomConverterGenerator randomConv = new RandomConverterGenerator(Integer.parseInt(inputCup.getText()));
                    CoreWinnings cw = new CoreWinnings(randomConv.getWinMultiplier(), Float.parseFloat(inputCash.getText()));

                    writer.write(inputCup.getText() + " Match cups: " + "[ " + randomConv.getWinMultiplier() + " ]" + "\n");

                    if(checkBoxRandomCash.isSelected()){
                        writer.write("Cash: " + randomConv.getRandomCash(Integer.parseInt(inputCash.getText()), 50) + "\n"); //In setting value space will be able to change
                    }else{
                        writer.write("Cash: " + inputCash.getText() + "\n");
                    }

                    writer.write("Cash win - VAT: " + cw.getWinningVAT() + "\n");
                    writer.write("Cash win: " + cw.getWinning() + "\n");

                    writer.write("Time: " + LocalDate.now() + "  " + randomConv.getRandomTime(1, 24) + "\n");

                    writer.write("\n");
                    writer.write("=====================================================================\n");
                }

                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

//randomNeed Xwin = new randomNeed(Integer.parseInt(inputCup.getText()));
//   CoreWinnings cw = new CoreWinnings(Xwin.getWinMultiplayer(), Float.valueOf(inputCash.getText()));
//
//            System.out.println(cw.getWinning() + " | " + cw.getWATWinning());
