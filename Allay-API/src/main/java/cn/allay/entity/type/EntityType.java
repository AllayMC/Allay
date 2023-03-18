package cn.allay.entity.type;

import cn.allay.entity.Entity;
import cn.allay.entity.definition.EntityDefinition;
import cn.allay.identifier.Identified;
import cn.allay.math.location.Location;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 *
 * Describes a specific type of entity
 */
public interface EntityType<T extends Entity> extends Identified {

    EntityDefinition<T> getDefinition();

    Class<T> getCompiledEntityClass();

    T createEntity(Location<Double> location, NbtMap nbt);
}
