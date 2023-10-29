package cn.allay.api.data;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.item.type.ItemType;

import java.util.function.Function;

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

    Function<Integer, BlockState> getMetaToBlockStateMapper(ItemType<?> itemType);

    Function<Integer, Integer> getBlockStateHashToMetaMapper(BlockType<?> blockType);
}
