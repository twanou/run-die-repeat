package net.daneau.libgdxjam.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Author : Antoine Daneau
 * Date   : 27-10-2018
 */
public class Cameraman {

    private ObjectMap<Type, OrthographicCamera> cameras = new ObjectMap<>();
    private CameraTarget cameraTarget;
    private int pixelOffsetY;

    public Cameraman(CameraTarget cameraTarget, int pixelOffsetY) {
        this.cameraTarget = cameraTarget;
        this.cameras.put(Type.FIXED, createCamera());
        this.cameras.put(Type.MOVING, createCamera());
        this.pixelOffsetY = pixelOffsetY;
    }

    public void update() {
        this.cameras.get(Type.MOVING).position.y = this.cameraTarget.getPositionY() + this.pixelOffsetY;
        this.cameras.get(Type.MOVING).update();
    }

    public Matrix4 combined(Type type) {
        return this.cameras.get(type).combined;
    }

    public int getTop(Type type) {
        return (int) (this.cameras.get(type).position.y + this.cameras.get(type).viewportHeight / 2);
    }

    public int getBottom(Type type) {
        return (int) (this.cameras.get(type).position.y - this.cameras.get(type).viewportHeight / 2);
    }

    public int getLeft(Type type) {
        return (int) (this.cameras.get(type).position.x - this.cameras.get(type).viewportHeight / 2);
    }

    public int getRight(Type type) {
        return (int) (this.cameras.get(type).position.x + this.cameras.get(type).viewportHeight / 2);
    }

    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2); // Corner at (0, 0)
        camera.update();
        return camera;
    }

    public enum Type {
        MOVING,
        FIXED
    }

}
