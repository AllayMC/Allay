package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectNightVisionType} represents the built-in night vision effect
 * with fixed registry metadata. This implementation inherits empty lifecycle
 * callbacks and defines metadata without additional server-side tick or damage
 * processing. The constructor registers its numeric ID, namespaced identifier,
 * and particle color.
 *
 * @author daoge_cmd
 */
public class EffectNightVisionType extends AbstractEffectType {
    /**
     * Creates and registers the built-in night vision effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectNightVisionType() {
        super(16, new Identifier("minecraft:night_vision"), new Color(0xc2ff66));
    }
}
