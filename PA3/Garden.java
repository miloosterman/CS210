public class Garden {
    private static final String[] FLOWERS = { "iris", "lily", "rose", "daisy", "tulip", "sunflower" };
    private static final String[] TREES = { "oak", "willow", "banana", "coconut", "pine" };
    private static final String[] VEGETABLES = { "garlic", "zucchini", "tomato", "yam", "lettuce" };

    private Plant[][] crops;

    public Garden(int rows, int cols) {
        this.crops = new Plant[rows][cols];

    }

    public Plant[][] getCrops() {
        return this.crops;
    }
    /**
     * Create plant based on which set of plants the input is a type of
     * @param row
     * @param col
     * @param plantType
     */
    public void plant(int row, int col, String plantType) {
        if (checkType(plantType, FLOWERS)) {
            crops[row][col] = new Flowers(plantType.charAt(0));
        } else if (checkType(plantType, TREES)) {
            crops[row][col] = new Trees(plantType.charAt(0));
        } else if (checkType(plantType, VEGETABLES)) {
            crops[row][col] = new Vegetables(plantType.charAt(0));
        }
    }
    /**
     * Grow method for entire Garden, growing by amount input by user
     * @param amount
     */
    public void grow(int amount) {
        for (Plant[] pArr : this.crops) {
            for (Plant p : pArr) {
                if (p instanceof Plant) {
                    for (int i = 0; i < amount; i++) {
                        p.grow();
                    }
                }
            }
        }
    }
    /**
     * Grow method for specific plot in Garden
     * @param amount
     * @param row
     * @param col
     */
    public void grow(int amount, int row, int col){
        if (crops[row][col] instanceof Plant){
            for (int i = 0; i < amount; i++){
                crops[row][col].grow();
            }
        }
    }

    public void grow(int amount, String type){
        switch (type) {
            case "flower":
            for (Plant[] pArr : this.crops) {
                for (Plant p : pArr) {
                    if (p instanceof Flowers) {
                        for (int i = 0; i < amount; i++) {
                            p.grow();
                        }
                    }
                }
            }
                break;
            case "tree":
            for (Plant[] pArr : this.crops) {
                for (Plant p : pArr) {
                    if (p instanceof Trees) {
                        for (int i = 0; i < amount; i++) {
                            p.grow();
                        }
                    }
                }
            }

            case "vegetable":
            for (Plant[] pArr : this.crops) {
                for (Plant p : pArr) {
                    if (p instanceof Vegetables) {
                        for (int i = 0; i < amount; i++) {
                            p.grow();
                        }
                    }
                }
            }
        
            default:
                break;
        }
    }

    public boolean checkType(String plantType, String[] testArr) {
        for (String s : testArr) {
            if (s == plantType) {
                return true;
            }
        }
        return false;
    }

    // Iterate through 2d array of crops, adding the first line of each column to
    // string then new line repeat
    public String toString() {
        String res = "";
        for (int i = 0; i < this.crops.length; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < this.crops[i].length; k++) {
                    if (this.crops[i][k] == null) {
                        res += ".....";
                    } else {
                        res += this.crops[i][k].printRow(j);
                    }
                }
                res += "\n";
            }
        }

        return res;
    }
}