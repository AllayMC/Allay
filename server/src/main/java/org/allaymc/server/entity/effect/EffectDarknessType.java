package org.allaymc.server.entity.effect;

import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectDarknessType} represents the built-in harmful darkness effect
 * with fixed registry metadata. This implementation inherits empty lifecycle
 * callbacks, so its class defines metadata without additional server-side tick
 * behavior. The constructor registers its numeric ID, namespaced identifier,
 * and particle color.
 *
 * @author daoge_cmd
 */
public class EffectDarknessType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful darkness effect metadata.
     * Registration requires an initialized mutable effect registry and can
     * replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectDarknessType() {
        super(30, new Identifier("minecraft:darkness"), new Color(0x292721), true);
    }
}
