package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.form.Forms;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldSettings;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author daoge_cmd
 */
public class WorldCommand extends VanillaCommand {
    public WorldCommand() {
        super("world", TrKeys.ALLAY_COMMAND_WORLD_DESCRIPTION);
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
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_UNKNOWNDIM, dimName);
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
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_UNKNOWN, worldName);
                        return context.fail();
                    }
                    if (Server.getInstance().getWorldPool().getWorld(worldName) != null) {
                        context.addError("%" + TrKeys.ALLAY_WORLD_LOADED, worldName);
                        return context.fail();
                    }

                    worldSetting.enable(true);
                    context.addOutput(TrKeys.ALLAY_WORLD_LOADING, worldName);
                    Server.getInstance().getWorldPool().loadWorld(worldName, worldSetting);
                    context.addOutput(TrKeys.ALLAY_WORLD_LOADED, worldName);
                    return context.success();
                })
                .root()
                .key("create")
                .exec((context, player) -> {
                    var langCode = player.getLoginData().getLangCode();
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
                                var storageType = storageTypes.get(Integer.parseInt(response.get(1)));
                                var overworldGenerator = generatorTypes.get(Integer.parseInt(response.get(3)));
                                var overworldPreset = response.get(4);
                                var enableNether = Boolean.parseBoolean(response.get(6));
                                var netherGenerator = generatorTypes.get(Integer.parseInt(response.get(7)));
                                var netherPreset = response.get(8);
                                var enableTheEnd = Boolean.parseBoolean(response.get(10));
                                var theEndGenerator = generatorTypes.get(Integer.parseInt(response.get(11)));
                                var theEndPreset = response.get(12);

                                var worldSettingBuilder = WorldSettings.WorldSetting.builder()
                                        .enable(true)
                                        .storageType(storageType)
                                        .overworld(WorldSettings.WorldSetting.DimensionSettings.builder()
                                                .generatorType(overworldGenerator)
                                                .generatorPreset(overworldPreset)
                                                .build());
                                if (enableNether) {
                                    worldSettingBuilder.nether(WorldSettings.WorldSetting.DimensionSettings.builder()
                                            .generatorType(netherGenerator)
                                            .generatorPreset(netherPreset)
                                            .build());
                                }
                                if (enableTheEnd) {
                                    worldSettingBuilder.theEnd(WorldSettings.WorldSetting.DimensionSettings.builder()
                                            .generatorType(theEndGenerator)
                                            .generatorPreset(theEndPreset)
                                            .build());
                                }

                                player.sendTr(TrKeys.ALLAY_WORLD_LOADING, name);
                                Server.getInstance().getWorldPool().loadWorld(name, worldSettingBuilder.build());
                                player.sendTr(TrKeys.ALLAY_WORLD_LOADED, name);
                            })
                            .sendTo(player);
                    return context.success();
                }, SenderType.PLAYER);

    }
}
