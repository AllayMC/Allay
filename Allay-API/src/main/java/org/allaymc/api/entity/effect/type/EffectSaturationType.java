package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.player.EntityPlayerHungerComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectSaturationType extends AbstractEffectType {
    public static final EffectSaturationType SATURATION_TYPE = new EffectSaturationType();

    private EffectSaturationType() {
        super(23, new Identifier("minecraft:saturation"));
    }

    @Override
    public void onTick(Entity entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayerHungerComponent hungerComponent) {
            var level = effectInstance.getLevel();
            hungerComponent.saturate(level, 2 * level);
        }
    }
}
