package org.allaymc.server.command;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import lombok.extern.log4j.Log4j2;
import org.allaymc.api.command.CommandSender;

@Log4j2
public class GamemodeCommand {

    @CommandDescription("Gamemode command")
    @CommandMethod("gamemode|gm <mode> <player>")
    private void gamemode(
            CommandSender sender,
            @Argument("mode") int mode,
            @Argument("player") String player
    ) {
        log.info("sender: {}, mode: {}, player: {}", sender.getClass().getSimpleName(), mode, player);
    }
}
