package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectHealthBoostType extends AbstractEffectType {
    public static final EffectHealthBoostType HEALTH_BOOST_TYPE = new EffectHealthBoostType();

    private EffectHealthBoostType() {
        super(21, new Identifier("minecraft:health_boost"));
    }

    @Override
    public void onAdd(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityAttributeComponent attributeComponent)) return;
        var level = effectInstance.getLevel();
        attributeComponent.setMaxHealth(attributeComponent.getMaxHealth() + (level * 4));
    }

    @Override
    public void onRemove(Entity entity, EffectInstance effectInstance) {
        if (!(entity instanceof EntityAttributeComponent attributeComponent)) return;
        var level = effectInstance.getLevel();
        attributeComponent.setMaxHealth(attributeComponent.getMaxHealth() - (level * 4));
        if (attributeComponent.getHealth() > attributeComponent.getMaxHealth()) {
            attributeComponent.setHealth(attributeComponent.getMaxHealth());
        }
    }
}
