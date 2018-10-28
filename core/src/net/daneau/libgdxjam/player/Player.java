package net.daneau.libgdxjam.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import net.daneau.libgdxjam.graphics.Animation;
import net.daneau.libgdxjam.graphics.CameraTarget;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public class Player implements CameraTarget {

    private Rectangle collisionBox;
    private int pixelRunningSpeed; // per seconds
    private int pixelDodgingSpeed; // per seconds
    private TextureAtlas textureAtlas = new TextureAtlas("atlases/sprites.atlas");
    private Animation animation;

    public Player(int startX, int startY, int pixelRunningSpeed, int pixelDodgingSpeed) {
        this.collisionBox = new Rectangle(startX, startY, 50, 50);
        this.pixelRunningSpeed = pixelRunningSpeed;
        this.pixelDodgingSpeed = pixelDodgingSpeed;
        Sprite sprite = this.textureAtlas.createSprite("up1");
        sprite.setSize(sprite.getWidth() * 8, sprite.getHeight() * 8);
        Sprite sprite2 = this.textureAtlas.createSprite("up2");
        sprite2.setSize(sprite2.getWidth() * 8, sprite2.getHeight() * 8);

        this.animation = new Animation(0.1f, sprite, sprite2);

    }

    public void update(float delta) {
        this.collisionBox.y += (int) (delta * this.pixelRunningSpeed);
        this.animation.setPosition(this.collisionBox.x, this.collisionBox.y);
        this.animation.update(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.collisionBox.x -= delta * this.pixelDodgingSpeed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.collisionBox.x += delta * this.pixelDodgingSpeed;
        }
    }

    public void draw(SpriteBatch batch) {
        this.animation.draw(batch);
    }

    @Override
    public float getPositionX() {
        return this.collisionBox.x;
    }

    @Override
    public float getPositionY() {
        return this.collisionBox.y;
    }

    public Rectangle getCollisionBox() {
        return this.collisionBox;
    }
}
