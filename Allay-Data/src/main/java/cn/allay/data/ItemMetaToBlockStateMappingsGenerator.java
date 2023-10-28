package cn.allay.data;

import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/10/28
 *
 * @author daoge_cmd
 */
public class ItemMetaToBlockStateMappingsGenerator {
    @SneakyThrows
    public static void main(String[] args) {
        //                                                itemId       meta blockStateHash
        var itemMetaToBlockStateHashFullMap = new HashMap<String, Map<String, Object>>();
        NbtMap creativeItemsNBT;
        try (var reader = NbtUtils.createGZIPReader(new FileInputStream(Path.of("Allay-Data/resources/creative_items.nbt").toFile()))) {
            creativeItemsNBT = (NbtMap) reader.readTag();
        }
        // Build full mappings
        creativeItemsNBT.forEach((key, value) -> {
            var obj = (NbtMap) value;
            if (!obj.containsKey("blockStateHash")) {
                return;
            }
            var blockStateHash = obj.getInt("blockStateHash");
            var itemIdentifier = obj.getString("name");
            var meta = obj.getInt("damage");
            if (!itemMetaToBlockStateHashFullMap.containsKey(itemIdentifier)) {
                itemMetaToBlockStateHashFullMap.put(itemIdentifier, new HashMap<>());
            }
            itemMetaToBlockStateHashFullMap
                    .get(itemIdentifier)
                    .put(String.valueOf(meta), blockStateHash);
        });
        // Remove useless mappings (which only has one meta) and save it to nbt
        var resultNBT = NbtMap.builder();
        itemMetaToBlockStateHashFullMap.forEach((itemIdentifier, metaToBlockStateHashMap) -> {
            if (metaToBlockStateHashMap.size() > 1) {
                resultNBT.putCompound(itemIdentifier, NbtMap.fromMap(metaToBlockStateHashMap));
            }
        });
        var path = Path.of("Allay-Data/resources/item_meta_block_state_bimap.nbt");
        Files.deleteIfExists(path);
        Files.createFile(path);
        var tagStream = new FileOutputStream(path.toFile());
        var writer = NbtUtils.createGZIPWriter(tagStream);
        writer.writeTag(resultNBT.build());
        writer.close();
        tagStream.close();
    }
}
