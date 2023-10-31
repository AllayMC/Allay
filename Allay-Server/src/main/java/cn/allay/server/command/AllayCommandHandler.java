package cn.allay.server.command;

import cn.allay.api.command.exception.InvalidValueResolverException;
import cn.allay.api.command.impl.CommandHandlerImpl;
import cn.allay.server.command.defaults.GamemodeCommand;
import org.cloudburstmc.protocol.bedrock.data.GameType;

public class AllayCommandHandler extends CommandHandlerImpl {

    public AllayCommandHandler() {
        this.registerValueResolver(GameType.class, (sender, input) -> {
            try {
                return GameType.from(Integer.parseInt(input));
            } catch (Exception exception0) {
                try {
                    return GameType.valueOf(input.toUpperCase());
                } catch (Exception exception1) {
                    throw new InvalidValueResolverException(exception1.initCause(exception0));
                }
            }
        });

        register(new GamemodeCommand());
    }
}
