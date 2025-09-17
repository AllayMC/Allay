package org.allaymc.server.command.defaults;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.debugshape.DebugLine;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.block.BlockBreakEvent;
import org.allaymc.api.form.Forms;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.data.ItemLockMode;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.permission.Permission;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Explosion;
import org.allaymc.server.utils.JSONUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.command.CommandData;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author daoge_cmd
 */
public class GameTestCommand extends VanillaCommand {

    public GameTestCommand() {
        super("gametest", TrKeys.MC_GAMETEST_DESCRIPTION);
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

                    player.getContainer(ContainerType.INVENTORY).setItemInHand(blockState.toItemStack());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("rfinv")
                .exec((context, player) -> {
                    player.viewContents(player.getContainer(ContainerType.INVENTORY));
                    player.viewContents(player.getContainer(ContainerType.ARMOR));
                    player.viewContents(player.getContainer(ContainerType.OFFHAND));
                    context.addOutput("Inventory is refreshed!");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("trs")
                .str("key")
                .enumClass("langCode", LangCode.class).optional()
                .remain("args").optional()
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
                    player.getDimension().getEntityManager().addEntity(entity);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("setperm")
                .str("perm")
                .bool("value")
                .exec((context) -> {
                    String name = context.getResult(1);
                    var permission = Permission.get(name);
                    if (permission == null) {
                        context.addError("Unknown permission: " + name);
                        return context.fail();
                    }

                    boolean value = context.getResult(2);
                    context.getSender().setPermission(permission, value);
                    context.addOutput("Perm " + permission + " was set to " + value);
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
                    var lightEngine = player.getDimension().getLightEngine();
                    player.sendText("InternalLight: " + lightEngine.getInternalLight(x, y, z));
                    player.sendText("BlockLight: " + lightEngine.getBlockLight(x, y, z));
                    player.sendText("SkyLight: " + lightEngine.getSkyLight(x, y, z));
                    player.sendText("InternalSkyLight: " + lightEngine.getInternalSkyLight(x, y, z));
                    player.sendText("QueuedUpdateCount: " + lightEngine.getQueuedUpdateCount());
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
                    item.setDamage(item.getMaxDamage() / 2);
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
                        pos = new Vector3d(context.getSender().getCommandExecuteLocation());
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
                }, SenderType.PLAYER)
                .root()
                .key("testsimpleform")
                .exec((context, player) -> {
                    Forms.simple()
                            .title("Test Simple Form")
                            .content("test content")
                            .button("test button 1")
                            .onClick(button -> player.sendText("You clicked button 1"))
                            .divider()
                            .label("test label")
                            .header("test header")
                            .button("test button 2")
                            .onClick(button -> player.sendText("You clicked button 2"))
                            .sendTo(player);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("blockstate")
                .bool("enable")
                .exec((context, player) -> {
                    boolean enable = context.getResult(1);
                    Consumer<BlockBreakEvent> lambda = (event) -> {
                        System.out.println(event.getBlock().getBlockStateTag());
                        var data = event.getBlock().getBlockStateData();
                        System.out.println(data.collisionShape());
                        System.out.println(data.shape());
                        event.setCancelled(true);
                    };
                    if (enable) {
                        Server.getInstance().getEventBus().registerListenerFor(BlockBreakEvent.class, lambda);
                        player.sendText("Break block to see the blockstate");
                    } else {
                        Server.getInstance().getEventBus().unregisterListenerFor(BlockBreakEvent.class, lambda);
                        player.sendText("Disabled");
                    }
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("renderobj")
                .str("filename")
                .floatNum("scale", 1).optional()
                .pos("pos").optional()
                .exec((context, player) -> {
                    String filename = context.getResult(1);
                    String objData;
                    try {
                        objData = Files.readString(Path.of(filename));
                    } catch (IOException e) {
                        context.addError(e.getMessage());
                        return context.fail();
                    }
                    float scale = context.getResult(2);
                    Vector3fc pos = context.getResult(3);
                    if (pos == null) {
                        var playerLocation = player.getLocation();
                        pos = new Vector3f((float) playerLocation.x(), (float) playerLocation.y(), (float) playerLocation.z());
                    }

                    // Parse the obj data
                    var vertices = new ArrayList<Vector3f>();
                    var lines = new ArrayList<List<Integer>>();
                    var faces = new ArrayList<List<Integer>>();

                    var contentLines = objData.split("\n");
                    for (var line : contentLines) {
                        var tokens = line.trim().split("\\s+");
                        if (tokens.length == 0) {
                            continue;
                        }

                        switch (tokens[0]) {
                            case "v" -> vertices.add(new Vector3f(
                                    Float.parseFloat(tokens[1]),
                                    Float.parseFloat(tokens[2]),
                                    Float.parseFloat(tokens[3])
                            ));
                            case "l" -> {
                                List<Integer> lineIndices = new ArrayList<>();
                                for (int i = 1; i < tokens.length; i++) {
                                    lineIndices.add(Integer.parseInt(tokens[i]) - 1);
                                }
                                lines.add(lineIndices);
                            }
                            case "f" -> {
                                List<Integer> faceIndices = new ArrayList<>();
                                for (int i = 1; i < tokens.length; i++) {
                                    // Handle format like "3/285/285" - extract only the vertex index (first number)
                                    String vertexRef = tokens[i];
                                    if (vertexRef.contains("/")) {
                                        vertexRef = vertexRef.split("/")[0];
                                    }
                                    faceIndices.add(Integer.parseInt(vertexRef) - 1);
                                }
                                faces.add(faceIndices);
                            }
                        }
                    }

                    if (scale != 1.0f) {
                        for (var vec : vertices) {
                            vec.mul(scale);
                        }
                    }

                    List<DebugLine> shapes = new ArrayList<>();

                    // Draw lines
                    for (var line : lines) {
                        for (int i = 0; i < line.size() - 1; i++) {
                            var start = vertices.get(line.get(i));
                            var end = vertices.get(line.get(i + 1));
                            if (start != null && end != null) {
                                shapes.add(new DebugLine(pos.add(start, new Vector3f()), null, pos.add(end, new Vector3f())));
                            }
                        }
                    }

                    // Draw faces
                    for (List<Integer> face : faces) {
                        for (int i = 0; i < face.size(); i++) {
                            var start = vertices.get(face.get(i));
                            var end = vertices.get(face.get((i + 1) % face.size()));
                            if (start != null && end != null) {
                                shapes.add(new DebugLine(pos.add(start, new Vector3f()), null, pos.add(end, new Vector3f())));
                            }
                        }
                    }

                    // Add debug shapes to the dimension
                    for (var shape : shapes) {
                        player.getDimension().addDebugShape(shape);
                    }

                    context.addOutput("Done");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("removealldebugshapes")
                .exec((context, player) -> {
                    player.getDimension().removeAllDebugShapes();
                    context.addOutput("Done");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("setmapimage")
                .str("filename")
                .exec((context, player) -> {
                    if (!(player.getItemInHand() instanceof ItemFilledMapStack map)) {
                        context.addError("You must hold a filled map in your hand to set its image.");
                        return context.fail();
                    }

                    String filename = context.getResult(1);
                    Path path = Path.of(filename);
                    if (!Files.exists(path)) {
                        context.addError("File does not exist: " + filename);
                        return context.fail();
                    }

                    try {
                        var image = ImageIO.read(Files.newInputStream(path));
                        if (image == null) {
                            context.addError("Failed to load image from file: " + filename);
                            return context.fail();
                        }

                        map.setImage(image);
                        map.sendToPlayer(player);
                        context.addOutput("Map image set successfully!");
                    } catch (IOException e) {
                        context.addError("Error reading file: " + e.getMessage());
                        return context.fail();
                    }

                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("addcooldown")
                .str("category", null).optional()
                .exec((context, player) -> {
                    String category = context.getResult(1);
                    if (category != null) {
                        player.setCooldown(category, 20);
                    } else {
                        player.setCooldown(player.getItemInHand().getItemType(), 20);
                    }

                    return context.success();
                }, SenderType.PLAYER);
    }
}
