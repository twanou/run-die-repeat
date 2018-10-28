package net.daneau.libgdxjam.obstacles;

/**
 * Author : Antoine Daneau
 * Date   : 28-10-2018
 */
public class Puddle extends Obstacle {

    public Puddle(int posX, int posY) {
        super(posX, posY, "puddle", "splash.mp3");
    }

    @Override
    public String getCause() {
        return "Drowned in a puddle";
    }
}
