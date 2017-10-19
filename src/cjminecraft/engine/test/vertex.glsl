#version 330

layout(location = 0) in vec3 position;

out vec3 pass_colour;

uniform vec3 colour;

void main(void) {

	pass_colour = colour;

	gl_Position = vec4(position, 1.0);

}