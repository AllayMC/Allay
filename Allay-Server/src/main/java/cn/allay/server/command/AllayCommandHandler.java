package cn.allay.server.command;

import cn.allay.api.command.impl.CommandHandlerImpl;
import cn.allay.server.command.defaults.GamemodeCommand;

public class AllayCommandHandler extends CommandHandlerImpl {

    public AllayCommandHandler() {
        register(new GamemodeCommand());
    }
}
