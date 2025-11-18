package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.world.StructureFile;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.joml.Vector3d;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * TODO: i18n
 *
 * @author daoge_cmd
 */
public class StructureCommand extends VanillaCommand {

    private static final Path STRUCTURE_DIR = Path.of("structures");
    private static final String STRUCTURE_FILE_EXT = ".mcstructure";

    public StructureCommand() {
        super("structure", "Manage structure");
        aliases.add("struct");
    }

    private static void checkStructureDirectory() {
        if (!Files.exists(STRUCTURE_DIR)) {
            try {
                Files.createDirectory(STRUCTURE_DIR);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create structure directory", e);
            }
        }
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("pick")
                .str("filename")
                .pos("start")
                .pos("end")
                .exec((context, player) -> {
                    String fileName = context.getResult(1);
                    Vector3d start = ((Vector3d) context.getResult(2)).floor();
                    Vector3d end = ((Vector3d) context.getResult(3)).floor();

                    var sizeX = (int) (end.x - start.x + 1);
                    var sizeY = (int) (end.y - start.y + 1);
                    var sizeZ = (int) (end.z - start.z + 1);
                    if (sizeX <= 0 || sizeY <= 0 || sizeZ <= 0) {
                        context.addError("Invalid size");
                        return context.fail();
                    }

                    var structure = StructureFile.pick(
                            player.getDimension(),
                            (int) start.x, (int) start.y, (int) start.z,
                            sizeX, sizeY, sizeZ,
                            true
                    );
                    var filePath = STRUCTURE_DIR.resolve(fileName + STRUCTURE_FILE_EXT);

                    checkStructureDirectory();
                    try {
                        Files.deleteIfExists(filePath);
                    } catch (IOException e) {
                        context.addError(e.toString());
                        return context.fail();
                    }

                    try (var writer = NbtUtils.createWriterLE(Files.newOutputStream(filePath))) {
                        writer.writeTag(structure.toNBT());
                    } catch (IOException e) {
                        context.addError(e.toString());
                        return context.fail();
                    }

                    context.addOutput("Structure file is saved to " + filePath);
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("place")
                .str("filename")
                .pos("pos")
                .exec((context, player) -> {
                    String fileName = context.getResult(1);
                    Vector3d pos = ((Vector3d) context.getResult(2)).floor();
                    NbtMap nbt;
                    var filePath = STRUCTURE_DIR.resolve(fileName + STRUCTURE_FILE_EXT);

                    checkStructureDirectory();
                    if (!Files.exists(filePath)) {
                        context.addError("Structure file " + filePath + " not found");
                        return context.fail();
                    }
                    try (var reader = NbtUtils.createReaderLE(Files.newInputStream(filePath))) {
                        nbt = (NbtMap) reader.readTag();
                    } catch (IOException e) {
                        context.addError(e.toString());
                        return context.fail();
                    }

                    var structure = StructureFile.formNBT(nbt);
                    structure.place(player.getDimension(), (int) pos.x, (int) pos.y, (int) pos.z);

                    context.addOutput("Structure " + filePath + " is placed");
                    return context.success();
                }, SenderType.PLAYER)
                .root()
                .key("list")
                .exec(context -> {
                    checkStructureDirectory();
                    context.addOutput("Available structure files:");
                    try (var paths = Files.list(STRUCTURE_DIR)) {
                        paths.filter(path -> path.toString().endsWith(STRUCTURE_FILE_EXT))
                                .forEach(path -> context.addOutput("- " + path.getFileName().toString()));
                    } catch (IOException e) {
                        context.addError(e.toString());
                        return context.fail();
                    }
                    return context.success();
                });
    }
}
