package cn.allay.server.data;

import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.data.VanillaItemMetaBlockStateBiMap;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.registry.ItemTypeRegistry;
import cn.allay.api.item.type.ItemType;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Allay Project 2023/10/28
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayVanillaItemMetaBlockStateBiMap implements VanillaItemMetaBlockStateBiMap {

    private static final Map<ItemType<?>, Map<Integer, BlockState>> ITEM_TYPE_TO_META_MAP = new HashMap<>();
    private static final Map<BlockType<?>, Map<Integer, Integer>> BLOCK_STATE_HASH_TO_META_MAP = new HashMap<>();

    public void init() {
        // Load item_meta_block_state_bimap.nbt
        try (var reader = NbtUtils.createGZIPReader(AllayVanillaItemMetaBlockStateBiMap.class.getClassLoader().getResourceAsStream("item_meta_block_state_bimap.nbt"))) {
            var nbt = (NbtMap) reader.readTag();
            nbt.forEach((itemIdentifier, metaToHash) -> {
                ItemType<?> itemType = ItemTypeRegistry.getRegistry().get(new Identifier(itemIdentifier));
                Objects.requireNonNull(itemType, "Cannot find item type by identifier: " + itemIdentifier);
                var metaToHashMap = (NbtMap) metaToHash;
                metaToHashMap.forEach((meta, blockStateHash) -> {
                    var metaInt = Integer.parseInt(meta);
                    var blockStateHashInt = (Integer) blockStateHash;
                    var blockState = BlockStateHashPalette.getRegistry().get(blockStateHashInt);
                    Objects.requireNonNull(blockState, "Cannot find block state by hash: " + blockStateHashInt);
                    ITEM_TYPE_TO_META_MAP.computeIfAbsent(itemType, k -> new Int2ObjectOpenHashMap<>()).put(metaInt, blockState);
                    BLOCK_STATE_HASH_TO_META_MAP.computeIfAbsent(blockState.getBlockType(), k -> new Int2ObjectOpenHashMap<>()).put(blockStateHashInt, metaInt);
                });
            });
        } catch (IOException e) {
            log.error("Cannot load item_meta_block_state_bimap.nbt!", e);
        }
    }

    @Override
    public Map<Integer, BlockState> getMetaToBlockStateMap(ItemType<?> itemType) {
        return ITEM_TYPE_TO_META_MAP.getOrDefault(itemType, Map.of(0, itemType.getBlockType().getDefaultState()));
    }

    @Override
    public Map<Integer, Integer> getBlockStateHashToMetaMap(BlockType<?> blockType) {
        return BLOCK_STATE_HASH_TO_META_MAP.getOrDefault(blockType, Map.of(blockType.getDefaultState().blockStateHash(), 0));
    }
}
