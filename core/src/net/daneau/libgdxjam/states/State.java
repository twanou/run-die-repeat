package net.daneau.libgdxjam.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public interface State {

    State update(float delta);

    void draw(SpriteBatch spriteBatch);
}
