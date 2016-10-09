package org.peekmoon.bomberman.board;

import org.peekmoon.bomberman.Geometry;
import org.peekmoon.bomberman.Mesh;
import org.peekmoon.bomberman.Texture;
import org.peekmoon.bomberman.network.status.BrickItemStatus;
import org.peekmoon.bomberman.shader.ProgramShader;

public class BrickItemRenderer extends ItemRenderer<BrickItemStatus> {
    
    private final Texture brickTexture = new Texture("brick.png");
    private final Mesh brickBoxMesh = Mesh.get("cube", brickTexture);

    private final Geometry geometry; 
    
    
    public BrickItemRenderer(ProgramShader shader) {
        geometry = new Geometry(brickBoxMesh, shader);
    }
    
	@Override
	public void render(BrickItemStatus item) {
		geometry.setPosition(item.getI(), item.getJ(), 0);
		geometry.render();
	}
	
    public void release() {
        brickBoxMesh.release();
        brickTexture.release();
    }

}