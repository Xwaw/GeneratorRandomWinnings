public class OptionsManager {

    boolean randomCash;
    int spaceOfRandomCash;
    boolean openGeneratedTxt;
    String cupsToSetAt;
    String generateCupStyle;
    String filePath;
    int[] rangeRandomTime;

    float[] cup2;
    float[] cup3;
    float[] cup4;
    float[] cup5;


    public boolean isRandomCash() {return randomCash;}

    public int getSpaceOfRandomCash() {return spaceOfRandomCash;}

    public boolean isOpenGeneratedTxt() {return openGeneratedTxt;}

    public String getCupsToSetAt() {return cupsToSetAt;}

    public String getGenerateCupStyle() {return generateCupStyle;}

    public String getFilePath() {return filePath;}

    public int[] getRangeRandomTime() {return rangeRandomTime;}

    public float[] getCup2() {return cup2;}

    public void setCup2(float[] cup2) {this.cup2 = cup2;}

    public float[] getCup3() {return cup3;}

    public void setCup3(float[] cup3) {this.cup3 = cup3;}

    public float[] getCup4() {return cup4;}

    public void setCup4(float[] cup4) {this.cup4 = cup4;}

    public float[] getCup5() {return cup5;}

    public void setCup5(float[] cup5) {this.cup5 = cup5;}

    public void setRandomCash(boolean randomCash) {this.randomCash = randomCash;}

    public void setSpaceOfRandomCash(int spaceOfRandomCash) {this.spaceOfRandomCash = spaceOfRandomCash;}

    public void setOpenGeneratedTxt(boolean openGeneratedTxt) {this.openGeneratedTxt = openGeneratedTxt;}

    public void setCupsToSetAt(String cupsToSetAt) {this.cupsToSetAt = cupsToSetAt;}

    public void setGenerateCupStyle(String generateCupStyle) {this.generateCupStyle = generateCupStyle;}

    public void setFilePath(String filePath) {this.filePath = filePath;}

    public void setRangeRandomTime(int[] rangeRandomTime) {this.rangeRandomTime = rangeRandomTime;}
}
