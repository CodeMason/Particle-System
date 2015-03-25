package core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import effect.*;

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

    private Dimension screenDimensions;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);

        renderer = new ShapeRenderer();

        screenDimensions = new Dimension(512, 512);
        effect = new Fire(screenDimensions, 256, 256);
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
        screenDimensions = new Dimension(width, height);
        effect.setScreenDimensions(screenDimensions, width/2, height/2);
    }

    @Override
    public void render() {
        // Quick Effect Swithing
        if(Gdx.input.isKeyPressed(Input.Keys.F1)) {
            effect = new Fire(screenDimensions, screenDimensions.width/2, screenDimensions.height/2);
        } else if(Gdx.input.isKeyPressed(Input.Keys.F2)) {
            effect = new ParticleHoleEffect(screenDimensions, 0, screenDimensions.height);
        } else if(Gdx.input.isKeyPressed(Input.Keys.F3)) {
            effect = new RainbowSnow(screenDimensions, 0, screenDimensions.height);
        } else if(Gdx.input.isKeyPressed(Input.Keys.F4)) {
            effect = new Snow(screenDimensions, 0, screenDimensions.height);
        } else if(Gdx.input.isKeyPressed(Input.Keys.F5)) {
            effect = new SplitWave(screenDimensions, 0, screenDimensions.height);
        } else if(Gdx.input.isKeyPressed(Input.Keys.F6)) {
            effect = new Stars(screenDimensions, 0, screenDimensions.height);
        }

        // Rendering
        Gdx.graphics.getGL30().glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.graphics.getGL30().glClear(GL30.GL_COLOR_BUFFER_BIT);

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
