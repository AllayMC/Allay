package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author daoge_cmd
 */
public class EffectSaturationType extends AbstractEffectType {
    public EffectSaturationType() {
        super(23, new Identifier("minecraft:saturation"), new Color(0xf82423));
    }

    @Override
    public void onTick(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player) {
            var level = effectInstance.getLevel();
            player.saturate(level, 2 * level);
        }
    }
}
