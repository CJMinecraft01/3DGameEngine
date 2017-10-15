#version 330

in vec2 pass_textureCoordinates;
in vec3 surfaceNormal;
in vec3 toCameraVector;

out vec4 out_Colour;

uniform sampler2D modelTexture;

void main(void) {

	vec4 textureColour = texture(modelTexture, pass_textureCoordinates);
	if (textureColour.a == 0) {
		discard;
	}
	gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
	out_Colour = textureColour;

}