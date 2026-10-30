package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.StructureFile;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.joml.Vector3d;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * {@code StructureCommand} saves, places, and lists little-endian structure
 * files for command users. Player-only pick and place branches interpret
 * coordinates in the player's current dimension, while listing is available
 * without a player. Filenames are resolved directly against
 * {@link #STRUCTURE_DIR}; this command does not normalize or confine supplied
 * paths.
 *
 * <p>
 * Picking captures an inclusive region, deletes an existing destination, and
 * then opens a new writer. A failed write can therefore leave the old file lost
 * and the replacement incomplete. Reading finishes before placement begins, so
 * file-read failures do not place blocks. Directory creation failures propagate
 * as runtime exceptions, while branch-local I/O failures become command errors.
 *
 * TODO: Replace literal feedback with translation keys before localizing this command.
 *
 * @author daoge_cmd
 */
public class StructureCommand extends Command {

    // STRUCTURE_DIR and STRUCTURE_FILE_EXT define the path and suffix shared by
    // saving, placement, and listing. The directory is relative to the server's
    // working directory, and supplied filenames are resolved without validation.
    /**
     * Relative structure storage directory.
     */
    private static final Path STRUCTURE_DIR = Path.of("structures");
    /**
     * Structure filename extension suffix.
     */
    private static final String STRUCTURE_FILE_EXT = ".mcstructure";

    /**
     * Creates the authorized {@code /structure} command and its {@code /struct} alias.
     */
    public StructureCommand() {
        super("structure", "Manage structure", Permissions.COMMAND_STRUCTURE);
        aliases.add("struct");
    }

    /**
     * Creates the structure directory when its path does not exist.
     * An existing path is left untouched, including a regular file; directory
     * creation failures are wrapped in a {@link RuntimeException} for command
     * dispatch to handle.
     */
    private static void checkStructureDirectory() {
        if (!Files.exists(STRUCTURE_DIR)) {
            try {
                Files.createDirectory(STRUCTURE_DIR);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create structure directory", e);
            }
        }
    }

    /**
     * Installs player-only structure capture and placement plus directory listing.
     * Capture requires ordered corners after flooring and includes entities in
     * the picked region, except players. Filenames receive the fixed structure
     * suffix and are resolved without path validation.
     *
     * <p>
     * Capture replaces a file by deleting it before writing, with no restoration
     * after a failed write. Placement reads the full NBT tag before decoding
     * and placing it. Branch-local I/O errors produce failure feedback, while
     * directory setup and malformed structure contents can propagate exceptions.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .key("pick")
                .str("filename")
                .pos("start")
                .pos("end")
                .exec((context, player) -> {
                    String fileName = context.getResult(1);
                    // Flooring selects containing blocks before inclusive region dimensions are computed.
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
                    // Replacement removes the old bytes before a new writer
                    // opens. Later write failure cannot restore that earlier file.
                    try {
                        Files.deleteIfExists(filePath);
                    } catch (IOException e) {
                        // A failed deletion prevents the new writer from
                        // opening. Report failure without attempting a
                        // replacement or file restoration.
                        context.addError(e.toString());
                        return context.fail();
                    }

                    try (var writer = NbtUtils.createWriterLE(Files.newOutputStream(filePath))) {
                        writer.writeTag(structure.toNBT());
                    } catch (IOException e) {
                        // A write failure occurs after destination deletion.
                        // Report failure with no restoration of the old or
                        // partially written bytes.
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
                    // Flooring anchors placement at the block containing the supplied position.
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
                        // File input must finish before placement begins.
                        // A read failure reports an error without applying
                        // any structure blocks.
                        context.addError(e.toString());
                        return context.fail();
                    }

                    var structure = StructureFile.fromNBT(nbt);
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
                        // Directory opening fails after the heading was queued.
                        // Preserve that heading and report failure instead of
                        // producing a successful empty listing.
                        context.addError(e.toString());
                        return context.fail();
                    }
                    return context.success();
                });
    }
}
