package cjminecraft.engine.shaders.uniforms;

/**
 * Represents a uniform variable in a shader file which has a sampler2D value
 * 
 * @author CJMinecraft
 *
 */
public class UniformSampler2D extends UniformInt {
	
	private int currentValue;
	private boolean used = false;
	
	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformSampler2D(String name) {
		super(name);
	}
	
	@Override
	public UniformVariable<Integer> loadValue(Integer value) {
		if(!this.used && this.currentValue != value.intValue()) {
			super.loadValue(value);
			this.used = true;
			this.currentValue = value;
		}
		return this;
	}

}
