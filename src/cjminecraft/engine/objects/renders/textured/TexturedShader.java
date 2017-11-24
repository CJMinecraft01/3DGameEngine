package cjminecraft.engine.objects.renders.textured;

import cjminecraft.engine.shaders.Shader;
import cjminecraft.engine.shaders.uniforms.UniformMat4;
import cjminecraft.engine.shaders.uniforms.UniformSampler2D;

public class TexturedShader extends Shader {

	public static final UniformMat4 TRANSFORMATION_MATRIX = new UniformMat4("transformationMatrix");
	public static final UniformMat4 VIEW_MATRIX = new UniformMat4("viewMatrix");
	public static final UniformMat4 PROJECTION_MATRIX = new UniformMat4("projectionMatrix");
	public static final UniformSampler2D MODEL_TEXTURE = new UniformSampler2D("modelTexture");
	
	public TexturedShader() {
		super("", "position", "textureCoordinates");
		super.storeAllUniformLocations(VIEW_MATRIX, PROJECTION_MATRIX, TRANSFORMATION_MATRIX, MODEL_TEXTURE);
	}

}
