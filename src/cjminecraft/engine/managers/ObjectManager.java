package cjminecraft.engine.managers;

import cjminecraft.engine.loaders.TextureLoader;
import cjminecraft.engine.loaders.obj.OBJLoader;
import cjminecraft.engine.objects.GameObject;
import cjminecraft.engine.objects.data.ColourData;
import cjminecraft.engine.objects.data.TextureData;
import cjminecraft.engine.objects.data.TransformationData;
import cjminecraft.engine.objects.renders.ObjectRenderer;
import cjminecraft.engine.test.TestRenderer;
import cjminecraft.engine.util.IManager;

public class ObjectManager implements IManager {

	private static ObjectManager instance;

	// TODO remove test
	private TestRenderer testRenderer;

	private ObjectRenderer objectRenderer;
	private GameObject object;

	@Override
	public void preInit() throws Exception {
		this.testRenderer = new TestRenderer();
		this.objectRenderer = new ObjectRenderer();
	}

	@Override
	public void init() throws Exception {
		this.object = new GameObject().attach(OBJLoader.loadOBJ("barrel"))
				.attach(new TransformationData(0, 0, 0, 0, 0, 0, 1, 1, 1)).attach(new TextureData("barrel"));
	}

	@Override
	public void postInit() throws Exception {
	}

	@Override
	public void loop() throws Exception {
		//testRenderer.render();
		this.objectRenderer.render(this.object, CameraManager.getInstance().getCamera());
	}

	@Override
	public void cleanUp() throws Exception {
		testRenderer.cleanUp();
		this.objectRenderer.cleanUp();
	}

	public static ObjectManager getInstance() {
		return instance == null ? instance = new ObjectManager() : instance;
	}

}
