package cn.allay.api.block.palette;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.registry.MappedRegistry;

import java.util.Map;

/**
 * TODO: Replaced by blockStateHash, the block runtime id is not used anymore.
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
@Deprecated
public interface VanillaBlockStatePalette extends MappedRegistry<BlockState, Integer, Map<BlockState, Integer>> {
    ApiInstanceHolder<VanillaBlockStatePalette> REGISTRY = ApiInstanceHolder.of();

    static VanillaBlockStatePalette getRegistry() {
        return REGISTRY.get();
    }
}
