package cn.allay.server.item.type;

import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.init.ItemStackInitInfo;
import cn.allay.api.item.init.SimpleItemStackInitInfo;
import cn.allay.api.item.type.CreativeItemRegistry;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.registry.RegistryLoader;
import cn.allay.api.registry.SimpleMappedRegistry;
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
                int count = obj.getInt("count");
                int damage = obj.getInt("damage");
                int blockStateHash = obj.getInt("blockStateHash");
                var blockState = BlockStateHashPalette.getRegistry().get(blockStateHash);
                var tag = obj.getCompound("tag", NbtMap.builder().build());
                assert itemType != null;
                var itemStack = itemType.createItemStack(
                        SimpleItemStackInitInfo
                                .builder()
                                .count(count)
                                .damage(damage)
                                .nbt(tag)
                                .blockState(blockState)
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
