package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectWaterBreathingType} represents the built-in water breathing
 * effect with fixed registry metadata. Living-entity breathing checks consume
 * this effect directly to permit underwater breathing, while this
 * implementation inherits empty lifecycle callbacks. The constructor registers
 * its numeric ID, namespaced identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectWaterBreathingType extends AbstractEffectType {
    /**
     * Creates and registers the built-in water breathing effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectWaterBreathingType() {
        super(13, new Identifier("minecraft:water_breathing"), new Color(0x98dac0));
    }
}
