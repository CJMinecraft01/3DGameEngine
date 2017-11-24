package cjminecraft.engine.objects.data;

import org.joml.Vector4f;

public class ColourData extends Data {

	private Vector4f colour;
	
	public ColourData(Vector4f colour) {
		this.colour = colour;
	}
	
	public ColourData(float r, float g, float b, float a) {
		this(new Vector4f(r, g, b, a));
	}
	
	public ColourData(float r, float g, float b) {
		this(r, g, b, 1.0F);
	}
	
	public Vector4f getColour() {
		return colour;
	}
	
	public ColourData setColour(Vector4f colour) {
		this.colour = colour;
		return this;
	}
	
	@Override
	public DataType<?> getDataType() {
		return DataType.COLOUR_DATA;
	}

}
