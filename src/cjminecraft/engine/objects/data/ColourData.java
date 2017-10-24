package cjminecraft.engine.objects.data;

import org.joml.Vector3f;

public class ColourData extends Data {

	private Vector3f colour;
	
	public ColourData(Vector3f colour) {
		this.colour = colour;
	}
	
	public Vector3f getColour() {
		return colour;
	}
	
	public ColourData setColour(Vector3f colour) {
		this.colour = colour;
		return this;
	}
	
	@Override
	public DataType<?> getDataType() {
		return DataType.COLOUR_DATA;
	}

}
