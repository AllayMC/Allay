package org.allaymc.server.command.defaults;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.ddui.DDUI;
import org.allaymc.api.ddui.element.CloseButton;
import org.allaymc.api.ddui.element.DropdownItem;
import org.allaymc.api.ddui.element.Header;
import org.allaymc.api.ddui.element.Label;
import org.allaymc.api.debugshape.DebugLine;
import org.allaymc.api.debugshape.DebugText;
import org.allaymc.api.dialog.Dialog;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.block.BlockBreakEvent;
import org.allaymc.api.form.Forms;
import org.allaymc.api.item.data.ItemLockMode;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.LangCode;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.HudElement;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.explosion.Explosion;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.utils.JSONUtils;
import org.cloudburstmc.nbt.NbtMap;
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
import java.util.function.LongFunction;

/**
 * @author daoge_cmd
 */
public class GameTestCommand extends Command {

    public GameTestCommand() {
        super("gametest", TrKeys.MC_GAMETEST_DESCRIPTION, "allay.command.gametest");
        aliases.add("gt");
    }

    @Override
    public boolean isDebugCommand() {
        return true;
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

                    player.getContainer(ContainerTypes.INVENTORY).setItemInHand(blockState.toItemStack());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("rfinv")
                .exec((context, p) -> {
                    var player = p.getController();
                    player.viewContainerContents(p.getContainer(ContainerTypes.INVENTORY));
                    player.viewContainerContents(p.getContainer(ContainerTypes.ARMOR));
                    player.viewContainerContents(p.getContainer(ContainerTypes.OFFHAND));
                    context.addOutput("Inventory is refreshed!");
                    return context.success();
                }, SenderType.ACTUAL_PLAYER)
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
                        player.sendMessage(I18n.get().tr(langCode, key, args));
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
                    player.sendMessage(player.getDimension().getBlockState(player.getLocation().sub(0, 1, 0, new Vector3d()), 0).toString());
                    player.sendMessage(player.getDimension().getBlockState(player.getLocation().sub(0, 1, 0, new Vector3d()), 1).toString());
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
                    player.sendMessage("Lore is set");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("getheight")
                .exec((context, player) -> {
                    var floorLoc = player.getLocation().floor(new Vector3d());
                    player.sendMessage("Height is " + player.getDimension().getHeight((int) floorLoc.x, (int) floorLoc.z));
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
                    player.sendMessage("InternalLight: " + lightEngine.getInternalLight(x, y, z));
                    player.sendMessage("BlockLight: " + lightEngine.getBlockLight(x, y, z));
                    player.sendMessage("SkyLight: " + lightEngine.getSkyLight(x, y, z));
                    player.sendMessage("InternalSkyLight: " + lightEngine.getInternalSkyLight(x, y, z));
                    player.sendMessage("QueuedUpdateCount: " + lightEngine.getQueuedUpdateCount());
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
                    player.sendMessage("Item is locked in " + context.getResult(1) + " mode!");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("phantom")
                .bool("value")
                .exec((context, player) -> {
                    boolean value = context.getResult(1);
                    player.setPhantom(value);
                    player.sendMessage("Phantom set to " + player.isPhantom());
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("testsimpleform")
                .exec((context, player) -> {
                    Forms.simple()
                            .title("Test Simple Form")
                            .content("test content")
                            .button("test button 1")
                            .onClick(button -> player.sendMessage("You clicked button 1"))
                            .divider()
                            .label("test label")
                            .header("test header")
                            .button("test button 2")
                            .onClick(button -> player.sendMessage("You clicked button 2"))
                            .sendTo(player.getController());
                    return context.success();
                }, SenderType.ACTUAL_PLAYER)
                .root()
                .key("testcustomfrom")
                .exec((context, player) -> {
                    Forms.custom()
                            .title("Test Custom Form")
                            .input("test input", "type sth here", "", "this is a tooltip")
                            .sendTo(player.getController());
                    return context.success();
                }, SenderType.ACTUAL_PLAYER)
                .root()
                .key("testddui")
                .exec((context, player) -> {
                    var controller = player.getController();
                    var title = DDUI.observable("DDUI Coverage Lab");
                    var summary = DDUI.observable("");
                    var profileHeader = DDUI.observable("Profile");
                    var name = DDUI.observable("Allay", true);
                    var notes = DDUI.observable("Type here to test client-to-server sync.", true);
                    var showAdvanced = DDUI.observable(true, true);
                    var lockEditing = DDUI.observable(false, true);
                    var expertMode = DDUI.observable(false, true);
                    var verboseOutput = DDUI.observable(true, true);
                    var difficulty = DDUI.observable(2L, true);
                    var difficultyMin = DDUI.observable(1L);
                    var difficultyMax = DDUI.observable(5L);
                    var theme = DDUI.observable(1L, true);
                    var nameDescription = DDUI.observable("Typing here updates the title, header and summary.");
                    var difficultyDescription = DDUI.observable("Current slider range: 1 - 5");
                    var snapshotLabel = DDUI.observable("Print Snapshot");
                    var snapshotTooltip = DDUI.observable("Dump all DDUI values to chat");
                    var editDisabled = DDUI.observable(false);
                    var advancedVisible = DDUI.observable(true);
                    var closeLabel = DDUI.observable("Close Coverage Form");
                    var imperativeLabelVisible = DDUI.observable(false);
                    var verboseDisabled = DDUI.observable(false);

                    LongFunction<String> themeName = value -> switch ((int) value) {
                        case 0 -> "Classic";
                        case 1 -> "Builder";
                        case 2 -> "Speedrunner";
                        default -> "Unknown(" + value + ")";
                    };
                    Runnable refreshSummary = () -> {
                        var currentName = name.get().isBlank() ? "<empty>" : name.get();
                        profileHeader.set("Profile: " + currentName);
                        title.set("DDUI Coverage Lab [" + themeName.apply(theme.get()) + "]");
                        nameDescription.set("Name length: " + name.get().length() + "/24");
                        difficultyDescription.set("Current slider range: " + difficultyMin.get() + " - " + difficultyMax.get());
                        snapshotLabel.set(lockEditing.get() ? "Snapshot Disabled" : "Print Snapshot");
                        snapshotTooltip.set("Theme=" + themeName.apply(theme.get()) + ", Difficulty=" + difficulty.get());
                        closeLabel.set(showAdvanced.get() ? "Close Coverage Form" : "Close Compact Form");
                        summary.set("name=" + currentName
                                + ", advanced=" + showAdvanced.get()
                                + ", locked=" + lockEditing.get()
                                + ", expert=" + expertMode.get()
                                + ", difficulty=" + difficulty.get()
                                + ", theme=" + themeName.apply(theme.get())
                                + ", verbose=" + verboseOutput.get());
                    };

                    var form = DDUI.customForm()
                            .title(title)
                            .onClose((session, reason) -> controller.sendMessage("DDUI custom form closed: " + reason));
                    form.header("DDUI Coverage Demo");
                    form.label("This example intentionally covers every current custom-form element type and most runtime update paths.");
                    form.element(new Label(summary));
                    form.divider();
                    form.element(new Header(profileHeader));

                    var nameField = form.textField("Name", name)
                            .description(nameDescription)
                            .disabled(editDisabled);
                    var notesField = form.textField("Notes", notes)
                            .description("Observable-backed free-form text field.")
                            .disabled(editDisabled);
                    var showAdvancedToggle = form.toggle("Show advanced section", showAdvanced)
                            .description("Toggles visibility for the advanced controls below.");
                    var lockEditingToggle = form.toggle("Disable editable controls", lockEditing)
                            .description("Disables text fields, slider, dropdown and snapshot button.");
                    var expertToggle = form.toggle("Expert mode", expertMode)
                            .description("Expands the slider range from 1-5 to 2-10.");

                    form.spacer();
                    form.element(new Header("Advanced Controls").visible(advancedVisible));
                    form.element(new Label("Use the buttons below to test observable updates, session.get/set, visibility and disabled state.")
                            .visible(advancedVisible));

                    var imperativeLabel = new Label("This label is toggled only through session.setVisible(...).")
                            .visible(false);
                    form.element(imperativeLabel);

                    var difficultySlider = form.slider("Difficulty", difficulty, difficultyMin, difficultyMax)
                            .description(difficultyDescription)
                            .disabled(editDisabled)
                            .visible(advancedVisible);
                    var themeDropdown = form.dropdown("Theme Preset", theme, List.of(
                                    new DropdownItem("Classic", "Baseline preset", 0L),
                                    new DropdownItem("Builder", "Good for structure editing", 1L),
                                    new DropdownItem("Speedrunner", "High intensity preset", 2L)
                            ))
                            .description("Dropdown labels, descriptions and selected value writeback.")
                            .disabled(editDisabled)
                            .visible(advancedVisible);
                    var verboseToggle = form.toggle("Verbose button output", verboseOutput)
                            .description("Buttons print extra state while this toggle is enabled.")
                            .visible(advancedVisible);
                    var scratchField = form.textField("Scratch Pad")
                            .description("Not observable-backed; used to test session.set(String).")
                            .visible(advancedVisible);
                    var scratchSlider = form.slider("Scratch Value", 0L, 10L, 3L)
                            .description("Not observable-backed; used to test session.set(Long).")
                            .visible(advancedVisible);

                    form.spacer();
                    var snapshotButton = form.button(snapshotLabel)
                            .tooltip(snapshotTooltip)
                            .disabled(editDisabled);
                    var presetButton = form.button("Apply Server Preset")
                            .tooltip("Mutate multiple observables from the server side.");
                    var sessionOpsButton = form.button("Run session.* updates")
                            .tooltip("Calls session.set, session.get, session.setVisible and session.setDisabled.")
                            .visible(advancedVisible);
                    var closeButton = new CloseButton(closeLabel);
                    form.element(closeButton);

                    nameField.onChange((session, value) -> {
                        if (value.length() > 24) {
                            name.set(value.substring(0, 24));
                        }
                        refreshSummary.run();
                    });
                    notesField.onChange((session, value) -> refreshSummary.run());
                    showAdvancedToggle.onChange((session, value) -> {
                        advancedVisible.set(value);
                        refreshSummary.run();
                    });
                    lockEditingToggle.onChange((session, value) -> {
                        editDisabled.set(value);
                        refreshSummary.run();
                    });
                    expertToggle.onChange((session, value) -> {
                        difficultyMin.set(value ? 2L : 1L);
                        difficultyMax.set(value ? 10L : 5L);
                        if (difficulty.get() < difficultyMin.get()) {
                            difficulty.set(difficultyMin.get());
                        }
                        if (difficulty.get() > difficultyMax.get()) {
                            difficulty.set(difficultyMax.get());
                        }
                        refreshSummary.run();
                    });
                    difficultySlider.onChange((session, value) -> refreshSummary.run());
                    themeDropdown.onChange((session, value) -> refreshSummary.run());
                    verboseToggle.onChange((session, value) -> refreshSummary.run());

                    snapshotButton.onClick(session -> {
                        controller.sendMessage("DDUI snapshot => name=" + session.get(nameField)
                                + ", notes=" + session.get(notesField)
                                + ", difficulty=" + session.get(difficultySlider)
                                + ", theme=" + themeName.apply(session.get(themeDropdown))
                                + ", verbose=" + session.get(verboseToggle)
                                + ", scratchText=" + session.get(scratchField)
                                + ", scratchValue=" + session.get(scratchSlider));
                        if (Boolean.TRUE.equals(session.get(verboseToggle))) {
                            controller.sendMessage("Live summary => " + summary.get());
                        }
                    });
                    presetButton.onClick(session -> {
                        name.set("Preset-" + Server.getInstance().getTick());
                        notes.set("Updated from the server preset button.");
                        showAdvanced.set(true);
                        advancedVisible.set(true);
                        lockEditing.set(false);
                        editDisabled.set(false);
                        expertMode.set(true);
                        difficultyMin.set(2L);
                        difficultyMax.set(10L);
                        difficulty.set(7L);
                        theme.set(2L);
                        verboseOutput.set(true);
                        imperativeLabelVisible.set(false);
                        session.setVisible(imperativeLabel, false);
                        verboseDisabled.set(false);
                        session.setDisabled(verboseToggle, false);
                        session.set(scratchField, "Preset applied at tick " + Server.getInstance().getTick());
                        session.set(scratchSlider, 8L);
                        refreshSummary.run();
                        controller.sendMessage("Applied DDUI server preset.");
                    });
                    sessionOpsButton.onClick(session -> {
                        imperativeLabelVisible.set(!imperativeLabelVisible.get());
                        session.setVisible(imperativeLabel, imperativeLabelVisible.get());

                        verboseDisabled.set(!verboseDisabled.get());
                        session.setDisabled(verboseToggle, verboseDisabled.get());

                        var nextScratchValue = (session.get(scratchSlider) + 1L) % 11L;
                        session.set(scratchField, "session.set at tick " + Server.getInstance().getTick());
                        session.set(scratchSlider, nextScratchValue);

                        controller.sendMessage("Session API test => visible=" + imperativeLabelVisible.get()
                                + ", verboseDisabled=" + verboseDisabled.get()
                                + ", scratchText=" + session.get(scratchField)
                                + ", scratchValue=" + session.get(scratchSlider));
                    });
                    closeButton.onClick(session -> controller.sendMessage("DDUI close button callback fired."));

                    refreshSummary.run();

                    try {
                        form.sendTo(controller);
                    } catch (UnsupportedOperationException e) {
                        context.addError(e.getMessage());
                        return context.fail();
                    }
                    return context.success();
                }, SenderType.ACTUAL_PLAYER)
                .root()
                .key("testmessagebox")
                .exec((context, player) -> {
                    var controller = player.getController();
                    try {
                        DDUI.messageBox()
                                .title("Test Message Box")
                                .body("Choose one option or close the box.")
                                .button1("Button 1", session -> controller.sendMessage("You clicked button 1"))
                                .button2("Button 2", session -> controller.sendMessage("You clicked button 2"))
                                .onResponse((session, result) -> controller.sendMessage("Message box result: " + result))
                                .onClose((session, reason) -> controller.sendMessage("Message box closed: " + reason))
                                .sendTo(controller);
                    } catch (UnsupportedOperationException e) {
                        context.addError(e.getMessage());
                        return context.fail();
                    }
                    return context.success();
                }, SenderType.ACTUAL_PLAYER)
                .root()
                .key("testdialog")
                .exec((context, player) -> {
                    var controller = player.getController();
                    var dialog = Dialog.create();
                    dialog.title("Test Dialog")
                            .body("I'm allay-chan QAQ\nCounter=1")
                            .button("baka")
                            .onClick(button -> controller.sendMessage("You are baka!"))
                            .button("super baka")
                            .onClick(button -> controller.sendMessage("You are super baka!"))
                            .button("Add One!", button -> {
                                var oldBody = dialog.getBody();
                                var index = oldBody.lastIndexOf("=");
                                var counter = Integer.parseInt(oldBody.substring(index + 1)) + 1;
                                dialog.body("I'm allay-chan QAQ\nCounter=" + counter);
                            })
                            .nextDialog(dialog)
                            .onClose(() -> controller.sendMessage("You closed the dialog!"));
                    dialog.sendTo(controller, player);
                    return context.success();
                }, SenderType.ACTUAL_PLAYER)
                .root()
                .key("blockstate")
                .bool("enable")
                .exec((context, player) -> {
                    boolean enable = context.getResult(1);
                    Consumer<BlockBreakEvent> lambda = (event) -> {
                        System.out.println(event.getBlock().getBlockStateNBT());
                        var data = event.getBlock().getBlockStateData();
                        System.out.println(data.collisionShape());
                        System.out.println(data.shape());
                        event.setCancelled(true);
                    };
                    if (enable) {
                        Server.getInstance().getEventBus().registerListenerFor(BlockBreakEvent.class, lambda);
                        player.sendMessage("Break block to see the blockstate");
                    } else {
                        Server.getInstance().getEventBus().unregisterListenerFor(BlockBreakEvent.class, lambda);
                        player.sendMessage("Disabled");
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
                        map.sendToPlayer(player.getController());
                        context.addOutput("Map image set successfully!");
                    } catch (IOException e) {
                        context.addError("Error reading file: " + e.getMessage());
                        return context.fail();
                    }

                    return context.success();
                }, SenderType.ACTUAL_PLAYER)
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
                }, SenderType.PLAYER)
                .root()
                .key("clonehanditem")
                .exec((context, player) -> {
                    player.getItemInHand().setCount(player.getItemInHand().getItemType().getItemData().maxStackSize());
                    player.notifyItemInHandChange();
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("openfakechest")
                .exec((context, player) -> {
                    var controller = player.getController();
                    if (controller == null) {
                        return context.fail();
                    }

                    var fakeChest = FakeContainerFactory.getFactory().createFakeChestContainer();
                    fakeChest.setCustomName("Fake Chest Container");
                    fakeChest.setItemStackWithListener(0, ItemTypes.DIAMOND.createItemStack(), () -> player.sendMessage("You clicked the diamond item"));
                    fakeChest.addPlayer(controller);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("openfakedoublechest")
                .exec((context, player) -> {
                    var controller = player.getController();
                    if (controller == null) {
                        return context.fail();
                    }

                    var fakeDoubleChest = FakeContainerFactory.getFactory().createFakeDoubleChestContainer();
                    fakeDoubleChest.setCustomName("Fake Double Chest Container");
                    fakeDoubleChest.setItemStackWithListener(0, ItemTypes.DIAMOND.createItemStack(), () -> player.sendMessage("You clicked the diamond item"));
                    fakeDoubleChest.addPlayer(controller);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("invisiblenode")
                .permission("allay.command.gametest.invisiblenode")
                .exec(context -> {
                    context.getSender().sendMessage("QAQ");
                    return context.success();
                })
                .root()
                .key("spawnplayer")
                .exec((context, sender) -> {
                    var player = EntityTypes.PLAYER.createEntity(
                            EntityInitInfo.builder()
                                    .loc(sender.getLocation())
                                    .build()
                    );
                    player.setNameTag(sender.getNameTag());
                    player.setNameTagAlwaysShow(true);
                    player.setDisplayName(sender.getDisplayName());
                    player.setSkin(sender.getSkin());
                    sender.getDimension().getEntityManager().addEntity(player);

                    context.addOutput(TrKeys.MC_COMMANDS_SUMMON_SUCCESS);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("sethudelement")
                .enumClass("element", HudElement.class)
                .bool("visible", true)
                .exec((context, sender) -> {
                    HudElement element = context.getResult(1);
                    boolean visible = context.getResult(2);
                    sender.getController().setHudElementVisibility(element, visible);
                    return context.success();
                }, SenderType.ACTUAL_PLAYER)
                .root()
                .key("setimmobile")
                .bool("value")
                .exec((context, sender) -> {
                    boolean value = context.getResult(1);
                    sender.setImmobile(value);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("setscale")
                .target("targets")
                .doubleNum("scale")
                .exec((context, sender) -> {
                    List<Entity> entities = context.getResult(1);
                    double scale = context.getResult(2);
                    for (var entity : entities) {
                        entity.setScale(scale);
                    }
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("attachdebugshape")
                .target("target")
                .optional()
                .exec((context, sender) -> {
                    List<Entity> entities = context.getResult(1);
                    var entity = entities != null && !entities.isEmpty() ? entities.getFirst() : sender;
                    var debugText = new DebugText(new Vector3f(0f, 3f, 0f), null, "test");
                    entity.attachDebugShape(debugText);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("spawnsuperzombie")
                .exec((context, sender) -> {
                    var zombie = EntityTypes.ZOMBIE.createEntity(EntityInitInfo.builder()
                            .loc(sender.getLocation())
                            .build());
                    var armor = zombie.getContainer(ContainerTypes.ARMOR);
                    var hand = zombie.getContainer(ContainerTypes.ENTITY_HAND);
                    var offhand = zombie.getContainer(ContainerTypes.OFFHAND);
                    armor.setHelmet(ItemTypes.DIAMOND_HELMET.createItemStack());
                    armor.setChestplate(ItemTypes.DIAMOND_CHESTPLATE.createItemStack());
                    armor.setLeggings(ItemTypes.DIAMOND_LEGGINGS.createItemStack());
                    armor.setBoots(ItemTypes.DIAMOND_BOOTS.createItemStack());
                    hand.setItemInHand(ItemTypes.DIAMOND_SWORD.createItemStack());
                    offhand.setOffhand(ItemTypes.SHIELD.createItemStack());
                    sender.getDimension().getEntityManager().addEntity(zombie);
                    context.addOutput("Done.");
                    return context.success();
                }, SenderType.PLAYER);

    }
}
