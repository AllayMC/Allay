package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectHealthBoostType extends AbstractEffectType {
    public EffectHealthBoostType() {
        super(21, new Identifier("minecraft:health_boost"));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityAttributeComponent component) || !component.supportHealth()) return;
        var level = effectInstance.getLevel();
        component.setMaxHealth(component.getMaxHealth() + (level * 4));
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityAttributeComponent component) || !component.supportHealth()) return;
        var level = effectInstance.getLevel();
        component.setMaxHealth(component.getMaxHealth() - (level * 4));
        if (component.getHealth() > component.getMaxHealth()) {
            component.setHealth(component.getMaxHealth());
        }
    }
}
