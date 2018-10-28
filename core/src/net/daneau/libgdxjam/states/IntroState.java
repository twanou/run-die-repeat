package net.daneau.libgdxjam.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import net.daneau.libgdxjam.utils.GameFont;
import net.daneau.libgdxjam.utils.Pair;

/**
 * Author : Antoine Daneau
 * Date   : 28-10-2018
 */
public class IntroState implements State {

    private Array<Sound> ticks;
    private Array<Pair<String, Float>> messages;
    private float timeCount = 0;
    private int currentMessage = 0;
    private int currentTick = 0;

    public IntroState() {
        this.ticks = new Array<>();
        this.ticks.add(Gdx.audio.newSound(Gdx.files.internal("sfx/tick1.mp3")));
        this.ticks.add(Gdx.audio.newSound(Gdx.files.internal("sfx/tick2.mp3")));
        this.ticks.add(Gdx.audio.newSound(Gdx.files.internal("sfx/tick3.mp3")));
        this.ticks.add(Gdx.audio.newSound(Gdx.files.internal("sfx/tick4.mp3")));
        this.messages = new Array<>();

        this.messages.add(new Pair<>("You are stuck in a temporal loop", 3f));
        this.messages.add(new Pair<>("Each time you die", 1f));
        this.messages.add(new Pair<>("the same day start over...", 1f));
        this.messages.add(new Pair<>("at 7:42 AM", 1f));
        this.messages.add(new Pair<>("Use the arrow keys", 1f));
        this.messages.add(new Pair<>("to dodge obstacles", 1f));
        this.messages.add(new Pair<>("If you see something", 1f));
        this.messages.add(new Pair<>("that you think could kill you...", 1f));
        this.messages.add(new Pair<>("It WILL", 1f));
        this.messages.add(new Pair<>("3", 1f));
        this.messages.add(new Pair<>("2", 1f));
        this.messages.add(new Pair<>("1", 1f));


    }

    @Override
    public State update(float delta) {
        this.timeCount += delta;

        if (this.timeCount >= this.messages.get(this.currentMessage).getValue()) {
            ++this.currentMessage;

            if (this.currentMessage == this.messages.size) {
                return new GameState();
            }

            ++this.currentTick;
            if (this.currentTick == this.ticks.size) {
                this.currentTick = 0;
            }
            this.ticks.get(this.currentTick).play();
            this.timeCount = 0;

        }

        return this;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        GameFont.INTRO_FONT.draw(spriteBatch, this.messages.get(this.currentMessage).getKey(), 0, Gdx.graphics.getHeight() / 2f, Gdx.graphics.getWidth(), Align.center, false);
        spriteBatch.end();
    }
}
