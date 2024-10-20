package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldSettings;

import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class WorldCommand extends SimpleCommand {
    public WorldCommand() {
        super("world", TrKeys.A_COMMAND_WORLD_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("list")
                .exec(context -> {
                    context.addOutput(TrKeys.A_COMMAND_WORLD_LIST);
                    for (var world : Server.getInstance().getWorldPool().getWorlds().values()) {
                        context.addOutput(
                                "- " +
                                TextFormat.GREEN + world.getWorldData().getName() +
                                TextFormat.YELLOW + "[" +
                                world.getDimensions()
                                        .values()
                                        .stream()
                                        .map(dim -> dim.getDimensionInfo().toString())
                                        .collect(Collectors.joining(", "))
                                + "]"
                        );
                    }
                    return context.success();
                })
                .root()
                .key("tp")
                .str("world")
                .enums("dimension", "overworld", new String[]{"overworld", "nether", "the_end"})
                .optional()
                .exec((context, entity) -> {
                    String worldName = context.getResult(1);
                    String dimName = context.getResult(2);
                    var world = Server.getInstance().getWorldPool().getWorld(worldName);
                    if (world == null) {
                        context.addError("%" + TrKeys.A_COMMAND_WORLD_UNKNOWN, worldName);
                        return context.fail();
                    }

                    var dimInfo = DimensionInfo.fromName(dimName);
                    if (dimInfo == null) {
                        context.addError("%" + TrKeys.A_COMMAND_WORLD_UNKNOWNDIM, dimName);
                        return context.fail();
                    }
                    var dim = world.getDimension(dimInfo.dimensionId());

                    entity.teleport(new Location3f(0, 64, 0, dim));
                    context.addOutput(TrKeys.A_COMMAND_WORLD_TP_SUCCESS, worldName, dimName);
                    return context.success();
                }, SenderType.ENTITY)
                .root()
                .key("unload")
                .str("world")
                .exec(context -> {
                    String worldName = context.getResult(1);
                    var world = Server.getInstance().getWorldPool().getWorld(worldName);
                    if (world == null) {
                        context.addError("%" + TrKeys.A_COMMAND_WORLD_UNKNOWN, worldName);
                        return context.fail();
                    }
                    if (world == Server.getInstance().getWorldPool().getDefaultWorld()) {
                        context.addError("%" + TrKeys.A_COMMAND_WORLD_UNLOAD_FAILED_DEFAULT);
                        return context.fail();
                    }

                    world.getPlayers().forEach(player -> player.teleport(Server.getInstance().getWorldPool().getGlobalSpawnPoint()));
                    // Unload the world after 1 second, because teleport players to another world will take some time
                    Server.getInstance().getScheduler().scheduleDelayed(Server.getInstance(), () -> {
                        Server.getInstance().getWorldPool().unloadWorld(worldName);
                        return true;
                    }, 20);
                    return context.success();
                })
                .root()
                .key("load")
                .str("world")
                .exec(context -> {
                    String worldName = context.getResult(1);
                    var worldSetting = Server.getInstance().getWorldPool().getWorldConfig().worlds().get(worldName);
                    if (worldSetting == null) {
                        context.addError("%" + TrKeys.A_COMMAND_WORLD_UNKNOWN, worldName);
                        return context.fail();
                    }
                    if (Server.getInstance().getWorldPool().getWorld(worldName) != null) {
                        context.addError("%" + TrKeys.A_WORLD_LOADED, worldName);
                        return context.fail();
                    }

                    worldSetting.enable(true);
                    context.addOutput(TrKeys.A_WORLD_LOADING, worldName);
                    Server.getInstance().getWorldPool().loadWorld(worldName, worldSetting);
                    context.addOutput(TrKeys.A_WORLD_LOADED, worldName);
                    return context.success();
                });

    }
}
