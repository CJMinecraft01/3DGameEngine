package cjminecraft.engine.loaders;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import cjminecraft.engine.util.objects.RawObject;

public class VaoLoader {
	
	private static List<Integer> vaos = new ArrayList<Integer>();
	private static List<Integer> vbos = new ArrayList<Integer>();
	
	public static void cleanUp() {
		for(int vao : vaos)
			glDeleteVertexArrays(vao);
		for(int vbo : vbos)
			glDeleteBuffers(vbo);
	}
	
	private static int createVAO() {
		int vaoId = glGenBuffers();
		vaos.add(vaoId);
		glBindVertexArray(vaoId);
		return vaoId;
	}
	
	private static void unbindVAO() {
		glBindVertexArray(0);
	}
	
	private static void storeDataInAttributeList(int attributeNymber, int coordinateSize, float[] data) {
		int vboId = glGenBuffers();
		vbos.add(vboId);
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glVertexAttribPointer(attributeNymber, coordinateSize, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	private static void bindIndicesBuffer(int[] indices) {
		int vboId = glGenBuffers();
		vbos.add(vboId);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
	}
	
	public static int loadToVAO(float[] positions, float[] textureCoords) {
		int vaoId = createVAO();
		storeDataInAttributeList(0, 2, positions);
		storeDataInAttributeList(1, 2, textureCoords);
		unbindVAO();
		return vaoId;
	}
	
	public static RawObject loadToVAO(float[] positions, int dimensions) {
		int vaoId = createVAO();
		storeDataInAttributeList(0, dimensions, positions);
		unbindVAO();
		return new RawObject(vaoId, positions.length / dimensions);
	}
	
	public static RawObject loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices) {
		int vaoId = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 3, positions);
		storeDataInAttributeList(1, 2, textureCoords);
		storeDataInAttributeList(2, 3, normals);
		unbindVAO();
		return new RawObject(vaoId, indices.length);
	}
	
	public static RawObject loadToVAO(float[] positions, float[] textureCoords, float[] normals, float[] tangents,
			int[] indices) {
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 3, positions);
		storeDataInAttributeList(1, 2, textureCoords);
		storeDataInAttributeList(2, 3, normals);
		storeDataInAttributeList(3, 3, tangents);
		unbindVAO();
		return new RawObject(vaoID, indices.length);
	}
	
	public static int createEmptyVbo(int floatCount) {
		int vbo = glGenBuffers();
		vbos.add(vbo);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, floatCount * 4, GL_STREAM_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		return vbo;
	}
	
	public static void addInstancedAttrivute(int vao, int vbo, int attribute, int dataSize, int instancedDataLength, int offset) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBindVertexArray(vao);
		glVertexAttribPointer(attribute, dataSize, GL_FLOAT, false, instancedDataLength * 4, offset * 4);
		glVertexAttribDivisor(GL_ARRAY_BUFFER, 1);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	public static void updateVbo(int vbo, float[] data) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, data.length * 4, GL_STATIC_DRAW);
		glBufferSubData(GL_ARRAY_BUFFER, 0, data);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

}
