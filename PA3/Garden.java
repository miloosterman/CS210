public class Garden{

    private int rows;
    private int cols;
    private Plant[][] plants;

    public Garden(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.plants = new Plant[rows][cols];
        for (int i = 0; i < plants.length; i++){
            for (int j = 0; j < plants[i].length; j++){
                this.plants[i][j] = new Plant();
            }
        }
    }

    public String toString(){
        String res = "";
        for (int i=0; i<plants.length;i++){
            for (int j=0; j<plants[i].length;j++){
                res += plants[i][j].printRow(j);
            }
            res += "\n";
        }

        return res;
    }
}