package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectConduitPowerType} represents the built-in conduit power effect
 * with fixed registry metadata. Living-entity breathing and block-breaking
 * speed checks consume this effect directly, while this implementation inherits
 * empty lifecycle callbacks. The constructor registers its numeric ID,
 * namespaced identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectConduitPowerType extends AbstractEffectType {
    /**
     * Creates and registers the built-in conduit power effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectConduitPowerType() {
        super(26, new Identifier("minecraft:conduit_power"), new Color(0x1dc2d1));
    }
}
