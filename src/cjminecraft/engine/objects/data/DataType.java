package cjminecraft.engine.objects.data;

public class DataType<T> {
	
	public static final DataType<TextureData> TEXTURE_DATA = new DataType<TextureData>("texture");
	public static final DataType<TransformationData> TRANSORMATION_DATA = new DataType<TransformationData>("transformation");
	public static final DataType<VertexData> VERTEX_DATA = new DataType<VertexData>("vertex");
	
	private String key;
	
	public DataType(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		return getKey();
	}

}
