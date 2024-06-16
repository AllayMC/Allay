package org.allaymc.server.item.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import org.allaymc.api.utils.JSONUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collections;
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
            this(() -> AllayCreativeItemRegistry.class.getClassLoader().getResourceAsStream("creative_items.json"));
        }

        @SneakyThrows
        @Override
        public Map<Integer, ItemStack> load(Void input) {
            log.info(I18n.get().tr(TrKeys.A_CREATIVEITEM_LOADING));
            var map = new TreeMap<Integer, ItemStack>();
            JsonObject from = JSONUtils.from(inputStreamSupplier.get(), JsonObject.class);
            JsonArray items = from.getAsJsonArray("items");
            for (int i = 0; i < items.size(); i++) {
                JsonObject obj = items.get(i).getAsJsonObject();
                var itemType = ItemTypeRegistry.getRegistry().get(new Identifier(obj.get("id").getAsString()));

                JsonElement jsonElement = obj.get("damage");
                int meta = jsonElement == null ? 0 : jsonElement.getAsInt();

                NbtMap tag = obj.get("nbt_b64") == null ? NbtMap.builder().build() : (NbtMap) NbtUtils.createReaderLE(new ByteArrayInputStream(Base64.getDecoder().decode(obj.get("nbt_b64").getAsString()))).readTag();
                assert itemType != null;
                var itemStack = itemType.createItemStack(
                        SimpleItemStackInitInfo
                                .builder()
                                .count(1)
                                .meta(meta)
                                .extraTag(tag)
                                .stackNetworkId(i + 1)
                                .build()
                );
                map.put(i, itemStack);
            }
            log.info(I18n.get().tr(TrKeys.A_CREATIVEITEM_LOADED));
            return Collections.synchronizedMap(map);
        }
    }
}
