package net.daneau.libgdxjam.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import net.daneau.libgdxjam.utils.GameFont;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public class GameOverState implements State {

    private static Music theme;

    static {
        theme = Gdx.audio.newMusic(Gdx.files.internal("music/gameover.mp3"));
        theme.setLooping(true);
    }

    private boolean showCredit;
    private String timeOfDeath;
    private String cause;

    public GameOverState(String timeOfDeath, String cause) {
        this.timeOfDeath = timeOfDeath;
        this.cause = cause;
        theme.play();
    }

    @Override
    public State update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            theme.stop();
            return new GameState();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            this.showCredit = true;
        }
        return this;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        if (this.showCredit) {
            GameFont.INTRO_FONT.draw(spriteBatch, "A game by Twanoo", 0, Gdx.graphics.getHeight() * 0.9f, Gdx.graphics.getWidth(), Align.center, false);

            GameFont.INTRO_FONT.draw(spriteBatch, "- Music and sound effects -", 0, Gdx.graphics.getHeight() * 0.8f, Gdx.graphics.getWidth(), Align.center, false);

            GameFont.INTRO_FONT.draw(spriteBatch, "Cleyton Kauffman", 0, Gdx.graphics.getHeight() * 0.7f, Gdx.graphics.getWidth(), Align.center, false);
            GameFont.INTRO_FONT.draw(spriteBatch, "http://opengameart.org/users/doppelganger", 0, Gdx.graphics.getHeight() * 0.65f, Gdx.graphics.getWidth(), Align.center, false);

            GameFont.INTRO_FONT.draw(spriteBatch, "Macro", 0, Gdx.graphics.getHeight() * 0.5f, Gdx.graphics.getWidth(), Align.center, false);
            GameFont.INTRO_FONT.draw(spriteBatch, "https://opengameart.org/users/macro", 0, Gdx.graphics.getHeight() * 0.45f, Gdx.graphics.getWidth(), Align.center, false);

            GameFont.INTRO_FONT.draw(spriteBatch, "Iwan Gabovitch", 0, Gdx.graphics.getHeight() * 0.3f, Gdx.graphics.getWidth(), Align.center, false);
            GameFont.INTRO_FONT.draw(spriteBatch, "https://opengameart.org/users/qubodup", 0, Gdx.graphics.getHeight() * 0.25f, Gdx.graphics.getWidth(), Align.center, false);

            GameFont.GAMEOVER_FONT.draw(spriteBatch, "Press SPACE to repeat", 0, Gdx.graphics.getHeight() * 0.1f, Gdx.graphics.getWidth(), Align.center, false);
        } else {
            GameFont.GAMEOVER_FONT.draw(spriteBatch, "Time of death : " + this.timeOfDeath, 0, Gdx.graphics.getHeight() * 0.8f, Gdx.graphics.getWidth(), Align.center, false);
            GameFont.GAMEOVER_FONT.draw(spriteBatch, "Cause : " + this.cause, 0, Gdx.graphics.getHeight() * 0.6f, Gdx.graphics.getWidth(), Align.center, false);
            GameFont.GAMEOVER_FONT.draw(spriteBatch, "Press SPACE to repeat", 0, Gdx.graphics.getHeight() * 0.20f, Gdx.graphics.getWidth(), Align.center, false);

            GameFont.SMALL_FONT.draw(spriteBatch, "Press C for credits", 0, Gdx.graphics.getHeight() * 0.05f, Gdx.graphics.getWidth(), Align.center, false);
        }


        spriteBatch.end();


    }
}
