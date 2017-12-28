#version 330

layout(location = 0) in vec3 position;

out vec3 pass_colour;

uniform vec3 colour;

uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;
uniform mat4 transformationMatrix;

void main(void) {

	//vec4 worldPosition = transformationMatrix * vec4(position, 1.0);
	gl_Position = viewMatrix * vec4(position, 1.0) * transformationMatrix;

	pass_colour = colour;

}