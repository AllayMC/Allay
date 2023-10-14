package cn.allay.server.command;

import cn.allay.api.command.impl.CommandHandleImpl;
import cn.allay.server.command.defaults.GamemodeCommand;

public class AllayCommandHandler extends CommandHandleImpl {

    public AllayCommandHandler() {
        register(new GamemodeCommand());
    }
}
