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

    public int getRandomCash(float cash, int space){
        Random randomCash = new Random();

        if (cash < space){
            space = Math.round(cash);
        }
        return randomCash.nextInt(Math.round(cash) - space, Math.round(cash) + space) ;
    }

    public float getWinMultiplier() {
        return Math.round(winMultiplier *100)/100.0f;
    }

    public String getRandomTime(int minH, int maxH){
        Random randomTime = new Random();

        int[] time;
        if (minH >= maxH - 1) {
            time = new int [] {maxH, randomTime.nextInt(0,59)};
        }
        time = new int [] {randomTime.nextInt(minH, maxH - 1), randomTime.nextInt(0,59)};

        return String.format("%02d:%02d", time[0], time[1]);
    }
}
