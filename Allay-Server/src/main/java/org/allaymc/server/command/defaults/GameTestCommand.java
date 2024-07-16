package org.allaymc.server.command.defaults;

import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.registry.EntityTypeRegistry;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.api.utils.TextFormat;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.joml.Vector3f;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public class GameTestCommand extends SimpleCommand {
    public GameTestCommand() {
        super("gametest", TrKeys.M_GAMETEST_DESCRIPTION);
        aliases.add("gt");
        flags.add(CommandData.Flag.TEST_USAGE);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("gb")
                .intNum("blockStateHash")
                .exec((context, player) -> {
                    int blockStateHash = context.getResult(1);
                    var blockState = BlockStateHashPalette.getRegistry().get(blockStateHash);
                    if (blockState == null) {
                        context.addError("Unknown block state hash!");
                        return context.fail();
                    }

                    player.getContainer(FullContainerType.PLAYER_INVENTORY).setItemInHand(blockState.toItemStack());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("rfinv")
                .exec((context, player) -> {
                    player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), FixedContainerId.PLAYER_INVENTORY);
                    player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.ARMOR), FixedContainerId.ARMOR);
                    player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.OFFHAND), FixedContainerId.OFFHAND);
                    context.addOutput("Inventory is refreshed!");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("trs")
                .str("key")
                .enums("langCode", LangCode.class)
                .optional()
                .remain("args")
                .optional()
                .exec((context, player) -> {
                    String key = context.getResult(1);
                    String lang = context.getResult(2);
                    List<String> args = context.getResult(3);

                    LangCode langCode;
                    if (lang.isEmpty()) langCode = I18n.FALLBACK_LANG;
                    else langCode = LangCode.byName(lang, true);

                    try {
                        player.sendText(I18n.get().tr(langCode, key, args));
                    } catch (Throwable t) {
                        context.addOutput(TextFormat.RED + "Unknown key!");
                        return context.fail();
                    }

                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("trc")
                .str("key")
                .remain("args")
                .exec((context, player) -> {
                    String key = context.getResult(1);
                    List<String> args = context.getResult(2);
                    player.sendTr(key, true, args.toArray(String[]::new));
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("spawn")
                .str("entityType")
                .intNum("count", 1)
                .optional()
                .exec((context, player) -> {
                    var entityType = EntityTypeRegistry.getRegistry().get(new Identifier((String) context.getResult(1)));
                    int count = context.getResult(2);
                    if (entityType == null) {
                        context.addOutput(TextFormat.RED + "Unknown entity type!");
                        return context.fail();
                    }

                    for (var i = 1; i <= count; i++) {
                        var dim = player.getLocation().dimension();
                        var loc = player.getLocation();
                        var entity = entityType.createEntity(
                                SimpleEntityInitInfo.builder()
                                        .dimension(dim)
                                        .loc(loc)
                                        .build()
                        );
                        dim.getEntityService().addEntity(entity);
                    }

                    context.addOutput("Spawned " + count + " " + entityType.getIdentifier().toString());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("cleare")
                .exec((context, player) -> {
                    player.getLocation().dimension().getEntities().values().stream()
                            .filter(entity -> !(entity instanceof EntityPlayer))
                            .forEach(EntityBaseComponent::despawn);

                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("dumpcmd")
                .exec((context, player) -> {
                    var cmdPk = Server.getInstance().getCommandRegistry().encodeAvailableCommandsPacketFor(player);
                    try {
                        Files.deleteIfExists(Path.of("cmd_pk_allay.json"));
                    } catch (IOException e) {
                        context.addOutput(TextFormat.RED + "" + e);
                        return context.fail();
                    }

                    JSONUtils.toFile("cmd_pk_allay.json", cmdPk, writer -> writer.setIndent("  "));
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("suicide")
                .exec((context, player) -> {
                    player.attack(114514);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("fallblock")
                .exec((context, player) -> {
                    var entity = EntityTypes.FALLING_BLOCK.createEntity(
                            SimpleEntityInitInfo.builder()
                                    .loc(player.getLocation())
                                    .nbt(NbtMap.builder()
                                            .putInt("BlockStateHash", BlockTypes.SAND.getDefaultState().blockStateHash())
                                            .build()
                                    )
                                    .build()
                    );
                    player.getDimension().getEntityService().addEntity(entity);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("food")
                .exec((context, player) -> {
                    player.setFoodLevel(Math.min(20, player.getFoodLevel() + 5)); // bread 5 food
                    player.setFoodSaturationLevel(player.getFoodSaturationLevel() + 6); // bread 6 saturation
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("setperm")
                .str("perm")
                .bool("value")
                .exec((context) -> {
                    String perm = context.getResult(1);
                    boolean value = context.getResult(2);
                    context.getSender().setPerm(perm, value);
                    context.addOutput("Perm " + perm + " was set to " + value);
                    return context.success();
                })
                .root()
                .key("applymotion")
                .floatNum("mx")
                .floatNum("my")
                .floatNum("mz")
                .exec((context, player) -> {
                    player.letClientApplyMotion(new Vector3f(context.getResult(1), context.getResult(2), context.getResult(3)));
                    return context.success();
                }, SenderType.PLAYER);
    }
}
