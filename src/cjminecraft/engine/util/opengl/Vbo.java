package cjminecraft.engine.util.opengl;

import static cjminecraft.engine.util.GLError.*;
import static org.lwjgl.opengl.GL15.*;

public class Vbo {
	
	private final int id;
	private final int type;
	private final int usage;
	
	private Vbo(int id, int type, int usage) {
		this.id = id;
		this.type = type;
		this.usage = usage;
		bind();
	}
	
	public static Vbo create(int type, int usage) {
		return new Vbo(glCallT(() -> glGenBuffers()), type, usage);
	}
	
	public void bind() {
		glCall(() -> glBindBuffer(this.type, this.id));
	}
	
	public void unbind() {
		glCall(() -> glBindBuffer(this.type, 0));
	}
	
	public void allocateData(long sizeInBytes) {
		glCall(() -> glBufferData(this.type, sizeInBytes, this.usage));
	}
	
	public void storeData(long startInBytes, int[] data) {
		glCall(() -> glBufferSubData(this.type, startInBytes, data));
	}
	
	public void storeData(long startInBytes, float[] data) {
		glCall(() -> glBufferSubData(this.type, startInBytes, data));
	}
	
	public void delete() {
		glCall(() -> glDeleteBuffers(this.id));
	}

}
