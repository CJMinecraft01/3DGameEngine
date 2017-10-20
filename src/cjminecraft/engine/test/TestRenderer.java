package cjminecraft.engine.test;

import java.util.Random;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import cjminecraft.engine.loaders.VaoLoader;
import cjminecraft.engine.objects.data.VertexData;

public class TestRenderer {
	
	private final TestShader shader;
	
	private float[] vertices = {
			-0.5f, 0.5f, 0,
			-0.5f, -0.5f, 0,
			0.5f, -0.5f, 0,
			0.5f, 0.5f, 0
		};
	
	private int[] indices = {
			0,1,3,
			3,1,2
		};
	
	private float colourR, colourG, colourB;
	private boolean switchR, switchG, switchB;
	
	private VertexData data1;
	private VertexData data2;
	
	
	private Random random = new Random();
	
	public TestRenderer() {
		this.shader = new TestShader();
		this.data1 = VaoLoader.loadToVAO(vertices, indices);
		this.data2 = VaoLoader.loadToVAO(new float[] { -1, -1, -1, -1, -0.5f, -1, -0.5f, -1, -0.5f }, new int[] { 1, 2, 3 });
	}
	
	public void render() {
		this.shader.start();
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
		TestShader.COLOUR.loadValue(new Vector3f(colourR, colourG, colourB));
		//System.out.println(GL11.glGetError());
		data1.getVao().bind();
		data1.getVao().enableAttributes();
		GL11.glDrawElements(GL11.GL_TRIANGLES, data1.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		data1.getVao().disableAttributes();
		data1.getVao().unbind();
		
		data2.getVao().bind();
		data2.getVao().enableAttributes();
		GL11.glDrawElements(GL11.GL_TRIANGLES, data2.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		data2.getVao().disableAttributes();
		data2.getVao().unbind();
		this.shader.stop();
	}

	public void cleanUp() {
		this.shader.cleanUp();
	}

}
