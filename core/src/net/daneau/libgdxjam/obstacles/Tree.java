package net.daneau.libgdxjam.obstacles;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public class Tree extends Obstacle {

    public Tree(int posX, int posY) {
        super(posX, posY, "tree", "paf.ogg");
    }

    @Override
    public String getCause() {
        return "Hit a tree";
    }
}
