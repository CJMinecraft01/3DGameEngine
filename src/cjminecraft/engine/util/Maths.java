package cjminecraft.engine.util;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import cjminecraft.engine.objects.data.TransformationData;

public class Maths {
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.translate(translation);
		matrix.rotateXYZ(rotation);
		matrix.scale(scale);
		return matrix;
	}
	
	public static Matrix4f createTransformationMatrix(TransformationData transformation) {
		return createTransformationMatrix(transformation.getPosition(), transformation.getRotation(), transformation.getScale());
	}

}
