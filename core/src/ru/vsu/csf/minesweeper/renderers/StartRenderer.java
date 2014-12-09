package ru.vsu.csf.minesweeper.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by max on 09.12.2014.
 */
public class StartRenderer {

    private static final int EASY         = 1;
    private static final int MED          = 2;
    private static final int HARD         = 3;


    private static final int BTN_HEIGHT         = 60;
    private static final int BTN_WIDTH          = 120;
    private static final int BTNS_Y             = 600;
    private static final int BTN_EASY_LEFT_X    = 90;
    private static final int BTN_MED_LEFT_X     = 250;
    private static final int BTN_HARD_LEFT_X    = 390;


    private TextureRegion simple;
    private TextureRegion standart;
    private TextureRegion hard;
    private BitmapFont font;


    public StartRenderer() {
        simple = new TextureRegion(new Texture(Gdx.files.internal("gfx/Easylevel.png")));
        standart = new TextureRegion(new Texture(Gdx.files.internal("gfx/Mediumlevel.png")));
        hard = new TextureRegion(new Texture(Gdx.files.internal("gfx/Hardlevel.png")));
        font = new BitmapFont() {{
            setColor(Color.NAVY);
        }};

    }

    public void render(SpriteBatch batch) {
        batch.draw(new Texture(Gdx.files.internal("gfx/backgroundStart.png")),0,0);
        font.draw(batch, "Made by Mishanin Max. 3.1 group", 200, 200);
        batch.draw(simple, BTN_EASY_LEFT_X, BTNS_Y);
        batch.draw(standart, BTN_MED_LEFT_X, BTNS_Y);
        batch.draw(hard, BTN_HARD_LEFT_X, BTNS_Y);
    }

    public int onLMBClick(int x, int y) {

        if (inBtn(x, y, BTN_EASY_LEFT_X, BTNS_Y)){
            return EASY;
        }
        if (inBtn(x, y, BTN_MED_LEFT_X, BTNS_Y)){
            return MED;
        }
        if (inBtn(x, y, BTN_HARD_LEFT_X, BTNS_Y)){
            return HARD;
        }

        return -1;
    }

    private boolean inBtn(int x, int y, float leftBotX, float leftBotY){

        if ((x >= leftBotX) && (x <= (leftBotX + BTN_WIDTH)) && (y >= leftBotY) && (y <= (leftBotY + BTN_HEIGHT))){
            return  true;
        }
        else
            return  false;

    }


}
