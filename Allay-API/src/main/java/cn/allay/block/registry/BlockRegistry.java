package cn.allay.block.registry;

import cn.allay.block.type.BlockType;
import cn.allay.identifier.Identifier;
import cn.allay.registry.SimpleMappedRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
public final class BlockRegistry extends SimpleMappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>>{

    private static final BlockRegistry INSTANCE = new BlockRegistry();

    private BlockRegistry() {
        super(null, input -> new HashMap<>());
    }

    public static BlockRegistry getInstance() {
        return INSTANCE;
    }
}
