package org.allaymc.server.item.registry;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.registry.CreativeItemRegistry;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.utils.ResourceUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
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

    @AllArgsConstructor
    public static class Loader implements RegistryLoader<Void, Map<Integer, ItemStack>> {
        protected Supplier<InputStream> inputStreamSupplier;

        public Loader() {
            this(() -> ResourceUtils.getResource("creative_items.nbt"));
        }

        @SneakyThrows
        @Override
        public Map<Integer, ItemStack> load(Void input) {
            log.info(I18n.get().tr(TrKeys.A_CREATIVEITEM_LOADING));
            var map = new TreeMap<Integer, ItemStack>();
            try (var reader = NbtUtils.createGZIPReader(inputStreamSupplier.get())) {
                var nbt = (NbtMap) reader.readTag();
                var items = nbt.getList("items", NbtType.COMPOUND);
                for (int i = 0; i < items.size(); i++) {
                    var item = items.get(i);
                    var itemType = ItemTypeRegistry.getRegistry().get(new Identifier(item.getString("name")));
                    Objects.requireNonNull(itemType, "itemType cannot be null!");

                    int meta = item.getShort("damage");
                    var tag = item.getCompound("tag", NbtMap.builder().build());
                    map.put(i, itemType.createItemStack(
                            SimpleItemStackInitInfo
                                    .builder()
                                    .count(1)
                                    .meta(meta)
                                    .extraTag(tag)
                                    .stackNetworkId(i + 1)
                                    .build()
                    ));
                }
            }
            log.info(I18n.get().tr(TrKeys.A_CREATIVEITEM_LOADED));
            return Collections.synchronizedMap(map);
        }
    }
}
