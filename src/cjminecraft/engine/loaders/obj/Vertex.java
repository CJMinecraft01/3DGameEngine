package cjminecraft.engine.loaders.obj;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

class Vertex {
	
	private static final int NO_INDEX = -1;
	
	private Vector3f position;
	private int textureIndex = NO_INDEX;
	private int normalIndex = NO_INDEX;
	private Vertex duplicateVertex = null;
	private int index;
	private float length;
	private List<Vector3f> tangents = new ArrayList<Vector3f>();
	private Vector3f averagedTangent = new Vector3f(0, 0, 0);
	
	public Vertex(int index,Vector3f position){
		this.index = index;
		this.position = position;
		this.length = position.length();
	}
	
	public void addTangent(Vector3f tangent){
		this.tangents.add(tangent);
	}
	
	public void averageTangents(){
		if(this.tangents.isEmpty())
			return;
		for(Vector3f tangent : this.tangents)
			tangent.add(tangent, this.averagedTangent);
		this.averagedTangent.normalize();
	}
	
	public Vector3f getAverageTangent(){
		return this.averagedTangent;
	}
	
	public int getIndex(){
		return this.index;
	}
	
	public float getLength(){
		return this.length;
	}
	
	public boolean isSet(){
		return this.textureIndex != NO_INDEX && this.normalIndex != NO_INDEX;
	}
	
	public boolean hasSameTextureAndNormal(int textureIndexOther,int normalIndexOther){
		return textureIndexOther == this.textureIndex && normalIndexOther == this.normalIndex;
	}
	
	public void setTextureIndex(int textureIndex){
		this.textureIndex = textureIndex;
	}
	
	public void setNormalIndex(int normalIndex){
		this.normalIndex = normalIndex;
	}

	public Vector3f getPosition() {
		return this.position;
	}

	public int getTextureIndex() {
		return this.textureIndex;
	}

	public int getNormalIndex() {
		return this.normalIndex;
	}

	public Vertex getDuplicateVertex() {
		return this.duplicateVertex;
	}

	public void setDuplicateVertex(Vertex duplicateVertex) {
		this.duplicateVertex = duplicateVertex;
	}

}
