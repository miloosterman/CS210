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

    public abstract void grow();

    public void remove(){
        for (int i = 0; i < this.crop.length; i++){
            for (int j = 0; j < this.crop[i].length; j++){
                this.crop[i][j] = SOIL;
            }
        }
    }

    public void remove(char firstLetter){
        for (int i = 0; i < this.crop.length; i++){
            for (int j = 0; j < this.crop[i].length; j++){
                if (this.crop[i][j] == firstLetter){
                    this.crop[i][j] = SOIL;
                }
            }
        }
    }

    /**
     * Grow the row of crop according to passed argument
     * 
     * @param row
     * @return
     */
    public String printRow(int row) {
        String res = "";
        for (char c : this.crop[row]) {
            res += c;
        }

        return res;
    }
}
