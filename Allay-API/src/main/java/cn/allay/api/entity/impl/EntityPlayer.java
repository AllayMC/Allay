package cn.allay.api.entity.impl;

import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.impl.attribute.EntityAttributeComponent;
import cn.allay.api.entity.component.impl.attribute.EntityAttributeComponentImpl;

import static cn.allay.api.entity.component.impl.attribute.EntityAttributeComponentImpl.basicAttributes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPlayer extends
        Entity,
        EntityAttributeComponent {
    @AutoRegister
    ComponentProvider<EntityAttributeComponentImpl> ATTRIBUTE_COMPONENT =
            ComponentProvider.of(
                    () -> new EntityAttributeComponentImpl(basicAttributes()),
                    EntityAttributeComponentImpl.class
            );
}
