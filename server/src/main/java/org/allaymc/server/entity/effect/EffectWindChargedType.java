package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.explosion.WindExplosion;

import java.awt.*;

/**
 * {@code EffectWindChargedType} represents wind-charged metadata and releases a
 * wind burst when its entity dies. The callback uses the player wind-charge
 * configuration, assigns the dying entity as the excluded source, and disables
 * shooter self-knockback before applying the burst at its location.
 *
 * @author IWareQ
 */
public class EffectWindChargedType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful wind charged effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectWindChargedType() {
        super(32, new Identifier("minecraft:wind_charged"), new Color(0xbdc9ff), true);
    }

    /**
     * Applies a player-style wind burst at the dying entity's location. The
     * entity becomes the excluded source and self-knockback is disabled; the
     * burst handles nearby motion, particles, and sound without a damage pass.
     */
    @Override
    public void onEntityDies(EntityLiving entity, EffectInstance effectInstance) {
        var explosion = WindExplosion.playerWindCharge();
        explosion.setSource(entity);
        explosion.setApplySelfKnockback(false);
        explosion.explode(entity.getDimension(), entity.getLocation());
    }
}
