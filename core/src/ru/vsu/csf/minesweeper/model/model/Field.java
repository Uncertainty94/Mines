package ru.vsu.csf.minesweeper.model.model;

import java.util.Random;

/**
 * Created by max on 27.10.2014.
 */
public class Field {

    private static final int EASY   = 1;
    private static final int MEDIUM = 2;
    private static final int HARD   = 3;

    private Difficulty difficulty;

    private Cell[][] field;
    private boolean[][] openedClosedField;
    private boolean[][] flags;
    private boolean freeze;

    public Field(int diff) {
        switch (diff){
            case EASY   : {
                difficulty = Difficulty.EASY;
                break;
            }
            case MEDIUM : {
                difficulty = Difficulty.MEDIUM;
                break;
            }
            case HARD   : {
                difficulty = Difficulty.HARD;
                break;
            }
            default: difficulty = Difficulty.MEDIUM;
        }

        resetField();

    }

    private void setMines(int mineCount){
        Random random = new Random();
        int x, y;

        while (mineCount > 0){
            x = random.nextInt(field.length);
            y = random.nextInt(field[0].length);
            if (field[x][y] != null && !field[x][y].getType().equals(Types.BOMB)){
                field[x][y].setType(Types.BOMB);
                mineCount--;
            }
        }
    }

    private void setNumbers(){
        int aroundMineCount = 0;

        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field[i].length; j++) {
                aroundMineCount = 0;

                if (!field[i][j].getType().equals(Types.BOMB)) {
                    if ((i - 1) >= 0) {
                        if ((j - 1 >= 0) && (field[i - 1][j - 1].getType().equals(Types.BOMB))) {
                            aroundMineCount++;
                        }
                        if ((j + 1 < field[i - 1].length) && (field[i - 1][j + 1].getType().equals(Types.BOMB))) {
                            aroundMineCount++;
                        }
                        if (field[i - 1][j].getType().equals(Types.BOMB)) {
                            aroundMineCount++;
                        }
                    }

                    if ((i + 1) < field.length) {
                        if ((j - 1 >= 0) && (field[i + 1][j - 1].getType().equals(Types.BOMB))) {
                            aroundMineCount++;
                        }
                        if ((j + 1 < field[i + 1].length) && (field[i + 1][j + 1].getType().equals(Types.BOMB))) {
                            aroundMineCount++;
                        }
                        if (field[i + 1][j].getType().equals(Types.BOMB)) {
                            aroundMineCount++;
                        }
                    }

                    if (((j + 1) < field[i].length) && (field[i][j + 1].getType().equals(Types.BOMB))) {
                        aroundMineCount++;
                    }

                    if (((j - 1) >= 0) && (field[i][j - 1].getType().equals(Types.BOMB))) {
                        aroundMineCount++;
                    }

                    if (aroundMineCount > 0) {
                        field[i][j].setType(Types.NUMBER);
                        field[i][j].setNum(aroundMineCount);
                    }
                }
            }
        }
    }

    private void typeField(){

        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field[i].length; j++){
                if (field[i][j].getType().equals(Types.NUMBER)){
                    System.out.print(field[i][j].getNum() + "\t");
                } else {
                    System.out.print(field[i][j].getType().name().substring(0,1) + "\t");
                }
            }
            System.out.println();
        }
    }

    public void resetField() {
        int mineCount = difficulty.getMineCount(), height = difficulty.getHeight(), width = difficulty.getWidth();
        freeze = false;
        openedClosedField = new boolean[difficulty.getWidth()][difficulty.getHeight()];
        flags = new boolean[difficulty.getWidth()][difficulty.getHeight()];
        field = new Cell[height][width];

        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field[i].length; j++) {
                field[i][j]= new Cell(Types.EMPTY);
                openedClosedField[i][j] = false;
                flags[i][j] = false;
            }

        setMines(mineCount);
//        typeField();
//        System.out.println("\n\n\n\n");
        setNumbers();
//        typeField();
//        System.out.println("\n\n\n\n");
    }

    public void leftClickOnCell(int x, int y){

        if (!freeze) {

            if (!flags[x][y]) {

                if (field[x][y].getType().equals(Types.NUMBER)) {
                    openedClosedField[x][y] = true;
                } else if (field[x][y].getType().equals(Types.BOMB)) {
                    openedClosedField[x][y] = true;
                    freeze = true;
                } else if (field[x][y].getType().equals(Types.EMPTY)) {
                    openedClosedField[x][y] = true;

                    if ((x - 1) >= 0) {
                        if ((y - 1 >= 0)) {
                            if (!openedClosedField[x - 1][y - 1])
                                leftClickOnCell(x - 1, y - 1);
                        }
                        if (y + 1 < field[0].length) {
                            if (!openedClosedField[x - 1][y + 1])
                                leftClickOnCell(x - 1, y + 1);
                        }
                        leftClickOnCell(x - 1, y);
                    }

                    if ((x + 1) < field.length) {
                        if (y - 1 >= 0) {
                            if (!openedClosedField[x + 1][y - 1])
                                leftClickOnCell(x + 1, y - 1);
                        }
                        if (y + 1 < field[0].length) {
                            if (!openedClosedField[x + 1][y + 1])
                                leftClickOnCell(x + 1, y + 1);
                        }
                        if (!openedClosedField[x + 1][y])
                            leftClickOnCell(x + 1, y);
                    }

                    if ((y + 1) < field[0].length) {
                        if (!openedClosedField[x][y + 1])
                            leftClickOnCell(x, y + 1);
                    }

                    if ((y - 1) >= 0) {
                        if (!openedClosedField[x][y - 1])
                            leftClickOnCell(x, y - 1);
                    }
                }
            }
        }
    }

    public void rightClickOnCell(int x, int y){
        if (!freeze) {
            if (!openedClosedField[x][y]) {
                if (!flags[x][y])
                    flags[x][y] = true;
                else
                    flags[x][y] = false;
            }
        }
    }


    public Cell[][] getField() {
        return field;
    }

    public boolean[][] getOpenedClosedField() {
        return openedClosedField;
    }

    public boolean[][] getFlags() {
        return flags;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public boolean isWin() {
        int countOpened = 0, countFlags = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (flags[i][j])
                    countFlags++;
                if (openedClosedField[i][j])
                    countOpened++;
            }
        }
        if (countFlags == difficulty.getMineCount() && (countFlags + countOpened) == field.length*field.length) {
            freeze = true;
            return true;
        }
        else
            return false;
    }
}
