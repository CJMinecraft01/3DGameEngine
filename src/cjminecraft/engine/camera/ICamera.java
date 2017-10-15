package cjminecraft.engine.camera;

import org.joml.Matrix4f;

public interface ICamera {
	
	public void move();
	
	public Matrix4f getViewMatrix();
	public Matrix4f getProjectionMatrix();
	public Matrix4f getProjectionViewMatrix();

}
