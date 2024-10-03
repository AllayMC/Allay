package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 */
public class EffectRegenerationType extends AbstractEffectType {
    public EffectRegenerationType() {
        super(10, new Identifier("minecraft:regeneration"));
    }

    @Override
    public void onTick(Entity entity, EffectInstance effectInstance) {
        if (
                !(entity instanceof EntityAttributeComponent attributeComponent) ||
                entity.isUndead()
        ) return;
        var level = effectInstance.getLevel() - 1;

        var ticksPerHealth = 50 >> level;
        if (ticksPerHealth < 1) ticksPerHealth = 1;

        if (effectInstance.getDuration() % ticksPerHealth != 0) return;

        var healthPerSecond = 20f / ticksPerHealth;
        attributeComponent.setHealth(attributeComponent.getHealth() + healthPerSecond);
    }
}
