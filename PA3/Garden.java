public class Garden {
    private static final String[] FLOWERS = { "iris", "lily", "rose", "daisy", "tulip", "sunflower" };
    private static final String[] TREES = { "oak", "willow", "banana", "coconut", "pine" };
    private static final String[] VEGETABLES = { "garlic", "zucchini", "tomato", "yam", "lettuce" };

    private Plant[][] crops;
    private int rows;
    private int cols;

    public Garden(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.crops = new Plant[rows][cols];

    }

    public Plant[][] getCrops() {
        return this.crops;
    }

    /**
     * Create plant based on which set of plants the input is a type of
     * 
     * @param row
     * @param col
     * @param plantType
     */
    public void plant(int row, int col, String plantType) {
        if (checkType(plantType, FLOWERS)) {
            crops[row][col] = new Flower(plantType.charAt(0));
        } else if (checkType(plantType, TREES)) {
            crops[row][col] = new Tree(plantType.charAt(0));
        } else if (checkType(plantType, VEGETABLES)) {
            crops[row][col] = new Vegetable(plantType.charAt(0));
        }
    }

    /**
     * Grow method for entire Garden, growing by amount input by user
     * 
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
     * Overloaded grow method for specific plot in Garden
     * 
     * @param amount
     * @param row
     * @param col
     */
    public void grow(int amount, int row, int col) {
        if (row < this.rows && cols < this.cols && crops[row][col] instanceof Plant) {
            for (int i = 0; i < amount; i++) {
                crops[row][col].grow();
            }
        } else {
            System.out.println("Can't grow there.");
        }
    }

    /**
     * Overloaded grow method for class of plant
     * 
     * @param amount
     * @param type
     */
    public void grow(int amount, String type) {
        switch (type) {
            case "flower":
                for (Plant[] pArr : this.crops) {
                    for (Plant p : pArr) {
                        if (p instanceof Flower) {
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
                        if (p instanceof Tree) {
                            for (int i = 0; i < amount; i++) {
                                p.grow();
                            }
                        }
                    }
                }
                break;
            case "vegetable":
                for (Plant[] pArr : this.crops) {
                    for (Plant p : pArr) {
                        if (p instanceof Vegetable) {
                            for (int i = 0; i < amount; i++) {
                                p.grow();
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    public void remove(String plantType) {
        switch (plantType) {
            case "flower":
                for (Plant[] pArr : this.crops) {
                    for (Plant p : pArr) {
                        if (p instanceof Flower) {
                            p.remove();
                            p = null;
                        }
                    }
                }
                break;
            case "tree":
                for (Plant[] pArr : this.crops) {
                    for (Plant p : pArr) {
                        if (p instanceof Tree) {
                            p.remove();
                            p = null;
                        }
                    }
                }
                break;
            case "vegetable":
                for (Plant[] pArr : this.crops) {
                    for (Plant p : pArr) {
                        if (p instanceof Vegetable) {
                            p.remove();
                            p = null;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    public void remove(String plantType, int row, int col) {
        switch (plantType) {
            case "flower":
                if (this.crops[row][col] instanceof Flower) {
                    this.crops[row][col].remove();
                    this.crops[row][col] = null;
                } else {
                    System.out.println("Can't pick there.");
                }
                break;
            case "tree":
                if (this.crops[row][col] instanceof Tree) {
                    this.crops[row][col].remove();
                    this.crops[row][col] = null;
                } else {
                    System.out.println("Can't cut there.");
                }
                break;
            case "vegetable":
                if (this.crops[row][col] instanceof Vegetable) {
                    this.crops[row][col].remove();
                    this.crops[row][col] = null;
                } else {
                    System.out.println("Can't harvest there.");
                }
                break;
            default:
                break;
        }
    }

    public void remove(String plantType, char firstLetter) {
        if (checkType(plantType, FLOWERS)) {
            for (Plant[] pArr : this.crops) {
                for (Plant p : pArr) {
                    if (p instanceof Flower) {
                        p.remove(firstLetter);
                    }
                }
            }
        } else if (checkType(plantType, TREES)) {
            for (Plant[] pArr : this.crops) {
                for (Plant p : pArr) {
                    if (p instanceof Tree) {
                        p.remove(firstLetter);
                    }
                }
            }
        } else if (checkType(plantType, VEGETABLES)) {
            for (Plant[] pArr : this.crops) {
                for (Plant p : pArr) {
                    if (p instanceof Vegetable) {
                        p.remove(firstLetter);
                    }
                }
            }
        }
    }

    /**
     * Compare plantType argument to array of possible plant types, returning true
     * if argument matches
     * 
     * @param plantType
     * @param testArr
     * @return Boolean dependent on match
     */
    public boolean checkType(String plantType, String[] testArr) {
        for (String s : testArr) {
            if (s.equals(plantType)) {
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
                        // res += ".....";
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