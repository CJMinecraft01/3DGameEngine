package cjminecraft.engine.util.objects;

public class ObjectTexture {
	
	private int textureId;
	private boolean transparency = false;
	
	public ObjectTexture(int textureId) {
		this.textureId = textureId;
	}
	
	public boolean hasTransparency() {
		return transparency;
	}
	
	public ObjectTexture setTransparency(boolean transparency) {
		this.transparency = transparency;
		return this;
	}
	
	public int getTextureId() {
		return textureId;
	}

}
