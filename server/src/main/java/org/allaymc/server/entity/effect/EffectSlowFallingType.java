package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectSlowFallingType} represents the built-in slow falling effect
 * with fixed registry metadata. Living-entity fall-damage and entity-physics
 * critical-attack checks consume this effect directly, while this
 * implementation inherits empty lifecycle callbacks. The constructor registers
 * its numeric ID, namespaced identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectSlowFallingType extends AbstractEffectType {
    /**
     * Creates and registers the built-in slow falling effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectSlowFallingType() {
        super(27, new Identifier("minecraft:slow_falling"), new Color(0xf3cfb9));
    }
}
