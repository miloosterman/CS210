public class Vegetables extends Plant {
    private char firstLetter;

    public Vegetables(char firstLetter){
        this.crop[0][2] = firstLetter;
        this.firstLetter = firstLetter;
    }

    public void grow() {
        for (int i = CROP_SIZE-1; i < this.crop.length; i--){
            if (this.crop[i][2] != SOIL && i+1 <= CROP_SIZE){
                this.crop[i+1][2] = firstLetter;
                break;
            }
        }
    }
}
