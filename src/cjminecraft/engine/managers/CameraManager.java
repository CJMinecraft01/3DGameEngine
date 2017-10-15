package cjminecraft.engine.managers;

import cjminecraft.engine.camera.Camera;
import cjminecraft.engine.camera.ICamera;
import cjminecraft.engine.util.IManager;

public class CameraManager implements IManager {

	private static CameraManager instance = new CameraManager();
	
	private ICamera camera;
	
	@Override
	public void preInit() throws Exception {
		this.camera = new Camera();
	}

	@Override
	public void init() throws Exception {
	}

	@Override
	public void postInit() throws Exception {
	}

	@Override
	public void loop() throws Exception {
		this.camera.move();
	}

	@Override
	public void cleanUp() throws Exception {
	}
	
	public ICamera getCamera() {
		return camera;
	}
	
	public static CameraManager getInstance() {
		return instance;
	}

}
