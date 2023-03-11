package cn.allay.entity.type;

import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.entity.Entity;
import cn.allay.identifier.Identified;
import cn.allay.math.location.Location;
import org.cloudburstmc.nbt.NbtMap;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 *
 * Describes a specific type of entity
 */
public interface EntityType<T extends Entity> extends Identified {
    List<ComponentImpl> getComponents();

    Class<T> getSourceClass();

    Class<T> getComponentedClass();

    T createEntity(Location<Double> location, NbtMap nbt);
}
