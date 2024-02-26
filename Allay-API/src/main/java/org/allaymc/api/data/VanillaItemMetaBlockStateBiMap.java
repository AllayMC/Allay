package org.allaymc.api.data;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.type.ItemType;

import java.util.function.Function;

/**
 * Allay Project 2023/10/28
 * Stores a bi-mapping between item meta values and block state
 *
 * @author daoge_cmd
 */
public interface VanillaItemMetaBlockStateBiMap {
    ApiInstanceHolder<VanillaItemMetaBlockStateBiMap> REGISTRY = ApiInstanceHolder.create();

    static VanillaItemMetaBlockStateBiMap getRegistry() {
        return REGISTRY.get();
    }

    Function<Integer, BlockState> getMetaToBlockStateMapper(ItemType<?> itemType);

    Function<Integer, Integer> getBlockStateHashToMetaMapper(BlockType<?> blockType);
}
