package cn.allay.api.block.palette;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * @author Cool_Loong <br>
 * @date 2023/5/29 <br>
 * Allay Project
 */
public interface BlockStateHashPalette extends MappedRegistry<Integer, BlockState, Map<Integer, BlockState>> {
    ApiInstanceHolder<BlockStateHashPalette> REGISTRY = ApiInstanceHolder.of();

    static BlockStateHashPalette getRegistry() {
        return REGISTRY.get();
    }
}
