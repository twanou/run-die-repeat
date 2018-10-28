package net.daneau.libgdxjam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.daneau.libgdxjam.states.IntroState;
import net.daneau.libgdxjam.states.State;

public class Main extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    private State currentState;
    private OrthographicCamera defaultCamera;

    @Override
    public void create() {
        /*
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        TexturePacker.process(settings, "sprites", "atlases", "sprites");
        */

        this.defaultCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.defaultCamera.translate(this.defaultCamera.viewportWidth / 2, this.defaultCamera.viewportHeight / 2); // Corner at (0, 0)
        this.defaultCamera.update();

        this.spriteBatch = new SpriteBatch();
        this.currentState = new IntroState();
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        State newState = this.currentState.update(Gdx.graphics.getDeltaTime());

        if (newState != this.currentState) {
            this.currentState = newState;
            this.spriteBatch.setProjectionMatrix(this.defaultCamera.combined);
        } else {
            this.currentState.draw(this.spriteBatch);
        }
    }

    @Override
    public void dispose() {
        this.spriteBatch.dispose();
    }
}
