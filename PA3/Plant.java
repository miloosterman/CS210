//Create abstract methods in this class that will grow/harvest/etc
//Compare type to ENUMS and use overridden pick function
public class Plant {
    private static final int CROP_SIZE = 5;
    private char[][] crop = new char[CROP_SIZE][CROP_SIZE];
    
    public Plant(){
        for (int i = 0; i < crop.length; i++){
            for (int j = 0; j < crop[i].length; j++){
                this.crop[i][j] = '.';
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
