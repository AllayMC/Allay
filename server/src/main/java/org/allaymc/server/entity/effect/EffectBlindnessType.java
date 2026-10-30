package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectBlindnessType} represents the built-in harmful blindness effect
 * with fixed registry metadata. Entity physics checks this effect when deciding
 * whether a critical attack is permitted; this implementation inherits empty
 * lifecycle callbacks. The constructor registers its numeric ID, namespaced
 * identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectBlindnessType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful blindness effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectBlindnessType() {
        super(15, new Identifier("minecraft:blindness"), new Color(0x1f1f23), true);
    }
}
