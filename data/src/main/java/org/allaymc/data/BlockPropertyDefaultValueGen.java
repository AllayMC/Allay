package org.allaymc.data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.Allay;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * NOTICE: Unused currently
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockPropertyDefaultValueGen {

    @SneakyThrows
    public static void main(String[] args) {
        Allay.initI18n();
        Allay.initAllay();

        var defaultBlockStates = new HashMap<String, Long>();
        var gson = new GsonBuilder().create();
        try (var reader = Files.newBufferedReader(Path.of("data/resources/block_types.json"))) {
            JsonParser.parseReader(reader).getAsJsonObject().asMap().forEach((name, obj) -> {
                var data = gson.fromJson(obj.toString(), BlockTypeData.class);
                defaultBlockStates.put(name, data.defaultBlockStateHash);
            });
        }

        var blockPropertyDefaultValues = new HashMap<BlockPropertyType<?>, BlockPropertyType.BlockPropertyValue<?, ?, ?>>();
        defaultBlockStates.forEach((name, hash) -> {
            var blockState = Registries.BLOCK_STATE_PALETTE.get(hash.intValue());
            blockState.getPropertyValues().forEach((type, value) -> {
                var oldValue = blockPropertyDefaultValues.get(type);
                if (oldValue != null && value != oldValue) {
                    log.warn("Duplicate block property type {}. Old value: {}, new value: {}. Block: {}", type.getName(), oldValue.getValue(), value.getValue(), name);
                    return;
                }
                blockPropertyDefaultValues.put(type, value);
            });
        });

        var serializedValues = new HashMap<String, Object>();
        blockPropertyDefaultValues.forEach((type, value) -> {
            serializedValues.put(type.getName(), value.getValue());
        });
        Files.writeString(Path.of("data/resources/unpacked/block_property_default_values.json"), gson.toJson(serializedValues));
    }

    public static class BlockTypeData {
        public long defaultBlockStateHash;
    }
}