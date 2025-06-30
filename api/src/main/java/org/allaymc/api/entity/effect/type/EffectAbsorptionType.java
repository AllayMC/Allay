package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectAbsorptionType extends AbstractEffectType {
    EffectAbsorptionType() {
        super(22, new Identifier("minecraft:absorption"));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        if (entity instanceof EntityAttributeComponent attributeComponent && attributeComponent.supportAttribute(AttributeType.ABSORPTION)) {
            attributeComponent.setAbsorption(effectInstance.getLevel() * 4);
        }
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        if (entity instanceof EntityAttributeComponent attributeComponent && attributeComponent.supportAttribute(AttributeType.ABSORPTION)) {
            attributeComponent.setAbsorption(0);
        }
    }
}
