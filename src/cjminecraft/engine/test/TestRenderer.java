package cjminecraft.engine.test;

import java.util.Random;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import cjminecraft.engine.loaders.VaoLoader;
import cjminecraft.engine.managers.CameraManager;
import cjminecraft.engine.objects.data.VertexData;

public class TestRenderer {
	
	private TestShader shader;
	
	private float[] vertices = {
		    // front
		    -1.0F, -1.0F,  1.0F,
		     1.0F, -1.0F,  1.0F,
		     1.0F,  1.0F,  1.0F,
		    -1.0F,  1.0F,  1.0F,
		    // back
		    -1.0F, -1.0F, -1.0F,
		     1.0F, -1.0F, -1.0F,
		     1.0F,  1.0F, -1.0F,
		    -1.0F,  1.0F, -1.0F,
		};
	
	private int[] indices = {
			// front
			0, 1, 2,
			2, 3, 0,
			// top
			1, 5, 6,
			6, 2, 1,
			// back
			7, 6, 5,
			5, 4, 7,
			// bottom
			4, 0, 3,
			3, 7, 4,
			// left
			4, 5, 1,
			1, 0, 4,
			// right
			3, 2, 6,
			6, 7, 3,
		};
	
	private float colourR = 1, colourG = 1, colourB = 1;
	private boolean switchR, switchG, switchB;
	
	private VertexData data1;
	private VertexData data2;
	
	private Random random = new Random();
	
	public TestRenderer() {
		this.shader = new TestShader();
		this.data1 = VaoLoader.loadToVAO(vertices, indices);
		//this.data2 = VaoLoader.loadToVAO(new float[] { -1, -1, -1, -1, -0.5f, -1, -0.5f, -1, -0.5f }, new int[] { 1, 2, 3 });
	}
	
	public void render() {
		this.shader.start();
		/*
		if(!switchR) {
			colourR += random.nextFloat() * random.nextFloat() / 30.0F;
			if(colourR >= 1)
				switchR = true;
		} else {
			colourR -= random.nextFloat() * random.nextFloat() / 30.0F;
			if(colourR <= 0)
				switchR = false;
		}
		if(!switchG) {
			colourG += random.nextFloat() * random.nextFloat() / 30.0F;
			if(colourR >= 1)
				switchG = true;
		} else {
			colourG -= random.nextFloat() * random.nextFloat() / 30.0F;
			if(colourG <= 0)
				switchG = false;
		}
		if(!switchB) {
			colourB += random.nextFloat() * random.nextFloat() / 30.0F;
			if(colourB >= 1)
				switchB = true;
		} else {
			colourB -= random.nextFloat() * random.nextFloat() / 30.0F;
			if(colourB <= 0)
				switchB = false;
		}
		*/
		TestShader.COLOUR.loadValue(new Vector3f(colourR, colourG, colourB));
		TestShader.PROJECTION_MATRIX.loadValue(CameraManager.getInstance().getCamera().getProjectionMatrix().invert());
		TestShader.VIEW_MATRIX.loadValue(CameraManager.getInstance().getCamera().getViewMatrix());
		//System.out.println(GL11.glGetError());
		
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
