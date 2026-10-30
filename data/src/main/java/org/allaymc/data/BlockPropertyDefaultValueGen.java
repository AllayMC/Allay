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
 * {@code BlockPropertyDefaultValueGen} derives property defaults from the
 * registered states selected by block-type metadata. This manual data tool
 * initializes Allay before resolving stored state hashes through its palette,
 * then emits one value for each property type. Several block types can
 * contribute the same property, so the output cannot simply contain every
 * observed block default independently.
 *
 * <p>
 * The first visited value is retained when a later block supplies a different
 * property-value object. Conflicts are logged and skipped without replacing
 * that value. {@code HashMap} traversal does not guarantee input-file order, so
 * callers cannot rely on the first value in that file winning a conflict.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockPropertyDefaultValueGen {

    /**
     * Initializes runtime registries and exports defaults inferred from block
     * metadata in the repository's resource directory. {@code args} is ignored,
     * and input hashes are narrowed to the palette's integer key representation
     * before lookup. Missing palette entries fail rather than being skipped.
     *
     * <p>
     * Conflicting property-value objects leave the first observed default in
     * place and produce a warning. The resulting property-name map replaces the
     * output JSON directly; failures are propagated without restoring the
     * previous file or undoing runtime initialization.
     */
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
            // Palette keys use the hash's low 32 bits.
            var blockState = Registries.BLOCK_STATE_PALETTE.get(hash.intValue());
            blockState.getPropertyValues().forEach((type, value) -> {
                var oldValue = blockPropertyDefaultValues.get(type);
                if (oldValue != null && value != oldValue) {
                    // Keep the first visited default when another block supplies
                    // a different value object; later observations cannot overwrite that choice.
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
        Utils.writeFileWithCRLF(Path.of("data/resources/unpacked/block_property_default_values.json"), gson.toJson(serializedValues));
    }

    /**
     * {@code BlockTypeData} represents the default state hash attached to one
     * block type.
     */
    public static class BlockTypeData {
        /**
         * {@code defaultBlockStateHash} holds the input hash before
         * {@code main} narrows it to the runtime palette's integer key. An
         * omitted JSON member leaves this value at {@code 0}.
         */
        public long defaultBlockStateHash;
    }
}
