package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.explosion.WindExplosion;

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
        var explosion = WindExplosion.playerWindCharge();
        explosion.setSource(entity);
        explosion.setApplySelfKnockback(false);
        explosion.explode(entity.getDimension(), entity.getLocation());
    }
}
