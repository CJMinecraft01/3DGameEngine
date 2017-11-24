#version 330

layout(location = 0) in vec3 position;

out vec4 pass_colour;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

uniform vec4 colour;

void main(void) {

	gl_Position = viewMatrix * vec4(position, 1.0) * projectionMatrix * transformationMatrix;
	
	pass_colour = colour;

}