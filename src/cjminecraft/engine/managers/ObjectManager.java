package cjminecraft.engine.managers;

import cjminecraft.engine.test.TestRenderer;
import cjminecraft.engine.util.IManager;

public class ObjectManager implements IManager {

	private static ObjectManager instance;
	
	//TODO remove test
	private TestRenderer testRenderer;

	@Override
	public void preInit() throws Exception {
		this.testRenderer = new TestRenderer();
	}

	@Override
	public void init() throws Exception {
	}

	@Override
	public void postInit() throws Exception {
	}

	@Override
	public void loop() throws Exception {
		testRenderer.render();
	}

	@Override
	public void cleanUp() throws Exception {
		testRenderer.cleanUp();
	}

	public static ObjectManager getInstance() {
		return instance == null ? instance = new ObjectManager() : instance;
	}

}
