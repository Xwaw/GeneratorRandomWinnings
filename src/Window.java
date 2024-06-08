import javax.swing.*;
import java.awt.*;
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

    String txtName = "test";
    String txtPathName = "C:\\Users\\admin\\Desktop\\" + txtName + ".txt";

    Window(){
        this.setSize(265, 300);
        this.setTitle("Generator random winnings");
        this.setLayout(null);
        //                  X   X    Y   Y   Y
        int[] parameters = {40, 85, 30, 60, 90};

        textCup = new JLabel("Cups: ");
        textCup.setBounds(parameters[0], parameters[2], 50, 20);
        add(textCup, BorderLayout.EAST);
        textCash = new JLabel("Cash: ");
        textCash.setBounds(parameters[0], parameters[3], 50, 20);
        add(textCash, BorderLayout.EAST);
        textCopies = new JLabel("Copies: ");
        textCopies.setBounds(parameters[0], parameters[4], 50, 20);
        add(textCopies, BorderLayout.EAST);

        inputCup = new JTextField("2");
        inputCup.setBounds(parameters[1], parameters[2], 125, 25);
        add(inputCup, BorderLayout.EAST);

        inputCash = new JTextField();
        inputCash.setBounds(parameters[1], parameters[3], 125, 25);
        add(inputCash, BorderLayout.EAST);

        inputCopies = new JTextField();
        inputCopies.setBounds(parameters[1], parameters[4], 125, 25);
        add(inputCopies, BorderLayout.EAST);


        GenerateButton = new JButton("Generate");
        GenerateButton.setBounds(25, 170, 200, 50);
        add(GenerateButton, BorderLayout.SOUTH);

        GenerateButton.addActionListener(this);

        this.add(GenerateButton);

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

                    writer.write("Cash: " + inputCash.getText() + "\n");

                    writer.write("Cash win: " + cw.getWinningVAT() + "\n");
                    writer.write("Cash win: " + cw.getWinning() + "\n");

                    int[] time = randomConv.getRandomTime(1, 24);
                    writer.write("Time: " + LocalDate.now() + "  " + time[0] + ":" + time[1] + "\n");

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
