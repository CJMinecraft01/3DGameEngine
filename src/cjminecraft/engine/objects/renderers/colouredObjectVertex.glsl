#version 330

layout(location = 0) in vec3 position;

out vec4 pass_colour;

uniform vec4 colour;

void main(void) {

	pass_colour = colour;
	
	gl_Position = vec4(position, 1.0);

}