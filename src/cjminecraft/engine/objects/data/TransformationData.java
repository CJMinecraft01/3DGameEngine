package cjminecraft.engine.objects.data;

import org.joml.Vector3f;

public class TransformationData extends Data {
	
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;
	
	public TransformationData(float x, float y, float z, float rx, float ry, float rz, float sx, float sy, float sz) {
		this(new Vector3f(x, y, z), new Vector3f(rx, ry, rz), new Vector3f(sx, sy, sz));
	}
	
	public TransformationData(float x, float y, float z, Vector3f rotation, float sx, float sy, float sz) {
		this(new Vector3f(x, y, z), rotation, new Vector3f(sx, sy, sz));
	}
	
	public TransformationData(float x, float y, float z, float rx, float ry, float rz, Vector3f scale) {
		this(new Vector3f(x, y, z), new Vector3f(rx, ry, rz), scale);
	}
	
	public TransformationData(float x, float y, float z, Vector3f rotation, Vector3f scale) {
		this(new Vector3f(x, y, z), rotation, scale);
	}
	
	public TransformationData(Vector3f position, float rx, float ry, float rz, float sx, float sy, float sz) {
		this(position, new Vector3f(rx, ry, rz), new Vector3f(sx, sy, sz));
	}
	
	public TransformationData(Vector3f position, float rx, float ry, float rz, Vector3f scale) {
		this(position, new Vector3f(rx, ry, rz), scale);
	}
	
	public TransformationData(Vector3f position, Vector3f rotation, float sx, float sy, float sz) {
		this(position, rotation, new Vector3f(sx, sy, sz));
	}
	
	public TransformationData(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public TransformationData setPosition(Vector3f position) {
		this.position = position;
		return this;
	}
	
	public TransformationData setPositionX(float x) {
		this.position.x = x;
		return this;
	}
	
	public TransformationData setPositionY(float y) {
		this.position.y = y;
		return this;
	}
	
	public TransformationData setPositionZ(float z) {
		this.position.z = z;
		return this;
	}
	
	public Vector3f getPosition() {
		return this.position;
	}
	
	public float getPositionX() {
		return this.position.x;
	}
	
	public float getPositionY() {
		return this.position.y;
	}
	
	public float getPositionZ() {
		return this.position.z;
	}
	
	public TransformationData setRotation(Vector3f rotation) {
		this.rotation = rotation;
		return this;
	}
	
	public TransformationData setRotationPitch(float pitch) {
		this.rotation.y = pitch;
		return this;
	}
	
	public TransformationData setRotationYaw(float yaw) {
		this.rotation.z = yaw;
		return this;
	}
	
	public TransformationData setRotationRoll(float roll) {
		this.rotation.x = roll;
		return this;
	}
	
	public Vector3f getRotation() {
		return this.rotation;
	}
	
	public float getRotationRoll() {
		return this.rotation.x;
	}
	
	public float getRotationPitch() {
		return this.rotation.y;
	}
	
	public float getRotationYaw() {
		return this.rotation.z;
	}
	
	public TransformationData setScale(Vector3f scale) {
		this.scale = scale;
		return this;
	}
	
	public Vector3f getScale() {
		return this.scale;
	}
	
	public float getScaleX() {
		return this.scale.x;
	}
	
	public float getScaleY() {
		return this.scale.y;
	}
	
	public float getScaleZ() {
		return this.scale.z;
	}
	
	public TransformationData increasePositionX(float dx) {
		this.position.x += dx;
		return this;
	}
	
	public TransformationData increasePositionY(float dy) {
		this.position.y += dy;
		return this;
	}
	
	public TransformationData increasePositionZ(float dz) {
		this.position.z += dz;
		return this;
	}
	
	public TransformationData increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
		return this;
	}
	
	public TransformationData increasePosition(Vector3f position) {
		this.position.add(position);
		return this;
	}
	
	public TransformationData increaseRotationRoll(float roll) {
		this.rotation.x += roll;
		return this;
	}
	
	public TransformationData increaseRotationPitch(float pitch) {
		this.rotation.y += pitch;
		return this;
	}
	
	public TransformationData increaseRotationYaw(float yaw) {
		this.rotation.z += yaw;
		return this;
	}
	
	public TransformationData increaseRotation(float roll, float pitch, float yaw) {
		this.rotation.x += roll;
		this.rotation.y += pitch;
		this.rotation.z += yaw;
		return this;
	}
	
	public TransformationData increaseRotation(Vector3f rotation) {
		this.rotation.add(rotation);
		return this;
	}
	
	public TransformationData increaseScaleX(float sx) {
		this.scale.x += sx;
		return this;
	}
	
	public TransformationData increaseScaleY(float sy) {
		this.scale.y += sy;
		return this;
	}
	
	public TransformationData increaseScaleZ(float sz) {
		this.scale.z += sz;
		return this;
	}
	
	public TransformationData increaseScale(float sx, float sy, float sz) {
		this.scale.x += sx;
		this.scale.y += sy;
		this.scale.z += sz;
		return this;
	}
	
	public TransformationData increaseScale(Vector3f scale) {
		this.scale.add(scale);
		return this;
	}
	
	public TransformationData invertRoll() {
		this.rotation.x = -this.rotation.x;
		return this;
	}
	
	public TransformationData invertPitch() {
		this.rotation.y = -this.rotation.y;
		return this;
	}
	
	public TransformationData invertYaw() {
		this.rotation.z = -this.rotation.z;
		return this;
	}
	
	@Override
	public DataType<?> getDataType() {
		return DataType.TRANSORMATION_DATA;
	}

}
