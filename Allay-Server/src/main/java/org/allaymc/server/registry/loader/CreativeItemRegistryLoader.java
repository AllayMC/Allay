package org.allaymc.server.registry.loader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.utils.ItemMetaBlockStateBiMap;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.BufferedInputStream;
import java.util.Objects;

/**
 * @author daoge_cmd
 */
@Slf4j
public class CreativeItemRegistryLoader implements RegistryLoader<Void, Int2ObjectMap<ItemStack>> {
    @SneakyThrows
    @Override
    public Int2ObjectMap<ItemStack> load(Void unused) {
        log.info(I18n.get().tr(TrKeys.A_CREATIVEITEM_LOADING));
        // We should initialize this bimap before loading creative items
        // Because loading creative item will use it
        ItemMetaBlockStateBiMap.init();
        var map = new Int2ObjectOpenHashMap<ItemStack>();
        try (var reader = NbtUtils.createGZIPReader(new BufferedInputStream(Utils.getResource("creative_items.nbt")))) {
            var nbt = (NbtMap) reader.readTag();
            var items = nbt.getList("items", NbtType.COMPOUND);
            for (int i = 0; i < items.size(); i++) {
                var item = items.get(i);
                var itemType = Registries.ITEMS.get(new Identifier(item.getString("name")));
                Objects.requireNonNull(itemType, "itemType cannot be null!");

                int meta = item.getShort("damage");
                var tag = item.getCompound("tag", NbtMap.builder().build());
                map.put(i, itemType.createItemStack(
                        ItemStackInitInfo
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
        return map;
    }
}
