public class Vegetable extends Plant {
    private char firstLetter;

    public Vegetable(char firstLetter){
        this.crop[0][2] = firstLetter;
        this.firstLetter = firstLetter;
    }

    public void grow() {
       for (int i = 0; i < this.crop.length; i++){
            if (this.crop[i][2] != SOIL && i+1 < this.CROP_SIZE && this.crop[i+1][2] == SOIL){
                this.crop[i+1][2] = firstLetter;
                break;
            }
        }
    }
}
