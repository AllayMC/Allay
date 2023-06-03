package cn.allay.server.block.type;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockStateRegistry;
import cn.allay.api.registry.SimpleMappedRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Cool_Loong <br>
 * Date: 2023/5/29 <br>
 * Allay Project
 */
@Slf4j
public class AllayBlockStateRegistry extends SimpleMappedRegistry<Integer, BlockState, Map<Integer, BlockState>> implements BlockStateRegistry {
    public AllayBlockStateRegistry() {
        super(null, input -> new ConcurrentHashMap<>());
    }
}