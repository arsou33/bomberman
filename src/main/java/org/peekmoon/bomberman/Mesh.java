package org.peekmoon.bomberman;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Mesh implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private float[] positions;
    private short[] indices;
    
    private int indicesVbo;
    private int vao;
    
    public static Mesh get(String name) {
        // TODO : Move getResourceAsStream in a resource provider
        // TODO : Try catch resource
        try {
            ObjectInputStream ois = new ObjectInputStream(Mesh.class.getResourceAsStream("/mesh/" + name + ".mesh" ));
            Mesh mesh = (Mesh) ois.readObject() ;
            mesh.init();
            return mesh;
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private void init() {
        // TODO : delete buffer
        int positionVbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, positionVbo);
        glBufferData(GL_ARRAY_BUFFER, positions, GL_STATIC_DRAW);
        
        // TODO : delete buffer 
        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vao);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, NULL);

        indicesVbo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesVbo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
        
        GLUtils.checkError("Unable to init object");

    }
    
    public void draw() {
        glBindVertexArray(vao);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesVbo);
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_SHORT, 0);
    }
    
    // TODO : Only use by asset builder find better implementation
    public void setIndices(short[] indices) {
        this.indices = indices;
    }


    public void setPositions(float[] positions) {
        this.positions = positions;
    }
    
}
