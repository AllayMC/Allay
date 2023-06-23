package cn.allay.server.block.type;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.type.BlockTypes;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleMappedRegistry;
import cn.allay.server.utils.PackageClassLoader;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/26 <br>
 * Allay Project <br>
 */
public final class AllayBlockTypeRegistry extends SimpleMappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> implements BlockTypeRegistry {
    public AllayBlockTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        for (var field : BlockTypes.class.getDeclaredFields()) {
            var loaded = field.get(null);
        }
    }
}
