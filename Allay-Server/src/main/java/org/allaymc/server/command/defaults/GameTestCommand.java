package org.allaymc.server.command.defaults;

import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.TrKeys;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;

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
                .exec(context -> {
                    var player = context.getSender().asPlayer();
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
                .exec(context -> {
                    var player = context.getSender().asPlayer();
                    player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), FixedContainerId.PLAYER_INVENTORY);
                    context.addOutput("§aInventory is refreshed!");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("tps")
                .exec(context -> {
                    var player = context.getSender().asPlayer();
                    player.sendText("§aTPS: " + player.getLocation().dimension().getWorld().getTps() + ", Entity Count: " + player.getLocation().dimension().getEntities().size());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("translate")
                .str("key")
                .enums("lang", LangCode.class)
                .optional()
                .exec(context -> {
                    var player = context.getSender().asPlayer();
                    String key = context.getResult(1);
                    String lang = context.getResult(2);
                    if (lang.isEmpty()) lang = LangCode.en_US.name();
                    var langCode = LangCode.valueOf(lang);
                    try {
                        player.sendText(I18n.get().tr(langCode, key));
                    } catch (Throwable t) {
                        context.addOutput("§cUnknown key!");
                        return context.failed();
                    }
                    return context.success();
                }, SenderType.PLAYER);
    }
}
