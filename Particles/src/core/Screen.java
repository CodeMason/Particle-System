package core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import effect.Effect;
import effect.Fire;

import java.awt.*;

/**
 * Represents a screen on which to draw.
 * @author Valkryst
 * --- Last Edit 29-January-2015
 */
public class Screen implements ApplicationListener {
    private OrthographicCamera camera;
    private ShapeRenderer renderer;
    private Effect effect;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);

        renderer = new ShapeRenderer();

        effect = new Fire(new Dimension(912, 512), 256f, 256f);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        effect.setScreenDimensions(new Dimension(width, height), width/2, height/2);
    }

    @Override
    public void render() {
        Gdx.graphics.getGL30().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL30().glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

        Gdx.graphics.getGL30().glEnable(GL30.GL_BLEND);
        Gdx.graphics.getGL30().glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);

        camera.update();
        effect.update();

        renderer.setProjectionMatrix(camera.combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        effect.render(renderer);
        renderer.end();
    }
}
