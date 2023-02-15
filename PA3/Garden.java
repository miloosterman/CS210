public class Garden{

    int rows;
    int cols;
    Plant[][] plants;

    public Garden(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.plants = new Plant[rows][cols];
    }

    public void print(){
        for (int i = 0; i < plants.length; i++){
            for (int j = 0; j < plants[i].length; j++){
                System.out.print(plants[i][j]);
            }
            System.out.print("\n");
        }
    }
}