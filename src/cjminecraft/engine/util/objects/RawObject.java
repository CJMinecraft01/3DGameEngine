package cjminecraft.engine.util.objects;

public class RawObject {
	
	private int vaoId;
	private int vertexCount;
	
	public RawObject(int vaoId, int vertexCount) {
		this.vaoId = vaoId;
		this.vertexCount = vertexCount;
	}

	public int getVaoId() {
		return vaoId;
	}

	public int getVertexCount() {
		return vertexCount;
	}

}
