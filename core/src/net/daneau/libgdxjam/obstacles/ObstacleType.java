package net.daneau.libgdxjam.obstacles;

import java.lang.reflect.Constructor;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public enum ObstacleType {
    MINE(Mine.class),
    TREE(Tree.class),
    ARROW(Arrow.class),
    PUDDLE(Puddle.class),
    HOLE(Hole.class);

    private Constructor<? extends Obstacle> constructor;

    ObstacleType(Class<? extends Obstacle> type) {
        try {
            this.constructor = type.getDeclaredConstructor(int.class, int.class);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating obstacle");
        }
    }

    public Obstacle create(int posX, int posY) {
        try {
            return this.constructor.newInstance(posX, posY);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating obstacle");
        }
    }
}
