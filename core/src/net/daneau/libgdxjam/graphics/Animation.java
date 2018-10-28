package net.daneau.libgdxjam.graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public class Animation {

    private Array<Sprite> frames;
    private int currentFrame = 0;
    private float speed;
    private float count = 0;

    public Animation(float speed, Sprite... frames) {
        this.frames = new Array<>(frames);
        this.speed = speed;
    }

    public void setPosition(float x, float y) {
        for (Sprite sprite : this.frames) {
            sprite.setPosition(x, y);
        }
    }

    public void update(float delta) {
        this.count += delta;

        if (this.count >= this.speed) {
            ++this.currentFrame;

            if (this.currentFrame == this.frames.size) {
                this.currentFrame = 0;
            }
            this.count -= this.speed;

        }

    }

    public void draw(SpriteBatch spriteBatch) {
        this.frames.get(this.currentFrame).draw(spriteBatch);
    }

}
