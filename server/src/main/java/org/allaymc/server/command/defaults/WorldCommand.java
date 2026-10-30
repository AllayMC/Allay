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
import org.allaymc.api.utils.identifier.IdentifierUtils;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.generator.WorldGenerator;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * {@code WorldCommand} lists loaded worlds, requests world teleports and
 * unloads, and opens an interactive world-creation form. Teleports validate
 * world and dimension availability, using world spawn in the overworld and
 * {@code (0, 64, 0)} in other dimensions. Feedback reports requests without
 * confirming teleport acceptance.
 *
 * <p>
 * Unload first requests player transfers to global spawn, then schedules removal
 * twenty scheduler ticks later. The delay allows transfers to progress but is
 * not a completion barrier, and the handler returns before removal runs.
 * Creation captures registry-key lists for form index resolution and constructs
 * storage and generators only when a response arrives; command success means
 * the form was sent, not that a world was created.
 *
 * @author daoge_cmd
 */
public class WorldCommand extends Command {
    /**
     * Creates the {@code /world} command with its built-in authorization permission.
     */
    public WorldCommand() {
        super("world", TrKeys.ALLAY_COMMAND_WORLD_DESCRIPTION, Permissions.COMMAND_WORLD);
    }

    /**
     * Installs world listing, entity teleportation, delayed unloading, and creation.
     * Teleports default to the overworld, and unloading rejects the default
     * world before requesting any player transfers. Neither path inspects
     * individual teleport results.
     *
     * <p>
     * Unload schedules removal after twenty scheduler ticks and reports success
     * before that task runs. Creation requires an actual player and defers world
     * construction to the form response, using captured option lists to resolve
     * submitted storage and generator indexes.
     */
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
                                        .map(dim -> dim.getDimensionType().getIdentifier().toString())
                                        .collect(Collectors.joining(", "))
                                + "]"
                        );
                    }
                    return context.success();
                })
                .root()
                .key("tp")
                .str("world")
                .str("dimension", DimensionTypes.OVERWORLD.getIdentifier().toString())
                .optional()
                .exec((context, entity) -> {
                    String worldName = context.getResult(1);
                    String dimName = context.getResult(2);
                    var world = Server.getInstance().getWorldPool().getWorld(worldName);
                    if (world == null) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_UNKNOWN, worldName);
                        return context.fail();
                    }

                    var identifier = IdentifierUtils.tryParse(dimName);
                    var dimensionType = identifier == null ? null : Registries.DIMENSIONS.getByK2(identifier);
                    if (dimensionType == null) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_DIM_UNKNOWN, dimName);
                        return context.fail();
                    }

                    var dim = world.getDimension(dimensionType);
                    if (dim == null) {
                        context.addError("%" + TrKeys.ALLAY_COMMAND_WORLD_DIM_DISABLED, dimName);
                        return context.fail();
                    }
                    if (dim.getDimensionType() == DimensionTypes.OVERWORLD) {
                        entity.teleport(world.getSpawnPoint());
                    } else {
                        // TODO: Replace this fixed destination with a safe
                        // spawn search for non-overworld dimensions.
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
                    // Cross-world transfers need time to progress. Delay removal
                    // by twenty scheduler ticks, without waiting for individual
                    // teleport completion or verifying that every request succeeded.
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
                    // Retain option ordering with this form so later registry
                    // iteration changes cannot associate submitted indexes with
                    // different displayed storage or generator names.
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
