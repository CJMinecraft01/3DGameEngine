package cjminecraft.engine.objects;

import java.util.HashMap;

import cjminecraft.engine.objects.data.Data;
import cjminecraft.engine.objects.data.DataType;

public class GameObject {
	
	private HashMap<DataType<?>, Data> objectData = new HashMap<DataType<?>, Data>();
	
	public GameObject attach(Data data) {
		this.objectData.putIfAbsent(data.getDataType(), data);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Data> T getData(DataType<T> type) {
		return (T) this.objectData.get(type);
	}
	
	public boolean hasData(DataType<?> type) {
		return this.objectData.containsKey(type);
	}

}
