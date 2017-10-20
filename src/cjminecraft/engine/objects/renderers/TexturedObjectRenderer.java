package cjminecraft.engine.objects.renderers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import java.util.List;

import cjminecraft.engine.camera.ICamera;
import cjminecraft.engine.objects.GameObject;
import cjminecraft.engine.objects.data.DataType;
import cjminecraft.engine.objects.data.TextureData;
import cjminecraft.engine.objects.data.TransformationData;
import cjminecraft.engine.util.IRenderer;
import cjminecraft.engine.util.Maths;
import cjminecraft.engine.util.OpenGLUtils;
import cjminecraft.engine.util.opengl.Vao;

public class TexturedObjectRenderer implements IRenderer<GameObject> {
	
	private final TexturedObjectShader shader;
	
	public TexturedObjectRenderer() {
		this.shader = new TexturedObjectShader();
	}
	
	@Override
	public void render(GameObject object, ICamera camera) {
		if(!object.hasData(DataType.VERTEX_DATA) || !object.hasData(DataType.TRANSORMATION_DATA) || !object.hasData(DataType.TEXTURE_DATA))
			return;
		this.shader.start();
		TexturedObjectShader.PROJECTION_MATRIX.loadValue(camera.getProjectionMatrix());
		TexturedObjectShader.VIEW_MATRIX.loadValue(camera.getViewMatrix());
		prepareObject(object);
		glDrawElements(GL_TRIANGLES, object.getData(DataType.VERTEX_DATA).getVertexCount(), GL_UNSIGNED_INT, 0);
		unbindObject(object);
		this.shader.stop();
	}

	@Override
	public void render(List<GameObject> objectBatch, ICamera camera) {
	}

	@Override
	public void cleanUp() {
		this.shader.cleanUp();
	}
	
	
	private void prepareObject(GameObject object) {
		TransformationData transformation = object.getData(DataType.TRANSORMATION_DATA);
		TexturedObjectShader.TRANSFORMATION_MATRIX.loadValue(Maths.createTransformationMatrix(transformation));
		Vao vao = object.getData(DataType.VERTEX_DATA).getVao();
		vao.bind();
		vao.enableAttributes();
		TextureData texture = object.getData(DataType.TEXTURE_DATA);
		TexturedObjectShader.MODEL_TEXTURE.loadValue(texture.getTextureId());
		if(texture.hasTransparency())
			OpenGLUtils.disableCulling();
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, texture.getTextureId());
	}
	
	private void unbindObject(GameObject object) {
		OpenGLUtils.enableCulling();
		Vao vao = object.getData(DataType.VERTEX_DATA).getVao();
		vao.disableAttributes();
	}

}
