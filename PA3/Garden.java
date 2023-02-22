public class Garden{
    private static final int MAX_COLS = 16;
    private static final String[] FLOWERS = {"iris", "lily", "rose", "daisy", "tulip", "sunflower"};
    private static final String[] TREES = {"oak", "willow", "banana", "coconut", "pine"};
    private static final String[] VEGETABLES = {"garlic", "zucchini", "tomato", "yam", "lettuce"};
    
    private int rows;
    private int cols;
    private Plant[][] crops;

    public Garden(int rows, int cols){
        if (cols > MAX_COLS){
            cols = 16;
        }
        this.rows = rows;
        this.cols = cols;
        this.crops = new Plant[rows][cols];
        for (int i = 0; i < crops.length; i++){
            for (int j = 0; j < crops[i].length; j++){
                this.crops[i][j] = new Plant();
            }
        }
    }

    public void plant(int row, int col, String plantType){
        if (checkType(plantType, FLOWERS)){
            crops[row][col] = new Flowers(plantType.charAt(0));
        } else if (checkType(plantType, TREES)){
            crops[row][col] = new Trees(plantType.charAt(0));
        } else if (checkType(plantType, VEGETABLES)){
            crops[row][col] = new Vegetables(plantType.charAt(0));
        }
    }

    public boolean checkType(String plantType, String[] testArr){
        for (String s : testArr){
            if (s == plantType){
                return true;
            }
        }
        return false;
    }

    //Iterate through 2d array of crops, adding the first line of each column to string then new line repeat
    public String toString(){
        String res = "";
        for (int i=0; i < this.crops.length; i++){
            for (int j=0; j < 5; j++){
                for (int k=0; k < this.crops[i].length; k++){
                    res += this.crops[i][k].printRow(j);
                }
                res += "\n";
            }
        }

        return res;
    }
}