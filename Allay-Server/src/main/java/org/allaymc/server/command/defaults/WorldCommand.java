package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;

import java.util.stream.Collectors;

/**
 * Allay Project 2024/2/24
 * TODO: I18N
 * @author daoge_cmd
 */
public class WorldCommand extends SimpleCommand {
    public WorldCommand() {
        super("world", "world command");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("list")
                .exec(context -> {
                    context.addOutput("Available world list: ");
                    for (var world : Server.getInstance().getWorldPool().getWorlds().values()) {
                        context.addOutput("- " + TextFormat.GREEN + world.getWorldData().getName());
                        context.addOutput(" - " + TextFormat.YELLOW +
                                          world.getDimensions()
                                                  .values()
                                                  .stream()
                                                  .map(dim -> String.valueOf(dim.getDimensionInfo().dimensionId()))
                                                  .collect(Collectors.joining(", "))
                        );
                    }
                    return context.success();
                });
    }
}
