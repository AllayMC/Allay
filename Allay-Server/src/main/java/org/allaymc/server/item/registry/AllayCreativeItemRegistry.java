package org.allaymc.server.item.registry;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.registry.CreativeItemRegistry;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayCreativeItemRegistry extends SimpleMappedRegistry<Integer, ItemStack, Map<Integer, ItemStack>> implements CreativeItemRegistry {

    protected ItemData[] cachedNetworkItemDataArray;

    public AllayCreativeItemRegistry(RegistryLoader<Void, Map<Integer, ItemStack>> loader) {
        super(null, loader);
        rebuildNetworkItemDataArray();
    }

    @Override
    public ItemData[] getNetworkItemDataArray() {
        return cachedNetworkItemDataArray;
    }

    @Override
    public void rebuildNetworkItemDataArray() {
        cachedNetworkItemDataArray = mappings.values().stream().map(ItemStack::toNetworkItemData).toArray(ItemData[]::new);
    }

    public static class Loader implements RegistryLoader<Void, Map<Integer, ItemStack>> {

        protected Supplier<InputStream> inputStreamSupplier;

        public Loader(Supplier<InputStream> inputStreamSupplier) {
            this.inputStreamSupplier = inputStreamSupplier;
        }

        public Loader() {
            this(() -> AllayCreativeItemRegistry.class.getClassLoader().getResourceAsStream("creative_items.nbt"));
        }

        @SneakyThrows
        @Override
        public Map<Integer, ItemStack> load(Void input) {
            log.info("Start loading creative item registry...");
            var map = new TreeMap<Integer, ItemStack>();
            NbtMap nbt;
            try (var reader = NbtUtils.createGZIPReader(inputStreamSupplier.get())) {
                nbt = (NbtMap) reader.readTag();
            }
            nbt.forEach((key, value) -> {
                var index = Integer.parseInt(key);
                var obj = (NbtMap) value;
                var itemType = ItemTypeRegistry.getRegistry().get(new Identifier(obj.getString("name")));
                int meta = obj.getInt("damage");
                var tag = obj.getCompound("tag", NbtMap.builder().build());
                assert itemType != null;
                var itemStack = itemType.createItemStack(
                        SimpleItemStackInitInfo
                                .builder()
                                .count(1)
                                .meta(meta)
                                .extraTag(tag)
                                .stackNetworkId(index + 1)
                                .build()
                );
                map.put(index, itemStack);
            });
            log.info("Loaded creative item registry successfully");
            return map;
        }
    }
}
