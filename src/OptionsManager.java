public class OptionsManager {
    boolean randomCash;
    int spaceOfRandomCash;
    boolean openGeneratedTxt;
    int cupsToSetAt;
    String generateCupStyle;
    String filePath;
    int[] rangeRandomTime;
    float[][] rangeRandomWinMultiplayer;

    public boolean isRandomCash() {return randomCash;}

    public int getSpaceOfRandomCash() {return spaceOfRandomCash;}

    public boolean isOpenGeneratedCash(boolean selected) {return openGeneratedTxt;}

    public int getCupsToSetAt() {return cupsToSetAt;}

    public String getGenerateCupStyle() {return generateCupStyle;}

    public String getFilePath() {return filePath;}

    public int[] getRangeRandomTime() {return rangeRandomTime;}

    public float[][] getRangeRandomWinMultiplayer() {return rangeRandomWinMultiplayer;}

    public void setRandomCash(boolean randomCash) {this.randomCash = randomCash;}

    public void setSpaceOfRandomCash(int spaceOfRandomCash) {this.spaceOfRandomCash = spaceOfRandomCash;}

    public void setOpenGeneratedTxt(boolean openGeneratedTxt) {this.openGeneratedTxt = openGeneratedTxt;}

    public void setCupsToSetAt(int cupsToSetAt) {this.cupsToSetAt = cupsToSetAt;}

    public void setGenerateCupStyle(String generateCupStyle) {this.generateCupStyle = generateCupStyle;}

    public void setFilePath(String filePath) {this.filePath = filePath;}

    public void setRangeRandomTime(int[] rangeRandomTime) {this.rangeRandomTime = rangeRandomTime;}

    public void setRangeRandomWinMultiplayer(float[][] rangeRandomWinMultiplayer) {this.rangeRandomWinMultiplayer = rangeRandomWinMultiplayer;}
}
