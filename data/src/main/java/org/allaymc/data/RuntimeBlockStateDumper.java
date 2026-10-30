package org.allaymc.data;

import org.allaymc.api.utils.hash.HashUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * {@code RuntimeBlockStateDumper} writes a readable block-state dump containing
 * palette positions and state hashes. The unpacked NBT palette supplies the
 * iteration order, and each entry is printed with its name and state properties
 * before its two numeric identifiers. Runtime IDs start at zero and describe
 * positions in this specific palette rather than a permanent identity across
 * palette revisions.
 *
 * <p>
 * Hash input is a copy of each block compound with its version member removed.
 * Keeping the palette index and the computed hash as separate output fields
 * allows readers to distinguish ordering changes from changes in the hashed
 * compound. The text file is replaced directly after the entire dump is built.
 *
 * @author Cool_Loong
 */
public class RuntimeBlockStateDumper {
    /**
     * Reads the repository's compressed block palette and replaces its text
     * dump with names, properties, hashes, and sequential runtime IDs.
     * {@code args} is ignored, and property text follows the compound's own key
     * iteration order. The dump is assembled in memory before output begins.
     *
     * <p>
     * Hashing excludes version metadata but retains the other compound members.
     * Deleting the previous output precedes creation of its replacement, so an
     * output failure can leave the dump missing or incomplete.
     *
     * @throws IOException if the palette cannot be read or output cannot be
     * written
     */
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("# WARNING! Don't edit this file! It's automatically regenerated!");
        stringBuilder.append('\n');
        try (var reader = NbtUtils.createGZIPReader(new FileInputStream(Path.of("data/resources/unpacked/block_palette.nbt").toFile()))) {
            NbtMap map = (NbtMap) reader.readTag();
            var blocks = map.getList("blocks", NbtType.COMPOUND);
            int runtimeId = 0;
            for (var b : blocks) {
                StringBuilder bString = new StringBuilder(b.getString("name"));
                NbtMap states = b.getCompound("states");
                for (var key : states.keySet()) {
                    bString.append(';').append(key).append('=').append(states.get(key).toString());
                }
                stringBuilder.append(bString).append('\n');
                NbtMapBuilder builder = NbtMapBuilder.from(b);
                // Exclude version metadata from the hash input while retaining
                // the original palette compound for its separate textual state and runtime index.
                builder.remove("version");
                NbtMap build = builder.build();
                int blockHash = HashUtils.fnv1a_32_nbt(build);
                stringBuilder.append("blockHash=").append(blockHash).append('\n');
                stringBuilder.append("runtimeId=").append(runtimeId).append("\n\n");
                runtimeId++;
            }
            Path path = Path.of("data/resources/unpacked/runtime_block_states.txt");
            // CREATE_NEW requires the old dump to be removed first; this direct
            // replacement leaves no backup if writing the newly created file fails.
            Files.deleteIfExists(path);
            Files.writeString(path, stringBuilder, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        }
    }
}
