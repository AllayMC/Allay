package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectHasteType} represents the built-in haste effect with fixed
 * registry metadata. Block-breaking calculations read this effect level
 * directly to adjust mining speed, while this implementation inherits empty
 * lifecycle callbacks. The constructor registers its numeric ID, namespaced
 * identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectHasteType extends AbstractEffectType {
    /**
     * Creates and registers the built-in haste effect metadata. Registration
     * requires an initialized mutable effect registry and can replace existing
     * associations under this type's fixed numeric or namespaced key.
     */
    public EffectHasteType() {
        super(3, new Identifier("minecraft:haste"), new Color(0xd9c043));
    }
}
