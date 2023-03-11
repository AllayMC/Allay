package cn.allay.entity.type;

import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.entity.Entity;
import cn.allay.identifier.Identifier;
import cn.allay.math.location.Location;
import lombok.Builder;
import org.cloudburstmc.nbt.NbtMap;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public final class EntityTypeRecord<T extends Entity> implements EntityType<T> {

    private final List<ComponentImpl> components;
    private final Class<T> sourceClass;
    private final Identifier identifier;

    private final Class<T> componentedClass;

    @Builder
    private EntityTypeRecord(List<ComponentImpl> components, Class<T> sourceClass, Identifier identifier) {
        this.components = components;
        this.sourceClass = sourceClass;
        this.identifier = identifier;
        this.componentedClass = buildComponentedClass();
    }

    @Override
    public List<ComponentImpl> getComponents() {
        return components;
    }

    @Override
    public Class<T> getSourceClass() {
        return sourceClass;
    }

    @Override
    public Class<T> getComponentedClass() {
        return componentedClass;
    }

    @Override
    public T createEntity(Location<Double> location, NbtMap nbt) {
        //TODO
        return null;
    }

    @Override
    public Identifier getNamespaceId() {
        return identifier;
    }

    private Class<T> buildComponentedClass() {
        //TODO
        return null;
    }
}
