package net.daneau.libgdxjam.obstacles;

/**
 * Author : Antoine Daneau
 * Date   : 28-10-2018
 */
public class Hole extends Obstacle {

    public Hole(int posX, int posY) {
        super(posX, posY, "hole", "falling.mp3");
    }

    @Override
    public String getCause() {
        return "Fell in a mysterious hole";
    }
}
