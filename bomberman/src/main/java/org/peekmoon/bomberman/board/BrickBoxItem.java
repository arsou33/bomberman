package org.peekmoon.bomberman.board;

import org.peekmoon.bomberman.Geometry;
import org.peekmoon.bomberman.Mesh;
import org.peekmoon.bomberman.Texture;
import org.peekmoon.bomberman.shader.ProgramShader;

public class BrickBoxItem extends Item {
    
    private static final Texture brickTexture = new Texture("brick.png");
    private static final Mesh brickBoxMesh = Mesh.get("cube", brickTexture);

    private final Geometry geometry; 
    
    
    public BrickBoxItem(Board board, ProgramShader shader, int i, int j) {
        super(shader, board, i, j);
        geometry = new Geometry(brickBoxMesh, shader);
        geometry.setPosition(i, j, 0);
    }

    @Override
    public boolean isTraversable() {
        return false;
    }

    @Override
    public boolean isPropagateFire() {
        return false;
    }

    @Override
    public void render() {
        geometry.render();
    }

    @Override
    public void release() {
        brickBoxMesh.release();
        brickTexture.release();
    }

    @Override
    public boolean update(float elapsed) {
        return false; 
    }

    @Override
    public boolean fire() {
        return false;
    }
    

}
