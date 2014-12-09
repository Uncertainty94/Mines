package ru.vsu.csf.minesweeper.model.model;

/**
 * Created by max on 27.10.2014.
 */
public enum Difficulty {
    HARD(15,15,30),
    MEDIUM(10,10,25),
    EASY(5,5,5);

    private int height, width, mineCount;

    Difficulty(int height, int width, int mineCount) {
        this.height = height;
        this.width = width;
        this.mineCount = mineCount;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }
}
