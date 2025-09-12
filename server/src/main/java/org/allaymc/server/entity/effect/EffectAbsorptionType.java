package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectAbsorptionType extends AbstractEffectType {
    public EffectAbsorptionType() {
        super(22, new Identifier("minecraft:absorption"), new Color(0x2552a5));
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
