package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectSaturationType extends AbstractEffectType {
    EffectSaturationType() {
        super(23, new Identifier("minecraft:saturation"), new Color(0xf82423));
    }

    @Override
    public void onTick(Entity entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayerAttributeComponent attributeComponent) {
            var level = effectInstance.getLevel();
            attributeComponent.saturate(level, 2 * level);
        }
    }
}
