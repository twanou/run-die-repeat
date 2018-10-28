package net.daneau.libgdxjam.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public abstract class Obstacle {

    Rectangle collisionBox;
    private TextureAtlas textureAtlas = new TextureAtlas("atlases/sprites.atlas");
    private Sprite sprite;
    private Sound hitSound;

    Obstacle(int posX, int posY, String spriteName, String hitSound) {
        this.sprite = this.textureAtlas.createSprite(spriteName);
        this.sprite.setSize(this.sprite.getWidth() * 8, this.sprite.getHeight() * 8);
        this.collisionBox = new Rectangle(posX, posY, this.sprite.getWidth(), this.sprite.getHeight());
        this.hitSound = Gdx.audio.newSound(Gdx.files.internal("sfx/" + hitSound));
        this.sprite.setPosition(this.collisionBox.x, this.collisionBox.y);
    }

    public Rectangle getColisionBox() {
        return this.collisionBox;
    }

    public float getPositionY() {
        return this.collisionBox.y;
    }

    public void update(float delta) {
        this.sprite.setPosition(this.collisionBox.x, this.collisionBox.y);
    }

    public void draw(SpriteBatch spriteBatch) {
        this.sprite.draw(spriteBatch);
    }

    public abstract String getCause();

    public void hit() {
        this.hitSound.play(1f);
    }

    void setAlpha(float a) {
        this.sprite.setAlpha(a);
    }


}
