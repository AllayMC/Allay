package org.allaymc.data;

import org.allaymc.api.common.utils.HashUtils;
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

public class RuntimeBlockStateDumper {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("# WARNING! Don't edit this file! It's automatically regenerated!");
        stringBuilder.append('\n');
        try (var reader = NbtUtils.createGZIPReader(new FileInputStream(Path.of("Allay-Data/resources/unpacked/block_palette.nbt").toFile()))) {
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
                builder.remove("version");
                NbtMap build = builder.build();
                int blockHash = HashUtils.fnv1a_32_nbt(build);
                stringBuilder.append("blockHash=").append(blockHash).append('\n');
                stringBuilder.append("runtimeId=").append(runtimeId).append("\n\n");
                runtimeId++;
            }
            Path path = Path.of("Allay-Data/resources/unpacked/runtime_block_states.txt");
            Files.deleteIfExists(path);
            Files.writeString(path, stringBuilder, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        }
    }
}
