package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.math.location.Location3f;
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
                })
                .root()
                .key("tp")
                .str("world")
                .intNum("dimId")
                .exec((context, player) -> {
                    String worldName = context.getSecondResult();
                    int dimId = context.getThirdResult();
                    var world = Server.getInstance().getWorldPool().getWorld(worldName);
                    if (world == null) {
                        context.addOutput(TextFormat.RED + "Unknown world: " + worldName);
                        return context.failed();
                    }
                    var dim = world.getDimension(dimId);
                    if (dim == null) {
                        context.addOutput(TextFormat.RED + "Unknown dimension: " + dimId);
                        return context.failed();
                    }
                    player.teleport(new Location3f(0, 64, 0, dim));
                    context.addOutput("Teleported to " + worldName + ":" + dimId);
                    return context.success();
                }, SenderType.PLAYER);
    }
}
