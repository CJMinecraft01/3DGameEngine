package cjminecraft.engine.objects.renderers;

import cjminecraft.engine.shaders.Shader;
import cjminecraft.engine.shaders.uniforms.UniformMat4;
import cjminecraft.engine.shaders.uniforms.UniformSampler2D;

class TexturedObjectShader extends Shader {

	public static final UniformMat4 TRANSFORMATION_MATRIX = new UniformMat4("transformationMatrix");
	public static final UniformMat4 PROJECTION_MATRIX = new UniformMat4("projectionMatrix");
	public static final UniformMat4 VIEW_MATRIX = new UniformMat4("viewMatrix");
	public static final UniformSampler2D MODEL_TEXTURE = new UniformSampler2D("modelTexture");
	
	public TexturedObjectShader() {
		super("texturedObject", "position", "textureCoordinates", "normal");
		super.storeAllUniformLocations(TRANSFORMATION_MATRIX, PROJECTION_MATRIX, VIEW_MATRIX, MODEL_TEXTURE);
	}

}
