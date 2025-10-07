package org.allaymc.server.registry.loader;

import lombok.SneakyThrows;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.data.ItemId;
import org.allaymc.server.item.type.ItemDefinition;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;

import java.io.BufferedInputStream;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
public class ItemDefinitionRegistryLoader implements RegistryLoader<Void, Map<ItemId, ItemDefinition>> {
    @SneakyThrows
    @Override
    public Map<ItemId, ItemDefinition> load(Void unused) {
        try (var reader = NbtUtils.createGZIPReader(new BufferedInputStream(Utils.getResource("item_components.nbt")))) {
            var map = new EnumMap<ItemId, ItemDefinition>(ItemId.class);
            var nbt = (NbtMap) reader.readTag();
            nbt.forEach((itemId, t) -> {
                var tag = (NbtMap) t;
                var data = new ItemDefinition(
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
