import com.google.gson.Gson;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OptionsWindow extends JFrame implements ActionListener {
    JCheckBox checkBoxRandomCash;
    JCheckBox checkBoxOpeningAfterGenerate;

    JComboBox comboBoxRandomCups;
    JComboBox comboBoxValuesCups;

    JTextField minValueForCups;
    JTextField maxValueForCups;
    JTextField inputFilePath;
    JTextField minTime;
    JTextField maxTime;
    JTextField spaceInput;

    JLabel valueCupsText;

    JButton saveOptionsButton;

    OptionsWindow(){
        this.setSize(420, 300);
        this.setTitle("Options");
        this.setLayout(null);

        OptionsManager fromOptionsSave = null;

        Gson gson = new Gson();
        Path filePath = Paths.get("optionsSave.json");
        try(FileReader reader = new FileReader(filePath.toFile())){
            fromOptionsSave = gson.fromJson(reader, OptionsManager.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Image icon = ImageIO.read(Main.class.getClassLoader().getResource("settingsIcon.png"));
            setIconImage(icon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //                        X1, Y1, X2, Y2, X3, Y3,  X4, Y4, X5, Y5,  X6, Y6,  X7, Y7,  X8, Y8,  X9, Y9, X10,Y10, X11, Y11,X12,Y12, X13, Y13,X14,Y14,X15,Y15,X16,Y16,X17,Y17,X18,Y18,X19,Y19,X20,Y20
        final int[] parameters = {15, 15, 15, 45, 15, 75, 115, 75,250, 15, 320, 15, 285, 55, 340, 55, 240, 55, 295, 85, 350, 85, 15, 220, 145, 220, 15,120,150,120,185,120,150,140,185,140,235,145,145,10};
        //                        0,  1,  2,  3,  4,  5,  6,   7,  8,  9,  10,  11, 12,  13, 14,  15, 16,  17, 18,  19, 20,  21, 22,  23, 24,  25,  26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,38,39

        JLabel generatedCupsText = new JLabel("Generated cups");
        generatedCupsText.setBounds(parameters[4], parameters[5], 100, 30);
        add(generatedCupsText);
        JLabel cupsToSetText = new JLabel("Cups to set");
        cupsToSetText.setBounds(parameters[8], parameters[9], 80, 30);
        add(cupsToSetText);
        JLabel minText = new JLabel("min.");
        minText.setBounds(parameters[18], parameters[19], 50, 30);
        add(minText);
        JLabel maxText = new JLabel("max.");
        maxText.setBounds(parameters[20], parameters[21], 50, 30);
        add(maxText);
        JLabel filePathText = new JLabel("Path for creating TXT:");
        filePathText.setBounds(parameters[22], parameters[23], 150, 30);
        add(filePathText);
        JLabel rangeRandomTimeText = new JLabel("Range for random time:");
        rangeRandomTimeText.setBounds(parameters[26], parameters[27], 150, 30);
        add(rangeRandomTimeText);
        JLabel minTextTime = new JLabel("min.");
        minTextTime.setBounds(parameters[32], parameters[33], 50, 30);
        add(minTextTime);
        JLabel maxTextTime = new JLabel("max.");
        maxTextTime.setBounds(parameters[34], parameters[35], 50, 30);
        add(maxTextTime);

        //////////////////////////////////
        checkBoxRandomCash = new JCheckBox("Set random Cash");
        checkBoxRandomCash.setBounds(parameters[0], parameters[1], 130, 20);
        checkBoxRandomCash.setSelected(fromOptionsSave.isRandomCash());
        add(checkBoxRandomCash);
        spaceInput = new JTextField(String.valueOf(fromOptionsSave.getSpaceOfRandomCash()));
        spaceInput.setBounds(parameters[38], parameters[39], 50, 30);
        add(spaceInput);
        /////////////////////////////////

        checkBoxOpeningAfterGenerate = new JCheckBox("Open After Generate");
        checkBoxOpeningAfterGenerate.setBounds(parameters[2], parameters[3], 150, 20);
        checkBoxOpeningAfterGenerate.setSelected(fromOptionsSave.isOpenGeneratedTxt());
        add(checkBoxOpeningAfterGenerate);

        String[] listOfGeneratingWithCups = { "Constant", "Random", "In sequence"};
        comboBoxRandomCups = new JComboBox(listOfGeneratingWithCups);
        comboBoxRandomCups.setBounds(parameters[6], parameters[7], 100, 30);
        comboBoxRandomCups.setSelectedItem(fromOptionsSave.getGenerateCupStyle());
        add(comboBoxRandomCups);

        String[] valuesOfCups = {"2", "3", "4", "5"};
        comboBoxValuesCups = new JComboBox(valuesOfCups);
        comboBoxValuesCups.setBounds(parameters[10], parameters[11], 40, 30);
        comboBoxValuesCups.setSelectedItem(fromOptionsSave.getCupsToSetAt());
        add(comboBoxValuesCups);
        comboBoxValuesCups.addActionListener(this);


        minValueForCups = new JTextField();
        minValueForCups.setBounds(parameters[12], parameters[13], 50, 30);
        add(minValueForCups);

        maxValueForCups = new JTextField();
        maxValueForCups.setBounds(parameters[14], parameters[15], 50, 30);
        add(maxValueForCups);

        inputFilePath = new JTextField(fromOptionsSave.getFilePath());
        inputFilePath.setBounds(parameters[24], parameters[25], 250, 30);
        add(inputFilePath);

        int[] timeRange = fromOptionsSave.getRangeRandomTime();

        minTime = new JTextField(String.valueOf(timeRange[0]));
        minTime.setBounds(parameters[28], parameters[29], 30, 30);
        add(minTime);

        maxTime = new JTextField(String.valueOf(timeRange[1]));
        maxTime.setBounds(parameters[30], parameters[31], 30, 30);
        add(maxTime);


        saveOptionsButton = new JButton("Save options");
        saveOptionsButton.setBounds(parameters[36], parameters[37], 150, 40);
        add(saveOptionsButton);
        saveOptionsButton.addActionListener(this);


        valueCupsText = new JLabel(comboBoxValuesCups.getSelectedItem().toString() + " Cups:");
        valueCupsText.setBounds(parameters[16], parameters[17], 80, 30);
        add(valueCupsText);

        setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == comboBoxValuesCups){
            valueCupsText.setText(comboBoxValuesCups.getSelectedItem().toString() + " cups:");
        }
        if(e.getSource() == saveOptionsButton){
            OptionsManager optionsSave = new OptionsManager();

            optionsSave.setRandomCash(checkBoxRandomCash.isSelected());
            optionsSave.setOpenGeneratedTxt(checkBoxOpeningAfterGenerate.isSelected());

            optionsSave.setSpaceOfRandomCash(Integer.parseInt(spaceInput.getText()));
            optionsSave.setCupsToSetAt(comboBoxValuesCups.getSelectedItem().toString());

            optionsSave.setGenerateCupStyle(comboBoxRandomCups.getSelectedItem().toString());
            optionsSave.setFilePath(inputFilePath.getText());

            int[] timeRangeToSet = {Integer.parseInt(minTime.getText()), Integer.parseInt(maxTime.getText())};

            if(timeRangeToSet[0] > timeRangeToSet[1]) {
                timeRangeToSet[0] = timeRangeToSet[1];
            }

            if(timeRangeToSet[0] < 1 || timeRangeToSet[1] < 1){
                timeRangeToSet[0] = 1;
                timeRangeToSet[1] = 24;
            }else if(timeRangeToSet[1] > 24 || timeRangeToSet[0] > 24){
                timeRangeToSet[1] = 24;
                timeRangeToSet[0] = 1;
            }

            optionsSave.setRangeRandomTime(timeRangeToSet);
            //optionsSave.setRangeRandomWinMultiplayer(new float[][] {Float.parseFloat()});

            Gson gson = new Gson();
            String json = gson.toJson(optionsSave);
            Path filePath = Paths.get("optionsSave.json");

            try(FileWriter writer = new FileWriter(filePath.toFile())){
                writer.write(json);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
