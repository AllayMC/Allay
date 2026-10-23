package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectMiningFatigueType} represents the built-in harmful mining
 * fatigue effect with fixed registry metadata. Block-breaking calculations read
 * this effect level directly to adjust mining speed, while this implementation
 * inherits empty lifecycle callbacks. The constructor registers its numeric ID,
 * namespaced identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectMiningFatigueType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful mining fatigue effect
     * metadata. Registration requires an initialized mutable effect registry
     * and can replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectMiningFatigueType() {
        super(4, new Identifier("minecraft:mining_fatigue"), new Color(0x4a4217), true);
    }
}
