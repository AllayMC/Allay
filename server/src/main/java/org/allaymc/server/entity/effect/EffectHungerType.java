package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
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
    public void onTick(EntityLiving entity, EffectInstance effectInstance) {
        if (entity instanceof EntityPlayer player) {
            player.exhaust(0.005f * effectInstance.getLevel());
        }
    }
}
