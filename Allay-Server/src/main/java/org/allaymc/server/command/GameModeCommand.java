package org.allaymc.server.command;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import org.allaymc.api.command.CommandSender;
import org.cloudburstmc.protocol.bedrock.data.GameType;

public class GameModeCommand {

    @CommandDescription("GameMode command")
    @CommandMethod("gamemode|gm <mode> <player>")
    private void gamemode(
            CommandSender sender,
            @Argument("mode") int mode,
            @Argument("player") String player
    ) {
        sender.reply("sender: %s, mode: %s, player: %s", sender.getClass().getSimpleName(), mode, player);
    }

    @CommandDescription("GameMode Creative command")
    @CommandMethod("gmc <player>")
    private void gmc(
            CommandSender sender,
            @Argument("player") String player
    ) {
        sender.reply("sender: %s, mode: %s, player: %s", sender.getClass().getSimpleName(), GameType.CREATIVE.name(), player);
    }

    @CommandDescription("GameMode Survival command")
    @CommandMethod("gms <player>")
    private void gms(
            CommandSender sender,
            @Argument("player") String player
    ) {
        sender.reply("sender: %s, mode: %s, player: %s", sender.getClass().getSimpleName(), GameType.SURVIVAL.name(), player);
    }
}
