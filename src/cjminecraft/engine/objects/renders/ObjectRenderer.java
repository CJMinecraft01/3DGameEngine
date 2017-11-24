package cjminecraft.engine.objects.renders;

import java.util.List;

import cjminecraft.engine.camera.ICamera;
import cjminecraft.engine.objects.GameObject;
import cjminecraft.engine.objects.data.DataType;
import cjminecraft.engine.objects.data.VertexData;
import cjminecraft.engine.objects.renders.coloured.ColouredShader;
import cjminecraft.engine.objects.renders.textured.TexturedShader;
import cjminecraft.engine.util.IRenderer;
import cjminecraft.engine.util.Maths;
import cjminecraft.engine.util.opengl.OpenGLUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static cjminecraft.engine.util.opengl.GLError.*;

public class ObjectRenderer implements IRenderer<GameObject> {

	private final ColouredShader colouredShader;
	private final TexturedShader texturedShader;
	
	public ObjectRenderer() {
		this.colouredShader = new ColouredShader();
		this.texturedShader = new TexturedShader();
	}
	
	@Override
	public void render(GameObject object, ICamera camera) {
		if(!object.hasData(DataType.VERTEX_DATA) || !object.hasData(DataType.TRANSORMATION_DATA))
			return;
		if(object.hasData(DataType.COLOUR_DATA)) {
			this.colouredShader.start();
			ColouredShader.COLOUR.loadValue(object.getData(DataType.COLOUR_DATA).getColour());
			ColouredShader.PROJECTION_MATRIX.loadValue(camera.getProjectionMatrix());
			ColouredShader.VIEW_MATRIX.loadValue(camera.getViewMatrix());
			ColouredShader.TRANSFORMATION_MATRIX.loadValue(Maths.createTransformationMatrix(object.getData(DataType.TRANSORMATION_DATA)));
			this.colouredShader.stop();
			
			VertexData vertexData = object.getData(DataType.VERTEX_DATA);
			vertexData.getVao().bind();
			vertexData.getVao().enableAttributes(0);
			vertexData.getIndexBuffer().bind();
			
			if(object.getData(DataType.COLOUR_DATA).getColour().w != 0)
				OpenGLUtils.disableCulling();
			
			glCall(() -> glDrawElements(GL_TRIANGLES, vertexData.getVertexCount(), GL_UNSIGNED_INT, 0));
			
			vertexData.getIndexBuffer().unbind();
			vertexData.getVao().disableAttributes(0);
			vertexData.getVao().unbind();
		} else if(object.hasData(DataType.TEXTURE_DATA)) {
			this.texturedShader.start();
			TexturedShader.MODEL_TEXTURE.loadValue(object.getData(DataType.TEXTURE_DATA).getTextureId());
			TexturedShader.PROJECTION_MATRIX.loadValue(camera.getProjectionMatrix());
			TexturedShader.VIEW_MATRIX.loadValue(camera.getViewMatrix());
			TexturedShader.TRANSFORMATION_MATRIX.loadValue(Maths.createTransformationMatrix(object.getData(DataType.TRANSORMATION_DATA)));
			this.texturedShader.stop();
			
			VertexData vertexData = object.getData(DataType.VERTEX_DATA);
			vertexData.getVao().bind();
			vertexData.getVao().enableAttributes(0, 1);
			vertexData.getIndexBuffer().bind();
			
			if(object.getData(DataType.TEXTURE_DATA).hasTransparency())
				OpenGLUtils.disableCulling();
			
			glCall(() -> glActiveTexture(GL_TEXTURE0));
			glCall(() -> glBindTexture(GL_TEXTURE_2D, object.getData(DataType.TEXTURE_DATA).getTextureId()));
			
			glCall(() -> glDrawElements(GL_TRIANGLES, vertexData.getVertexCount(), GL_UNSIGNED_INT, 0));
			
			vertexData.getIndexBuffer().unbind();
			vertexData.getVao().disableAttributes(0, 1);
			vertexData.getVao().unbind();
		}
	}

	@Override
	public void render(List<GameObject> objectBatch, ICamera camera) {
		
	}

	@Override
	public void cleanUp() {
		this.colouredShader.cleanUp();
	}

}
