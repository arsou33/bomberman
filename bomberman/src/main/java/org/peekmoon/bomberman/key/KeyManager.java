package org.peekmoon.bomberman.key;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.peekmoon.bomberman.Camera;
import org.peekmoon.bomberman.board.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyManager extends GLFWKeyCallback {
    
    private final static Logger log = LoggerFactory.getLogger(KeyManager.class);

    
    private final float speed = 0.05f;
    private final long window;
    private final Player player;
    private final List<KeyStateAction> keyStateActions = new ArrayList<>();
    
    
    public KeyManager(long window, Player player, Camera camera) {
        this.window = window;
        this.player = player;
        addKeyAction(GLFW_KEY_ESCAPE, elapsed -> glfwSetWindowShouldClose(window, true));
        
        // Camera movement
        
        addKeyAction(GLFW_KEY_UP, elapsed -> camera.translate(0,speed,0), KeyMod.CONTROL);
        addKeyAction(GLFW_KEY_DOWN, elapsed -> camera.translate(0, -speed, 0), KeyMod.CONTROL);
        addKeyAction(GLFW_KEY_LEFT, elapsed -> camera.translate(-speed, 0, 0), KeyMod.CONTROL);
        addKeyAction(GLFW_KEY_RIGHT, elapsed -> camera.translate(speed, 0, 0), KeyMod.CONTROL);

        addKeyAction(GLFW_KEY_PAGE_UP, elapsed -> camera.translate(0,0,speed), KeyMod.CONTROL);
        addKeyAction(GLFW_KEY_PAGE_DOWN, elapsed -> camera.translate(0,0,-speed), KeyMod.CONTROL);

        
        addKeyAction(GLFW_KEY_UP, elapsed -> camera.targetTranslate(0,speed,0), KeyMod.SHIFT);
        addKeyAction(GLFW_KEY_DOWN, elapsed -> camera.targetTranslate(0, -speed, 0), KeyMod.SHIFT);
        addKeyAction(GLFW_KEY_LEFT, elapsed -> camera.targetTranslate(-speed, 0, 0), KeyMod.SHIFT);
        addKeyAction(GLFW_KEY_RIGHT, elapsed -> camera.targetTranslate(speed, 0, 0), KeyMod.SHIFT);

        addKeyAction(GLFW_KEY_PAGE_UP, elapsed -> camera.targetTranslate(0,0,speed), KeyMod.SHIFT);
        addKeyAction(GLFW_KEY_PAGE_DOWN, elapsed -> camera.targetTranslate(0,0,-speed), KeyMod.SHIFT);
        
        
        // Player movement
        addKeyAction(GLFW_KEY_UP, elapsed -> player.moveUp(elapsed));
        addKeyAction(GLFW_KEY_DOWN, elapsed -> player.moveDown(elapsed));
        addKeyAction(GLFW_KEY_LEFT, elapsed -> player.moveLeft(elapsed));
        addKeyAction(GLFW_KEY_RIGHT, elapsed -> player.moveRight(elapsed));
    
    }


    private void addKeyAction(int key, KeyAction action, KeyMod... mods) {
        keyStateActions.add(new KeyStateAction(window, key, action, mods));
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_REPEAT) return;
        log.debug("Invoke Key : {} scancode : {} action : {} mods : {}", key, scancode, action, mods);
        
        if (key == GLFW_KEY_SPACE && action == GLFW_PRESS) {
            player.dropBomb();
        }
        
        
    }
    
    public void update(float elapsed) {
        for (KeyStateAction keyStateAction : keyStateActions) {
            keyStateAction.update(elapsed);
        }        
    }
    

  



}