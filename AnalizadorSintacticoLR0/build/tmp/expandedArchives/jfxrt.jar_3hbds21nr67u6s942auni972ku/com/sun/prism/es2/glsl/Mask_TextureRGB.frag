#ifdef GL_ES
#extension GL_OES_standard_derivatives : enable
precision highp float;
precision highp int;
#define HIGHP highp
#define MEDIUMP mediump
#define LOWP lowp
#else
#define HIGHP
#define MEDIUMP
#define LOWP
#endif
varying vec2 texCoord0;
varying vec2 texCoord1;
varying LOWP vec4 perVertexColor;
uniform sampler2D imageTex;
uniform sampler2D maskTex;
LOWP vec4 paint(vec2 imgCoord, vec2 maskCoord) {
return texture2D(imageTex, imgCoord) * texture2D(maskTex, maskCoord).a;
}
void main() {
gl_FragColor = paint(texCoord0, texCoord1) * perVertexColor;
}
