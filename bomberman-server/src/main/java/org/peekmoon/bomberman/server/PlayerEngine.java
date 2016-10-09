package org.peekmoon.bomberman.server;

import java.util.LinkedList;
import java.util.List;

import org.peekmoon.bomberman.network.Direction;
import org.peekmoon.bomberman.network.status.BoardStatus;
import org.peekmoon.bomberman.network.status.PlayerStatus;
import org.peekmoon.bomberman.network.status.TileStatus;

public class PlayerEngine {
    
    private static final float speed = 5;

    private final PlayerStatus player = new PlayerStatus();
    private final BoardStatus board;
    
    private final List<Direction> activeDirections = new LinkedList<>();
    
    private float elapsed = 0.02f; // TODO : Fix elapsed

    
    public PlayerEngine(BoardStatus board) {
    	this.board = board;
    }
    
    
    public void startMove(Direction direction) {
        activeDirections.remove(direction); // Should not be in but just in case udp packet lost
        activeDirections.add(0, direction);
    }
    
    public void stopMove(Direction direction) {
    	activeDirections.remove(direction);
    }
    
    public void move(Direction direction) {
        if (direction == null) return;
        switch (direction) {
        case DOWN:
            moveDown(elapsed);
            break;
        case LEFT:
            moveLeft(elapsed);
            break;
        case RIGHT:
            moveRight(elapsed);
            break;
        case UP:
            moveUp(elapsed);
            break;
        default:
            throw new IllegalStateException("Direction " + direction + " is unknown");
        
        }
    }
    
    // TODO : Reimplement check on tile
    
    public void moveUp(float elapsed) {
        if (Math.ceil(player.getY()) != Math.round(player.getY())) { // We are entering a cell
            TileStatus destTile = board.get(player.getX(), (float)Math.floor(player.getY())).get(Direction.UP);
            if (!destTile.isTraversable()) return;
        }

        float distToMove = elapsed * speed;
        float deltaToAlign = Math.round(player.getX()) - player.getX();

        if (Math.abs(deltaToAlign) < distToMove) {
            player.setX(Math.round(player.getX()));
            distToMove -= Math.abs(deltaToAlign);
        } else {
            player.deltaX(distToMove * Math.signum(deltaToAlign));
            return;
        }
        player.deltaY(distToMove);
    }
    
    
    public void moveDown(float elapsed) {
        if (Math.floor(player.getY()) != Math.round(player.getY())) { // We are entering a cell
            TileStatus destTile = board.get(player.getX(), (float)Math.ceil(player.getY())).get(Direction.DOWN);
            if (!destTile.isTraversable()) return;
        }
        
        float distToMove = elapsed * speed;
        float deltaToAlign = Math.round(player.getX()) - player.getX();

        if (Math.abs(deltaToAlign) < distToMove) {
            player.setX(Math.round(player.getX()));
            distToMove -= Math.abs(deltaToAlign);
        } else {
            player.deltaX(distToMove * Math.signum(deltaToAlign));
            return;
        }
        player.deltaY(-distToMove);
    }

    public void moveLeft(float elapsed) {
        if (Math.floor(player.getX()) != Math.round(player.getX())) { // We are entering a cell
            TileStatus destTile = board.get((float)Math.ceil(player.getX()), player.getY()).get(Direction.LEFT);
            if (!destTile.isTraversable()) return;
        }
        
        float distToMove = elapsed * speed;
        float deltaToAlign = Math.round(player.getY()) - player.getY();

        if (Math.abs(deltaToAlign) < distToMove) {
            player.setY(Math.round(player.getY()));
            distToMove -= Math.abs(deltaToAlign);
        } else {
            player.deltaY(distToMove * Math.signum(deltaToAlign));
            return;
        }
        player.deltaX(-distToMove);
    }

    public void moveRight(float elapsed) {
        if (Math.ceil(player.getX()) != Math.round(player.getX())) { // We are entering a cell
            TileStatus destTile = board.get((float)Math.floor(player.getX()), player.getY()).get(Direction.RIGHT);
            if (!destTile.isTraversable()) return;
        }        
        float distToMove = elapsed * speed;
        float deltaToAlign = Math.round(player.getY()) - player.getY();

        if (Math.abs(deltaToAlign) < distToMove) {
            player.setY(Math.round(player.getY()));
            distToMove -= Math.abs(deltaToAlign);
        } else {
            player.deltaY(distToMove * Math.signum(deltaToAlign));
            return;
        }
        player.deltaX(distToMove);
    }

    public void dropBomb() {
        TileStatus currentTile = board.get(player.getX(), player.getY());
        if (currentTile.canDropBomb()) {
            currentTile.dropBomb();
        }
    }

    public PlayerStatus getStatus() {
        return player;
    }

    public void update() {
        if (!activeDirections.isEmpty()) {
            move(activeDirections.get(0));
        }
    }


}