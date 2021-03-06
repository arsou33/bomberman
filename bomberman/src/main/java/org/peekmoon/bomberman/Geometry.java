package org.peekmoon.bomberman;

import org.joml.Matrix4f;
import org.peekmoon.bomberman.opengl.GLMesh;
import org.peekmoon.bomberman.shader.ModelMatrix;

public class Geometry {
    
    private GLMesh mesh;
    private ModelMatrix modelMatrix;
    private Matrix4f transformation;
    
    
    public Geometry(GLMesh mesh, ModelMatrix modelMatrix) {
        this.mesh = mesh;
        this.modelMatrix = modelMatrix;
        transformation = new Matrix4f();
    }
    
    public void setPosition(float x, float y, float z) {
        transformation.setTranslation(x, y, z);
    }
    
    public void render() {
        modelMatrix.setModelMatrix(transformation);
        mesh.draw();
    }

}
