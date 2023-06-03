package cn.allay.api.block.type;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: Cool_Loong <br>
 * Date: 2023/5/29 <br>
 * Allay Project
 */
public interface BlockStateHashPalette extends MappedRegistry<Integer, BlockState, Map<Integer, BlockState>> {
    ApiInstanceHolder<BlockStateHashPalette> REGISTRY = ApiInstanceHolder.of();

    static BlockStateHashPalette getRegistry() {
        return REGISTRY.get();
    }
}
