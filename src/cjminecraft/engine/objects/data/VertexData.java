package cjminecraft.engine.objects.data;

import cjminecraft.engine.util.opengl.Vao;
import cjminecraft.engine.util.opengl.Vbo;

public class VertexData extends Data {

	private Vao vao;
	private Vbo indexBuffer;
	private int vertexCount;

	public VertexData(Vao vao, int vertexCount) {
		this.vao = vao;
		this.vertexCount = vertexCount;
	}
	
	public VertexData(Vao vao, Vbo indexBuffer, int vertexCount) {
		this.vao = vao;
		this.indexBuffer = indexBuffer;
		this.vertexCount = vertexCount;
	}

	public Vao getVao() {
		return this.vao;
	}

	public int getVertexCount() {
		return this.vertexCount;
	}
	
	public Vbo getIndexBuffer() {
		return indexBuffer;
	}

	@Override
	public DataType<?> getDataType() {
		return DataType.VERTEX_DATA;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof VertexData
				? ((VertexData) obj).vao.id == this.vao.id && ((VertexData) obj).vertexCount == this.vertexCount : false;
	}

}
