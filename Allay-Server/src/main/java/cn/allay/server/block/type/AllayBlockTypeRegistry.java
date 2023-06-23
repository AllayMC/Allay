package cn.allay.server.block.type;

import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.type.VanillaBlockTypes;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleMappedRegistry;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daoge_cmd <br>
 * @date 2023/3/26 <br>
 * Allay Project <br>
 */
public final class AllayBlockTypeRegistry extends SimpleMappedRegistry<Identifier, BlockType<?>, Map<Identifier, BlockType<?>>> implements BlockTypeRegistry {
    public AllayBlockTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        for (var field : VanillaBlockTypes.class.getDeclaredFields()) {
            var loaded = field.get(null);
        }
    }
}
