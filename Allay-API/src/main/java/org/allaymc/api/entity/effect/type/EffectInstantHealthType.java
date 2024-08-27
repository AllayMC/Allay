package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantHealthType extends AbstractEffectType {
    public EffectInstantHealthType() {
        super(6, new Identifier("minecraft:instant_health"));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityAttributeComponent attributeComponent)) return;
        var level = effectInstance.getLevel();
        attributeComponent.setHealth(attributeComponent.getHealth() + (float) (2 * Math.pow(2, level)));
        // TODO: damage undead mobs
    }
}
