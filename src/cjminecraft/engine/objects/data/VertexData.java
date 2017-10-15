package cjminecraft.engine.objects.data;

public class VertexData extends Data {

	private int vaoId;
	private int vertexCount;

	public VertexData(int vaoId, int vertexCount) {
		this.vaoId = vaoId;
		this.vertexCount = vertexCount;
	}

	public int getVaoId() {
		return vaoId;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	@Override
	public DataType<?> getDataType() {
		return DataType.VERTEX_DATA;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof VertexData
				? ((VertexData) obj).vaoId == this.vaoId && ((VertexData) obj).vertexCount == this.vertexCount : false;
	}

}
