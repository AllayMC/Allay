package org.allaymc.server.command.defaults;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.UnopenedContainerId;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.*;
import org.allaymc.server.block.type.BlockLootTable;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class GameTestCommand extends SimpleCommand {

    protected final BossBar bossBar = BossBar.create();

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
                    var blockState = Registries.BLOCK_STATE_PALETTE.get(blockStateHash);
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
                    player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), UnopenedContainerId.PLAYER_INVENTORY);
                    player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.ARMOR), UnopenedContainerId.ARMOR);
                    player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.OFFHAND), UnopenedContainerId.OFFHAND);
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
                .key("setblock")
                .str("blockType")
                .exec((context, player) -> {
                    var blockType = Registries.BLOCKS.get(new Identifier((String) context.getResult(1)));
                    if (blockType == null) {
                        context.addOutput(TextFormat.RED + "Unknown block type!");
                        return context.fail();
                    }

                    var loc = player.getLocation();
                    var floorLoc = loc.floor(new Vector3f());
                    loc.dimension().setBlockState((int) floorLoc.x(), (int) floorLoc.y(), (int) floorLoc.z(), blockType.getDefaultState());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("dumpcmd")
                .exec((context, player) -> {
                    var cmdPk = Registries.COMMANDS.encodeAvailableCommandsPacketFor(player);
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
                .key("dumpitemidtoblockstatehashmap")
                .exec(context -> {
                    var map = new HashMap<>();

                    for (var blockState : Registries.BLOCK_STATE_PALETTE.getContent().values()) {
                        var itemStack = blockState.toItemStack();
                        var itemId = ItemId.fromIdentifier(itemStack.getItemType().getIdentifier());
                        if (itemId == null) {
                            // Is not vanilla item
                            continue;
                        }
                        var meta = itemStack.getMeta();
                        map.put(itemId.getRuntimeId() + (meta == 0 ? "" : ":" + meta), blockState.blockStateHash());
                    }

                    try {
                        Files.deleteIfExists(Path.of("item_id_to_block_state_hash_map.json"));
                    } catch (IOException e) {
                        context.addOutput(TextFormat.RED + "" + e);
                        return context.fail();
                    }

                    JSONUtils.toFile("item_id_to_block_state_hash_map.json", map, writer -> writer.setIndent("  "));
                    return context.success();
                })
                .root()
                .key("suicide")
                .exec((context, player) -> {
                    player.attack(114514);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("spawnfallblock")
                .blockType("block")
                .exec((context, player) -> {
                    var entity = EntityTypes.FALLING_BLOCK.createEntity(
                            EntityInitInfo.builder()
                                    .loc(player.getLocation())
                                    .nbt(NbtMap.builder()
                                            .putInt("BlockStateHash", ((BlockType<?>) context.getResult(1)).getDefaultState().blockStateHash())
                                            .build()
                                    )
                                    .build()
                    );
                    player.getDimension().getEntityService().addEntity(entity);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("setperm")
                .str("perm")
                .bool("value")
                .exec((context) -> {
                    String perm = context.getResult(1);
                    boolean value = context.getResult(2);
                    context.getSender().setPermission(perm, value);
                    context.addOutput("Perm " + perm + " was set to " + value);
                    return context.success();
                })
                .root()
                .key("reloadblockloottable")
                .exec(context -> {
                    try {
                        var path = Path.of("../data/resources/loot_tables/blocks.json");
                        if (!Files.exists(path)) {
                            context.addError("This command can only be used in local environment!");
                            return context.fail();
                        }
                        BlockLootTable.readFrom(new InputStreamReader(Files.newInputStream(path)));
                        context.addOutput("Block loot tables have been reloaded!");
                    } catch (IOException e) {
                        context.addError(e.toString());
                        return context.fail();
                    }
                    return context.success();
                })
                .root()
                .key("spawnxporb")
                .intNum("xp")
                .exec((context, player) -> {
                    player.getDimension().splitAndDropXpOrb(
                            player.getLocation().add(0, -1, 0, new Vector3f()),
                            context.getResult(1)
                    );
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("damageself")
                .floatNum("damage")
                .exec((context, player) -> {
                    player.attack(DamageContainer.entityAttack(player, context.getResult(1)));
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("printblockunder")
                .exec((context, player) -> {
                    player.sendText(player.getDimension().getBlockState(player.getLocation().sub(0, 1, 0, new Vector3f())).toString());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("genball")
                .pos("pos")
                .intNum("a")
                .intNum("b")
                .intNum("c")
                .blockType("block")
                .exec((context, player) -> {
                    var pos = MathUtils.floor((Vector3fc) context.getResult(1));
                    int a = context.getResult(2);
                    int b = context.getResult(3);
                    int c = context.getResult(4);
                    int min = Math.min(a, Math.min(b, c));
                    int max = Math.max(a, Math.max(b, c));
                    BlockType<?> block = context.getResult(5);

                    for (int x = pos.x - max; x <= pos.x + max; x++) {
                        for (int y = pos.y - max; y <= pos.y + max; y++) {
                            for (int z = pos.z - max; z <= pos.z + max; z++) {
                                if (
                                        Math.pow((x - pos.x) * ((float) min / (float) a), 2) +
                                        Math.pow((y - pos.y) * ((float) min / (float) b), 2) +
                                        Math.pow((z - pos.z) * ((float) min / (float) c), 2) <= min * min
                                ) {
                                    player.getDimension().setBlockState(x, y, z, block.getDefaultState());
                                }
                            }
                        }
                    }
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("setlore")
                .msg("lore")
                .exec((context, player) -> {
                    var lore = AllayStringUtils.fastSplit(context.getResult(1), " ");
                    var item = player.getItemInHand();
                    item.setLore(lore);
                    player.notifyItemInHandChange();
                    player.sendText("Lore is set");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("getheight")
                .exec((context, player) -> {
                    var floorLoc = player.getLocation().floor(new Vector3f());
                    player.sendText("Height is " + player.getDimension().getHeight((int) floorLoc.x, (int) floorLoc.z));
                    return context.success();
                }, SenderType.PLAYER);
    }
}
