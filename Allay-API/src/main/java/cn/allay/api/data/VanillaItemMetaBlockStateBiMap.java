package cn.allay.api.data;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.item.type.ItemType;

import java.util.Map;

/**
 * Allay Project 2023/10/28
 * Stores a bi-mapping between item meta values and block state
 *
 * @author daoge_cmd
 */
public interface VanillaItemMetaBlockStateBiMap {
    ApiInstanceHolder<VanillaItemMetaBlockStateBiMap> REGISTRY = ApiInstanceHolder.of();

    static VanillaItemMetaBlockStateBiMap getRegistry() {
        return REGISTRY.get();
    }

    Map<Integer, BlockState> getMetaToBlockStateMap(ItemType<?> itemType);

    Map<Integer, Integer> getBlockStateHashToMetaMap(BlockType<?> blockType);
}
