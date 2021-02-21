import java.util.Random;

public class PlayField {

    private boolean[][] mineField;
    private int xSize;
    private int ySize;
    private int openFields;
    public static long bestTime = Long.MAX_VALUE;
    public PlayField(int xSize, int ySize, int numberOfMines) {
        mineField = new boolean[xSize][ySize];
        Random random = new Random();
        for(int i = 0; i < numberOfMines;) {
            int xIndex = random.nextInt(xSize);
            int yIndex = random.nextInt(ySize);
            if(!mineField[xIndex][yIndex]) {
                mineField[xIndex][yIndex] = true;
                i++;
            }
        }
        this.xSize = xSize;
        this.ySize = xSize;
        openFields = xSize * ySize - numberOfMines;
    }

    public void decrementOpenFields() {
        openFields--;
    }

    public boolean hasWon() {
        return openFields == 0;
    }

    public boolean isThereMine(int x, int y) {
        return mineField[x][y];
    }

    public int getNumberOfMinesAround(int x, int y) {
        int mineCount = 0;

        // top left
        if (x > 0 && y > 0 && mineField[x-1][y-1]) mineCount++;

        // top
        if (y > 0 && mineField[x][y-1]) mineCount++;

        // right top
        if (x < xSize - 1 && y > 0 && mineField[x+1][y-1]) mineCount++;

        // left
        if (x > 0 && mineField[x-1][y]) mineCount++;
        // right
        if (x < xSize - 1 && mineField[x+1][y]) mineCount++;
        // left down
        if (x > 0 && y < ySize - 1 && mineField[x-1][y+1]) mineCount++;
        // down
        if (y < ySize - 1 && mineField[x][y+1]) mineCount++;
        // right down
        if (x < xSize - 1 && y < ySize - 1 && mineField[x+1][y+1]) mineCount++;
        return mineCount;
    }
}
