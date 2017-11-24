package cjminecraft.engine.test;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.Random;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import cjminecraft.engine.loaders.VaoLoader;
import cjminecraft.engine.managers.CameraManager;
import cjminecraft.engine.objects.data.TransformationData;
import cjminecraft.engine.objects.data.VertexData;
import cjminecraft.engine.util.Maths;

public class TestRenderer {

	private TestShader shader;

	public static float[] vertices = {
			// Front face
			-1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F,

			// Back face
			-1.0F, -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, -1.0F, -1.0F,

			// Top face
			-1.0F, 1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F,

			// Bottom face
			-1.0F, -1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, -1.0F, -1.0F, 1.0F,

			// Right face
			1.0F, -1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F, 1.0F,

			// Left face
			-1.0F, -1.0F, -1.0F, -1.0F, -1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1.0F, 1.0F, -1.0F, };

	public static int[] indices = { 0, 1, 2, 0, 2, 3, // front
			4, 5, 6, 4, 6, 7, // back
			8, 9, 10, 8, 10, 11, // top
			12, 13, 14, 12, 14, 15, // bottom
			16, 17, 18, 16, 18, 19, // right
			20, 21, 22, 20, 22, 23, // left
	};

	private float colourR = 1, colourG = 1, colourB = 1;
	private boolean switchR, switchG, switchB;

	private VertexData data1;
	private TransformationData data3;
	
	//private VertexData data2;

	private Random random = new Random();

	public TestRenderer() {
		this.shader = new TestShader();
		this.data1 = VaoLoader.loadToVAO(vertices, indices);
		this.data3 = new TransformationData(0, 0, 0, 0, 0, 0, 1, 1, 1);
		// this.data2 = VaoLoader.loadToVAO(new float[] { -1, -1, -1, -1, -0.5f,
		// -1, -0.5f, -1, -0.5f },
		// new int[] { 1, 2, 3 });
	}

	public void render() {
		this.shader.start();

		if (!switchR) {
			colourR += random.nextFloat() * random.nextFloat() / 30.0F;
			if (colourR >= 1)
				switchR = true;
		} else {
			colourR -= random.nextFloat() * random.nextFloat() / 30.0F;
			if (colourR <= 0)
				switchR = false;
		}
		if (!switchG) {
			colourG += random.nextFloat() * random.nextFloat() / 30.0F;
			if (colourR >= 1)
				switchG = true;
		} else {
			colourG -= random.nextFloat() * random.nextFloat() / 30.0F;
			if (colourG <= 0)
				switchG = false;
		}
		if (!switchB) {
			colourB += random.nextFloat() * random.nextFloat() / 30.0F;
			if (colourB >= 1)
				switchB = true;
		} else {
			colourB -= random.nextFloat() * random.nextFloat() / 30.0F;
			if (colourB <= 0)
				switchB = false;
		}
		
		this.data3.increaseRotation(0, 0, 0.1F);

		TestShader.COLOUR.loadValue(new Vector3f(colourR, colourG, colourB));
		TestShader.PROJECTION_MATRIX.loadValue(CameraManager.getInstance().getCamera().getProjectionMatrix());
		TestShader.VIEW_MATRIX.loadValue(CameraManager.getInstance().getCamera().getViewMatrix());
		TestShader.TRANSFORMATION_MATRIX.loadValue(Maths.createTransformationMatrix(this.data3));
		
		// System.out.println(GL11.glGetError());

		data1.getVao().bind();
		data1.getVao().enableAttributes();
		data1.getIndexBuffer().bind();
		GL11.glDrawElements(GL11.GL_TRIANGLES, data1.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		data1.getIndexBuffer().unbind();
		data1.getVao().disableAttributes();
		data1.getVao().unbind();
		this.shader.stop();
	}

	public void cleanUp() {
		this.shader.cleanUp();
	}

}
