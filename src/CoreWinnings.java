public class CoreWinnings {
    private final float VAT = 0.88f; // -12% wat
    final float Winning; //Cash after win
    final float WATWinning; // Cash after win - 12% WAT

    CoreWinnings(float winMultiplayer, float cashSet){
        Winning = cashSet * winMultiplayer; // Win

        WATWinning = Winning * VAT; // WatWin
    }

    public float getWinning() {return Math.round(Winning * 100)/100.0f;}// 0.00

    public float getWinningVAT() {return Math.round(WATWinning * 100)/100.0f;}// 0.00 -12% wat
}
