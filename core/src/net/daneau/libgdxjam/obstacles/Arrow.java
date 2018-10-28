package net.daneau.libgdxjam.obstacles;

/**
 * Author : Antoine Daneau
 * Date   : 28-10-2018
 */
public class Arrow extends Obstacle {

    public Arrow(int posX, int posY) {
        super(posX, posY, "arrow", "arrow-hit.mp3");
    }

    @Override
    public void update(float delta) {
        this.collisionBox.y -= delta * 2000;
        super.update(delta);

    }

    @Override
    public String getCause() {
        return "Took an arrow in the knee";
    }
}
