package cjminecraft.engine.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import cjminecraft.engine.Engine;
import cjminecraft.engine.managers.WindowManager;
import cjminecraft.engine.objects.GameObject;
import cjminecraft.engine.objects.data.DataType;
import cjminecraft.engine.objects.data.TransformationData;
import cjminecraft.engine.util.OpenGLUtils;

import static org.lwjgl.glfw.GLFW.*;

public class Camera extends GameObject implements ICamera {

	public static final float MOVEMENT_AMOUNT = 1F;
	
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix = new Matrix4f();

	public Camera() {
		attach(new TransformationData(0, 0, 0, 0, 0, 0, 0, 0, 0));
		updateViewMatrix();
		this.projectionMatrix = createProjectionMatrix();
		glfwSetKeyCallback(WindowManager.getInstance().getWindowId(), (window, key, scancode, action, mods) -> {
			if(key == GLFW_KEY_A)
				getData(DataType.TRANSORMATION_DATA).increasePositionX(-MOVEMENT_AMOUNT);
			if(key == GLFW_KEY_D)
				getData(DataType.TRANSORMATION_DATA).increasePositionX(MOVEMENT_AMOUNT);
			if(key == GLFW_KEY_W)
				getData(DataType.TRANSORMATION_DATA).increasePositionZ(-MOVEMENT_AMOUNT);
			if(key == GLFW_KEY_S)
				getData(DataType.TRANSORMATION_DATA).increasePositionZ(MOVEMENT_AMOUNT);
			if(key == GLFW_KEY_LEFT_SHIFT)
				getData(DataType.TRANSORMATION_DATA).increasePositionY(-MOVEMENT_AMOUNT);
			if(key == GLFW_KEY_SPACE)
				getData(DataType.TRANSORMATION_DATA).increasePositionY(MOVEMENT_AMOUNT);
			if(key == GLFW_KEY_P)
				OpenGLUtils.toggleWireframeMode();
		});
	}

	@Override
	public void move() {
		updateViewMatrix();
	}

	private void updateViewMatrix() {
		this.viewMatrix.zero();
		this.viewMatrix.rotate((float) Math.toRadians(getData(DataType.TRANSORMATION_DATA).getRotation().y),
				new Vector3f(1, 0, 0)); // Pitch
		this.viewMatrix.rotate((float) Math.toRadians(getData(DataType.TRANSORMATION_DATA).getRotation().z),
				new Vector3f(0, 1, 0)); // Yaw
		this.viewMatrix.rotate((float) Math.toRadians(getData(DataType.TRANSORMATION_DATA).getRotation().x),
				new Vector3f(0, 0, 1)); // Roll
		Vector3f negativeCameraPos = getData(DataType.TRANSORMATION_DATA).getPosition().negate(new Vector3f());
		this.viewMatrix.translate(negativeCameraPos);

	}

	@Override
	public Matrix4f getProjectionMatrix() {
		return this.projectionMatrix;
	}

	@Override
	public Matrix4f getViewMatrix() {
		return this.viewMatrix;
	}

	@Override
	public Matrix4f getProjectionViewMatrix() {
		return this.viewMatrix.mul(this.projectionMatrix);
	}

	private static Matrix4f createProjectionMatrix() {
		Matrix4f projectionMatrix = new Matrix4f();
		float aspectRatio = (float) WindowManager.getInstance().getWidth()
				/ (float) WindowManager.getInstance().getHeight();
		float yScale = (float) ((1F / Math.tan(Math.toRadians(Float.valueOf(Engine.getOption("fov")) / 2F))));
		float xScale = yScale / aspectRatio;
		float frustrumLength = Float.valueOf(Engine.getOption("far_plane"))
				- Float.valueOf(Engine.getOption("near_plane"));

		projectionMatrix.m00(xScale);
		projectionMatrix.m11(yScale);
		projectionMatrix
				.m22(-((Float.valueOf(Engine.getOption("far_plane")) + Float.valueOf(Engine.getOption("near_plane")))
						/ frustrumLength));
		projectionMatrix.m23(-1);
		projectionMatrix.m21(
				-((2F * Float.valueOf(Engine.getOption("far_plane")) * Float.valueOf(Engine.getOption("near_plane")))
						/ frustrumLength));
		projectionMatrix.m33(0);
		return projectionMatrix;
	}

}
