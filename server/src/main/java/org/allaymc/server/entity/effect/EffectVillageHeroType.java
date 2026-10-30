package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectVillageHeroType} represents the built-in village hero effect
 * with fixed registry metadata. This implementation inherits empty lifecycle
 * callbacks and defines metadata without additional server-side tick or damage
 * processing. The constructor registers its numeric ID, namespaced identifier,
 * and particle color.
 *
 * @author daoge_cmd
 */
public class EffectVillageHeroType extends AbstractEffectType {
    /**
     * Creates and registers the built-in village hero effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectVillageHeroType() {
        super(29, new Identifier("minecraft:village_hero"), new Color(0x44ff44));
    }
}
