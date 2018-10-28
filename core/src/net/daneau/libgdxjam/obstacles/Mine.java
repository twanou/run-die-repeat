package net.daneau.libgdxjam.obstacles;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public class Mine extends Obstacle {

    public Mine(int posX, int posY) {
        super(posX, posY, "mine", "explosion.wav");
        this.setAlpha(0.2f);
    }


    @Override
    public String getCause() {
        return "Stepped on a mine";
    }
}
