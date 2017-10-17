package cjminecraft.engine.test;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

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
	
	private VertexData data;
	
	public TestRenderer() {
		this.shader = new TestShader();
		this.data = VaoLoader.loadToVAO(vertices, indices);
	}
	
	public void render() {
		this.shader.start();
		System.out.println(GL11.glGetError());
		GL30.glBindVertexArray(data.getVaoId());
		GL20.glEnableVertexAttribArray(0);
		//GL11.glDrawElements(GL11.GL_TRIANGLES, data.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		System.out.println("SHOULD BE RENDERERING SOMETHING");
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		this.shader.stop();
	}

	public void cleanUp() {
		this.shader.cleanUp();
	}

}
