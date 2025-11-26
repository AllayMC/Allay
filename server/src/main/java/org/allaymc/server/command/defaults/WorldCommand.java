package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.form.Forms;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.generator.WorldGenerator;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class WorldCommand extends Command {
    public WorldCommand() {
        super("world", TrKeys.ALLAY_COMMAND_WORLD_DESCRIPTION, Permissions.COMMAND_WORLD);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("list")
                .exec(context -> {
                    context.addOutput(TrKeys.ALLAY_COMMAND_WORLD_LIST);
                    for (var world : Server.getInstance().getWorldPool().getWorlds().values()) {
                        context.addOutput(
                                "- " +
                                TextFormat.GREEN + world.getName() + "(" + world.getWorldData().getDisplayName() + ")" + TextFormat.RESET +
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
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_UNKNOWN, worldName);
                        return context.fail();
                    }

                    var dimInfo = DimensionInfo.fromName(dimName);
                    if (dimInfo == null) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_DIM_UNKNOWN, dimName);
                        return context.fail();
                    }

                    var dim = world.getDimension(dimInfo.dimensionId());
                    if (dim == null) {

                    }
                    if (dim.getDimensionInfo() == DimensionInfo.OVERWORLD) {
                        entity.teleport(world.getSpawnPoint());
                    } else {
                        // TODO: Find a safe location in nether and the_end
                        entity.teleport(new Location3d(0, 64, 0, dim));
                    }

                    context.addOutput(TrKeys.ALLAY_COMMAND_WORLD_TP_SUCCESS, worldName, dimName);
                    return context.success();
                }, SenderType.ENTITY)
                .root()
                .key("unload")
                .str("world")
                .exec(context -> {
                    String worldName = context.getResult(1);
                    var world = Server.getInstance().getWorldPool().getWorld(worldName);
                    if (world == null) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_UNKNOWN, worldName);
                        return context.fail();
                    }
                    if (world == Server.getInstance().getWorldPool().getDefaultWorld()) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_UNLOAD_FAILED_DEFAULT);
                        return context.fail();
                    }

                    world.getPlayers().forEach(player -> player.getControlledEntity().teleport(Server.getInstance().getWorldPool().getGlobalSpawnPoint()));
                    // Unload the world after 1 second, because teleport players to another world will take some time
                    Server.getInstance().getScheduler().scheduleDelayed(Server.getInstance(), () -> {
                        Server.getInstance().getWorldPool().unloadWorld(worldName);
                        return true;
                    }, 20);
                    return context.success();
                })
                .root()
                .key("create")
                .exec((context, player) -> {
                    var langCode = player.getController().getLoginData().getLangCode();
                    var storageTypes = new ArrayList<>(Registries.WORLD_STORAGE_FACTORIES.getContent().keySet());
                    var generatorTypes = new ArrayList<>(Registries.WORLD_GENERATOR_FACTORIES.getContent().keySet());
                    Forms.custom()
                            .title(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_TITLE))
                            .input(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_INPUT_NAME))
                            .dropdown(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_STORAGETYPE), storageTypes)
                            .divider()
                            .dropdown(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORTYPE_OVERWORLD), generatorTypes)
                            .input(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORPRESET_OVERWORLD))
                            .divider()
                            .toggle(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_TOGGLE_ENABLE_NETHER))
                            .dropdown(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORTYPE_NETHER), generatorTypes)
                            .input(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORPRESET_NETHER))
                            .divider()
                            .toggle(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_TOGGLE_ENABLE_THEEND))
                            .dropdown(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORTYPE_THEEND), generatorTypes)
                            .input(I18n.get().tr(langCode, TrKeys.ALLAY_COMMAND_WORLD_CREATE_DROPDOWN_GENERATORPRESET_THEEND))
                            .onResponse(response -> {
                                var name = response.get(0);
                                var storage = Registries.WORLD_STORAGE_FACTORIES
                                        .get(storageTypes.get(Integer.parseInt(response.get(1))))
                                        .apply(Server.getInstance().getWorldPool().getWorldFolder().resolve(name));
                                var overworldGenerator = Registries.WORLD_GENERATOR_FACTORIES
                                        .get(generatorTypes.get(Integer.parseInt(response.get(3))))
                                        .apply(response.get(4));

                                WorldGenerator netherGenerator = null;
                                if (Boolean.parseBoolean(response.get(6))) {
                                    netherGenerator = Registries.WORLD_GENERATOR_FACTORIES
                                            .get(generatorTypes.get(Integer.parseInt(response.get(7))))
                                            .apply(response.get(8));
                                }

                                WorldGenerator theEndGenerator = null;
                                if (Boolean.parseBoolean(response.get(10))) {
                                    theEndGenerator = Registries.WORLD_GENERATOR_FACTORIES
                                            .get(generatorTypes.get(Integer.parseInt(response.get(11))))
                                            .apply(response.get(12));
                                }

                                Server.getInstance().getWorldPool().loadWorld(name, storage, overworldGenerator, netherGenerator, theEndGenerator);
                            })
                            .sendTo(player.getController());
                    return context.success();
                }, SenderType.ACTUAL_PLAYER);

    }
}
