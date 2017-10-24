package cjminecraft.engine.loaders;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;
import static cjminecraft.engine.util.GLError.*;

import java.util.ArrayList;
import java.util.List;

import cjminecraft.engine.objects.data.VertexData;
import cjminecraft.engine.util.opengl.Vao;
import cjminecraft.engine.util.opengl.Vbo;

/**
 * The loader for Vertex Array Objects (VAOs) and Vertex Buffer Objects (VBOs)
 * 
 * @author CJMinecraft
 *
 */
public class VaoLoader {

	/**
	 * A cache of all the VAOs
	 */
	private static List<Vao> vaos = new ArrayList<Vao>();

	/**
	 * Delete all of the VAOs and VBOs from memory
	 */
	public static void cleanUp() {
		for (Vao vao : vaos)
			vao.delete(true);
	}

	/**
	 * Create a blank vertex array object (VAO)
	 * 
	 * @return The id of the VAO
	 */
	private static Vao createVAO() {
		Vao vao = Vao.create();
		vao.bind();
		vaos.add(vao);
		return vao;
	}

	/**
	 * Load positions to a VAO
	 * 
	 * @param positions
	 *            The positions to load
	 * @param indices
	 *            The order of each point from the positions
	 * @return The {@link VertexData} containing all the vertex information
	 */
	public static VertexData loadToVAO(float[] positions, int[] indices) {
		Vao vao = createVAO();
		vao.storeData(0, 3, positions, GL_STATIC_DRAW);
		Vbo indexBuffer = vao.createIndexBuffer(indices);
		vao.unbind();
		return new VertexData(vao, indexBuffer, indices.length);
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
		Vao vao = createVAO();
		vao.storeData(0, dimensions, positions, GL_STATIC_DRAW);
		vao.unbind();
		return new VertexData(vao, positions.length / dimensions);
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
	public static VertexData loadToVAO(float[] positions, float[] textureCoords) {
		Vao vao = createVAO();
		vao.storeData(0, 2, positions, GL_STATIC_DRAW);
		vao.storeData(1, 2, textureCoords, GL_STATIC_DRAW);
		vao.unbind();
		return new VertexData(vao, positions.length / 2);
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
		Vao vao = createVAO();
		Vbo indexBuffer = vao.createIndexBuffer(indices);
		vao.storeData(0, 3, positions, GL_STATIC_DRAW);
		vao.storeData(1, 2, textureCoords, GL_STATIC_DRAW);
		vao.storeData(2, 3, normals, GL_STATIC_DRAW);
		vao.unbind();
		return new VertexData(vao, indexBuffer, indices.length);
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
		Vao vao = createVAO();
		Vbo indexBuffer = vao.createIndexBuffer(indices);
		vao.storeData(0, 3, positions, GL_STATIC_DRAW);
		vao.storeData(1, 2, textureCoords, GL_STATIC_DRAW);
		vao.storeData(2, 3, normals, GL_STATIC_DRAW);
		vao.storeData(3, 3, tangents, GL_STATIC_DRAW);
		vao.unbind();
		return new VertexData(vao, indexBuffer, indices.length);
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
	public static void addInstancedAttribute(Vao vao, Vbo vbo, int attribute, int dataSize, int instancedDataLength,
			int offset) {
		vbo.bind();
		vao.bind();
		glCall(() -> glVertexAttribPointer(attribute, dataSize, GL_FLOAT, false, instancedDataLength * 4, offset * 4));
		glCall(() -> glVertexAttribDivisor(GL_ARRAY_BUFFER, 1));
		vbo.unbind();
		vao.unbind();
	}

}
