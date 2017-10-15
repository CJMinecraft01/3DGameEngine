package cjminecraft.engine.loaders;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import cjminecraft.engine.objects.data.VertexData;

/**
 * The loader for Vertex Array Objects (VAOs) and Vertex Buffer Objects (VBOs)
 * 
 * @author CJMinecraft
 *
 */
public class VaoLoader {

	/**
	 * A cache of all the VAO ids
	 */
	private static List<Integer> vaos = new ArrayList<Integer>();
	/**
	 * A cache of all the VBO ids
	 */
	private static List<Integer> vbos = new ArrayList<Integer>();

	/**
	 * Delete all of the VAOs and VBOs from memory
	 */
	public static void cleanUp() {
		for (int vao : vaos)
			glDeleteVertexArrays(vao);
		for (int vbo : vbos)
			glDeleteBuffers(vbo);
	}

	/**
	 * Create a blank vertex array object (VAO)
	 * 
	 * @return The id of the VAO
	 */
	private static int createVAO() {
		int vaoId = glGenBuffers();
		vaos.add(vaoId);
		glBindVertexArray(vaoId);
		return vaoId;
	}

	/**
	 * Unbind a VAO
	 */
	private static void unbindVAO() {
		glBindVertexArray(0);
	}

	/**
	 * Store a <code>float[]</code> of data in the given attribute
	 * 
	 * @param attributeNumber
	 *            The attribute number
	 * @param coordinateSize
	 *            The dimensions of the data (<i>E.g. a world position would be
	 *            an example of a 3D array so 3 but a 2D screen position would
	 *            be 2</i>
	 * @param data
	 *            The data to store
	 */
	private static void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data) {
		int vboId = glGenBuffers();
		vbos.add(vboId);
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glVertexAttribPointer(attributeNumber, coordinateSize, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	/**
	 * Bind an indices buffer
	 * 
	 * @param indices
	 *            The buffer to bind (<i>This should just say the order in which
	 *            positions are etc.</i>)
	 */
	private static void bindIndicesBuffer(int[] indices) {
		int vboId = glGenBuffers();
		vbos.add(vboId);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
	}

	/**
	 * Load positions to a VAO
	 * 
	 * @param positions
	 *            The positions to load
	 * @param dimensions
	 *            The dimensions of the positions (<i>E.g. a world position
	 *            would be an example of a 3D array so 3 but a 2D screen
	 *            position would be 2</i>
	 * @return The {@link VertexData} containing all the vertex information
	 */
	public static VertexData loadToVAO(float[] positions, int dimensions) {
		int vaoId = createVAO();
		storeDataInAttributeList(0, dimensions, positions);
		unbindVAO();
		return new VertexData(vaoId, positions.length / dimensions);
	}

	/**
	 * Load positions and texture coordinates to a VAO
	 * 
	 * @param positions
	 *            The positions to load (Stored in position 0)
	 * @param textureCoords
	 *            The texture coordinates to load (Stored in position 1)
	 * @return The {@link VertexData} containing all the vertex information
	 */
	public static int loadToVAO(float[] positions, float[] textureCoords) {
		int vaoId = createVAO();
		storeDataInAttributeList(0, 2, positions);
		storeDataInAttributeList(1, 2, textureCoords);
		unbindVAO();
		return vaoId;
	}

	/**
	 * Load positions, texture coordinates, surface normals and indices to a VAO
	 * 
	 * @param positions
	 *            The positions to load (Stored in position 0)
	 * @param textureCoords
	 *            The textures to load (Stored in position 1)
	 * @param normals
	 *            The normals to load (Stored in position 2)
	 * @param indices
	 *            The indices to load
	 * @return The {@link VertexData} containing all the vertex information
	 */
	public static VertexData loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices) {
		int vaoId = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 3, positions);
		storeDataInAttributeList(1, 2, textureCoords);
		storeDataInAttributeList(2, 3, normals);
		unbindVAO();
		return new VertexData(vaoId, indices.length);
	}

	/**
	 * Load positions, texture coordinates, surface normals, tangents and
	 * indices to a VAO
	 * 
	 * @param positions
	 *            The positions to load (Stored in position 0)
	 * @param textureCoords
	 *            The textures to load (Stored in position 1)
	 * @param normals
	 *            The normals to load (Stored in position 2)
	 * @param tangents
	 *            The tangents to load (Stored in position 3)
	 * @param indices
	 *            The indices to load
	 * @return The {@link VertexData} containing all the vertex information
	 */
	public static VertexData loadToVAO(float[] positions, float[] textureCoords, float[] normals, float[] tangents,
			int[] indices) {
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 3, positions);
		storeDataInAttributeList(1, 2, textureCoords);
		storeDataInAttributeList(2, 3, normals);
		storeDataInAttributeList(3, 3, tangents);
		unbindVAO();
		return new VertexData(vaoID, indices.length);
	}

	/**
	 * Create an empty Vertex Buffer Object (VBO)
	 * 
	 * @param floatCount
	 *            The number of floats the VBO will hold
	 * @return The id of the VBO
	 */
	public static int createEmptyVbo(int floatCount) {
		int vbo = glGenBuffers();
		vbos.add(vbo);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, floatCount * 4, GL_STREAM_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		return vbo;
	}

	/**
	 * Add an instanced attribute to a VBO
	 * 
	 * @param vao
	 *            The VAO to add to the VBO
	 * @param vbo
	 *            The VBO to add an attribute to
	 * @param attribute
	 *            The attribute index of the VAO to add
	 * @param dataSize
	 *            The size of the data to add
	 * @param instancedDataLength
	 *            The size of the instanced data
	 * @param offset
	 *            The offset of the data
	 */
	public static void addInstancedAttrivute(int vao, int vbo, int attribute, int dataSize, int instancedDataLength,
			int offset) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBindVertexArray(vao);
		glVertexAttribPointer(attribute, dataSize, GL_FLOAT, false, instancedDataLength * 4, offset * 4);
		glVertexAttribDivisor(GL_ARRAY_BUFFER, 1);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		unbindVAO();
	}

	/**
	 * Update the given VBO with the given data
	 * 
	 * @param vbo
	 *            The VBO to update
	 * @param data
	 *            The data to give to the VBO
	 */
	public static void updateVbo(int vbo, float[] data) {
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, data.length * 4, GL_STATIC_DRAW);
		glBufferSubData(GL_ARRAY_BUFFER, 0, data);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

}
