package ru.vsu.csf.minesweeper.model.model;

/**
 * Created by max on 27.10.2014.
 */
public class Cell {
    private Types type;

    private int num;

    public Cell(Types type) {
        this.type = type;
        num = 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }
}
