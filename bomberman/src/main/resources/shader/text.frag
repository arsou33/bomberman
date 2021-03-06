#version 330 core

uniform sampler2D texture_diffuse;

in vec2 pass_texCoord;

out vec4 out_color;

void main () {
  out_color = texture(texture_diffuse, pass_texCoord);
}
