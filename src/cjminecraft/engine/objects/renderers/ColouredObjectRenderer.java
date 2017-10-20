package cjminecraft.engine.objects.renderers;

import java.util.List;

import org.joml.Vector4f;

import cjminecraft.engine.camera.ICamera;
import cjminecraft.engine.objects.GameObject;
import cjminecraft.engine.objects.data.DataType;
import cjminecraft.engine.util.IRenderer;

public class ColouredObjectRenderer implements IRenderer<GameObject> {

	private final ColouredObjectShader shader;
	
	public ColouredObjectRenderer() {
		this.shader = new ColouredObjectShader();
	}
	
	@Override
	public void render(GameObject object, ICamera camera) {
		if(!object.hasData(DataType.VERTEX_DATA))
			return;
		this.shader.start();
		ColouredObjectShader.COLOUR.loadValue(new Vector4f(0.5f, 0.5f, 1.0f, 1.0f));
		
	}

	@Override
	public void render(List<GameObject> objectBatch, ICamera camera) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanUp() {
		this.shader.cleanUp();
	}

}
