package cn.allay.block.type;

import cn.allay.identifier.Identifier;
import cn.allay.registry.SimpleMappedRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
public final class BlockTypeRegistry extends SimpleMappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>>{

    private static final BlockTypeRegistry INSTANCE = new BlockTypeRegistry();

    private BlockTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    public static BlockTypeRegistry getInstance() {
        return INSTANCE;
    }
}
