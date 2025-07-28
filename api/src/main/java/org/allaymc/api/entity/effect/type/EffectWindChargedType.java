package org.allaymc.api.entity.effect.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.effect.AbstractEffectType;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.utils.Identifier;

import java.awt.*;

/**
 * @author IWareQ
 */
public class EffectWindChargedType extends AbstractEffectType {
    EffectWindChargedType() {
        super(32, new Identifier("minecraft:wind_charged"), new Color(0xbdc9ff), true);
    }

    @Override
    public void onEntityDies(Entity entity, EffectInstance effectInstance) {
        //TODO: Create a wind charge explosion at the entity's position
    }
}
