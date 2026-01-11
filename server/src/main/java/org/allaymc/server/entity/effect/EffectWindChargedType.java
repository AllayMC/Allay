package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * @author IWareQ
 */
public class EffectWindChargedType extends AbstractEffectType {
    public EffectWindChargedType() {
        super(32, new Identifier("minecraft:wind_charged"), new Color(0xbdc9ff), true);
    }

    @Override
    public void onEntityDies(EntityLiving entity, EffectInstance effectInstance) {
        //TODO: Create a wind charge explosion at the entity's position
    }
}
