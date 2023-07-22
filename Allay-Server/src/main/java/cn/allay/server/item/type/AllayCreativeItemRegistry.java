package cn.allay.server.item.type;

import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.CreativeItemRegistry;
import cn.allay.api.item.type.ItemStackInitInfo;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.registry.RegistryLoader;
import cn.allay.api.registry.SimpleMappedRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.SNBTParser;

import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayCreativeItemRegistry extends SimpleMappedRegistry<Integer, ItemStack, Map<Integer, ItemStack>> implements CreativeItemRegistry {

    public AllayCreativeItemRegistry(RegistryLoader<Void, Map<Integer, ItemStack>> loader) {
        super(null, loader);
    }

    public static class Loader implements RegistryLoader<Void, Map<Integer, ItemStack>> {

        protected Supplier<InputStream> inputStreamSupplier;

        public Loader(Supplier<InputStream> inputStreamSupplier) {
            this.inputStreamSupplier = inputStreamSupplier;
        }

        public Loader() {
            this(() -> AllayCreativeItemRegistry.class.getClassLoader().getResourceAsStream("creative_items.snbt"));
        }

        @SneakyThrows
        @Override
        public Map<Integer, ItemStack> load(Void input) {
            log.info("Start loading creative item registry...");
            var map = new Int2ObjectOpenHashMap<ItemStack>();
            NbtMap nbt;
            try (var stream = inputStreamSupplier.get()) {
                nbt = (NbtMap) SNBTParser.parse(new String(Objects.requireNonNull(stream).readAllBytes()));
            }
            nbt.forEach((key, value) -> {
                var index = Integer.parseInt(key);
                var obj = (NbtMap) value;
                var itemType = ItemTypeRegistry.getRegistry().get(new Identifier(obj.getString("name")));
                var count = obj.getInt("count");
                var damage = obj.getInt("damage");
                var blockStateHash = obj.getInt("blockStateHash");
                var blockState = BlockStateHashPalette.getRegistry().get(blockStateHash);
                var tag = obj.getCompound("tag", null);
                var itemStack = itemType.createItemStack(new ItemStackInitInfo.Simple(count, damage, tag, blockState));
                map.put(index, itemStack);
            });
            log.info("Loaded creative item registry successfully");
            return map;
        }
    }
}
