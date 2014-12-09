package ru.vsu.csf.minesweeper.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import ru.vsu.csf.minesweeper.model.model.Difficulty;
import ru.vsu.csf.minesweeper.model.model.Field;
import ru.vsu.csf.minesweeper.renderers.FieldRenderer;

public class FieldScreen extends AbstractScreen{

    FieldRenderer renderer;

    public FieldScreen(Game game, int diff) {
        super(game);
        renderer = new FieldRenderer(new Field(diff));
    }

    @Override
    public void show() {
        super.show();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Gdx.graphics.getHeight() - screenY;
                switch (button) {
                    case Input.Buttons.LEFT:
                        renderer.onLMBClick(screenX, screenY);
                        break;
                    case Input.Buttons.RIGHT:
                        renderer.onRMBClick(screenX, screenY);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void render(float delta) {
        super.render(delta);


        batch.begin();
        renderer.render(batch);
        batch.end();
        if (renderer.isGoStartScreen()) {
            game.setScreen(new StartScreen(game));
            dispose();
        }
    }
}
