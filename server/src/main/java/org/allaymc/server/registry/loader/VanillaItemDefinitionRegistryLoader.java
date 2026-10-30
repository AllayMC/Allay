package org.allaymc.server.registry.loader;

import lombok.SneakyThrows;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.item.type.VanillaItemDefinitionData;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

import java.io.BufferedInputStream;
import java.util.EnumMap;
import java.util.Map;

/**
 * {@code VanillaItemDefinitionRegistryLoader} reads bundled item component definitions from
 * compressed NBT and associates them with built-in item IDs. Each value retains the
 * component-based flag, a compound containing the component data, and the protocol item
 * version used when advertising that definition.
 *
 * @author daoge_cmd
 */
public class VanillaItemDefinitionRegistryLoader implements RegistryLoader<Void, Map<ItemId, VanillaItemDefinitionData>> {
    /**
     * Loads GZIP-compressed {@code item_components.nbt} into a mutable enum map. Every resource
     * identifier must resolve to a built-in item ID, and malformed roots, unknown IDs, or
     * unsupported
     * version values abort loading rather than returning partial definitions.
     */
    @SneakyThrows
    @Override
    public Map<ItemId, VanillaItemDefinitionData> load(Void unused) {
        try (var reader = NbtUtils.createGZIPReader(new BufferedInputStream(Utils.getResource("item_components.nbt")))) {
            var map = new EnumMap<ItemId, VanillaItemDefinitionData>(ItemId.class);
            var nbt = (NbtMap) reader.readTag();
            nbt.forEach((itemId, t) -> {
                var tag = (NbtMap) t;
                var data = new VanillaItemDefinitionData(
                        tag.getBoolean("isComponentBased"),
                        NbtMap.builder().putCompound("components", tag.getCompound("components")).build(),
                        ItemVersion.from(tag.getInt("version"))
                );
                map.put(ItemId.fromIdentifier(new Identifier(itemId)), data);
            });
            return map;
        }
    }
}
