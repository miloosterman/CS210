//Create abstract methods in this class that will grow/harvest/etc
public class Plant {
    private final char SOIL = '.';
    private final int CROP_SIZE = 5;

    protected char[][] crop = new char[CROP_SIZE][CROP_SIZE];
    
    public Plant(){
        for (int i = 0; i < crop.length; i++){
            for (int j = 0; j < crop[i].length; j++){
                this.crop[i][j] = SOIL;
            }
        }
    }

    public String printRow(int row){
        String res = "";
        for (char c : this.crop[row]){
            res += c;
        }

        return res;
}
}
