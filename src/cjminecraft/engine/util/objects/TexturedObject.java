package cjminecraft.engine.util.objects;

public class TexturedObject {
	
	private RawObject rawObject;
	private ObjectTexture texture;
	
	public TexturedObject(RawObject rawObject, ObjectTexture texture) {
		this.rawObject = rawObject;
		this.texture = texture;
	}

	public RawObject getRawObject() {
		return rawObject;
	}

	public ObjectTexture getTexture() {
		return texture;
	}

}