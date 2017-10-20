package cjminecraft.engine.util.opengl;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;

import static cjminecraft.engine.util.GLError.*;

import java.util.ArrayList;
import java.util.List;

public class Vao {
	
	private List<Attribute> attributes = new ArrayList<Attribute>();
	private Vbo indexBuffer;
	private List<Vbo> relatedVbos = new ArrayList<Vbo>();
	
	public final int id;
	
	public static Vao create() {
		return new Vao(glCallT(() -> glGenVertexArrays()));
	}
	
	private Vao(int id) {
		this.id = id;
	}
	
	public void bind() {
		glCall(() -> glBindVertexArray(this.id));
	}
	
	public void unbind() {
		glCall(() -> glBindVertexArray(this.id));
	}
	
	public void enableAttributes() {
		for(Attribute attribute : this.attributes)
			attribute.enable(true);
	}
	
	public void disableAttributes() {
		for(Attribute attribute : this.attributes)
			attribute.enable(false);
	}
	
	public void disableAttributes(int... attributes) {
		for(int attribute : attributes)
			glCall(() -> glDisableVertexAttribArray(attribute));
	}
	
	public Vbo createDataFeed(int maxVertexCount, int usage, Attribute... attributes) {
		int bytesPerVertex = getVertexDataTotalBytes(attributes);
		Vbo vbo = Vbo.create(GL_ARRAY_BUFFER, usage);
		this.relatedVbos.add(vbo);
		vbo.allocateData(bytesPerVertex * maxVertexCount);
		linkAttributes(bytesPerVertex, attributes);
		vbo.unbind();
		return vbo;
	}
	
	public Vbo initDataFeed(float[] data, int usage, Attribute... attributes) {
		int bytesPerVertex = getVertexDataTotalBytes(attributes);
		Vbo vbo = Vbo.create(GL_ARRAY_BUFFER, usage);
		this.relatedVbos.add(vbo);
		vbo.allocateData(data.length * DataUtils.BYTES_IN_FLOAT);
		vbo.storeData(bytesPerVertex, data);
		linkAttributes(bytesPerVertex, attributes);
		vbo.unbind();
		return vbo;
	}
	
	public Vbo createIndexBuffer(int[] indices) {
		this.indexBuffer = Vbo.create(GL_ELEMENT_ARRAY_BUFFER, GL_STATIC_DRAW);
		this.indexBuffer.allocateData(indices.length * DataUtils.BYTES_IN_INT);
		this.indexBuffer.storeData(0, indices);
		return this.indexBuffer;
	}
	
	public void delete(boolean deleteVbos) {
		glDeleteVertexArrays(this.id);
		if(deleteVbos)
			for(Vbo vbo : this.relatedVbos)
				vbo.delete();
	}
	
	private void linkAttributes(int bytesPerVertex, Attribute... attributes) {
		int offset = 0;
		for(Attribute attribute : attributes) {
			attribute.link(offset, bytesPerVertex);
			offset += attribute.bytesPerVertex;
			attribute.enable(true);
			this.attributes.add(attribute);
		}
	}
	
	private int getVertexDataTotalBytes(Attribute... attributes) {
		int total = 0;
		for(Attribute attribute : attributes)
			total += attribute.bytesPerVertex;
		return total;
	}

}
