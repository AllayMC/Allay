package cn.allay.server.block.registry;

import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.registry.SimpleMappedRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Allay Project 2023/5/29
 *
 * @author Cool_Loong
 */
@Slf4j
public class AllayBlockStateHashPalette extends SimpleMappedRegistry<Integer, BlockState, Map<Integer, BlockState>> implements BlockStateHashPalette {
    public AllayBlockStateHashPalette() {
        super(null, input -> new Int2ObjectOpenHashMap<>());
    }
}