package cn.allay.entity.type;

import cn.allay.entity.Entity;
import cn.allay.entity.definition.EntityDefinition;
import cn.allay.identifier.Identified;
import cn.allay.identifier.Identifier;
import cn.allay.math.location.Loc;
import cn.allay.registry.MappedRegistry;
import cn.allay.registry.SimpleMappedRegistry;
import org.cloudburstmc.nbt.NbtMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 * <p>
 * Describes a specific type of entity
 */
public interface EntityType<T extends Entity> extends Identified {

    MappedRegistry<Identifier, EntityType<?>, Map<Identifier, EntityType<?>>> REGISTRY = SimpleMappedRegistry.create(i -> new HashMap<>());

    EntityDefinition<T> getDefinition();

    Class<T> getCompiledEntityClass();

    T createEntity(Loc<Double> location, NbtMap nbt);
}
