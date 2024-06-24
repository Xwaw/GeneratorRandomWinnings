import com.google.gson.Gson;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class RandomConverterGenerator {
    float winMultiplier;
    private OptionsManager optionsSave;

    RandomConverterGenerator(int cups){
        Random rand = new Random();

        Gson gson = new Gson();
        Path filePath = Paths.get("optionsSave.json");
        try(FileReader reader = new FileReader(filePath.toFile())){
            optionsSave = gson.fromJson(reader, OptionsManager.class);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error reading a file: " + e.getMessage());
        }

        switch(cups){
            case 2:
                winMultiplier = rand.nextFloat(optionsSave.getCup2()[0], optionsSave.getCup2()[1]);
                break;
            case 3:
                winMultiplier = rand.nextFloat(optionsSave.getCup3()[0], optionsSave.getCup3()[1]);
                break;
            case 4:
                winMultiplier = rand.nextFloat(optionsSave.getCup4()[0], optionsSave.getCup4()[1]);
                break;
            case 5:
                winMultiplier = rand.nextFloat(optionsSave.getCup5()[0], optionsSave.getCup5()[1]);
                break;
        }
    }

    public static String getRandomName() throws IOException {
        InputStream buffer = Main.class.getClassLoader().getResourceAsStream("randomNames.txt");

        if (buffer == null) {
            return null;
        }

        BufferedReader namesToRead = new BufferedReader(new InputStreamReader(buffer));

        Random rand = new Random();

        String line;
        int counter = 0;
        int chooseRandomIndexOfName = rand.nextInt(0,101);

        while((line = namesToRead.readLine()) != null){
            if(counter == chooseRandomIndexOfName){
                return line;
            }

            counter++;
        }

        return null;
    }

    public float getWinMultiplier() {return Math.round(winMultiplier *100)/100.0f;}

    public float getRandomCash(float cash, int space){
        Random randomCash = new Random();

        if (cash < space){
            space = randomCash.nextInt(0, Math.round(cash));
        }
        return randomCash.nextFloat(cash - space, cash + space);
    }

    public static String getRandomTime(int minH, int maxH){
        Random randomTime = new Random();

        int[] time;
        if (minH >= maxH - 1) {
            time = new int [] {maxH, randomTime.nextInt(0,59)};
        }
        time = new int [] {randomTime.nextInt(minH, maxH - 1), randomTime.nextInt(0,59)};

        return String.format("%02d:%02d", time[0], time[1]);
    }
}
