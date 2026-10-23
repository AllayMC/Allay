package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectRaidOmenType} represents the built-in raid omen effect with
 * fixed registry metadata. This implementation inherits empty lifecycle
 * callbacks and supplies metadata without raid-trigger logic; its harmful
 * classification remains the base default. The constructor registers its
 * numeric ID, namespaced identifier, and particle color.
 *
 * @author IWareQ
 */
public class EffectRaidOmenType extends AbstractEffectType {
    /**
     * Creates and registers the built-in raid omen effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectRaidOmenType() {
        super(36, new Identifier("minecraft:raid_omen"), new Color(0xde4058));
    }
}
