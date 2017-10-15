package cjminecraft.engine.util;

import java.util.List;

import cjminecraft.engine.camera.ICamera;

public interface IRenderer<T> {
	
	public void render(T object, ICamera camera);
	
	public void render(List<T> objectBatch, ICamera camera);
	
	public void cleanUp();

}
