package cn.allay.entity.type;

import cn.allay.component.injector.AllayComponentInjector;
import cn.allay.entity.Entity;
import cn.allay.entity.definition.EntityDefinition;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/18 <br>
 * Allay Project <br>
 */
public class AllayEntityTypeBuilder implements EntityTypeBuilder{

    @Override
    public <T extends Entity> EntityType<T> build(EntityDefinition<T> definition) {
        var injector = new AllayComponentInjector<T>();
        var clazz = injector.parentClass(definition.getTargetInterface())
                .withComponent(definition.getComponents())
                .inject();
        return new AllayEntityType<>(definition, clazz);
    }
}
