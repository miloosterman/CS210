//Create abstract methods in this class that will grow/harvest/etc
public abstract class Plant {
    protected final char SOIL = '.';
    protected final int CROP_SIZE = 5;

    protected char[][] crop;

    public Plant() {
        this.crop = new char[CROP_SIZE][CROP_SIZE];
        for (int i = 0; i < this.crop.length; i++) {
            for (int j = 0; j < this.crop[i].length; j++) {
                this.crop[i][j] = SOIL;
            }
        }
    }
    /**
     * Print the row that is passed as an argument from crop
     * @param row
     * @return res
     */
    public String printRow(int row) {
        String res = "";
        for (char c : this.crop[row]) {
            res += c;
        }

        return res;
    }
}
