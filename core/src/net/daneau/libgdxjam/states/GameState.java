package net.daneau.libgdxjam.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import net.daneau.libgdxjam.graphics.Cameraman;
import net.daneau.libgdxjam.graphics.Clock;
import net.daneau.libgdxjam.obstacles.Mine;
import net.daneau.libgdxjam.obstacles.Obstacle;
import net.daneau.libgdxjam.obstacles.ObstacleType;
import net.daneau.libgdxjam.player.Player;
import net.daneau.libgdxjam.utils.GameFont;

import java.util.Random;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public class GameState implements State {

    private static final int START_TIME = 7;
    private static Music theme;
    private static Sound off;

    static {
        theme = Gdx.audio.newMusic(Gdx.files.internal("music/theme.ogg"));
        theme.setLooping(true);
        off = Gdx.audio.newSound(Gdx.files.internal("sfx/off.ogg"));
    }

    private ObstacleType[] obstacleTypes = ObstacleType.values();
    private Random random;
    private Array<Obstacle> obstacles;
    private IntArray obstacleToRemove;
    private Player player;
    private Cameraman cameraman;
    private float distance;
    private Clock clock;

    public GameState() {
        this.clock = new Clock(GameFont.CLOCK);
        this.clock.setTime(START_TIME, 42, Clock.Period.AM);
        this.random = new Random(6342895);
        this.obstacles = new Array<>(false, 32);
        this.obstacleToRemove = new IntArray();
        this.player = new Player(Gdx.graphics.getWidth() / 2, 0, 800, 500);
        this.cameraman = new Cameraman(this.player, Gdx.graphics.getHeight() / 3);
        this.obstacles.add(new Mine(Gdx.graphics.getWidth() / 2, 600));
        theme.play();
    }

    @Override
    public State update(float delta) {
        this.clock.update(delta);
        float lastPositionY = this.player.getPositionY();
        this.player.update(delta);
        this.distance += this.player.getPositionY() - lastPositionY;
        this.cameraman.update();

        for (int i = 0; i < this.obstacles.size; ++i) {
            Obstacle obstacle = this.obstacles.get(i);

            obstacle.update(delta);

            if (this.player.getCollisionBox().overlaps(obstacle.getColisionBox())) {
                theme.stop();
                obstacle.hit();
                return new GameOverState(this.clock.toString(), obstacle.getCause());
            }

            if (obstacle.getPositionY() < this.cameraman.getBottom(Cameraman.Type.MOVING) - Gdx.graphics.getWidth() / 2) {
                this.obstacleToRemove.add(i);
            }
        }

        if (this.player.getPositionX() < 0 || this.player.getPositionX() > this.cameraman.getRight(Cameraman.Type.MOVING)) {
            theme.stop();
            off.play();
            return new GameOverState(this.clock.toString(), "Just don't go there");
        }

        for (int i = 0; i < this.obstacleToRemove.size; ++i) {
            this.obstacles.removeIndex(this.obstacleToRemove.get(i));
        }
        this.obstacleToRemove.clear();


        if (this.distance >= Gdx.graphics.getHeight() / 2f) {
            ObstacleType obstacleType = this.obstacleTypes[this.random.nextInt(this.obstacleTypes.length)];
            this.obstacles.add(obstacleType.create(this.random.nextInt(Gdx.graphics.getWidth()), this.cameraman.getTop(Cameraman.Type.MOVING)));
            this.distance -= Gdx.graphics.getHeight() / 2f;
        }

        return this;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(50 / 255f, 111 / 255f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(this.cameraman.combined(Cameraman.Type.MOVING));

        spriteBatch.begin();
        this.player.draw(spriteBatch);

        for (Obstacle obstacle : this.obstacles) {
            obstacle.draw(spriteBatch);
        }

        spriteBatch.end();

        this.drawTime(spriteBatch);

    }

    private void drawTime(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(this.cameraman.combined(Cameraman.Type.FIXED));
        spriteBatch.begin();
        this.clock.draw(spriteBatch);
        spriteBatch.end();
    }

}
