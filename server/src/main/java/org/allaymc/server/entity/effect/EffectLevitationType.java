package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectLevitationType} represents the built-in harmful levitation
 * effect with fixed registry metadata. Living-entity fall-damage eligibility
 * checks consume this effect directly, while this implementation inherits empty
 * lifecycle callbacks. The constructor registers its numeric ID, namespaced
 * identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectLevitationType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful levitation effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectLevitationType() {
        super(24, new Identifier("minecraft:levitation"), new Color(0xceffff), true);
    }
}
