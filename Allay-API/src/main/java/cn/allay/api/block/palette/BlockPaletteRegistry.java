package cn.allay.api.block.palette;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Author: Cool_Loong <br>
 * Date: 2023/5/29 <br>
 * Allay Project
 */
public interface BlockPaletteRegistry extends MappedRegistry<Integer, BlockPaletteDataEntry, Map<Integer, BlockPaletteDataEntry>> {
    ApiInstanceHolder<BlockPaletteRegistry> REGISTRY = ApiInstanceHolder.of();

    static BlockPaletteRegistry getRegistry() {
        return REGISTRY.get();
    }
}
