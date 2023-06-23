package cn.allay.server.entity.type;

import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.entity.type.VanillaEntityTypes;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.registry.SimpleMappedRegistry;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/26 <br>
 * Allay Project <br>
 */
public class AllayEntityTypeRegistry extends SimpleMappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> implements EntityTypeRegistry {
    public AllayEntityTypeRegistry() {
        super(null, input -> new HashMap<>());
    }

    @SneakyThrows
    public void init() {
        for (var field : VanillaEntityTypes.class.getDeclaredFields()) {
            var loaded = field.get(null);
        }
    }
}
