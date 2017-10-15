package cjminecraft.engine.managers;

import cjminecraft.engine.loaders.obj.OBJLoader;
import cjminecraft.engine.objects.GameObject;
import cjminecraft.engine.objects.data.TextureData;
import cjminecraft.engine.objects.data.TransformationData;
import cjminecraft.engine.objects.renderers.TexturedObjectRenderer;
import cjminecraft.engine.util.IManager;

public class ObjectManager implements IManager {

	private static ObjectManager instance;

	public TexturedObjectRenderer texturedObjectRenderer;
	
	private GameObject object;

	@Override
	public void preInit() throws Exception {
		this.texturedObjectRenderer = new TexturedObjectRenderer();
	}

	@Override
	public void init() throws Exception {
		this.object = new GameObject().attach(OBJLoader.loadOBJ("barrel"))
				.attach(new TransformationData(10, 10, 10, 0, 0, 0, 1, 1, 1)).attach(new TextureData("barrel"));
	}

	@Override
	public void postInit() throws Exception {
	}

	@Override
	public void loop() throws Exception {
		//this.texturedObjectRenderer.render(this.object, CameraManager.getInstance().getCamera());
	}

	@Override
	public void cleanUp() throws Exception {
		this.texturedObjectRenderer.cleanUp();
	}

	public static ObjectManager getInstance() {
		return instance == null ? instance = new ObjectManager() : instance;
	}

}
