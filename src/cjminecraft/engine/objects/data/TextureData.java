package cjminecraft.engine.objects.data;

import java.io.IOException;

import cjminecraft.engine.loaders.TextureLoader;

public class TextureData extends Data {

	private int textureId;
	private boolean transparency = false;

	public TextureData(String texture) throws IOException {
		this.textureId = TextureLoader.loadTexture(texture);
	}

	public TextureData(String texture, int u, int v) throws IOException {
		this.textureId = TextureLoader.loadTexture(texture, u, v);
	}

	public TextureData(String texture, int u, int v, int width, int height) throws IOException {
		this.textureId = TextureLoader.loadTexture(texture, u, v, width, height);
	}

	public TextureData setTransparency(boolean transparency) {
		this.transparency = transparency;
		return this;
	}

	public int getTextureId() {
		return textureId;
	}

	public boolean hasTransparency() {
		return transparency;
	}

	@Override
	public DataType<?> getDataType() {
		return DataType.TEXTURE_DATA;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof TextureData ? ((TextureData) obj).textureId == this.textureId
				&& ((TextureData) obj).transparency == this.transparency : false;
	}

}
