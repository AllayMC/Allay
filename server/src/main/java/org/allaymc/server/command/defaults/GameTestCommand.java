package org.allaymc.server.command.defaults;

import org.allaymc.api.block.type.BlockType;
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
import org.allaymc.api.item.data.ItemLockMode;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.JSONUtils;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.Explosion;
import org.allaymc.server.block.type.BlockLootTable;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.joml.Vector3d;
import org.joml.Vector3dc;

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
                .enumClass("langCode", LangCode.class)
                .optional()
                .remain("args")
                .optional()
                .exec((context, player) -> {
                    String key = context.getResult(1);
                    LangCode langCode = context.getResultOr(2, I18n.FALLBACK_LANG);
                    List<String> args = context.getResult(3);

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
                    player.sendTr(key, true, args.toArray());
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
                    var floorLoc = loc.floor(new Vector3d());
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
                            player.getLocation().add(0, -1, 0, new Vector3d()),
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
                    player.sendText(player.getDimension().getBlockState(player.getLocation().sub(0, 1, 0, new Vector3d()), 0).toString());
                    player.sendText(player.getDimension().getBlockState(player.getLocation().sub(0, 1, 0, new Vector3d()), 1).toString());
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
                    var pos = MathUtils.floor((Vector3dc) context.getResult(1));
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
                    var floorLoc = player.getLocation().floor(new Vector3d());
                    player.sendText("Height is " + player.getDimension().getHeight((int) floorLoc.x, (int) floorLoc.z));
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("getlightdata")
                .pos("pos")
                .exec((context, player) -> {
                    var floorLoc = ((Vector3d) context.getResult(1)).floor();
                    int x = (int) floorLoc.x;
                    int y = (int) floorLoc.y;
                    int z = (int) floorLoc.z;
                    var lightService = player.getDimension().getLightService();
                    player.sendText("InternalLight: " + lightService.getInternalLight(x, y, z));
                    player.sendText("BlockLight: " + lightService.getBlockLight(x, y, z));
                    player.sendText("SkyLight: " + lightService.getSkyLight(x, y, z));
                    player.sendText("InternalSkyLight: " + lightService.getInternalSkyLight(x, y, z));
                    player.sendText("QueuedUpdateCount: " + lightService.getQueuedUpdateCount());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("triggerexception")
                .exec(context -> {
                    throw new RuntimeException("Triggered exception");
                })
                .root()
                .key("itemdamage")
                .exec((context, player) -> {
                    var item = player.getItemInHand();
                    item.setDurability(item.getMaxDurability() / 2);
                    player.setItemInHand(item);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("explode")
                .pos("pos").optional()
                .floatNum("size", 4).optional()
                .bool("spawnfire").optional()
                .exec((context, player) -> {
                    var explosion = new Explosion(context.getResult(2), context.getResult(3));
                    Vector3d pos = context.getResult(1);
                    if (pos == null) {
                        pos = new Vector3d(context.getSender().getCmdExecuteLocation());
                    }
                    explosion.explode(player.getDimension(), pos);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("lockitem")
                .enumClass("mode", ItemLockMode.class)
                .exec((context, player) -> {
                    var item = player.getItemInHand();
                    item.setLockMode(context.getResult(1));
                    player.notifyItemInHandChange();
                    player.sendText("Item is locked in " + context.getResult(1) + " mode!");
                    return context.success();
                }, SenderType.PLAYER);
    }
}
