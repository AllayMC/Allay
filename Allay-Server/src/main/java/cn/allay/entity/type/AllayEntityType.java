package cn.allay.entity.type;

import cn.allay.entity.Entity;
import cn.allay.entity.definition.EntityDefinition;
import cn.allay.identifier.Identifier;
import cn.allay.math.location.Location;
import lombok.Getter;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/18 <br>
 * Allay Project <br>
 */
@Getter
public class AllayEntityType<T extends Entity> implements EntityType<T>{

    protected EntityDefinition<T> definition;
    protected Class<T> compiledEntityClass;

    public AllayEntityType(EntityDefinition<T> definition, Class<T> compiledEntityClass) {
        this.definition = definition;
        this.compiledEntityClass = compiledEntityClass;
    }

    @Override
    public T createEntity(Location<Double> location, NbtMap nbt) {
        //TODO
        return null;
    }

    @Override
    public Identifier getNamespaceId() {
        return definition.getNamespaceId();
    }
}
