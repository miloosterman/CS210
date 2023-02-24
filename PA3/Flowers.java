import java.util.ArrayList;


public class Flowers extends Plant {
    private char firstLetter;

    public Flowers(char firstLetter){
        this.crop[2][2] = firstLetter;
        this.firstLetter = firstLetter;
    }

    public void grow(){
        ArrayList<int[]> newGrowth = new ArrayList<int[]>();
        for (int i = 0; i < this.crop.length; i++){
            for (int j = 0; j < this.crop[i].length; j++){
                if (this.crop[i][j] != this.SOIL){
                    newGrowth.add(new int[]{i,j-1});
                    newGrowth.add(new int[]{i,j+1});
                    newGrowth.add(new int[]{i+1,j});
                    newGrowth.add(new int[]{i-1,j});
                }
                }
            }
        for (int[] i : newGrowth){
            if (i[0] < this.CROP_SIZE && i[1] < this.CROP_SIZE &&
            i[0] >= 0 && i[1] >= 0){
                this.crop[i[0]][i[1]] = this.firstLetter;
            }
        }
        }
    }

