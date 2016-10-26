package org.peekmoon.bomberman.network.command;

public enum CommandType {
    
    REGISTER(RegisterCommand.class),
    PING(PingCommand.class),
    PLAYER_START_MOVE(PlayerStartMoveCommand.class),
    PLAYER_STOP_MOVE(PlayerStopMoveCommand.class),
    PLAYER_DROP_BOMB(PlayerDropBombCommand.class);

    private Class<? extends Command> clazz;

    private CommandType(Class<? extends Command> clazz) {
        this.clazz = clazz;
    }
    
    public static CommandType get(Command command) {
        Class<? extends Command> commandClass = command.getClass();
        for (CommandType type : CommandType.values()) {
            if (type.clazz.equals(commandClass)) return type;
        }
        throw new IllegalStateException("Command class " + commandClass.getName() + " is unknown");
    }
    
    public Class<? extends Command> getCommandClass() {
        return clazz;
    }

}
