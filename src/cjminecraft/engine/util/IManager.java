package cjminecraft.engine.util;

public interface IManager {
	
	void preInit() throws Exception;
	
	void init() throws Exception;
	
	void postInit() throws Exception;
	
	void loop() throws Exception;
	
	void cleanUp() throws Exception;

}
