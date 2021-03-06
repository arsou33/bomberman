package org.peekmoon.bomberman.shader;

import org.joml.Matrix4f;

public interface ProgramProjectionView {
    
    void setProjection(Matrix4f projection);
    void setView(Matrix4f view);
}
