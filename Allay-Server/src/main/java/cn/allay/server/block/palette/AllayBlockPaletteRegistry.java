package cn.allay.server.block.palette;

import cn.allay.api.block.palette.BlockPaletteData;
import cn.allay.api.block.palette.BlockPaletteRegistry;
import cn.allay.api.registry.SimpleMappedRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Cool_Loong <br>
 * Date: 2023/5/29 <br>
 * Allay Project
 */
@Slf4j
public class AllayBlockPaletteRegistry extends SimpleMappedRegistry<Integer, BlockPaletteData, Map<Integer, BlockPaletteData>> implements BlockPaletteRegistry {
    public AllayBlockPaletteRegistry() {
        super(null, input -> new HashMap<>());
    }
}