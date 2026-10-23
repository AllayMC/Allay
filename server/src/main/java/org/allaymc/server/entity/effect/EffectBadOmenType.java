package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectBadOmenType} represents the built-in harmful bad omen effect
 * with fixed registry metadata. This implementation inherits empty lifecycle
 * callbacks and supplies metadata without raid-trigger logic. The constructor
 * registers its numeric ID, namespaced identifier, and particle color.
 *
 * @author daoge_cmd
 */
public class EffectBadOmenType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful bad omen effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectBadOmenType() {
        super(28, new Identifier("minecraft:bad_omen"), new Color(0x0b6138), true);
    }
}
