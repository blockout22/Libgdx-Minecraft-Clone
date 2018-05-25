package com.lib.gdx.clone.minecraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class LibGDXMinecraftClone extends ApplicationAdapter implements InputProcessor {

    private AssetManager assetManager;
    private ExtendViewport viewport;
    private Camera camera;

    private Array<String> assetsToUnload = new Array<String>();

    private SpriteBatch batch;
    private Sprite crosshair;

    private Environment environment;
    private ModelBatch modelBatch;
    private Model model;
    private ModelInstance instance;

    float rotation = 0f;


	@Override
	public void create () {
	    assetManager = new AssetManager();
	    viewport = new ExtendViewport(640, 480);
	    camera = new PerspectiveCamera(70, viewport.getWorldWidth(), viewport.getWorldHeight());
        camera.position.set(10f, 10f, 10f);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far = 300f;
	    camera.update();

	    loadAssets();

	    batch = new SpriteBatch();
	    crosshair = new Sprite(assetManager.get("crosshair.png", Texture.class));

	    environment = new Environment();
	    environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
	    environment.add(new DirectionalLight().set(0.8f, 0.6f, 0.6f, -1f, -0.8f, -0.2f));

	    modelBatch = new ModelBatch();

	    ModelBuilder modelBuilder = new ModelBuilder();
	    model = modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.GREEN)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
	    instance = new ModelInstance(model);

	}

	private void loadAssets()
    {
        //load Assets
        loadAsset("crosshair.png", Texture.class);

        assetManager.finishLoading();
    }

    private void loadAsset(String asset, Class type){
        assetManager.load(asset, type);
        assetsToUnload.add(asset);
    }

    private void unloadAssets()
    {
        for(int i = 0; i < assetsToUnload.size; i++){
            assetManager.unload(assetsToUnload.get(i));
        }
        assetsToUnload.clear();
        assetManager.clear();
    }


	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT| GL20.GL_DEPTH_BUFFER_BIT);
		camera.update();


		modelBatch.begin(camera);
		modelBatch.render(instance, environment);
		modelBatch.end();

        rotation += 0.9f;

        float width = 100;
        float height = 100;

		batch.begin();
		batch.draw(crosshair, (Gdx.graphics.getWidth() / 2) - (width / 2), (Gdx.graphics.getHeight() / 2) - (height / 2), width / 2, height / 2, width, height, 1, 1, rotation);
		batch.end();
	}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.viewportWidth = viewport.getWorldWidth();
        camera.viewportHeight = viewport.getWorldHeight();
        camera.update();
    }

    @Override
	public void dispose () {
	    unloadAssets();
	    batch.dispose();
	    model.dispose();
	}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
