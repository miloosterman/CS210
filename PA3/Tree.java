public class Tree extends Plant {
    private char firstLetter;

    public Tree(char firstLetter) {
        this.crop[4][2] = firstLetter;
        this.firstLetter = firstLetter;
    }

    public void grow() {
        for (int i = 0; i < this.crop.length; i++){
            if (this.crop[i][2] != SOIL && i-1 >= 0 && this.crop[i-1][2] == SOIL){
                this.crop[i-1][2] = firstLetter;
                break;
            }
        }
    }
}
