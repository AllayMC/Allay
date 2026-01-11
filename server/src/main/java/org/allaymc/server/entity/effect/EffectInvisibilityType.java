package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectInvisibilityType extends AbstractEffectType {
    public EffectInvisibilityType() {
        super(14, new Identifier("minecraft:invisibility"), new Color(0xf6f6f6));
    }

    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        entity.setInvisible(true);
    }

    @Override
    public void onRemove(EntityLiving entity, EffectInstance effectInstance) {
        entity.setInvisible(false);
    }
}
