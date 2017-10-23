package cjminecraft.engine.util.opengl;

import static org.lwjgl.opengl.GL15.*;

import static cjminecraft.engine.util.GLError.*;

public class Vbo {

	private final int id;
	private final int type;
	private final int usage;

	private Vbo(int id, int type, int usage) {
		this.id = id;
		this.type = type;
		this.usage = usage;
	}

	public void bind() {
		glCall(() -> glBindBuffer(this.type, this.id));
	}

	public void unbind() {
		glCall(() -> glBindBuffer(this.type, 0));
	}

	public void delete() {
		glCall(() -> glDeleteBuffers(this.id));
	}

	public void allocateData(int dataSize) {
		glCall(() -> glBufferData(this.type, dataSize * 4, this.usage));
	}

	public void allocateData(float[] data) {
		glCall(() -> glBufferData(this.type, data, this.usage));
	}

	public void allocateData(int[] data) {
		glCall(() -> glBufferData(this.type, data, this.usage));
	}

	public void update(int startInBytes, float[] data) {
		glCall(() -> glBufferSubData(this.type, startInBytes, data));
	}

	public void update(int startInBytes, int[] data) {
		glCall(() -> glBufferSubData(this.type, startInBytes, data));
	}

	public static Vbo create(int type, int usage) {
		return new Vbo(glCallT(() -> glGenBuffers()), type, usage);
	}

}
