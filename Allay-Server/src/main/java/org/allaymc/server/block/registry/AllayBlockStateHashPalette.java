package org.allaymc.server.block.registry;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.registry.SimpleMappedRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Allay Project 2023/5/29
 *
 * @author Cool_Loong
 */
@Slf4j
public class AllayBlockStateHashPalette extends SimpleMappedRegistry<Integer, BlockState, Map<Integer, BlockState>> implements BlockStateHashPalette {
    public AllayBlockStateHashPalette() {
        super(null, input -> new ConcurrentHashMap<>());
    }
}