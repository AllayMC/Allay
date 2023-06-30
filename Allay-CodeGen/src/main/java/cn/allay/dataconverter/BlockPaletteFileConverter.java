package cn.allay.dataconverter;

import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * Allay Project 2023/4/28
 *
 * @author daoge_cmd
 */
public final class BlockPaletteFileConverter {

    static final Path PMMP_BLOCK_PALETTE_FILE_PATH = Path.of("Data/canonical_block_states.nbt");
    static final Path OUT_FILE_PATH = Path.of("Data/block_palette_converted.nbt");

    @SneakyThrows
    public static void main(String[] args) {
        var reader = NbtUtils.createNetworkReader(Files.newInputStream(PMMP_BLOCK_PALETTE_FILE_PATH));
        List<NbtMap> blocks = new LinkedList<>();
        NbtMap tag;
        while (true) {
            try {
                if ((tag = (NbtMap) reader.readTag()) == null) break;
            } catch (IOException e) {
                break;
            }
            blocks.add(tag);
        }
        reader.close();
        var global = NbtMap.builder().putList("blocks", NbtType.COMPOUND, blocks).build();
        var out = new NBTOutputStream(new DataOutputStream(new GZIPOutputStream(Files.newOutputStream(OUT_FILE_PATH))));
        out.writeTag(global);
        out.close();
    }
}
