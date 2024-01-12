package org.allaymc.server.command.defaults;

import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.registry.EntityTypeRegistry;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.identifier.Identifier;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;

import java.util.List;

/**
 * Allay Project 2023/12/30
 *
 * @author daoge_cmd
 */
public class GameTestCommand extends SimpleCommand {
    public GameTestCommand() {
        super("gametest", TrKeys.M_GAMETEST_DESCRIPTION);
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
                        context.addOutput("§cUnknown block state hash!");
                        return context.failed();
                    }
                    player.getContainer(FullContainerType.PLAYER_INVENTORY).setItemInHand(blockState.toItemStack());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("rfinv")
                .exec((context, player) -> {
                    player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), FixedContainerId.PLAYER_INVENTORY);
                    context.addOutput("§aInventory is refreshed!");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("tps")
                .exec((context, player) -> {
                    player.sendText("§aTPS: " + player.getLocation().dimension().getWorld().getTps());
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
                    if (lang.isEmpty()) lang = LangCode.en_US.name();
                    var langCode = LangCode.valueOf(lang);
                    try {
                        player.sendText(I18n.get().tr(langCode, key, args));
                    } catch (Throwable t) {
                        context.addOutput("§cUnknown key!");
                        return context.failed();
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
                .exec((context, player) -> {
                    var entityType = EntityTypeRegistry.getRegistry().get(new Identifier((String)context.getResult(1)));
                    if (entityType == null) {
                        context.addOutput("§cUnknown entity type!");
                        return context.failed();
                    }
                    var dim = player.getLocation().dimension();
                    var loc = player.getLocation();
                    var entity = entityType.createEntity(
                            SimpleEntityInitInfo.builder()
                                    .dimension(dim)
                                    .loc(loc)
                                    .build()
                    );
                    dim.getEntityUpdateService().addEntity(entity);
                    return context.success();
                }, SenderType.PLAYER);
    }
}
