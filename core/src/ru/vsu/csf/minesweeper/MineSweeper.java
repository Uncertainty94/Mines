package ru.vsu.csf.minesweeper;

import com.badlogic.gdx.Game;
import ru.vsu.csf.minesweeper.screens.FieldScreen;
import ru.vsu.csf.minesweeper.screens.StartScreen;

import java.awt.*;

public class MineSweeper extends Game {

	@Override
	public void create () {
	    setScreen(new FieldScreen(this));

    }


}
