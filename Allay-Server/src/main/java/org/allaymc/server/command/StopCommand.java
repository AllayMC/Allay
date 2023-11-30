package org.allaymc.server.command;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.server.Server;

public class StopCommand {

    @CommandDescription("Stop command")
    @CommandMethod("stop|shutdown")
    private void stop(CommandSender sender) {
        sender.reply("Starting server shutdown...");
        Server.getInstance().shutdown();
    }
}
