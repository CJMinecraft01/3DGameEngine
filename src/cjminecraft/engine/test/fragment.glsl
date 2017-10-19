#version 330

in vec3 pass_colour;

out vec4 outColour;

void main(void) {
	
	outColour = vec4(pass_colour, 1.0);
	
}