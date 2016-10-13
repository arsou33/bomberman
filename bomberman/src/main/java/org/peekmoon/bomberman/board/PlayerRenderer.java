package org.peekmoon.bomberman.board;

import org.peekmoon.bomberman.Geometry;
import org.peekmoon.bomberman.Mesh;
import org.peekmoon.bomberman.Texture;
import org.peekmoon.bomberman.model.Player;
import org.peekmoon.bomberman.shader.ProgramShader;

public class PlayerRenderer {
    
    private final Geometry geometry;
    private final Mesh cowboyMesh;
    private final Texture cowboyTexture;
    
    public PlayerRenderer(ProgramShader shader) {
        cowboyTexture = new Texture("cowboy.png");
        cowboyMesh = Mesh.get("cowboy", cowboyTexture);
        geometry = new Geometry(cowboyMesh, shader);
    }
    
    public void render(Player status) {
        geometry.setPosition(status.getX(), status.getY(), 0);
        geometry.render();
    }
    
    public void release() {
        cowboyMesh.release();
        cowboyTexture.release();
    }
    




}