import java.io.*;
import java.util.Random;

public class RandomConverterGenerator {
    float winMultiplier;

    RandomConverterGenerator(int cups){
        Random rand = new Random();
        switch(cups){
            case 2:
                winMultiplier = rand.nextFloat(2.0f, 3.5f);
                break;
            case 3:
                winMultiplier = rand.nextFloat(4.0f, 7.0f);
                break;
            case 4:
                winMultiplier = rand.nextFloat(8.0f, 10.5f);
                break;
            case 5:
                winMultiplier = rand.nextFloat(11.0f, 15.0f);
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

    public float getRandomCash(float cash, int space){
        Random randomCash = new Random();

        if (cash < space){
            space = randomCash.nextInt(0, Math.round(cash));
        }
        return randomCash.nextFloat(cash - space, cash + space);
    }

    public float getWinMultiplier() {
        return Math.round(winMultiplier *100)/100.0f;
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
