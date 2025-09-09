package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectHealthBoostType extends AbstractEffectType {
    public EffectHealthBoostType() {
        super(21, new Identifier("minecraft:health_boost"), new Color(0xf87d23));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityAttributeComponent component) || !component.supportAttribute(AttributeType.HEALTH))
            return;
        var level = effectInstance.getLevel();
        component.setMaxHealth(component.getMaxHealth() + (level * 4));
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityAttributeComponent component) || !component.supportAttribute(AttributeType.HEALTH))
            return;
        var level = effectInstance.getLevel();
        component.setMaxHealth(component.getMaxHealth() - (level * 4));
        if (component.getHealth() > component.getMaxHealth()) {
            component.setHealth(component.getMaxHealth());
        }
    }
}
