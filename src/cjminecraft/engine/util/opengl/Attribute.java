package cjminecraft.engine.util.opengl;

import static cjminecraft.engine.util.GLError.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL20.*;

public class Attribute {
	
	protected final int attributeNumber;
	protected final int dataType;
	protected final boolean normalised;
	protected final int componentCount;
	protected final int bytesPerVertex;
	
	public Attribute(int attributeNumber, int dataType, int componentCount) {
		this.attributeNumber = attributeNumber;
		this.dataType = dataType;
		this.componentCount = componentCount;
		this.normalised = false;
		this.bytesPerVertex = calcBytesPerVertex();
	}
	
	public Attribute(int attributeNumber, int dataType, int componentCount, boolean normalised) {
		this.attributeNumber = attributeNumber;
		this.dataType = dataType;
		this.componentCount = componentCount;
		this.normalised = normalised;
		this.bytesPerVertex = calcBytesPerVertex();
	}
	
	protected void enable(boolean enable) {
		if(enable)
			glCall(() -> glEnableVertexAttribArray(this.attributeNumber));
		else
			glCall(() -> glDisableVertexAttribArray(this.attributeNumber));
	}
	
	protected void link(int offset, int stride) {
		glCall(() -> glVertexAttribPointer(this.attributeNumber, this.componentCount, this.dataType, this.normalised, stride, offset));
	}
	
	private int calcBytesPerVertex() {
		switch(this.dataType) {
		case GL_FLOAT:
		case GL_UNSIGNED_INT:
		case GL_INT:
			return 4 * this.componentCount;
		case GL_SHORT:
		case GL_UNSIGNED_SHORT:
			return 2 * this.componentCount;
		case GL_BYTE:
		case GL_UNSIGNED_BYTE:
			return this.componentCount;
		case GL_UNSIGNED_INT_2_10_10_10_REV:
			return 4;
		default:
			System.err.println("Unsupported data type for VAO attribute: " + this.dataType);
			return 0;
		}
	}

}
