package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectHungerType extends AbstractEffectType {
    public EffectHungerType() {
        super(17, new Identifier("minecraft:hunger"), new Color(0x587653), true);
    }

    @Override
    public void onTick(Entity entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayerAttributeComponent attributeComponent) {
            attributeComponent.exhaust(0.005f * effectInstance.getLevel());
        }
    }
}
