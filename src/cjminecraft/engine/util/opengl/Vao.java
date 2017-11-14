package cjminecraft.engine.util.opengl;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

import static cjminecraft.engine.util.GLError.*;

public class Vao {

	public final int id;
	private List<Vbo> relatedVbos = new ArrayList<Vbo>();
	private List<Attribute> attributes = new ArrayList<Attribute>();
	private Vbo indexBuffer;

	private Vao(int id) {
		this.id = id;
	}

	public void bind() {
		glCall(() -> glBindVertexArray(this.id));
	}

	public void unbind() {
		glCall(() -> glBindVertexArray(0));
	}

	public void delete(boolean deleteVbos) {
		glCall(() -> glDeleteVertexArrays(this.id));
		if (deleteVbos)
			for (Vbo vbo : this.relatedVbos)
				vbo.delete();
	}
	
	public void enableAttributes() {
		for(Attribute a : this.attributes)
			a.enable();
	}
	
	public void enableAttributes(int... attributes) {
		for(int a : attributes)
			glCall(() -> glEnableVertexAttribArray(a));
	}
	
	public void disableAttributes() {
		for(Attribute a : this.attributes)
			a.disable();
	}
	
	public void disableAttributes(int... attributes) {
		for(int a : attributes)
			glCall(() -> glDisableVertexAttribArray(a));
	}

	public Vbo storeData(int attribute, int componentCount, float[] data, int usage, boolean normalised) {
		Vbo vbo = Vbo.create(GL_ARRAY_BUFFER, usage);
		this.relatedVbos.add(vbo);
		vbo.bind();
		vbo.allocateData(data);
		Attribute a = new Attribute(attribute, GL_FLOAT, componentCount, normalised);
		this.attributes.add(a);
		a.link(0, 0);
		vbo.unbind();
		return vbo;
	}

	public Vbo storeData(int attribute, int componentCount, int[] data, int usage, boolean normalised) {
		Vbo vbo = Vbo.create(GL_ARRAY_BUFFER, usage);
		this.relatedVbos.add(vbo);
		vbo.bind();
		vbo.allocateData(data);
		Attribute a = new Attribute(attribute, GL_INT, componentCount, normalised);
		this.attributes.add(a);
		a.link(0, 0);
		vbo.unbind();
		return vbo;
	}
	
	public Vbo storeData(int attribute, int componentCount, float[] data, int usage) {
		Vbo vbo = Vbo.create(GL_ARRAY_BUFFER, usage);
		this.relatedVbos.add(vbo);
		vbo.bind();
		vbo.allocateData(data);
		Attribute a = new Attribute(attribute, GL_FLOAT, componentCount);
		this.attributes.add(a);
		a.link(0, 0);
		vbo.unbind();
		return vbo;
	}

	public Vbo storeData(int attribute, int componentCount, int[] data, int usage) {
		Vbo vbo = Vbo.create(GL_ARRAY_BUFFER, usage);
		this.relatedVbos.add(vbo);
		vbo.bind();
		vbo.allocateData(data);
		Attribute a = new Attribute(attribute, GL_INT, componentCount);
		this.attributes.add(a);
		a.link(0, 0);
		vbo.unbind();
		return vbo;
	}
	
	public Vbo createIndexBuffer(int[] indices) {
		this.indexBuffer = Vbo.create(GL_ELEMENT_ARRAY_BUFFER, GL_STATIC_DRAW);
		this.indexBuffer.bind();
		this.indexBuffer.allocateData(indices);
		this.indexBuffer.update(0, indices);
		this.indexBuffer.unbind();
		return this.indexBuffer;
	}

	public static Vao create() {
		return new Vao(glCallT(() -> glGenVertexArrays()));
	}

}
