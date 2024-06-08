import java.util.Random;

public class RandomConverterGenerator {
    float winMultiplier;

    RandomConverterGenerator(int Cups){
        Random rand = new Random();
        switch(Cups){
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

    public float getRandomCash(float cash, int space){
        Random randomCash = new Random();
        return Math.round(randomCash.nextFloat(cash - space, cash + space)*100)/100.0f;
    }

    public float getWinMultiplier() {
        return Math.round(winMultiplier *100)/100.0f;
    }

    public int[] getRandomTime(int minH, int maxH){
        Random randomTime = new Random();

        if (minH >= maxH - 1) {
            return new int [] {maxH, randomTime.nextInt(0,59)};
        }
        return new int [] {randomTime.nextInt(minH, maxH - 1), randomTime.nextInt(0,59)};
    }
}
