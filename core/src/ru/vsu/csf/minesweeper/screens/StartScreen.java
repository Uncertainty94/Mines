package ru.vsu.csf.minesweeper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by max on 09.12.2014.
 */
public class StartScreen extends AbstractScreen {
    BitmapFont font;

    public StartScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        font = new BitmapFont() {{
            setColor(Color.NAVY);
        }};
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(new Texture(Gdx.files.internal("gfx/backgroundStart.png")),0,0);
        font.draw(batch, "Tap anywhere to begin!", 200, 250);
        batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new FieldScreen(game));
            dispose();
        }
    }

}
