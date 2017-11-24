#version 150 core

in vec2 pass_textureCoordinates;

uniform sampler2D modelTexture;

void main(void) {

	vec4 textureColour = texture(modelTexture, pass_textureCoordinates);
	if (textureColour.a == 0) {
		discard;
	}
	
	gl_FragColor = textureColour;
	gl_FragColor = vec4(1.0, 0.0, 1.0, 1.0);

}