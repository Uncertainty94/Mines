package ru.vsu.csf.minesweeper.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vsu.csf.minesweeper.model.model.Cell;
import ru.vsu.csf.minesweeper.model.model.Field;

public class FieldRenderer {

    private static float CELL_SIZE = 30;
    private static final float RESTART_SIZE = 60;

    private static final int LVL_WIDTH = 120;
    private static final int LVL_HEIGHT = 60;

    boolean goStartScreen;

    private Field field;
    private TextureRegion openedCell;
    private TextureRegion background;
    private TextureRegion closedCell;
    private TextureRegion flagCell;
    private TextureRegion restartGood;
    private TextureRegion restartBad;
    private TextureRegion mine;
    private TextureRegion restartHappy;
    private TextureRegion win;
    private TextureRegion level;
    private BitmapFont font;


    public FieldRenderer(Field field) {
        this.field = field;
        flagCell = new TextureRegion(new Texture(Gdx.files.internal("gfx/flag.png")));
        mine = new TextureRegion(new Texture(Gdx.files.internal("gfx/mine.png")));
        openedCell = new TextureRegion(new Texture(Gdx.files.internal("gfx/openedCell.png")));
        closedCell = new TextureRegion(new Texture(Gdx.files.internal("gfx/closedCell.png")));
        background = new TextureRegion(new Texture(Gdx.files.internal("gfx/background.png")));
        restartGood = new TextureRegion(new Texture(Gdx.files.internal("gfx/restartGood.png")));
        restartBad = new TextureRegion(new Texture(Gdx.files.internal("gfx/restartBad.png")));
        restartHappy = new TextureRegion(new Texture(Gdx.files.internal("gfx/restartHappy.png")));
        win = new TextureRegion(new Texture(Gdx.files.internal("gfx/win.png")));
        level = new TextureRegion(new Texture(Gdx.files.internal("gfx/level.png")));
        goStartScreen = false;
        font = new BitmapFont() {{
            setColor(Color.NAVY);
        }};
        float widthRatio = Gdx.graphics.getWidth() / field.getField().length;
        float heightRatio = Gdx.graphics.getHeight() / field.getField()[0].length;
        CELL_SIZE = (widthRatio < heightRatio) ? widthRatio : heightRatio;
    }

    public void render(SpriteBatch batch) {
        Cell[][] cells = field.getField();
        boolean[][] openedClosed = field.getOpenedClosedField();
        boolean[][] flags = field.getFlags();

        batch.draw(background,
                0,
                0);

        batch.draw(level,450, 620);

        if (field.isWin()){
            batch.draw(win,
                    0,
                    0);
            batch.draw(restartHappy,
                    cells.length / 2 * CELL_SIZE + CELL_SIZE / 4,
                    cells[0].length * CELL_SIZE + 20,
                    RESTART_SIZE,
                    RESTART_SIZE);
        } else {
            if (!field.isFreeze())
                batch.draw(restartGood,
                        cells.length / 2 * CELL_SIZE + CELL_SIZE / 4,
                        cells[0].length * CELL_SIZE + 20,
                        RESTART_SIZE,
                        RESTART_SIZE);
            else
                batch.draw(restartBad,
                        cells.length / 2 * CELL_SIZE + CELL_SIZE / 4,
                        cells[0].length * CELL_SIZE + 20,
                        RESTART_SIZE,
                        RESTART_SIZE);

            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {

                    if (flags[i][j]) {
                        batch.draw(flagCell,
                                i * CELL_SIZE,
                                j * CELL_SIZE,
                                CELL_SIZE,
                                CELL_SIZE);
                    } else {

                        if (!openedClosed[i][j]) {
                            batch.draw(closedCell,
                                    i * CELL_SIZE,
                                    j * CELL_SIZE,
                                    CELL_SIZE,
                                    CELL_SIZE);
                        } else {
                            if (cells[i][j].getType().toString().substring(0, 1).equals("N")) {
                                batch.draw(openedCell,
                                        i * CELL_SIZE,
                                        j * CELL_SIZE,
                                        CELL_SIZE,
                                        CELL_SIZE);
                                font.draw(batch, cells[i][j].getNum() + "", i * CELL_SIZE + (CELL_SIZE / 2), j * CELL_SIZE + (CELL_SIZE / 2));
                            }
                            if (cells[i][j].getType().toString().substring(0, 1).equals("E")) {
                                batch.draw(openedCell,
                                        i * CELL_SIZE,
                                        j * CELL_SIZE,
                                        CELL_SIZE,
                                        CELL_SIZE);
                            }

                            if (cells[i][j].getType().toString().substring(0, 1).equals("B")) {
                                batch.draw(mine,
                                        i * CELL_SIZE,
                                        j * CELL_SIZE,
                                        CELL_SIZE,
                                        CELL_SIZE);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onLMBClick(int x, int y) {

        if (inRestartPolygon(x, y, field.getField().length / 2 * CELL_SIZE + CELL_SIZE / 4, field.getField()[0].length * CELL_SIZE + 20)){
            field.resetField();
        } else {
            if (inLvlPolygon(x, y, 450, 620)) {
                goStartScreen = true;
            } else {
                x /= CELL_SIZE;
                y /= CELL_SIZE;

                if (x < field.getField().length && y < field.getField().length)
                    field.leftClickOnCell(x, y);
            }
        }

    }

    public void onRMBClick(int x, int y) {
        x /= CELL_SIZE;
        y /= CELL_SIZE;

        field.rightClickOnCell(x, y);
    }

    private boolean inRestartPolygon(int x, int y, float leftBotX, float leftBotY){

        if ((x >= leftBotX) && (x <= (leftBotX + RESTART_SIZE)) && (y >= leftBotY) && (y <= (leftBotY + RESTART_SIZE))){
            return  true;
        }
        else
            return  false;

    }

    private boolean inLvlPolygon(int x, int y, float leftBotX, float leftBotY){

        if ((x >= leftBotX) && (x <= (leftBotX + LVL_WIDTH)) && (y >= leftBotY) && (y <= (leftBotY + LVL_HEIGHT))){
            return  true;
        }
        else
            return  false;

    }

    public boolean isGoStartScreen() {
        return goStartScreen;
    }
}